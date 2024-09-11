package com.example.nobeltesttask.rpsgame.config;

import com.example.nobeltesttask.rpsgame.exceptions.InvalidGameStatusException;
import com.example.nobeltesttask.rpsgame.handlers.GameHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RequestPredicate;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration(proxyBeanMethods = false)
public class RoutingConfiguration {
    private static final RequestPredicate ACCEPT_JSON = accept(MediaType.APPLICATION_JSON);

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(GameHandler handler) {
        return route()
                .GET("/api/v1/rpsgame/start", ACCEPT_JSON, handler::startGame)
                .GET("/api/v1/rpsgame/statistics", ACCEPT_JSON, handler::getStatistics)
                .GET("/api/v1/rpsgame/terminate", ACCEPT_JSON, handler::terminateGame)
                .GET("/api/v1/rpsgame/move/{choice}", ACCEPT_JSON, handler::makeMove)
                .onError(InvalidGameStatusException.class,
                        (e, request) -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                .build((servletRequest, servletResponse) -> {
                                    servletResponse.getWriter().print(e.getMessage());
                                    return null;
                                }))
                .build();
    }
}
