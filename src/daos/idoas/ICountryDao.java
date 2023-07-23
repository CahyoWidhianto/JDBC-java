package daos.idoas;

import java.util.List;

import models.Country;

public interface ICountryDao {
    public List<Country> getAll();

    public boolean create(Country country);

    Country getById(String id);

    void update(Country country, String id);

    Country delete(String id);

    Country search(String value);

    public List<Country> searchNameByCharacter(String key); 
    

}
