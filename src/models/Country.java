package models;

public class Country {
    String id;
    String name;
    int region;
    
    public Country() {
    }

    public Country(String id, String name, int region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }   
}