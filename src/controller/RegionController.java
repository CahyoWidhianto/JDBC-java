package controller;

import java.util.List;

import controller.iController.IControllerRegion;
import daos.RegionDAO;
import daos.idoas.IRegionDao;
import models.Region;
import tools.DBConnection;

public class RegionController implements IControllerRegion {

    private DBConnection dbConnection = new DBConnection();
    private IRegionDao iRegionDao = new RegionDAO(dbConnection.getConnection());

    @Override
    public List<Region> getAll() {
        List<Region> regions = iRegionDao.getAll();
        if (regions == null) {
            System.out.println("Data Masih Kosong");
            return null;
        } else {
            return regions;
        }
    }

    @Override
    public void create(Region region) {
        try {
            String name = region.getName();
            if (name.equalsIgnoreCase("")) {
                System.out.println("Inputan Nama Tidak Boleh Kosong");
            } else {
                if (iRegionDao.create(region)) {
                    System.out.println("Data Tersimpan");
                } else {
                    System.out.println("Data Gagal Disimpan");
                }
            }
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("eror : " + e.getMessage());
        }
    }

    @Override
    public Region byId(int id) {
        Region region = iRegionDao.byId(id);
        if (region == null) {
            System.out.println("Data Region Tidak Ditemukan");
        } else {
            System.out.println("+----+-----------------------------+--------+");
            System.out.println("| ID | Nama Region                 | Count  |");
            System.out.println("+----+-----------------------------+--------+");
            System.out.printf("| %2s | %-27s | %6d |\n", region.getId(),region.getName(),region.getCount());
            System.out.println("+----+-----------------------------+--------+");
        }
        return region;
    }

    @Override
    public void update(Region region, int id) {
        try {
            Region existingRegion = byId(id);
            existingRegion.setName(region.getName());
            existingRegion.setCount(region.getCount());
            iRegionDao.update(existingRegion, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Region delete(int id) {
        byId(id);
        Region region = iRegionDao.delete(id);
        return region;
    }

    @Override
    public List<Region> searchNameByCharacter(String key) {
        return iRegionDao.searchNameByCharacter(key);
    }

    

}
