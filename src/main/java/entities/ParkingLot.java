package entities;

public class ParkingLot {

    private String lotNumber;
    private int numberOfCars;

    public ParkingLot() {
    }

    public ParkingLot(String lotNumber, int numberOfCars) {
        this.lotNumber = lotNumber;
        this.numberOfCars = numberOfCars;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }

    public void setNumberOfCars(int numberOfCars) {
        this.numberOfCars = numberOfCars;
    }
}
