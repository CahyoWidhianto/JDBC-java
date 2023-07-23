package View;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BaseMenu {
    private static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    private static BufferedReader input = new BufferedReader(inputStreamReader);

    public static void menu() {
        try {
            int pilihan;
            do {
                System.out.println("+=================================+");
                System.out.println("|------------- MENU --------------|");
                System.out.println("+=================================+");
                System.out.println("| 1.Region                        |");
                System.out.println("| 2.Country                       |");
                System.out.println("| 0.Exit                          |");
                System.out.println("+=================================+");
                System.out.print("Pilih menu :");
                pilihan = Integer.parseInt(input.readLine().trim());
                switch (pilihan) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        RegionView.menu();
                        break;
                    case 2:
                        CountryView.menu();
                        break;
                    default:
                        break;
                }
            } while (pilihan != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
