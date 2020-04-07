package entities;

public class ParkingLots {

    private String lotName;
    private int numberOfCars;

    public ParkingLots() {
    }

    public ParkingLots(String lotName, int numberOfCars) {
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
