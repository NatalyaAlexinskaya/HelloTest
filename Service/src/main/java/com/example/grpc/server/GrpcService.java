package com.example.grpc.server;

import io.grpc.stub.StreamObserver;

@net.devh.boot.grpc.server.service.GrpcService
public class GrpcService extends GrpcServiceGrpc.GrpcServiceImplBase {
    @Override
    public void getGrpc(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse response = HelloResponse.newBuilder()
                .setResponse(request.getRequest())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
