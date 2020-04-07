package repositories;

import entities.ParkingLots;

import java.util.List;

public interface ParkingLotRepositoryI {
    void init(List<ParkingLots> parkingLots);
    String park (String carNumber);
    String fetch (String ticket);
}
