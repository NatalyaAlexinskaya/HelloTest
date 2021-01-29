package com.example.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;


public class ApplicationTwo {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9090)
                .addService(new HelloGrpcService())
                .build();

        server.start();

        server.awaitTermination();
    }
}
