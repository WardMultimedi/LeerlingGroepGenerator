package be.multimedi.StudGroupGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class ConsoleTool {
   private static Scanner keyboard = new Scanner(System.in);

   public void askPressEnterToContinue() {
      System.out.print("Press enter to continue.");
      keyboard.nextLine();
   }

   public String askUserInputString(String question, int minimumCharacters) {
      if (minimumCharacters <= 0) {
         System.out.print(question);
         return keyboard.nextLine();
      } else {
         String input = null;
         do {
            System.out.print(question);
            input = keyboard.nextLine();
            if (input.length() < minimumCharacters)
               System.out.format("Error: Input must be at least %d character%s.\n", minimumCharacters, minimumCharacters > 1 ? "s" : "");
         } while (input.length() < minimumCharacters);
         return input;
      }
   }

   public int askUserInputInteger(String question) {
      int input = 0;
      try {
         System.out.print(question);
         input = keyboard.nextInt();
      } catch (InputMismatchException ime) {
         System.out.println("Error: input is not a number");
      } finally {
         keyboard.nextLine();
      }
      return input;
   }

   public int askUserInputInteger(String question, int minimum) {
      int input = 0;
      do {
         input = askUserInputInteger(question);
         if (input < minimum) {
            System.out.println("Error: input must be higher or equal to " + minimum);
         }
      } while (input < minimum);
      return input;
   }

   public int askUserInputInteger(String question, int minimum, int maximum) {
      int input = 0;
      do {
         input = askUserInputInteger(question);
         if (input < minimum) {
            System.out.println("Error: input must be higher or equal to " + minimum);
         } else if (input > maximum) {
            System.out.println("Error: input must be lower or equal to " + maximum);
         }
      } while (input < minimum || input > maximum);
      return input;
   }
}
