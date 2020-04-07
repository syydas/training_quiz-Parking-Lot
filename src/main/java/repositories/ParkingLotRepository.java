package repositories;

import entities.ParkingLot;
import entities.ParkingLots;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotRepository implements ParkingLotRepositoryI {
    @Override
    public void init(List<ParkingLots> parkingLots) {
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            String sql = "TRUNCATE TABLE parking_lot";
            conn = JDBCUtil.connectToDB();
            ptmt = conn.prepareStatement(sql);
            ptmt.execute();
            initParkingLots(parkingLots);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(conn, ptmt);
        }
    }

    @Override
    public String park(String carNumber) {
        List<String> lotNames = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.connectToDB();
            String sql = "SELECT lot_name FROM parking_lot";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                lotNames.add(rs.getString("lot_name"));
            }
            return parkCar(lotNames, carNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(conn, stmt, rs);
        }
        return "";
    }

    public void initParkingLots(List<ParkingLots> parkingLots) {
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            for (ParkingLots lot : parkingLots) {
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
                        "car_num VARCHAR(101)" +
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

    private String parkCar(List<String> lotNames, String carNumber) {
        Connection conn = null;
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        ParkingLot lot = new ParkingLot();
        try {
            conn = JDBCUtil.connectToDB();
            for (String lotName : lotNames) {
                String sql = "SELECT id FROM " + lotName + " WHERE car_num IS NULL LIMIT 1";
                ptmt = conn.prepareStatement(sql);
                rs = ptmt.executeQuery();
                while (rs.next()) {
                    lot.setId(rs.getInt("id"));
                }
                if (lot.getId() != 0) {
                    lot.setCarNum(carNumber);
                    sql = "UPDATE " + lotName + " SET car_num = ?";
                    ptmt = conn.prepareStatement(sql);
                    ptmt.setString(1, lot.getCarNum());
                    ptmt.execute();
                    return lotName + "," + lot;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
