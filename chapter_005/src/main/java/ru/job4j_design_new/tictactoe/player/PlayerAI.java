package ru.job4j_design_new.tictactoe.player;

import ru.job4j_design_new.tictactoe.logic.Logic;
import ru.job4j_design_new.tictactoe.model.IBoard;

import java.util.Random;

public class PlayerAI implements Player {
    private Random random = new Random();

    private IBoard board;
    private Logic logic;

    public PlayerAI(IBoard board, Logic logic) {
        this.board = board;
        this.logic = logic;
    }

    @Override
    public void go(char symbol) {
        int x, y;
        do {
            x = random.nextInt(board.size());
            y = random.nextInt(board.size());
        } while (!logic.isCellValid(x, y));
        board.getBoard()[y][x] = symbol;
    }
}
