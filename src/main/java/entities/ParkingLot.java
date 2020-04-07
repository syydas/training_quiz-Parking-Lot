package entities;

public class ParkingLot {

    private String lotName;
    private int numberOfCars;

    public ParkingLot() {
    }

    public ParkingLot(String lotName, int numberOfCars) {
        this.lotName = lotName;
        this.numberOfCars = numberOfCars;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }

    public void setNumberOfCars(int numberOfCars) {
        this.numberOfCars = numberOfCars;
    }
}
