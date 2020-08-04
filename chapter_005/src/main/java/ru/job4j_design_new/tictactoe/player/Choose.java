package ru.job4j_design_new.tictactoe.player;

import ru.job4j_design_new.tictactoe.input.Input;
import ru.job4j_design_new.tictactoe.logic.Logic;
import ru.job4j_design_new.tictactoe.model.IBoard;

public interface Choose {

    Player[] getPlayers(Input input, IBoard board, Logic logic);
}
