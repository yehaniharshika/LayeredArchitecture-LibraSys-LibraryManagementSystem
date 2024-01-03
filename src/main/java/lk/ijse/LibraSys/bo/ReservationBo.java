package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.dto.ReservationDto;

import java.sql.SQLException;
import java.util.List;

public interface ReservationBo {
    String getBookBorrowCount() throws SQLException;

    boolean addReservation(ReservationDto dto) throws SQLException;

    boolean updateReservation(ReservationDto dto) throws SQLException;

    boolean deleteReservation(String reservationId) throws SQLException;

    ReservationDto searchReservation(String reservationId) throws SQLException;

    String generateNextReservationId() throws SQLException;

    List<ReservationDto> getAllReservation() throws SQLException;
}
