package com.ps.webflux.config;

import com.ps.webflux.dto.InputFailedValidationExceptionResponse;
import com.ps.webflux.exception.InputValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Configuration
public class RouterConfig {

    @Autowired
    private RequestHandler requestHandler;
    @Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunction(){
        return RouterFunctions.route()
                .GET("router/square/{input}",requestHandler::squareHandler)
                .GET("router/table/{input}",requestHandler::multiplicationTable)
                .GET("router/stream/{input}",requestHandler::multiplicationStreamTable)
                .POST("router/multiplay",requestHandler::multiply)
                .GET("router/square/{input}/validation",requestHandler::squareHandlerWithValidation)
                .onError(InputValidationException.class,handleErrorResponse())
                .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> handleErrorResponse(){
        return (err, req) -> {
            InputValidationException exception=(InputValidationException) err;
            InputFailedValidationExceptionResponse response=new InputFailedValidationExceptionResponse();
            response.setMessage(exception.getMessage());
            response.setInput(exception.getInput());
            response.setErrorCode(exception.getErrorCode());

            return ServerResponse.badRequest().bodyValue(response);
        };
    }
}
