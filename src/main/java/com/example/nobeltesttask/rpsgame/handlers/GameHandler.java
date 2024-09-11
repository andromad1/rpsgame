package com.example.nobeltesttask.rpsgame.handlers;

import com.example.nobeltesttask.rpsgame.model.Choices;
import com.example.nobeltesttask.rpsgame.service.GameService;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class GameHandler {
    GameService gameService;

    public GameHandler(GameService gameService) {
        this.gameService = gameService;
    }

    public ServerResponse startGame(ServerRequest request) {
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(gameService.start());
    }

    public ServerResponse getStatistics(ServerRequest request) {
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(gameService.statistics());
    }

    public ServerResponse terminateGame(ServerRequest request) {
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(gameService.terminate());
    }

    public ServerResponse makeMove(ServerRequest request) {
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(gameService.makeMove(Choices.valueOf(request.pathVariable("choice"))));
    }
}
