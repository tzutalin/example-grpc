import com.tzutalin.grpc.blobkeeper.BlobKeeperGrpc;
import com.tzutalin.grpc.blobkeeper.PutRequest;
import com.tzutalin.grpc.blobkeeper.PutResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class UploadFileServer {
    private static final Logger logger = Logger.getLogger(UploadFileServer.class.getName());
    private static final int PORT = 50051;

    private Server mServer;

    private void start() throws IOException {
    /* The port on which the mServer should run UploadFileServer*/

        mServer = ServerBuilder.forPort(PORT)
                .addService(new UploadFileServer.BlobKeeperImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + PORT);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC mServer since JVM is shutting down");
                UploadFileServer.this.stop();
                System.err.println("*** mServer shut down");
            }
        });
    }

    private void stop() {
        if (mServer != null) {
            mServer.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (mServer != null) {
            mServer.awaitTermination();
        }
    }

    /**
     * Main launches the mServer from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final UploadFileServer server = new UploadFileServer();
        server.start();
        server.blockUntilShutdown();
    }

    static class BlobKeeperImpl extends BlobKeeperGrpc.BlobKeeperImplBase {
        private int status = 200;
        private String message = "";
        private BufferedOutputStream mBufferedOutputStream = null;

        @Override
        public StreamObserver<PutRequest> getBlob(final StreamObserver<PutResponse> responseObserver) {
            return new StreamObserver<PutRequest>() {
                int mmCount = 0;

                @Override
                public void onNext(PutRequest request) {
                    // Print count
                    logger.info("onNext count: " + mmCount);
                    mmCount++;

                    if (mBufferedOutputStream == null) {
                        try {
                            mBufferedOutputStream = new BufferedOutputStream(new FileOutputStream("receive.png"));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                    byte[] data = request.getData().toByteArray();
                    long offset = request.getOffset();
                    try {
                        if (mBufferedOutputStream != null) {
                            mBufferedOutputStream.write(data);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {
                    responseObserver.onNext(PutResponse.newBuilder().setStatus(status).setMessage(message).build());
                    responseObserver.onCompleted();
                }
            };
        }
    }
}
