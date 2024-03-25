package com.ps.webflux.service;

import com.ps.webflux.dto.MultiplyRequestDTO;
import com.ps.webflux.dto.Response;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Logger;

import java.time.Duration;


@Service
@Slf4j
public class ReactiveMathService {

    public Mono<Response> findSquere(int input)
    {
        return Mono.fromSupplier(() -> input * input)
                .map(Response::new);
    }

    public Flux<Response> multiplicationTable(int input)  {

        return Flux.range(1,10)
                .doOnNext(i-> log.info("printing value ",i))
                .delayElements(Duration.ofSeconds(5))
                .map(i -> new Response(input *i));
    }
    
    public Mono<Response> findMultiplication(Mono<MultiplyRequestDTO> dto){
        return dto.map(data -> data.getA() * data.getB()).map(Response::new);
    }
}
