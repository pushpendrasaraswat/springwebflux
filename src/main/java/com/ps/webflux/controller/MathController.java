package com.ps.webflux.controller;

import com.ps.webflux.dto.MultiplyRequestDTO;
import com.ps.webflux.dto.Response;
import com.ps.webflux.service.MathService;
import com.ps.webflux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("math")
public class MathController {

    @Autowired
    private MathService mathService;

    @Autowired
    private ReactiveMathService reactiveMathService;

    @GetMapping("/square/{input}")
    public Response findSquare(@PathVariable(name = "input") int number){
        return mathService.findSquere(number);
    }

    @GetMapping("/table/{input}")
    public List<Response> multiplyTable(@PathVariable(name = "input") int number){
        return mathService.multiplicationTable(number);
    }

    @GetMapping("/reactive/square/{input}")
    public Mono<Response> reactiveFindSquare(@PathVariable(name = "input") int number){
        return reactiveMathService.findSquere(number);
    }

    @GetMapping("/reactive/table/{input}")
    public Flux<Response> reactiveMultiplyTable(@PathVariable(name = "input") int number) throws InterruptedException {
        return reactiveMathService.multiplicationTable(number);
    }

    @GetMapping(value = "/reactive/table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> reactiveMultiplyTablestream(@PathVariable(name = "input") int number) throws InterruptedException {
        return reactiveMathService.multiplicationTable(number);
    }

    @PostMapping(value = "/reactive/multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequestDTO> dto){
        return reactiveMathService.findMultiplication(dto);
    }
}
