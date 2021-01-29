package com.example.rest;

import com.example.grpc.server.HelloGrpcServiceGrpc;
import com.example.grpc.server.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class HelloService {
    private HelloGrpcServiceGrpc.HelloGrpcServiceBlockingStub client;

    @PostConstruct
    public void init() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("servicetest", 9090)
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
