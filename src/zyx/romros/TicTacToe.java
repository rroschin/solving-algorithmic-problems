package zyx.romros;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    Random turnChoice = new Random();
    Random starterSelector = new Random();
    Random characterSelector = new Random();
    String[][] board = new String[][] {
            { "_", "_", "_" },
            { "_", "_", "_" },
            { "_", "_", "_" }
    };

    String humanCharacter;
    String computerCharacter;

    public static void main(String[] args) {

        TicTacToe ttt = new TicTacToe();

        ttt.printWelcome();
        ttt.chooseCharacter();
        ttt.drawBoard();
        String starter = ttt.decideWhoStarts();
        if ("computer".equals(starter)) {
            ttt.makeComputerTurn();
            ttt.drawBoard();
        }
        while (ttt.hasMoreTurns()) {
            String humanTurn = ttt.askHumanForTurn();
            if (!ttt.isTurnPossible(humanTurn)) {
                ttt.printTurnNotPossible();
                continue;
            }
            ttt.updateGameStatus(humanTurn);
            ttt.drawBoard();
            if (!ttt.hasMoreTurns()) {
                break;
            }
            ttt.makeComputerTurn();
            ttt.drawBoard();
            ttt.printYourTurn();
        }

        ttt.printWinner();
    }

    private void printWelcome() {
        System.out.println("Welcome to my Tic Tac Toe game! Let's begin...");
    }

    private String chooseCharacter() {
        final String character = this.characterSelector.nextBoolean() ? "X" : "O";
        System.out.println("You are playing as '" + character + "'. Let's see who starts...");
        this.humanCharacter = character;
        this.computerCharacter = "X".equals(this.humanCharacter) ? "O" : "X";
        return character;
    }

    /**
     * X O X
     * X O O
     * O X O
     */
    private void drawBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.board.length; i++) {
            sb.append(" ");
            for (int j = 0; j < this.board[0].length; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private String decideWhoStarts() {
        final String starter = this.starterSelector.nextBoolean() ? "computer" : "human";
        System.out.println("Looks like " + ("computer".equals(starter) ? "I" : "You") + " start");
        return starter;
    }

    private void makeComputerTurn() {
        int i;
        int j;
        do {
            i = this.turnChoice.nextInt(3);
            j = this.turnChoice.nextInt(3);
        }
        while (!cellIsEmpty(i, j));

        this.board[i][j] = this.computerCharacter;
        System.out.println("I made my choice, here is the updated board: ");
    }

    private boolean cellIsEmpty(int i1, int j1) {
        return ("_".equals(this.board[i1][j1]));
    }

    private boolean hasMoreTurns() {
        //1. did I win?
        boolean didComputerWin = didCharacterWin(computerCharacter);
        if (didComputerWin) {
            return false;
        }

        //2. did you win?
        boolean didHumanWin = didCharacterWin(humanCharacter);
        if (didHumanWin) {
            return false;
        }

        //3. tie?
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                if ("_".equals(board[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean didCharacterWin(final String character) {
        return (character.equals(board[0][0]) && character.equals(board[0][1]) && character.equals(board[0][2]))
               ||
               (character.equals(board[1][0]) && character.equals(board[1][1]) && character.equals(board[1][2]))
               ||
               (character.equals(board[2][0]) && character.equals(board[2][1]) && character.equals(board[2][2]))
               ||
               (character.equals(board[0][0]) && character.equals(board[1][0]) && character.equals(board[2][0]))
               ||
               (character.equals(board[0][1]) && character.equals(board[1][1]) && character.equals(board[2][1]))
               ||
               (character.equals(board[0][2]) && character.equals(board[1][2]) && character.equals(board[2][2]))
               ||
               (character.equals(board[0][0]) && character.equals(board[1][1]) && character.equals(board[2][2]))
               ||
               (character.equals(board[2][0]) && character.equals(board[1][1]) && character.equals(board[0][2]));
    }

    private String askHumanForTurn() {
        return new Scanner(System.in).nextLine();
    }

    private boolean isTurnPossible(final String humanTurn) {
        if (!humanTurn.contains(" ")) {
            return false;
        }
        String[] in = humanTurn.split(" ");
        try {
            int row = Integer.parseInt(in[0]);
            int col = Integer.parseInt(in[1]);

            if (row < 1 || row > 3 || col < 1 || col > 3) {
                return false;
            }

            if (!cellIsEmpty(row - 1, col - 1)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void printTurnNotPossible() {
        System.out.println("Please use '1 3' format for input, where first number (1..3) is a row and second number (1..3) is a column");
        System.out.println("Also, please use cells that are no occupied already");
    }

    private void updateGameStatus(final String humanTurn) {
        String[] in = humanTurn.split(" ");

        int row = Integer.parseInt(in[0]);
        int col = Integer.parseInt(in[1]);

        board[row - 1][col - 1] = humanCharacter;
    }

    private void printYourTurn() {
        System.out.println("Your turn");
    }

    private void printWinner() {
        if (didCharacterWin(computerCharacter)) {
            System.out.println("Ha ha, I won!");
        } else if (didCharacterWin(humanCharacter)) {
            System.out.println("Oh no, You won!");
        } else {
            System.out.println("No idea what happened... :(");
        }
    }
}
