package apps.algos.stack;

import utils.Stack;

public class StackApp {
    public static void main(String[] args){
        Stack<String> s = new Stack<String>(5);
        s.push("10");
        s.push("20");
        s.push("30");

        String it = s.pop();
        System.out.println(it);
    }



}
