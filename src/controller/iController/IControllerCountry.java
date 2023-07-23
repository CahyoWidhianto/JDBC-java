package controller.iController;

import java.util.List;

import models.Country;

public interface IControllerCountry {
    public List<Country> getAll();
    public Country getById(String id);
    public Country delete(String id);
    public void update(Country country, String id);
    public void create(Country country);
    public List<Country> searchNameByCharacter(String key);
}
