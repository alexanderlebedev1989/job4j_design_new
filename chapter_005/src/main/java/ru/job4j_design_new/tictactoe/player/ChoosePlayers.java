package ru.job4j_design_new.tictactoe.player;

import ru.job4j_design_new.tictactoe.input.Input;
import ru.job4j_design_new.tictactoe.logic.Logic;
import ru.job4j_design_new.tictactoe.model.IBoard;


public class ChoosePlayers implements Choose {

    @Override
    public Player[] getPlayers(Input input, IBoard board, Logic logic) {
        Player[] players = new Player[2];
        Player player1 = null;
        Player player2 = null;
        System.out.println("Выберите игру...");
        System.out.println("1 - играет человек-человек");
        System.out.println("2 - играет человек-робот");
        System.out.println("3 - играет робот-робот");
        int number = input.ask("Введите число от 1 до 3: ");
        if (number == 1) {
            player1 = new PlayerHuman(input, board, logic);
            player2 = new PlayerHuman(input, board, logic);
        }
        if (number == 2) {
            player1 = new PlayerHuman(input, board, logic);
            player2 = new PlayerAI(board, logic);
        }
        if (number == 3) {
            player1 = new PlayerAI(board, logic);
            player2 = new PlayerAI(board, logic);
        }
        players[0] = player1;
        players[1] = player2;
        return players;
    }
}
