package com.example.nobeltesttask.rpsgame.service;

import com.example.nobeltesttask.rpsgame.model.Choices;
import com.example.nobeltesttask.rpsgame.model.Game;
import com.example.nobeltesttask.rpsgame.model.Round;

public interface GameService {
    Game start();
    Game statistics();
    Game terminate();
    Round makeMove(Choices playerMove);
}
