package Helpers;

import java.util.Scanner;

public class ConsoleHelper {
    public static void Print(String Message){

        System.out.println(Message);
    }
    public static void Print(String Message,ConsoleForeground ForeColor,ConsoleBackground BackgroundColor){
        System.out.print(ForeColor.getValue());
        System.out.print(BackgroundColor.getValue());

        System.out.println(Message);

        System.out.print(ConsoleForeground.RESET.getValue());
        System.out.print(ConsoleBackground.RESET.getValue());
    }
    public static void Print(String Message,ConsoleForeground ForeColor){
        System.out.print(ForeColor.getValue());

        System.out.println(Message);


        System.out.print(ConsoleForeground.RESET.getValue());

    }
    public static void Print(String Message,ConsoleBackground BackgroundColor){
        System.out.print(BackgroundColor.getValue());

        System.out.println(Message);

        System.out.print(ConsoleForeground.RESET.getValue());
    }

    public static int ReadInt(String Message){
        boolean erreur=false;
        do {
            erreur=false;
            try {
                Print(Message);
                Scanner scanner = new Scanner(System.in);
                return scanner.nextInt();
            } catch (Exception ex) {
                Print("Value entered is not valid.",ConsoleForeground.RED);
                erreur=true;
            }
        }while (erreur);
        return -1;
    }
    public static String ReadString(String Message){
        System.out.print(Message);
        Scanner scanner=new Scanner(System.in);
        return scanner.nextLine();
    }


    public static String padLeft(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append(' ');
        }
        sb.append(inputString);

        return sb.toString();
    }
    public static String padRight(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();

        sb.append(inputString);
        while (sb.length() < length ) {
            sb.append(' ');
        }

        return sb.toString();
    }
}
