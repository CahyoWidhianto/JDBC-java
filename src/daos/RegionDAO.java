package daos;

import daos.idoas.IRegionDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Region;

public class RegionDAO implements IRegionDao {

  private Connection connection;

  public RegionDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<Region> getAll() {
    List<Region> regions = new ArrayList<>();
    String query = "SELECT * FROM REGION";

    try {
      ResultSet resultset = connection.prepareStatement(query).executeQuery();
      while (resultset.next()) {
        Region region = new Region(
            resultset.getInt(1),
            resultset.getString(2),
            resultset.getInt(3));
        regions.add(region);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return regions;
  }

  @Override
  public boolean create(Region region) {
    boolean result = false;
    String query = "INSERT INTO REGION(id, name, count) VALUES(?, ?, ?)"; // Parameterized Query
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, region.getId());
      preparedStatement.setString(2, region.getName());
      preparedStatement.setInt(3, region.getCount());
      preparedStatement.executeUpdate();
      result = true;
    } catch (Exception e) {
      System.out.println("Error Message = " + e.getMessage());
    }
    return result;
  }

  @Override
  public Region byId(int id) {

    ResultSet result;
    String query = "SELECT * FROM region WHERE id=? ";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      result = preparedStatement.executeQuery();

      if (result.next()) {
        Region region = new Region();
        region.setId(result.getInt("id"));
        region.setName(result.getString("name"));
        region.setCount(result.getInt("count"));
        return region;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public Region delete(int id) {
    String query = "DELETE FROM region WHERE id=? ";
    Region region = new Region();
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return region;

  }

  @Override
  public void update(Region region, int id) {
    String query = "UPDATE region SET name=?, count=? WHERE id=?  ";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, region.getName());
      preparedStatement.setInt(2, region.getCount());
      preparedStatement.setInt(3, id);
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public Region search(String value) {
    Region region = new Region();
    ResultSet result = null;
    String query = "SELECT * FROM region WHERE name=? ";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, value);
      result = preparedStatement.executeQuery();

      while (result.next()) {
        region.setId(result.getInt("id"));
        region.setName(result.getString("name"));
        region.setCount(result.getInt("count"));
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return region;
  }
  @Override
  public List<Region> searchNameByCharacter(String key) {
    List<Region> regions = new ArrayList<>();
    Region region = new Region();
    String query = "SELECT * FROM REGION WHERE LOWER(name) LIKE LOWER(?) ORDER BY id";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, "%" + key + "%");
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        region = new Region(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getInt(3));
        regions.add(region);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error: " + e.getMessage());
    }
    return regions;
  }

}
