package com.tzutalin.grpc.blobkeeper;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.3.0)",
    comments = "Source: blob.proto")
public final class BlobKeeperGrpc {

  private BlobKeeperGrpc() {}

  public static final String SERVICE_NAME = "blobkeeper.BlobKeeper";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tzutalin.grpc.blobkeeper.PutRequest,
      com.tzutalin.grpc.blobkeeper.PutResponse> METHOD_GET_BLOB =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING,
          generateFullMethodName(
              "blobkeeper.BlobKeeper", "GetBlob"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tzutalin.grpc.blobkeeper.PutRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tzutalin.grpc.blobkeeper.PutResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BlobKeeperStub newStub(io.grpc.Channel channel) {
    return new BlobKeeperStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BlobKeeperBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new BlobKeeperBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static BlobKeeperFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new BlobKeeperFutureStub(channel);
  }

  /**
   */
  public static abstract class BlobKeeperImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<com.tzutalin.grpc.blobkeeper.PutRequest> getBlob(
        io.grpc.stub.StreamObserver<com.tzutalin.grpc.blobkeeper.PutResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_GET_BLOB, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_BLOB,
            asyncClientStreamingCall(
              new MethodHandlers<
                com.tzutalin.grpc.blobkeeper.PutRequest,
                com.tzutalin.grpc.blobkeeper.PutResponse>(
                  this, METHODID_GET_BLOB)))
          .build();
    }
  }

  /**
   */
  public static final class BlobKeeperStub extends io.grpc.stub.AbstractStub<BlobKeeperStub> {
    private BlobKeeperStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BlobKeeperStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlobKeeperStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BlobKeeperStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.tzutalin.grpc.blobkeeper.PutRequest> getBlob(
        io.grpc.stub.StreamObserver<com.tzutalin.grpc.blobkeeper.PutResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(METHOD_GET_BLOB, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class BlobKeeperBlockingStub extends io.grpc.stub.AbstractStub<BlobKeeperBlockingStub> {
    private BlobKeeperBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BlobKeeperBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlobKeeperBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BlobKeeperBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class BlobKeeperFutureStub extends io.grpc.stub.AbstractStub<BlobKeeperFutureStub> {
    private BlobKeeperFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BlobKeeperFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlobKeeperFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BlobKeeperFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_BLOB = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final BlobKeeperImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BlobKeeperImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BLOB:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getBlob(
              (io.grpc.stub.StreamObserver<com.tzutalin.grpc.blobkeeper.PutResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class BlobKeeperDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.tzutalin.grpc.blobkeeper.blob.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BlobKeeperGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BlobKeeperDescriptorSupplier())
              .addMethod(METHOD_GET_BLOB)
              .build();
        }
      }
    }
    return result;
  }
}
