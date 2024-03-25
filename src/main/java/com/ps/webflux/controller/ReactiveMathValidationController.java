package com.ps.webflux.controller;

import com.ps.webflux.dto.Response;
import com.ps.webflux.exception.InputValidationException;
import com.ps.webflux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathValidationController {

    @Autowired
    private ReactiveMathService mathService;

    @GetMapping("square/{input}/throw")
    public Mono<Response> findSquare(@PathVariable int input){
        if(input<10 || input >20){
            throw new InputValidationException(input);
        }
        return mathService.findSquere(input);
    }

    @GetMapping("square/{input}/mono-error")
    public Mono<Response> findSquareError(@PathVariable int input) {
        return Mono.just(input).
                handle((inputValue, sink) -> {
            if (inputValue > 10 && inputValue <= 20) {
                sink.next(inputValue);
            } else {
                sink.error(new InputValidationException(inputValue));
            }
        }).cast(Integer.class).flatMap(i -> mathService.findSquere(i));
    }
}
