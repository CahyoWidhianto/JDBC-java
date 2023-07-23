package controller.iController;

import java.util.List;

import models.Region;

public interface IControllerRegion {

    public List<Region> getAll();
  
    public Region byId(int id);
  
    public Region delete(int id);
  
    public void update(Region region, int id);

    public void create(Region region);

    public List<Region> searchNameByCharacter(String key);
  
    // Region search(String value);
}
