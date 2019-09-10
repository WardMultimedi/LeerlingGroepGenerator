package be.multimedi.StudGroupGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleTool {
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

   public LocalDate askUserInputDate(String question) {
      LocalDate ld = null;
      do {
         System.out.print(question);
         String date = keyboard.nextLine();
         try {
            ld = LocalDate.parse(date);
         } catch (DateTimeParseException dtpe) {
            System.out.println("Error: " + dtpe.getMessage());
            System.out.println("Expected format: YYYY-MM-DD");
         }
      } while (ld == null);
      return ld;
   }

   public LocalDate askUserInputDateBefore(String question, LocalDate maximumDate) {
      LocalDate ld = askUserInputDate(question);
      while (ld.isAfter(maximumDate) || ld.isEqual(maximumDate)) {
         System.out.println("Error: Date must be before " + maximumDate);
         ld = askUserInputDate(question);
      }
      return ld;
   }

   public LocalDate askUserInputDateBetween(String question, LocalDate minimumDate, LocalDate maximumDate) {
      LocalDate ld = askUserInputDate(question);
      while (ld.isBefore(minimumDate) || ld.isEqual(minimumDate) ||
              ld.isAfter(maximumDate) || ld.isEqual(maximumDate)) {
         System.out.println("Error: Date must be between " + minimumDate + " and " + maximumDate);
         ld = askUserInputDate(question);
      }
      return ld;
   }
}
