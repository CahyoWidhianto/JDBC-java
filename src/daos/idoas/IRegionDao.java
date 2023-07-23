package daos.idoas;

import java.util.List;
import models.Region;

public interface IRegionDao {
  public List<Region> getAll();

  public boolean create(Region region);

  Region byId(int id);

  Region delete(int id);

  void update(Region region, int id);

  Region search(String value);

  public List<Region> searchNameByCharacter(String key);

  
}
