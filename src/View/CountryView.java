package View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import controller.CountryController;
import daos.CountryDAO;
import daos.idoas.ICountryDao;
import models.Country;
import tools.DBConnection;

public class CountryView {
    static void menu() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(inputStreamReader);
        try {
            int pilihan;
            do {
                System.out.println("+=======================================+");
                System.out.println("|------------ Menu Country -------------|");
                System.out.println("|=======================================+");
                System.out.println("| 1. Insert Data Country                |");
                System.out.println("| 2. Show Data Country                  |");
                System.out.println("| 3. Get by Id Country                  |");
                System.out.println("| 4. Edit Data Country                  |");
                System.out.println("| 5. Delete Data Country                |");
                System.out.println("| 6. Cari Data Berdasakan Nama country  |");
                System.out.println("| 7. Cari Data Berdasakan value country |");
                System.out.println("| 99. Kembali ke Menu utama             |");
                System.out.println("| 0. Keluar                             |");
                System.out.println("+=======================================+");
                System.out.println("");
                System.out.print("PILIHAN> ");
                pilihan = Integer.parseInt(input.readLine());
                switch (pilihan) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        insert();
                        break;
                    case 2:
                        getAll();
                        break;
                    case 3:
                        getById();
                        break;
                    case 4:
                        update();
                        break;
                    case 5:
                        delete();
                        break;
                    case 6:
                        search();
                        break;
                    case 7:
                        searchNameByCharacter();
                        break;
                    case 99:
                        BaseMenu.menu();
                        break;
                    default:
                        System.out.println("Pilihan salah!");
                }
            } while (pilihan != 0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
    }

    static void getAll() throws SQLException {
        CountryController countryController = new CountryController();
        List<Country> countries = countryController.getAll();

        if (countries.size() > 0) {
            System.out.println("+----+-----------------------------+--------+");
            System.out.println("| ID | Nama Country                | Region |");
            System.out.println("+----+-----------------------------+--------+");

            for (Country country : countries) {
                System.out.printf("| %2s | %-27s | %6d |\n", country.getId(), country.getName(), country.getRegion());
            }

            System.out.println("+----+-----------------------------+--------+");
        } else {
            System.out.println("Data kosong");
        }
    }

    static void insert() {
        CountryController countryController = new CountryController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Masukan Id Country : ");
            String id = reader.readLine();

            System.out.println("Masukan Nama Country : ");
            String name = reader.readLine().trim();
            System.out.println("Masukan Region : ");
            String regionInput = reader.readLine();
            if (regionInput.matches("\\d+")) {
                int region = Integer.parseInt(regionInput);
                Country country = new Country(id, name, region);
                countryController.create(country);
            }

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    static void getById() {
        CountryController countryController = new CountryController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Masukan Id Region : ");
            String id = reader.readLine();
            countryController.getById(id);

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    static void update() {
        CountryController countryController = new CountryController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("masukan Id Country : ");
            String id = reader.readLine();

            System.out.println("Masukan Nama Country : ");
            String name = reader.readLine().trim();

            System.out.println("Masukan Region Contry : ");
            String regionInputan = reader.readLine();
            if (regionInputan.matches("\\d+")) {
                int region = Integer.parseInt(regionInputan);
                Country country = new Country(id, name, region);
                countryController.update(country, id);
            } else {
                System.out.println("Region yang dimasukan harus bilangan bulat!!");
            }

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    static void delete() {
        CountryController countryController = new CountryController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Masukan Id Region : ");
            String id = reader.readLine();
            countryController.delete(id);

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    static void search() {
        DBConnection dbConnection = new DBConnection();
        ICountryDao iCountryDao = new CountryDAO(dbConnection.getConnection());
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader input = new BufferedReader(inputStreamReader);
            System.out.println("Masukan nama yang ingin dicari :");
            String value = input.readLine().trim();
            Country country = iCountryDao.search(value);
            System.out.println("+----+-----------------------------+--------+");
            System.out.println("| ID | Nama Region                 | Count  |");
            System.out.println("+----+-----------------------------+--------+");
            System.out.printf("| %2s | %-27s | %6d |\n", country.getId(), country.getName(), country.getRegion());
            System.out.println("+----+-----------------------------+--------+");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
    }

    static void searchNameByCharacter() {
        CountryController countryController = new CountryController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Masukan Kata Kunci Country : ");
            String key = reader.readLine().trim();
            System.out.println("+----+-----------------------------+--------+");
            System.out.println("| ID | Nama Country                | Count  |");
            System.out.println("+----+-----------------------------+--------+");
            for(Country country : countryController.searchNameByCharacter(key)){
                System.out.printf("| %2s | %-27s | %6d |\n", country.getId(), country.getName(), country.getRegion());
            }
            System.out.println("+----+-----------------------------+--------+");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }

    }
}
