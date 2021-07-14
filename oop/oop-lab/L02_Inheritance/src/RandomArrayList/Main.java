package RandomArrayList;

public class Main {
    public static void main(String[] args) {
        RandomArrayList<Integer> list = new RandomArrayList<Integer>();

        list.add(1);
        list.add(2);
        list.add(13);

        System.out.println(list.getRandomElement());


    }
}
