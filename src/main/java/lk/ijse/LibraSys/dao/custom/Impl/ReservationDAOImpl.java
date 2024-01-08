package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.SQLUtil;
import lk.ijse.LibraSys.dao.custom.ReservationDAO;
import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.AuthorDto;
import lk.ijse.LibraSys.dto.ReservationDto;
import lk.ijse.LibraSys.entity.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public  String getCount() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(reservationId) FROM  reservation");

        String count =  null;
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            count = resultSet.getString(1);
        }
        return count;
    }

    @Override
    public  boolean save(Reservation entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO reservation VALUES (?,?,?,?,?,?,?,?)",
                entity.getReservationId(),
                entity.getBorrowedDate(),
                entity.getDueDate(),
                entity.getBookReturnDate(),
                entity.getFineStatus(),
                entity.getFineAmount(),
                entity.getMid(),
                entity.getISBN()
        );
    }

    @Override
    public boolean update(Reservation entity) throws SQLException {
        return SQLUtil.execute("UPDATE  reservation SET borrowedDate=?,dueDate=?,bookReturnDate=?,fineStatus=?,fineAmount=?,mid=?,ISBN=? WHERE reservationId=?",
                entity.getBorrowedDate(),
                entity.getDueDate(),
                entity.getBookReturnDate(),
                entity.getFineStatus(),
                entity.getFineAmount(),
                entity.getMid(),
                entity.getISBN(),
                entity.getReservationId()
        );
    }

    @Override
    public  boolean delete(String reservationId) throws SQLException {
        return SQLUtil.execute("DELETE FROM reservation WHERE reservationId=?",reservationId);

    }

    @Override
    public Reservation search(String reservationId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM reservation WHERE reservationId=?",reservationId);

        Reservation entity= null;
        if(resultSet.next()){
            entity = new Reservation(
                  resultSet.getString(1),
                  resultSet.getString(2),
                  resultSet.getString(3),
                  resultSet.getString(4),
                  resultSet.getString(5),
                  resultSet.getDouble(6),
                  resultSet.getString(7),
                  resultSet.getString(8)
            );
        }
        return entity;
    }

    @Override
    public  String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT reservationId FROM reservation ORDER BY reservationId DESC LIMIT 1");

        if(resultSet.next()){
            return  splitReservationId(resultSet.getString(1));
        }
       return splitReservationId(null);
    }

    private String splitReservationId(String currentReservationId) {
        if(currentReservationId != null){
            String[] split = currentReservationId.split("[R]");

            int reservationId = Integer.parseInt(split[1]);
            reservationId++;
            return "R" + String.format("%03d", reservationId);
        }else {
            return "R001";
        }
    }

    @Override
    public ArrayList<Reservation> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM  reservation");

        List<Reservation> reservationList = new ArrayList<>();

        while (resultSet.next()){
            reservationList.add(new Reservation(
                 resultSet.getString(1),
                 resultSet.getString(2),
                 resultSet.getString(3),
                 resultSet.getString(4),
                 resultSet.getString(5),
                 resultSet.getDouble(6),
                 resultSet.getString(7),
                 resultSet.getString(8)
            ));
        }
        return (ArrayList<Reservation>) reservationList;
    }
}
