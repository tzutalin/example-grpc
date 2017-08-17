import com.google.protobuf.ByteString;
import com.tzutalin.grpc.blobkeeper.BlobKeeperGrpc;
import com.tzutalin.grpc.blobkeeper.PutRequest;
import com.tzutalin.grpc.blobkeeper.PutResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class UploadFileClient {
    private static final Logger logger = Logger.getLogger(UploadFileClient.class.getName());
    private static final int PORT = 50051;

    private final ManagedChannel mChannel;
    private final BlobKeeperGrpc.BlobKeeperBlockingStub mBlockingStub;
    private final BlobKeeperGrpc.BlobKeeperStub mAsyncStub;

    public UploadFileClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext(true)
                .build());
    }

    UploadFileClient(ManagedChannel channel) {
        this.mChannel = channel;
        mBlockingStub = BlobKeeperGrpc.newBlockingStub(channel);
        mAsyncStub = BlobKeeperGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        mChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void startStream(final String filepath) {
        logger.info("Will try to getBlob");
        StreamObserver<PutResponse> responseObserver = new StreamObserver<PutResponse>() {

            @Override
            public void onNext(PutResponse value) {
                logger.info("Client response onNext");
            }

            @Override
            public void onError(Throwable t) {
                logger.info("Client response onError");
            }

            @Override
            public void onCompleted() {
                logger.info("Client response onCompleted");
            }
        };
        StreamObserver<PutRequest> requestObserver = mAsyncStub.getBlob(responseObserver);
        try {

            File file = new File(filepath);
            if (file.exists() == false) {
                logger.info("File does not exist");
                return;
            }
            try {
                BufferedInputStream bInputStream = new BufferedInputStream(new FileInputStream(file));
                int bufferSize = 512 * 1024; // 512k
                byte[] buffer = new byte[bufferSize];
                int tmp = 0;
                int size = 0;
                while ((tmp = bInputStream.read(buffer)) > 0) {
                    size += tmp;
                    ByteString byteString = ByteString.copyFrom(buffer);
                    PutRequest req = PutRequest.newBuilder().setData(byteString).setOffset(tmp).build();
                    requestObserver.onNext(req);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // TODO : Remove sleep
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (RuntimeException e) {
            // Cancel RPC
            requestObserver.onError(e);
            throw e;
        }
        requestObserver.onCompleted();
    }

    public static void main(String[] args) throws Exception {
        UploadFileClient client = new UploadFileClient("localhost", PORT);
        try {
            client.startStream("lena.png");
            logger.info("Done with startStream");
        } finally {
            client.shutdown();
        }
    }
}
