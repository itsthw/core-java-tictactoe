import java.util.*;

public class Main {

    public static ArrayList<Integer> pPositions = new ArrayList<>();
    public static ArrayList<Integer> cPositions = new ArrayList<>();

    // main function
    public static void main(String[] args) {
        // tictactoe board prototype
        char[][] b = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
        };
        board(b);
        // accept the input from user
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Enter number (1 - 9) for the placement: ");
            int playerPos = input.nextInt();

            while (pPositions.contains(playerPos) || cPositions.contains(pPositions)) {
                System.out.println("Postition already been placed, please enter another postion.");
                playerPos = input.nextInt();
            }

            System.out.println(playerPos);

            // player
            placement(b, playerPos, "player");

            // computer using random
            Random r = new Random();
            int computerPos = r.nextInt(9) + 1;
            while (pPositions.contains(computerPos) || cPositions.contains(computerPos)) {
                computerPos = r.nextInt(9) + 1;
            }
            placement(b, computerPos, "computer");

            // print the board
            board(b);

            // results
            String result = results();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
    }

    // board function
    public static void board(char[][] b) {
        for (char[] row : b) {
            for (char k : row) {
                System.out.print(k);
            }
            System.out.println();
        }
    }

    // player positioning using switch-cases
    public static void placement(char[][] b, int pos, String user) {
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            pPositions.add(pos);
        } else if (user.equals("computer")) {
            symbol = 'O';
            cPositions.add(pos);
        }
        switch (pos) {
            case 1:
                b[0][0] = symbol;
                break;
            case 2:
                b[0][2] = symbol;
                break;
            case 3:
                b[0][4] = symbol;
                break;
            case 4:
                b[2][0] = symbol;
                break;
            case 5:
                b[2][2] = symbol;
                break;
            case 6:
                b[2][4] = symbol;
                break;
            case 7:
                b[4][0] = symbol;
                break;
            case 8:
                b[4][2] = symbol;
                break;
            case 9:
                b[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String results() {
        List<Integer> topRow = Arrays.asList(1,2,3);
        List<Integer> midRow = Arrays.asList(4,5,6);
        List<Integer> bottomRow = Arrays.asList(7,8,9);
        List<Integer> leftColumn = Arrays.asList(1,4,7);
        List<Integer> midColumn = Arrays.asList(2,5,8);
        List<Integer> rightColumn = Arrays.asList(3,6,9);
        List<Integer> crossingOne = Arrays.asList(1,5,9);
        List<Integer> crossingTwo = Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(bottomRow);
        winning.add(leftColumn);
        winning.add(midColumn);
        winning.add(rightColumn);
        winning.add(crossingOne);
        winning.add(crossingTwo);

        for (List l : winning) {
            if (pPositions.containsAll(l)) {
                return "Congratulations, you won!";
            } else if (cPositions.containsAll(l)) {
                return "you lost! :(";
            } else if (pPositions.size() + cPositions.size() == 9){
                return "Tied!! :|";
            }
        }
        return "";
    }

}
