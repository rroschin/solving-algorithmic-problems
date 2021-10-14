package circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BattleshipsGame {

    private static final String INVALID = "invalid";
    private static final String HIT = "hit";
    private static final String MISS = "miss";
    private static final String WIN = "win";
    private static final String LOSE = "lose";

    private static final String PLAYER_ONE_ENTERED_N_SHIPS = "Player One entered %s ships.";
    private static final String PLAYER_TWO_YOU_HAVE_M_GUESSES_LEFT = "Player Two, you have %s guesses left. Board Status:";
    private static final String PLAYER_TWO_PLEASE_ENTER_YOUR_GUESS = "Player Two, please enter your guess: %s";
    private static final String THAT_WAS_A_GUESS_RESULT = "That was a %s!";
    private static final String YOU_GAME_STATUS = "You %s!";

    private static final String SHIP_HIT = "X";
    private static final String SHIP_MISS = "O";
    private static final String SHIP_UNKNOWN = ".";

    private static final int SHIP_NO = 0;
    private static final int SHIP_YES = 1;
    private static final int SHIP_DEAD = 2;


    /*
     * Complete the 'play' function below.
     *
     * The function accepts following parameters:
     *  1. STRING playerOneShips
     *  2. STRING_ARRAY playerTwoGuesses
     */

    enum GuessResult {
        HIT,
        MISS
    }

    public static void play(String playerOneShips, List<String> playerTwoGuesses) {
        /*
            Game sequence:
            1. Parse playerOneShips to "Ships"

            2. Validate Ships:
            2.1 Number of ships >= 1 AND <= 3
            2.2 A ship is VERTICAL OR HORIZONTAL
            2.3 No ships intersection
            2.4 Ships are not adjacent (e.g. there is a space between them)

            3. Put ships on a "Board"

            4. Parse and loop through playerTwoGuesses

            5. For each Guess check:
            5.1 Is it a valid input? (Valid = A <= IN <= F AND 1 <= IN <= 6) -> end game on invalid input
            5.2 If it is a Hit or Miss -> print board status, update "Board"
            5.3 If it is a Hit -> is it a Win?
        */

        final Game game = new Game();
        final Board board = game.createEmptyBoard();

        try {
            final List<Ship> ships = new ShipsParser().parseShips(playerOneShips);
            board.placeShips(ships);
            //            board.printPrivateBoard();
        } catch (IllegalArgumentException e) {
            printInvalidAndExit();
            throw new RuntimeException();
        }

        System.out.println(String.format(PLAYER_ONE_ENTERED_N_SHIPS, board.ships.size()));
        System.out.println();

        if (playerTwoGuesses.size() != Game.NUM_OF_GUESSES) {
            printInvalidAndExit();
        }

        int guesses = playerTwoGuesses.size();
        while (guesses > 0) {
            System.out.println(String.format(PLAYER_TWO_YOU_HAVE_M_GUESSES_LEFT, guesses));
            board.printPublicBoard();

            String guessInput = playerTwoGuesses.get(Game.NUM_OF_GUESSES - guesses);
            System.out.println(String.format(PLAYER_TWO_PLEASE_ENTER_YOUR_GUESS, guessInput));
            Square guess = new Square(guessInput);
            if (!guess.isValid()) {
                printInvalidAndExit();
            }

            GuessResult guessResult = game.makeGuess(guess);
            System.out.println(String.format(THAT_WAS_A_GUESS_RESULT, guessResult == GuessResult.HIT ? HIT : MISS));
            System.out.println();

            if (!board.anyShipsLeft()) {
                System.out.println(String.format(YOU_GAME_STATUS, WIN));
                System.exit(1);
            }

            guesses--;
        }
        System.out.println(String.format(YOU_GAME_STATUS, LOSE));
    }

    public static void printInvalidAndExit() {
        System.out.println(INVALID);
        System.exit(1); //really terminate or just 'return' would be OK?
    }

    public static void main(String[] args) {
        String playerOneShips = "A1 A3 D2 F2 B5 D5";
        List<String> playerTwoGuesses = List.of("A2", "A3", "A4", "B2", "D2", "F2", "E2", "E5", "C5", "B5");
        BattleshipsGame.play(playerOneShips, playerTwoGuesses);
    }

    static class Game {
        public static final int NUM_OF_GUESSES = 10;
        private Board board;

        public Board createEmptyBoard() {
            this.board = new Board();
            return this.board;
        }

        public GuessResult makeGuess(final Square guess) {
            return board.fire(guess);
        }
    }

    static class ShipsParser {
        public static final Map<String, Integer> A_TO_1 = Map.of("A", 1,
                                                                 "B", 2,
                                                                 "C", 3,
                                                                 "D", 4,
                                                                 "E", 5,
                                                                 "F", 6);
        public static final Map<String, Integer> NUM_TO_1 = Map.of("1", 1,
                                                                   "2", 2,
                                                                   "3", 3,
                                                                   "4", 4,
                                                                   "5", 5,
                                                                   "6", 6);
        public static final Set<String> VALID_ROWS = A_TO_1.keySet();
        public static final Set<String> VALID_COLS = NUM_TO_1.keySet();

        private static final int MIN_SHIPS = 1;
        private static final int MAX_SHIPS = 3;

        public List<Ship> parseShips(final String shipsInput) {
            if (shipsInput == null || shipsInput.isEmpty() || !shipsInput.contains(" ")) {
                throw new IllegalArgumentException();
            }
            String[] squares = shipsInput.split("\\s");
            if (squares.length % 2 != 0) {
                throw new IllegalArgumentException();
            }

            List<Ship> ships = new ArrayList<>();

            Ship ship = null;
            for (String square : squares) {
                if (square.length() != 2
                    || !VALID_ROWS.contains(String.valueOf(square.charAt(0)))
                    || !VALID_COLS.contains(String.valueOf(square.charAt(1)))) {
                    throw new IllegalArgumentException();
                }

                if (ship == null) {
                    ship = new Ship();
                    ship.start = new Square(square);
                } else {
                    ship.end = new Square(square);
                    if (!ship.isValid()) {
                        throw new IllegalArgumentException();
                    }
                    ships.add(ship);
                    ship = null;
                }
            }

            if (ships.size() < MIN_SHIPS || ships.size() > MAX_SHIPS) {
                throw new IllegalArgumentException();
            }

            return ships;
        }
    }

    static class Ship {
        private static final int SHIP_LENGTH = 3;
        public Square start;
        public Square end;

        public boolean isValid() {
            if (start == null || end == null) {
                return false;
            }
            boolean horizontal = start.aRow.equals(end.aRow);
            boolean vertical = start.aCol.equals(end.aCol);
            if (!horizontal && !vertical) {
                return false;
            }
            if (horizontal && (end.col - start.col + 1 != SHIP_LENGTH)) {
                return false;
            }
            if (vertical && (end.row - start.row + 1 != SHIP_LENGTH)) {
                return false;
            }
            return true;
        }

        public boolean isHorizontal() {
            return start.aRow.equals(end.aRow);
        }

        public boolean isVertical() {
            return start.aCol.equals(end.aCol);
        }
    }

    static class Board {
        private static final int BOARD_W = 6 + 1;
        private static final int BOARD_H = 6 + 1;

        private final int[][] privateBoard = new int[BOARD_H][BOARD_W];
        private final String[][] publicBoard = new String[BOARD_H][BOARD_W];

        List<Ship> ships;

        public Board() {
            for (int i = 1; i < BOARD_H; i++) {
                for (int j = 1; j < BOARD_W; j++) {
                    privateBoard[i][j] = SHIP_NO;
                    publicBoard[i][j] = SHIP_UNKNOWN;
                }
            }
        }

        public void placeShips(List<Ship> ships) {
            for (Ship ship : ships) {
                int startCol = ship.start.col;
                int startRow = ship.start.row;
                if (privateBoard[startRow][startCol] == SHIP_YES) {
                    throw new IllegalArgumentException();
                }
                privateBoard[startRow][startCol] = SHIP_YES;

                if (ship.isHorizontal()) {
                    if (privateBoard[startRow][startCol + 1] == SHIP_YES) {
                        throw new IllegalArgumentException();
                    }
                    privateBoard[startRow][startCol + 1] = SHIP_YES;
                } else if (ship.isVertical()) {
                    if (privateBoard[startRow + 1][startCol] == SHIP_YES) {
                        throw new IllegalArgumentException();
                    }
                    privateBoard[startRow + 1][startCol] = SHIP_YES;
                } else {
                    throw new IllegalArgumentException();
                }

                int endCol = ship.end.col;
                int endRow = ship.end.row;
                if (privateBoard[endRow][endCol] == SHIP_YES) {
                    throw new IllegalArgumentException();
                }
                privateBoard[endRow][endCol] = SHIP_YES;
            }
            this.ships = ships;
        }

        public void printPublicBoard() {
            for (int i = 1; i < BOARD_H; i++) {
                for (int j = 1; j < BOARD_W; j++) {
                    System.out.print(publicBoard[i][j]);
                }
                System.out.println();
            }
        }

        public void printPrivateBoard() {
            for (int i = 1; i < BOARD_H; i++) {
                for (int j = 1; j < BOARD_W; j++) {
                    System.out.print(privateBoard[i][j]);
                }
                System.out.println();
            }
        }

        public GuessResult fire(final Square guess) {
            if (publicBoard[guess.row][guess.col].equals(SHIP_HIT)) {
                return GuessResult.HIT; //already hit that
            }
            if (privateBoard[guess.row][guess.col] == SHIP_YES) {
                publicBoard[guess.row][guess.col] = SHIP_HIT;
                privateBoard[guess.row][guess.col] = SHIP_DEAD;
                return GuessResult.HIT;
            }
            publicBoard[guess.row][guess.col] = SHIP_MISS;
            return GuessResult.MISS;
        }

        public boolean anyShipsLeft() {
            for (int i = 1; i < BOARD_H; i++) {
                for (int j = 1; j < BOARD_W; j++) {
                    if (privateBoard[i][j] == SHIP_YES) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    static class Square {
        public final String formatted;
        public final int col;
        public final int row;
        public final String aRow;
        public final String aCol;

        public Square(final String formatted) {
            this.formatted = formatted;
            this.aRow = String.valueOf(formatted.charAt(0));
            this.aCol = String.valueOf(formatted.charAt(1));
            this.row = ShipsParser.A_TO_1.get(this.aRow);
            this.col = ShipsParser.NUM_TO_1.get(this.aCol);
        }

        public boolean isValid() {
            return ShipsParser.VALID_ROWS.contains(this.aRow) && ShipsParser.VALID_COLS.contains(this.aCol);
        }
    }

}
