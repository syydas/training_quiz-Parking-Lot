package repositories;

import entities.ParkingLot;

import java.util.List;

public interface ParkingLotRepositoryI {
    void init(List<ParkingLot> parkingLots);
    String park (String carNumber);
}
