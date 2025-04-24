import java.util.Scanner;

public class Infix_to_Postfix_Main {

    public static int precedence(char operator) {
        switch (operator) {
            case '^': return 3;
            case '*': case '/': return 2;
            case '+': case '-': return 1;
            default: return -1;
        }
    }

    public static String InfixToPostfix(String infix) throws Exception {
        StringBuilder output = new StringBuilder();
        ArrayStack stack = new ArrayStack(10);
        char[] expArray = infix.toCharArray();

        if (infix.length() >= 3 && infix.length() <= 20) { // Fixed length check
            for (int i = 0; i < expArray.length; i++) {
                char symb = expArray[i];

                if (Character.isDigit(symb)) {
                    output.append(symb).append(" ");
                } 
                else if (symb == '+' || symb == '-' || symb == '*' || symb == '/' || symb == '^') {
                    while (!stack.isEmpty() && precedence(symb) <= precedence((char) stack.top())) {
                        output.append(stack.pop()).append(" ");
                    }
                    stack.push(symb);
                } 
                else if (symb == '(') {
                    stack.push(symb);
                } 
                else if (symb == ')') {
                    while (!stack.isEmpty() && (char) stack.top() != '(') {
                        output.append(stack.pop()).append(" ");
                    }
                    stack.pop(); // Remove '(' from stack
                }
            }
        }
        else {
            System.out.println("INVALID PARAMETERS PLEASE TRY AGAIN");
        }


        while (!stack.isEmpty()) {
            output.append(stack.pop()).append(" ");
        }
        return output.toString().trim();
    }

    public static double evaluatePostfix(String postfix) {
        ArrayStack stack = new ArrayStack(20);
        char[] expArray = postfix.toCharArray();

        for (char ch : expArray) {
            if (Character.isDigit(ch)) {
                stack.push((double) (ch - '0')); // Convert char to double and push
            } 
            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^') {
                double op2 = (double) stack.pop();
                double op1 = (double) stack.pop();

                double result = 0;
                switch (ch) {
                    case '+': result = op1 + op2; break;
                    case '-': result = op1 - op2; break;
                    case '*': result = op1 * op2; break;
                    case '/': result = op1 / op2; break;
                    case '^': result = Math.pow(op1, op2); break;
                }
                stack.push(result);
            }
        }
        return (double) stack.pop();
    }

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.print("Please enter infix expression: ");
        String userInput = s.nextLine();

        String postfix = InfixToPostfix(userInput);
        System.out.println("Postfix expression: " + postfix);

        double result = evaluatePostfix(postfix);
        System.out.println("Evaluation result: " + result);
    }
}























