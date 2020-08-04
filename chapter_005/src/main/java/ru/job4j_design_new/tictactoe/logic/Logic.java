package ru.job4j_design_new.tictactoe.logic;

import ru.job4j_design_new.tictactoe.model.IBoard;
import java.util.function.Predicate;

public class Logic {

    private IBoard board;

    public Logic(IBoard board) {
        this.board = board;
    }

    public boolean fillBy(Predicate<Character> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != board.size(); index++) {
            char cell = board.getBoard()[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinnerX() {
        return isWin(character -> character == board.getCell().getSIGN_X());
    }

    public boolean isWinnerO() {
        return isWin(character -> (character == board.getCell().getSIGN_O()));
    }

    public boolean isWin(Predicate<Character> winCondition) {
        boolean result = false;
        for (int i = 0; i < board.getBoard().length; i++) {
            if (fillBy(winCondition, i, 0, 0, 1) ||
                    fillBy(winCondition, 0, i, 1, 0) ||
                    fillBy(winCondition, 0, 0, 1, 1) ||
                    fillBy(winCondition, board.size() - 1, 0, -1, 1)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean isTableFull() {
        for (int row = 0; row < board.size(); row++) {
            for (int col = 0; col < board.size(); col++) {
                if (board.getBoard()[row][col] == board.getCell().getSIGN_EMPTY()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= board.size() || y >= board.size()) {
            return false;
        }
        if (board.getBoard()[y][x] != board.getCell().getSIGN_EMPTY()) {
            return false;
        }
        return true;
    }
}
