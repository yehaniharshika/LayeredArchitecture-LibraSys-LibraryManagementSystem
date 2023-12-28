package lk.ijse.LibraSys.dao.custom;

import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.ReservationDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationDAO {
    String getBookBorrowCount() throws SQLException;

    boolean addReservation(ReservationDto dto) throws SQLException;

    boolean updateReservation(ReservationDto dto) throws SQLException;

    boolean deleteReservation(String reservationId) throws SQLException;

    ReservationDto searchReservation(String reservationId) throws SQLException;

    String generateNextReservationId(String reservationId) throws SQLException;

    List<ReservationDto> getAllReservation() throws SQLException;
}
