package lk.ijse.LibraSys.bo.custom.Impl;

import lk.ijse.LibraSys.bo.custom.ReservationBo;
import lk.ijse.LibraSys.dao.DAOFactory;
import lk.ijse.LibraSys.dao.custom.BookDAO;
import lk.ijse.LibraSys.dao.custom.Impl.BookDAOImpl;
import lk.ijse.LibraSys.dao.custom.Impl.MemberDAOImpl;
import lk.ijse.LibraSys.dao.custom.Impl.ReservationDAOImpl;
import lk.ijse.LibraSys.dao.custom.MemberDAO;
import lk.ijse.LibraSys.dao.custom.ReservationDAO;
import lk.ijse.LibraSys.dto.BookDto;
import lk.ijse.LibraSys.dto.MemberDto;
import lk.ijse.LibraSys.dto.ReservationDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBo {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    MemberDAO memberDAO = (MemberDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBER);
    BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);


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
    public MemberDto searchMember(String mid) throws SQLException {
        return memberDAO.search(mid);
    }

    @Override
    public BookDto searchBook(String ISBN) throws SQLException {
        return bookDAO.search(ISBN);
    }

    @Override
    public List<MemberDto> getAllMember() throws SQLException {
        ArrayList<MemberDto> memberDtos = new ArrayList<>();
        List<MemberDto> membersList = memberDAO.getAll();

        for (MemberDto dto : membersList){
            memberDtos.add(new MemberDto(dto.getMid(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getGender(),
                    dto.getTel(),
                    dto.getEmailAddress(),
                    dto.getIDNumber(),
                    dto.getFeeId(),
                    dto.getSNumber())
            );
        }
        return memberDtos;
    }

    @Override
    public List<BookDto> getAllBooks() throws SQLException {
        ArrayList<BookDto> bookDtos = new ArrayList<>();
        List<BookDto> booksList =  bookDAO.getAll();

        for (BookDto dto : booksList){
            bookDtos.add(new BookDto(dto.getISBN(),
                    dto.getBookName(),
                    dto.getCategory(),
                    dto.getQtyOnHand(),
                    dto.getRackCode(),
                    dto.getAuthorId())
            );
        }
        return bookDtos;
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
