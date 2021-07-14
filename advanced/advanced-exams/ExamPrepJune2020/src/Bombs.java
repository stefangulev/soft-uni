import java.util.*;
import java.util.stream.Collectors;

public class Bombs {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] bombEffects = Arrays.stream(scan.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int[] bombCasings = Arrays.stream(scan.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> effectQueue = new ArrayDeque<>();

        Arrays.stream(bombEffects).forEach(effectQueue::offer);


        ArrayDeque<Integer> casingStack = new ArrayDeque<>();

        Arrays.stream(bombCasings).forEach(casingStack::push);

        List<String> bombNames = Arrays.asList("Datura Bombs","Cherry Bombs","Smoke Decoy Bombs");
        List<Integer> specialValues = Arrays.asList(40, 60, 120);

        Map<String, Integer> pouch = new TreeMap<>();
        pouch.put("Datura Bombs", 0);
        pouch.put("Cherry Bombs", 0);
        pouch.put("Smoke Decoy Bombs", 0);

        int daturaCount = 0;
        int cherryCount =0;
        int smokeCount = 0;
        boolean pouchFull = false;
        while (!effectQueue.isEmpty() && !casingStack.isEmpty()) {

            if (smokeCount >= 3 && cherryCount >= 3 && daturaCount >= 3) {
                pouchFull = true;
                break;
            }

            int sum = effectQueue.peek() + casingStack.peek();

            if (specialValues.contains(sum)) {
                effectQueue.poll();
                casingStack.pop();
                String bombType = "";
                switch (sum) {
                    case 40:
                        bombType = "Datura Bombs";
                        daturaCount++;
                        break;
                    case 60:
                        bombType = "Cherry Bombs";
                        cherryCount++;
                        break;
                    case 120:
                        bombType = "Smoke Decoy Bombs";
                        smokeCount++;
                        break;
                }
                pouch.put(bombType, pouch.get(bombType) + 1);

            } else {
                casingStack.push(casingStack.pop() - 5);
            }
        }


        if (!pouchFull) {
            System.out.println("You don't have enough materials to fill the bomb pouch.");
        } else {
            System.out.println("Bene! You have successfully filled the bomb pouch!");
        }

        String effectsOuts = "Bomb Effects: " + (effectQueue.isEmpty() ? "empty" :
        effectQueue.stream().map(String::valueOf).collect(Collectors.joining(", ")));
        System.out.println(effectsOuts);

        String casingOuts = "Bomb Casings: " + (casingStack.isEmpty() ? "empty" :
                casingStack.stream().map(String::valueOf).collect(Collectors.joining(", ")));
        System.out.println(casingOuts);


        pouch.entrySet().stream().forEach(e -> System.out.println(String.format("%s: %d", e.getKey(), e.getValue())));








    }
}
