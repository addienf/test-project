package util;

import java.util.Scanner;

public class InputUtil {

    private static Scanner sc = new Scanner(System.in);

    public static String inputString (String info){
        System.out.print(info + " : ");
        return sc.nextLine();
    }

    public static Integer inputInteger (String info){
        System.out.print(info + " : ");
        return sc.nextInt();
    }
}
