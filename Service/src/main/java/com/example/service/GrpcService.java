package com.example.service;

import com.example.grpc.server.GrpcServiceGrpc;
import com.example.grpc.server.HelloRequest;
import com.example.grpc.server.HelloResponse;
import io.grpc.stub.StreamObserver;

@net.devh.boot.grpc.server.service.GrpcService
public class GrpcService extends GrpcServiceGrpc.GrpcServiceImplBase {
    private HelloService2 helloService2;

    public GrpcService(HelloService2 helloService2) {
        this.helloService2 = helloService2;
    }

    @Override
    public void getGrpc(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse response = HelloResponse.newBuilder()
                .setResponse(request.getRequest())
                .build();

        responseObserver.onNext(helloService2.redirectRequest(response));
        responseObserver.onCompleted();
    }
}
