package com.example.rest.client;

import com.example.grpc.server.GrpcServiceGrpc;
import com.example.grpc.server.HelloRequest;
import com.example.grpc.server.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class HelloService {
    @GrpcClient("service")
    GrpcServiceGrpc.GrpcServiceBlockingStub client;
    HelloService2 helloService2;

    public HelloService(HelloService2 helloService2) {
        this.helloService2 = helloService2;
    }

    @PostConstruct
    public void init() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        client = GrpcServiceGrpc.newBlockingStub(channel);
    }

    public String getResponse(String request) {
        HelloRequest helloRequest = HelloRequest.newBuilder()
                .setRequest(request)
                .build();

        HelloResponse helloResponse = client.getGrpc(helloRequest);
        helloService2.redirectRequest(helloResponse.getResponse());

        return helloResponse.getResponse();

    }
}
