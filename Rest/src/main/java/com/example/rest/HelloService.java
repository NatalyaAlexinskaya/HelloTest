package com.example.rest;

import com.example.grpc.server.HelloGrpcServiceGrpc;
import com.example.grpc.server.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class HelloService {
    @GrpcClient("HelloGrpcService")
    private HelloGrpcServiceGrpc.HelloGrpcServiceBlockingStub client;

    @PostConstruct
    public void init() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        client = HelloGrpcServiceGrpc.newBlockingStub(channel);
    }

    public String getResponse(String request) {
        HelloRequest helloRequest = HelloRequest.newBuilder()
               .setRequest(request)
               .build();

        client.getGrpc(helloRequest);

        return request;
    }
}
