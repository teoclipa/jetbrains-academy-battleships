package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final Board player1Board;
    private final Board player2Board;
    private final List<Ship> player1Ships;
    private final List<Ship> player2Ships;
    private final Scanner scanner;

    public Game() {
        player1Board = new Board();
        player2Board = new Board();
        player1Ships = new ArrayList<>();
        player2Ships = new ArrayList<>(player1Ships);
        scanner = new Scanner(System.in);
    }

    public void play() {
        //Create the ships for each player
        Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
        Ship battleship = new Ship("Battleship", 4);
        Ship submarine = new Ship("Submarine", 3);
        Ship cruiser = new Ship("Cruiser", 3);
        Ship destroyer = new Ship("Destroyer", 2);

        Ship aircraftCarrier2 = new Ship("Aircraft Carrier", 5);
        Ship battleship2 = new Ship("Battleship", 4);
        Ship submarine2 = new Ship("Submarine", 3);
        Ship cruiser2 = new Ship("Cruiser", 3);
        Ship destroyer2 = new Ship("Destroyer", 2);

        player1Ships.add(aircraftCarrier);
        player1Ships.add(battleship);
        player1Ships.add(submarine);
        player1Ships.add(cruiser);
        player1Ships.add(destroyer);

        player2Ships.add(aircraftCarrier2);
        player2Ships.add(battleship2);
        player2Ships.add(submarine2);
        player2Ships.add(cruiser2);
        player2Ships.add(destroyer2);


        // Place player 1's ships
        System.out.println("Player 1, place your ships on the game field:\n");
        placeShips(player1Board, player1Ships);

        // Place player 2's ships
        changePlayer();
        scanner.nextLine();
        scanner.nextLine();
        System.out.println("Player 2, place your ships on the game field:\n");

        placeShips(player2Board, player2Ships);

        changePlayer();
        scanner.nextLine();
        scanner.nextLine();
        //TO DO: implement the shooting logic
        //If a shot is invalid, just move on to the next player's turn

        while (!(areAllShipsSunk(player1Ships) || areAllShipsSunk(player2Ships))) {
            try {
                String shootCoordinate;
                player2Board.printShotCells();
                System.out.println("---------------------");
                player1Board.printShipsPlacement();

                System.out.println("Player 1, it's your turn:\n");

                shootCoordinate = scanner.nextLine();
                System.out.println();
                player2Board.shoot(shootCoordinate, player2Ships);
                changePlayer();
                scanner.nextLine();

                player1Board.printShotCells();
                System.out.println("---------------------");
                player2Board.printShipsPlacement();
                System.out.println("Player 2, it's your turn:\n");

                shootCoordinate = scanner.nextLine();
                System.out.println();
                player1Board.shoot(shootCoordinate, player1Ships);
                changePlayer();
                scanner.nextLine();
            } catch (InvalidPlacementException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    public void changePlayer() {
        System.out.println("Press Enter and pass the move to another player\n");

    }

    public void placeShips(Board playerBoard, List<Ship> playerShips) {
        playerBoard.printShipsPlacement();
        for (int i = 0; i < 5; ) {
            System.out.printf("Enter the coordinates of the %s (%d cells):\n", playerShips.get(i).getName(), playerShips.get(i).getLength());
            String startCoordinate = scanner.next();
            String endCoordinate = scanner.next();
            try {
                playerBoard.placeShip(playerShips.get(i), startCoordinate, endCoordinate);
                System.out.println();
                playerBoard.printShipsPlacement();
                System.out.println();
                i++;
            } catch (InvalidPlacementException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean areAllShipsSunk(List<Ship> playerShips) {
        return playerShips.get(0).isSunk() && playerShips.get(1).isSunk() && playerShips.get(2).isSunk() && playerShips.get(3).isSunk() && playerShips.get(4).isSunk();
    }
}
