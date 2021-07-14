import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TheHeiganDance {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double playerDamagePerRound = Double.parseDouble(scan.nextLine());

        double playerHitPoints = 18500;
        double heiganHitPoint = 3000000;

        int playerRow = 7;
        int playerCol = 7;

        int[][] heiganChamber = new int[15][15];

        boolean hasCloud = false;

        while (true) {
            heiganHitPoint -= playerDamagePerRound;
            if (heiganHitPoint <= 0) {
                System.out.println("Heigan: Defeated!");
                System.out.println(String.format("Player: %.0f", playerHitPoints ));
                System.out.println(String.format("Final position: %d, %d", playerRow, playerCol));
                return;
            }
            if (hasCloud) {
                playerHitPoints -= 3500;
                hasCloud = false;
            }
            if (playerHitPoints <= 0) {
                System.out.println(String.format("Heigan: %.2f", heiganHitPoint));
                System.out.println("Player: Killed by Plague Cloud");
                System.out.println(String.format("Final position: %d, %d", playerRow, playerCol));
                return;
            }
            String[] heiganAttack = scan.nextLine().split("\\s+");
            String spell = heiganAttack[0];
            int row = Integer.parseInt(heiganAttack[1]);
            int col = Integer.parseInt(heiganAttack[2]);
            List<List<Integer>> affectedSpaces = new ArrayList<>();
            for (int i = row + 1; i > row -2 ; i--) {
                for (int j = col + 1; j > col - 2 ; j--) {
                    if (row < heiganChamber.length && col < heiganChamber[0].length && row >= 0 && col >= 0) {
                        List<Integer> numbers = new ArrayList<>();
                        numbers.add(i);
                        numbers.add(j);
                        affectedSpaces.add(numbers);

                    }

                }
            }
            List<Integer> playerCurrentPosition = Arrays.asList(playerRow, playerCol);

            if (affectedSpaces.contains(playerCurrentPosition)) {
                List<Integer> playerMovedUp = Arrays.asList(playerRow - 1, playerCol);
                if (affectedSpaces.contains(playerMovedUp) || playerRow - 1 < 0) {
                    List<Integer> playerMovedRight = Arrays.asList(playerRow, playerCol + 1);
                    if (affectedSpaces.contains(playerMovedRight) || playerCol + 1 >= heiganChamber[0].length ) {
                        List<Integer> playerMovedDown = Arrays.asList(playerRow + 1, playerCol);
                        if (affectedSpaces.contains(playerMovedDown) || playerRow + 1 >= heiganChamber.length ) {
                            List<Integer> playerMovedLeft = Arrays.asList(playerRow, playerCol - 1);
                            if (affectedSpaces.contains(playerMovedLeft) || playerCol - 1 < 0) {
                                if (spell.equals("Cloud")) {
                                    int damageFromCloud = 3500;
                                    playerHitPoints-= damageFromCloud;
                                    hasCloud = true;

                                } else if (spell.equals("Eruption")) {
                                    int damageFromEruption =6000;
                                    playerHitPoints -= damageFromEruption;
                                }
                                if (playerHitPoints <= 0) {
                                    System.out.println(String.format("Heigan: %.2f", heiganHitPoint));
                                    if (spell.equals("Eruption")) {
                                        System.out.println(String.format("Player: Killed by %s", spell));
                                    } else if (spell.equals("Cloud")) {
                                        System.out.println("Player: Killed by Plague Cloud");
                                    }
                                    System.out.println(String.format("Final position: %d, %d", playerRow, playerCol));
                                    return;
                                }

                            } else {
                                playerCol = playerCol - 1;
                            }
                        } else {
                            playerRow = playerRow + 1;
                        }
                    } else {
                        playerCol = playerCol + 1;
                    }
                } else {
                    playerRow = playerRow - 1;
                }
            }




        }

    }
}
