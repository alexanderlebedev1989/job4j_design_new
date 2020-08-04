package ru.job4j_design_new.tictactoe.player;

import ru.job4j_design_new.tictactoe.input.Input;
import ru.job4j_design_new.tictactoe.logic.Logic;
import ru.job4j_design_new.tictactoe.model.IBoard;

public class PlayerHuman implements Player {

    private Input input;
    private IBoard board;
    private Logic logic;


    public PlayerHuman(Input input, IBoard board, Logic logic) {
        this.input = input;
        this.board = board;
        this.logic = logic;
    }

    @Override
    public void go(char symbol) {
        int x, y;
        do {
            x = input.ask("Введите координату X: ") - 1;
            y = input.ask("Введите координату Y: ") - 1;
        } while (!logic.isCellValid(x, y));
        board.getBoard()[y][x] = symbol;
    }
}
