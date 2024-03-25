package com.ps.webflux.service;

import com.ps.webflux.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MathService {

    public Response findSquere(int input){
        return new Response(input*input);
    }

    public List<Response> multiplicationTable(int input) {
        return IntStream.rangeClosed(1,10).peek(i -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
        .mapToObj(data -> new Response(data * input)).collect(Collectors.toList());
    }
}
