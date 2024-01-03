package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.dao.custom.Impl.ReservationDAOImpl;
import lk.ijse.LibraSys.dao.custom.ReservationDAO;
import lk.ijse.LibraSys.dto.ReservationDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements  ReservationBo{

    ReservationDAO reservationDAO = new ReservationDAOImpl();
    @Override
    public String getBookBorrowCount() throws SQLException {
        return reservationDAO.getCount();
    }

    @Override
    public boolean addReservation(ReservationDto dto) throws SQLException {
        return reservationDAO.save(new ReservationDto(dto.getReservationId(),
                dto.getBorrowedDate(),
                dto.getDueDate(),
                dto.getBookReturnDate(),
                dto.getFineStatus(),
                dto.getFineAmount(),
                dto.getMid(),
                dto.getISBN())
        );
    }

    @Override
    public boolean updateReservation(ReservationDto dto) throws SQLException {
        return reservationDAO.update(new ReservationDto(dto.getReservationId(),
                dto.getBorrowedDate(),
                dto.getDueDate(),
                dto.getBookReturnDate(),
                dto.getFineStatus(),
                dto.getFineAmount(),
                dto.getMid(),
                dto.getISBN()));
    }

    @Override
    public boolean deleteReservation(String reservationId) throws SQLException {
        return reservationDAO.delete(reservationId);
    }

    @Override
    public ReservationDto searchReservation(String reservationId) throws SQLException {
        return reservationDAO.search(reservationId);
    }

    @Override
    public String generateNextReservationId() throws SQLException {
        return reservationDAO.generateNextId();
    }

    @Override
    public List<ReservationDto> getAllReservation() throws SQLException {
        ArrayList<ReservationDto> reservationDtos = new ArrayList<>();
        List<ReservationDto> reservationsList = reservationDAO.getAll();

        for (ReservationDto dto : reservationsList){
            reservationDtos.add(new ReservationDto(
                    dto.getReservationId(),
                    dto.getBorrowedDate(),
                    dto.getDueDate(),
                    dto.getBookReturnDate(),
                    dto.getFineStatus(),
                    dto.getFineAmount(),
                    dto.getMid(),
                    dto.getISBN())
            );
        }
        return reservationDtos;
    }
}
