package entities;

public class ParkingLot {
    private int id;
    private String carNum;

    public ParkingLot() {
    }

    public ParkingLot(int id, String carNum) {
        this.id = id;
        this.carNum = carNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    @Override
    public String toString() {
        return id + "," + carNum ;
    }
}
