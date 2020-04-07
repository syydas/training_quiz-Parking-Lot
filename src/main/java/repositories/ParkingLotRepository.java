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
            String sql = "TRUNCATE TABLE parking_lot";
            conn = JDBCUtil.connectToDB();
            ptmt = conn.prepareStatement(sql);
            ptmt.executeUpdate();
            initParkingLots(parkingLots);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(conn, ptmt);
        }
    }

    public void initParkingLots(List<ParkingLot> parkingLots) {
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            for (ParkingLot lot : parkingLots) {
                String lotName = lot.getLotName();
                int lotNum = lot.getNumberOfCars();
                String sql = "INSERT INTO parking_lot (lot_name,lot_num) values(?,?)";
                conn = JDBCUtil.connectToDB();
                ptmt = conn.prepareStatement(sql);
                ptmt.setString(1, lotName);
                ptmt.setInt(2, lotNum);
                ptmt.execute();

                sql = "DROP TABLE IF EXISTS " + lotName;
                ptmt = conn.prepareStatement(sql);
                ptmt.execute();
                sql = "CREATE TABLE " + lotName + "(" +
                        "id INT PRIMARY KEY AUTO_INCREMENT," +
                        "car_num VARCHAR(" + lotNum + ")" +
                        ")";
                ptmt = conn.prepareStatement(sql);
                ptmt.execute();

                for (int i = 0; i < lotNum; i++) {
                    sql = "INSERT INTO " + lotName + " values()";
                    ptmt = conn.prepareStatement(sql);
                    ptmt.execute();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(conn, ptmt);
        }
    }
}
