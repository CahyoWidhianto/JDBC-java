package View;

import daos.RegionDAO;
import daos.idoas.IRegionDao;
import models.Region;
import tools.DBConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import controller.RegionController;

public class RegionView {

  static void menu() {
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader input = new BufferedReader(inputStreamReader);

    try {
      int pilihan;
      do {
        System.out.println("+=======================================+");
        System.out.println("|------------ Menu Region --------------|");
        System.out.println("|=======================================+");
        System.out.println("| 1. Insert Data Region                 |");
        System.out.println("| 2. Show Data Region                   |");
        System.out.println("| 3. Get by Id Region                   |");
        System.out.println("| 4. Edit Data Region                   |");
        System.out.println("| 5. Delete Data Region                 |");
        System.out.println("| 6. Cari Data Berdasakan Nama Region   |");
        System.out.println("| 7. Cari Data Berdasakan Value Region  |");
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
            getByid();
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
    }
  }

  static void insert() {
    RegionController regionController = new RegionController();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      System.out.println("Masukan Id Region : ");
      String idInput = reader.readLine();

      if (idInput.matches("\\d+")) {
        int id = Integer.parseInt(idInput);

        System.out.println("Masukan Nama Region : ");
        String name = reader.readLine().trim();

        System.out.println("Masukan Count Region : ");
        String countInput = reader.readLine();
        if (countInput.matches("\\d+")) {
          int count = Integer.parseInt(countInput);
          Region region = new Region(id, name, count);
          regionController.create(region);
        } else {
          System.out.println("Count yang di masukan harus bilangan bulat!! ");
        }
      } else {
        System.out.println("Id yang dimasukan harus bilangan bulat!!");
      }
    } catch (Exception e) {
      // e.printStackTrace();
      System.out.println("eror : " + e.getMessage());
    }

  }

  static void getAll() {
    RegionController regionController = new RegionController();
    System.out.println("+----+-----------------------------+--------+");
    System.out.println("| ID | Nama Region                 | Count  |");
    System.out.println("+----+-----------------------------+--------+");
    for (Region region : regionController.getAll()) {
      System.out.printf("| %2s | %-27s | %6d |\n", region.getId(), region.getName(), region.getCount());
    }
    System.out.println("+----+-----------------------------+--------+");
  }

  static void getByid() {
    RegionController regionController = new RegionController();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      System.out.println("Masukan Id Region : ");
      String idInput = reader.readLine();

      if (idInput.matches("\\d+")) {
        int id = Integer.parseInt(idInput);
        regionController.byId(id);
      } else {
        System.out.println("Id yang dimasukan harus bilangan bulat!!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static void update() {
    RegionController regionController = new RegionController();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      System.out.println("Masukan id Region : ");
      String idInput = reader.readLine();
      if (idInput.matches("\\d+")) {
        int id = Integer.parseInt(idInput);
        System.out.println("Masukan Nama Region : ");
        String name = reader.readLine().trim();
        if (name.isEmpty()) {
          System.out.println("Inputan Nama Tidak Boleh Kosong");
        } else {
          System.out.println("Masukan Count Region : ");
          String inputCount = reader.readLine();
          if (inputCount.matches("\\d+")) {
            int count = Integer.parseInt(inputCount);
            Region region = new Region(name, count);
            regionController.update(region, id);
          } else {
            System.out.println("Count yang dimasukan harus bilangan bulat!! ");
          }
        }
      } else {
        System.out.println("Id yang dimasukan harus bilangan bulat!!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  static void delete() {
    RegionController regionController = new RegionController();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      System.out.println("Masukan Id Region : ");
      String idInput = reader.readLine();

      if (idInput.matches("\\d+")) {
        int id = Integer.parseInt(idInput);
        regionController.delete(id);
      } else {
        System.out.println("Id yang dimasukan harus bilangan bulat!!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static void search() {
    DBConnection dbConnection = new DBConnection();
    IRegionDao iRegionDao = new RegionDAO(dbConnection.getConnection());
    try {
      InputStreamReader inputStreamReader = new InputStreamReader(System.in);
      BufferedReader input = new BufferedReader(inputStreamReader);
      System.out.println("Masukan nama yang ingin dicari :");
      String value = input.readLine().trim();
      Region region = iRegionDao.search(value);
      System.out.println("+----+-----------------------------+--------+");
      System.out.println("| ID | Nama Region                 | Count  |");
      System.out.println("+----+-----------------------------+--------+");
      System.out.printf("| %2s | %-27s | %6d |\n", region.getId(), region.getName(), region.getCount());
      System.out.println("+----+-----------------------------+--------+");

    } catch (Exception e) {
      e.getMessage();
    }

  }

  static void searchNameByCharacter() {
    RegionController regionController = new RegionController();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      System.out.println("Masukan Kata Kunci Region : ");
      String key = reader.readLine().trim();
      System.out.println("+----+-----------------------------+--------+");
      System.out.println("| ID | Nama Region                 | Count  |");
      System.out.println("+----+-----------------------------+--------+");
      for (Region region : regionController.searchNameByCharacter(key)) {
        System.out.printf("| %2s | %-27s | %6d |\n", region.getId(), region.getName(), region.getCount());
      }
      System.out.println("+----+-----------------------------+--------+");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error : " + e.getMessage());
    }
  }

}

// Create Project VSCode = ctrl + shift + p
