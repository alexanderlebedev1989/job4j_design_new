package ru.job4j_design_new.tictactoe;

import ru.job4j_design_new.tictactoe.input.Input;
import ru.job4j_design_new.tictactoe.input.InputConsole;
import ru.job4j_design_new.tictactoe.logic.Logic;
import ru.job4j_design_new.tictactoe.model.Board;
import ru.job4j_design_new.tictactoe.model.IBoard;
import ru.job4j_design_new.tictactoe.player.Choose;
import ru.job4j_design_new.tictactoe.player.ChoosePlayers;
import ru.job4j_design_new.tictactoe.player.Player;


public class Starter {

    private IBoard board;
    private Logic logic;
    private Input input;
    private Choose choose;

    public Starter(IBoard board, Logic logic, Input input) {
        this.board = board;
        this.logic = logic;
        this.input = input;
        this.choose = new ChoosePlayers();
    }

    public void game() {
        Player[] players = choose.getPlayers(input, board, logic);
        board.printTable();
        while (true) {
            players[0].go(board.getCell().getSIGN_X());
            board.printTable();
            if (logic.isWinnerX()) {
                System.out.println("YOU WIN");
                break;
            }
            if (logic.isTableFull()) {
                System.out.println("SORRY, DRAW");
                break;
            }
            players[1].go(board.getCell().getSIGN_O());
            board.printTable();
            if (logic.isWinnerO()) {
                System.out.println("YOU WIN");
                break;
            }
            if (logic.isTableFull()) {
                System.out.println("SORRY, DRAW");
                break;
            }
        }
        System.out.println("GAME OVER");
    }

    public static void main(String[] args) {
        IBoard board = new Board();
        Logic logic = new Logic(board);
        Input input = new InputConsole();
        Starter starter = new Starter(board, logic, input);
        starter.game();
    }
}
