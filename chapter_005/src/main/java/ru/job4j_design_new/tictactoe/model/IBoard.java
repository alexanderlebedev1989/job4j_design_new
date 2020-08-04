package ru.job4j_design_new.tictactoe.model;

public interface IBoard {

    void printTable();
    char[][] getBoard();
    Cell getCell();
    int size();
}
