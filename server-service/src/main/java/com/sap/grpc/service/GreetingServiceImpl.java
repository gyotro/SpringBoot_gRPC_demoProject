package com.sap.grpc.service;


import com.sap.grpc.GreetingRequest;
import com.sap.grpc.GreetingResponse;
import com.sap.grpc.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

// we need to extend the implementation based class
// https://yidongnan.github.io/grpc-spring-boot-starter/en/server/getting-started.html#project-setup

@GrpcService
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {
        // we receive the message from the request, and we push the response to the Observer
        String req = request.getMessage();
        System.out.println("Received Message: " + req);
        // Build the response using Builder pattern

        GreetingResponse response = GreetingResponse.newBuilder()
                .setResmessage("Reply to message: " + req)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

