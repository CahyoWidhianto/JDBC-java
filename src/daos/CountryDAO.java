package daos;

import java.sql.Connection;
// import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
// import java.util.List;
import java.util.List;

import daos.idoas.ICountryDao;
import models.Country;

public class CountryDAO implements ICountryDao {

    private Connection connection;

    public CountryDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Country> getAll() {
        ArrayList<Country> countries = new ArrayList<Country>();
        try {
            String query = "SELECT * FROM country";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Country country = new Country(rs.getString("id"), rs.getString("name"), rs.getInt("region"));
                countries.add(country);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return countries;
    }

    @Override
    public boolean create(Country country) {
        String query = "INSERT INTO COUNTRY(id, name, region) VALUES(?, ?, ?)";
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, country.getId());
            preparedStatement.setString(2, country.getName());
            preparedStatement.setInt(3, country.getRegion());
            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Eror : " + e.getMessage());
        }
        return result;
    }

    @Override
    public Country getById(String id) {
        Country country = null;
        ResultSet resultSet;
        String query = "SELECT * FROM country WHERE id=? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                country = new Country(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("region"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
        return country;
    }

    @Override
    public void update(Country country, String id) {
        String query = "UPDATE country SET name=?, region=? WHERE id=? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, country.getName());
            preparedStatement.setInt(2, country.getRegion());
            preparedStatement.setString(3, country.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Country delete(String id) {
        String query = "DELETE FROM country WHERE id=? ";
        Country country = new Country();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return country;
    }

    @Override
    public Country search(String value) {
        Country country = new Country();
        ResultSet rs = null;
        String query = "SELECT * FROM country WHERE name=? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                country.setId(rs.getString("id"));
                country.setName(rs.getString("name"));
                country.setRegion(rs.getInt("region"));
            }
        } catch (Exception e) {
           e.printStackTrace();
           System.out.println("Error: " + e.getMessage());
        }
        return country;
    }

    @Override
    public List<Country> searchNameByCharacter(String key) {
        List<Country> countries = new ArrayList<>();
        Country country = new Country();
        String query = "SELECT * FROM COUNTRY WHERE LOWER(name) LIKE LOWER(?) ORDER BY id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + key + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                country = new Country(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getInt(3)
                );
                countries.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return countries;
    }

    

}
