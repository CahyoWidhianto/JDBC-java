package controller;

import java.util.List;

import controller.iController.IControllerCountry;
import daos.CountryDAO;
import daos.idoas.ICountryDao;
import models.Country;
import tools.DBConnection;

public class CountryController implements IControllerCountry {
    private DBConnection dbConnection = new DBConnection();
    private ICountryDao iCountryDao = new CountryDAO(dbConnection.getConnection());

    @Override
    public List<Country> getAll() {
        List<Country> countries = iCountryDao.getAll();
        if (countries == null) {
            System.out.println("Data Masih Kosong");
            return null;
        } else {
            return countries;
        }
    }

    @Override
    public Country getById(String id) {
        Country country = iCountryDao.getById(id);
        if (country == null) {
            System.out.println("Data Country Tidak ditemukan");
        } else {
            System.out.println("+----+-----------------------------+--------+");
            System.out.println("| ID | Nama Country                | Region |");
            System.out.println("+----+-----------------------------+--------+");
            System.out.printf("| %2s | %-27s | %6d |\n", country.getId(), country.getName(), country.getRegion());
            System.out.println("+----+-----------------------------+--------+");
        }
        return country;
    }

    @Override
    public Country delete(String id) {
        getById(id);
        Country country = iCountryDao.delete(id);
        return country;
    }

    @Override
    public void update(Country country, String id) {
        try {
            Country existingCountry = getById(id);
            String name = country.getName();
            if (name.equalsIgnoreCase("")) {
                System.out.println("Input Nama Tidak Boleh Kosong");
            } else {
                existingCountry.setName(country.getName());
                existingCountry.setRegion(country.getRegion());
                iCountryDao.update(existingCountry, id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
    }

    @Override
    public void create(Country country) {
        try {
            String name = country.getName();
            if (name.equalsIgnoreCase("")) {
                System.out.println("Inputan Nama Tidak Boleh Kosong");
            } else {
                if (iCountryDao.create(country)) {
                    System.out.println("Data Tersimpan");
                } else {
                    System.out.println("Data Gagal tersimpan");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
    }

    @Override
    public List<Country> searchNameByCharacter(String key) {
        return iCountryDao.searchNameByCharacter(key);
    }

    
}
