package repositories;

import entities.ParkingLot;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ParkingLotRepository implements ParkingLotRepositoryI {
    @Override
    public void init(List<ParkingLot> parkingLots) {
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            for (ParkingLot lot : parkingLots) {
                conn = JDBCUtil.connectToDB();
                String sql = "INSERT INTO parking_lot (lotname,lotnum) values(?,?)";
                ptmt = conn.prepareStatement(sql);
                ptmt.setString(1, lot.getLotName());
                ptmt.setInt(2,lot.getNumberOfCars());
                ptmt.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(conn, ptmt);
        }
    }
}
