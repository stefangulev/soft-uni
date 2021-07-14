package stack;

public class Main {

    public static void main(String[] args) {
        StackOfStrings stack = new StackOfStrings();


        stack.push("levski");
        stack.push("cska");
        stack.push("botev");

        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.isEmpty());
    }
}
