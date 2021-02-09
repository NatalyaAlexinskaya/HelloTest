package com.example.service;

import com.example.grpc.server.HelloGrpcServiceGrpc;
import com.example.grpc.server.HelloRequest;
import com.example.grpc.server.HelloResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class HelloGrpcService extends HelloGrpcServiceGrpc.HelloGrpcServiceImplBase {
    private HelloService2 helloService2;

    public HelloGrpcService(HelloService2 helloService2) {
        this.helloService2 = helloService2;
    }

    @PostConstruct
    public void init() throws IOException {
        Server server = ServerBuilder.forPort(9090)
                .addService(new HelloGrpcService(helloService2))
                .build();

        server.start();
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
