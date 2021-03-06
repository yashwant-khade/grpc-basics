package com.grpc.dataverse.greeting.server;

import com.grpc.dataverse.calculator.server.CalculatorServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("gRPC server started...");

        Server server = ServerBuilder
                .forPort(50051)
                .addService(new GreetServiceImpl())
                .addService(new CalculatorServiceImpl())
                .build();
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("Shutdown request prompted");
            server.shutdown();
            System.out.println("The gRPC server is shutdown successfully");
        }));
        server.awaitTermination();
    }
}
