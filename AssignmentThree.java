public class AssignmentThree {
    static int stepSize = 50_000; // ✅ Only declare once

    static long startTime;
    static long endTime;

    static long operationCount1 = 0;
    static long operationCount2 = 0;
    static long operationCount3 = 0;
    static long operationCount4 = 0;

    static long reverseTime = 0;
    static long elementByTime = 0;
    static long staqueTime = 0;
    static long recursiveTime = 0;

    public static void main(String[] args) {
        int doublePalindrome = 0;

        startTime = System.currentTimeMillis();
        System.out.println("Input Size,Reverse & Compare,Element-by-Element,Stack & Queue,Recursive Check");

        for (int i = 0; i < 1_000_000; i++) {
            String myInput = String.valueOf(i);
            String binaryEquivalent = decToBinary(myInput);

            //Reset operation counters for each step
            operationCount1= 0;
             operationCount2 =0;
            operationCount3 = 0;
             operationCount4 = 0;

            // Reverse & Compare
            boolean  isDecimalPalindrome1 = reverseCheck(myInput);
            boolean isBinaryPalindrome1 =  reverseCheck(binaryEquivalent);

            // Element-by-Element Check
            boolean isDecimalPalindrome2 =elementByCheck(myInput);
             boolean isBinaryPalindrome2 = elementByCheck(binaryEquivalent);

            //  Stack & Queue Check
            boolean isDecimalPalindrome3 = staqueCheck(myInput);
            boolean isBinaryPalindrome3= staqueCheck(binaryEquivalent);

            //  Recursive Check
            boolean isDecimalPalindrome4 =  recursiveCheck(myInput);
            boolean isBinaryPalindrome4 =recursiveCheck(binaryEquivalent);

            // Double Count Check
            if ((isDecimalPalindrome1 && isBinaryPalindrome1) ||
                (isDecimalPalindrome2 && isBinaryPalindrome2) ||
                (isDecimalPalindrome3 && isBinaryPalindrome3) ||
                (isDecimalPalindrome4 && isBinaryPalindrome4)) {
                doublePalindrome++;
            }

            // Counting at each step size
            if (i % stepSize == 0) {
                System.out.println(i + "," + operationCount1 + "," + operationCount2 + "," + operationCount3 + "," + operationCount4);
            }
        }

        endTime = System.currentTimeMillis();
        long totalRunTime = endTime - startTime;

        // 
        System.out.println ("\nTotal Execution Time: " + totalRunTime + " ms");
        System.out.println("Number of Double-Base Palindromes: " + doublePalindrome);
    }

    // Reverse & Compare
    public static boolean reverseCheck(String myInput) 
    {
        operationCount1++; // Calling method

        StringBuilder reverse = new StringBuilder(myInput);
        operationCount1 += myInput.length(); // Copying characters
        reverse.reverse();
        operationCount1 += myInput.length() / 2; // Swapping characters during reversal

         operationCount1 += myInput.length(); // Comparing string with reverse of itself
        return myInput.equals(reverse.toString());
    }

    // Element-by-Element Check 
    public static boolean elementByCheck(String myInput) 
    {
        operationCount2++; // Calling method

        for (int i = 0; i < myInput.length() / 2; i++) { //Looping through only half of the string 
                operationCount2 += 3; // Loop + Compare + Character access
                if (myInput.charAt(i) != myInput.charAt(myInput.length() - 1 - i))
                 { //Comparing string for nth char with string length - nth char
                return false; 
                }
        }
        return true;
    }

    // Stack & Queue Check 
    public static boolean staqueCheck(String myInput) {
        operationCount3++; // Method call count

        ArrayQueue queue = new ArrayQueue(myInput.length());
        ArrayStack stack = new ArrayStack(myInput.length());

        operationCount3 += 2; // Creation of stack and queue

        for (char c : myInput.toCharArray())
         { //Iterating through String made to Char Array
            queue.enqueue(c);
            stack.push(c);
            operationCount3 += 3; // Loop instance + enqueue + push
            }

        while (!queue.isEmpty()) 
        {
            operationCount3 += 3; // Dequeue + pop + comparison
                if (!queue.dequeue().equals(stack.pop())) {
                return false;
            }
            }
        return true;
    }

    // Recursive Check
    public static boolean recursiveCheck(String myInput) 
        {
        operationCount4++; // Method call
         return myInput.equals(reverse(myInput)); // Compare original with reversed
    }

    // String reversal via recursion
    public static String reverse(String myInput) {
        operationCount4++; // Method call count

        if (myInput.length() <= 1) 
        {
            return myInput; //Returning string for case of one char string
        }
            return myInput.charAt(myInput.length() - 1) + reverse(myInput.substring(0, myInput.length() - 1));
    }

    //Decimal to Binary Conversion
    public static String decToBinary(String myInput)
     {
            int decimal = Integer.parseInt(myInput); //Storing a decimal integer of the String Input to be converted in Binary
        return Integer.toBinaryString(decimal);
        }
}
