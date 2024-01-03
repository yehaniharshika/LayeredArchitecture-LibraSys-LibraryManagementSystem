package lk.ijse.LibraSys.bo.custom;

import lk.ijse.LibraSys.dto.BookDto;
import lk.ijse.LibraSys.dto.MemberDto;
import lk.ijse.LibraSys.dto.ReservationDto;

import java.sql.SQLException;
import java.util.List;

public interface ReservationBo {


    boolean addReservation(ReservationDto dto) throws SQLException;

    boolean updateReservation(ReservationDto dto) throws SQLException;

    boolean deleteReservation(String reservationId) throws SQLException;

    ReservationDto searchReservation(String reservationId) throws SQLException;

    String generateNextReservationId() throws SQLException;
    MemberDto searchMember(String mid) throws SQLException;
    BookDto searchBook(String ISBN) throws SQLException;
    List<MemberDto> getAllMember() throws SQLException;
    List<BookDto> getAllBooks() throws SQLException;


    List<ReservationDto> getAllReservation() throws SQLException;
}
