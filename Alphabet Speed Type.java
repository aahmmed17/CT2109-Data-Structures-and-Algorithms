import java.util.Scanner;  // Import the Scanner class

class Main {

    
      public static void main(String[] args) {    

        Scanner s = new Scanner(System.in);

        System.out.println("Type the alphabet in order (hit enter between letters)");
        System.out.println("Forwards or Backwards (f/b)?");

        char[] alphabet = new char[26]; // Initialising the alphabet character array
        
        char fORb = s.nextLine().toLowerCase().charAt(0);

        if (fORb == 'f') //Taking forwards condition
        { 
          System.out.println("Begin!");

          for(int i=0; i<alphabet.length; i++) // Populating alphabet character array from a - z
          {
            alphabet[i] = (char)('a'+i);
          }
        
        }    
             else if (fORb == 'b')//Taking backwards condition
        { 
          
          System.out.println("Begin!");

          for(int i=0; i<26; i++)
          {  
            alphabet[i] = (char)('z'-i);// Populating alphabet character array from z - a
          }
        }
         else if(fORb!='b' || fORb!='f')
         {
          System.out.println("Invalid selection, please try again.");
         }


         while (fORb == 'f' || fORb == 'b') {
          long startTime = System.currentTimeMillis(); // Start timing
      
          for (int i = 0; i < 26; i++) {
              char entry = s.nextLine().toLowerCase().charAt(0); // Get user input for every instance of for loop
              
            if (entry == alphabet[i]) { //If entry matches correct array index 
                if (i < 25) {
                      System.out.println("[Correct! Now type: " + alphabet[i+1] + "]"); //next character
                } 
                else { //condition of the end of the array
                    long stopTime = System.currentTimeMillis(); // Stop timing
                    System.out.println("[Complete! Elapsed time: " + ((stopTime - startTime)/1000) + " seconds]"); //Calculate Time Elapsed
                    s.close();
                    return; // Exit the loop and program when complete
                  }
              } else {//condition for incorrect input
                  System.out.println("[Incorrect! Type " + alphabet[i] + "]");
                  i--; // Retry the same letter if incorrect
              }
          }
      }

    }
 
}  
           


      
    
  
 




  

