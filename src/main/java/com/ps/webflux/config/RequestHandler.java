package com.ps.webflux.config;

import com.ps.webflux.dto.Response;
import com.ps.webflux.service.ReactiveMathService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class RequestHandler {

    @Autowired
    private ReactiveMathService reactiveMathService;

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest){
        return ServerResponse.ok().body(reactiveMathService.findSquere(getInputValue(serverRequest)), Response.class);
    }

    public Mono<ServerResponse> multiplicationTable(ServerRequest serverRequest){
        return  ServerResponse.ok().body(reactiveMathService.multiplicationTable(getInputValue(serverRequest)),Response.class);
    }

    public Mono<ServerResponse> multiplicationStreamTable(ServerRequest serverRequest){
        return  ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(reactiveMathService.multiplicationTable(getInputValue(serverRequest)),Response.class);
    }

    int getInputValue(ServerRequest serverRequest){
        return Integer.valueOf(serverRequest.pathVariable("input"));
    }
}
