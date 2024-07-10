package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.MyConnection;
import com.model.Train;

public class TrainDao {

    public int addTrain(Train train) {
        String sql = "INSERT INTO TRAIN VALUES(?,?,?,?,?,?)";
        try (Connection con = MyConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, train.getTr_no());
            ps.setString(2, train.getTr_name());
            ps.setString(3, train.getFrom_stn());
            ps.setString(4, train.getTo_stn());
            ps.setLong(5, train.getSeats());
            ps.setDouble(6, train.getFare());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteTrainById(String trainNo) {
        String sql = "DELETE FROM TRAIN WHERE TR_NO=?";
        try (Connection con = MyConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, trainNo);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateTrain(Train train) {
        String sql = "UPDATE TRAIN SET TR_NAME=?, FROM_STN=?, TO_STN=?, SEATS=?, FARE=? WHERE TR_NO=?";
        try (Connection con = MyConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, train.getTr_name());
            ps.setString(2, train.getFrom_stn());
            ps.setString(3, train.getTo_stn());
            ps.setLong(4, train.getSeats());
            ps.setDouble(5, train.getFare());
            ps.setLong(6, train.getTr_no());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Train getTrainById(String trainNo) {
        String sql = "SELECT * FROM TRAIN WHERE TR_NO=?";
        try (Connection con = MyConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, trainNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Train train = new Train();
                    train.setTr_no(rs.getLong("tr_no"));
                    train.setTr_name(rs.getString("tr_name"));
                    train.setFrom_stn(rs.getString("from_stn"));
                    train.setTo_stn(rs.getString("to_stn"));
                    train.setSeats(rs.getInt("seats"));
                    train.setFare(rs.getDouble("fare"));
                    return train;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Train> getAllTrains() {
        List<Train> trains = new ArrayList<>();
        String sql = "SELECT * FROM TRAIN";
        try (Connection con = MyConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Train train = new Train();
                train.setTr_no(rs.getLong("tr_no"));
                train.setTr_name(rs.getString("tr_name"));
                train.setFrom_stn(rs.getString("from_stn"));
                train.setTo_stn(rs.getString("to_stn"));
                train.setSeats(rs.getInt("seats"));
                train.setFare(rs.getDouble("fare"));
                trains.add(train);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trains;
    }

    public List<Train> getTrainsBetweenStations(String fromStation, String toStation) {
        List<Train> trains = new ArrayList<>();
        String sql = "SELECT * FROM TRAIN WHERE UPPER(FROM_STN) LIKE UPPER(?) AND UPPER(TO_STN) LIKE UPPER(?)";
        try (Connection con = MyConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + fromStation + "%");
            ps.setString(2, "%" + toStation + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Train train = new Train();
                    train.setTr_no(rs.getLong("tr_no"));
                    train.setTr_name(rs.getString("tr_name"));
                    train.setFrom_stn(rs.getString("from_stn"));
                    train.setTo_stn(rs.getString("to_stn"));
                    train.setSeats(rs.getInt("seats"));
                    train.setFare(rs.getDouble("fare"));
                    trains.add(train);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trains;
    }
}
