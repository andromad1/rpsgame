package com.example.nobeltesttask.rpsgame.service.impl;

import com.example.nobeltesttask.rpsgame.exceptions.InvalidGameStatusException;
import com.example.nobeltesttask.rpsgame.model.*;
import com.example.nobeltesttask.rpsgame.service.GameService;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@SessionScope
@Service("gameService")
public class GameServiceImpl implements GameService {
    private final List<Choices> gamerChoices = new ArrayList<>();
    private final Set<Choices> usedChoices = EnumSet.noneOf(Choices.class);
    private Game game = new Game();
    private final Round round = new Round();

    @Override
    public Game start() {
        if (game.getStatus() == GameStatuses.STARTED) {
            throw new InvalidGameStatusException("The game already started");
        }

        game = new Game();
        game.setStatus(GameStatuses.STARTED);

        return game;
    }

    @Override
    public Game statistics() {
        return game;
    }

    @Override
    public Game terminate() {
        if (game.getStatus() != GameStatuses.STARTED) {
            throw new InvalidGameStatusException("The game not started yet");
        }

        game.setStatus(GameStatuses.TERMINATED);

        return game;
    }

    @Override
    public Round makeMove(Choices playerMove) {
        if (game.getStatus() != GameStatuses.STARTED) {
            throw new InvalidGameStatusException("The game not started yet");
        }

        gamerChoices.add(playerMove);
        usedChoices.add(playerMove);

        Choices compMove;

        if (usedChoices.size() == 3) {
            Choices assumedUserChoice = gamerChoices.get(new Random().nextInt(gamerChoices.size()));

            compMove = (assumedUserChoice == Choices.Rock ? Choices.Paper :
                    (assumedUserChoice == Choices.Paper ? Choices.Scissors : Choices.Rock));

        } else {
            compMove = Choices.values()[new Random().nextInt(3)];
        }

        if (playerMove == compMove) {
            round.setRes(RoundResults.It_s_a_tie);
        } else {
            if (playerMove == Choices.Rock && compMove == Choices.Scissors ||
                playerMove == Choices.Paper && compMove == Choices.Rock ||
                playerMove == Choices.Scissors && compMove == Choices.Paper) {
                round.setRes(RoundResults.Player_won);
                game.incUserWins();
            } else {
                round.setRes(RoundResults.Computer_won);
                game.incCompWins();
            }
        }

        round.setPlayerMove(playerMove);
        round.setCompMove(compMove);

        return round;
    }
}
