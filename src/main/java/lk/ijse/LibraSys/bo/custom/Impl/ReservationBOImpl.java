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
import lk.ijse.LibraSys.entity.Book;
import lk.ijse.LibraSys.entity.Member;
import lk.ijse.LibraSys.entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBo {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    MemberDAO memberDAO = (MemberDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBER);
    BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);


    @Override
    public boolean addReservation(ReservationDto dto) throws SQLException {
        return reservationDAO.save(new Reservation(dto.getReservationId(),
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
        return reservationDAO.update(new Reservation(dto.getReservationId(),
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
        Reservation reservation = reservationDAO.search(reservationId);
        if (reservation != null){
            return new ReservationDto(reservation);
        }
        return null;
    }

    @Override
    public String generateNextReservationId() throws SQLException {
        return reservationDAO.generateNextId();
    }

    @Override
    public MemberDto searchMember(String mid) throws SQLException {
        Member member = memberDAO.search(mid);
        if (member != null){
            return new MemberDto(member);
        }
        return null;
    }

    @Override
    public BookDto searchBook(String ISBN) throws SQLException {
        Book book = bookDAO.search(ISBN);

        if (book != null){
            return new BookDto(book);
        }
        return null;
    }

    @Override
    public List<MemberDto> getAllMember() throws SQLException {
        List<Member> allMembers = memberDAO.getAll();
        ArrayList<MemberDto> memberDtos = new ArrayList<>();

        for (Member member : allMembers){
            memberDtos.add(new MemberDto(
                    member.getMid(),
                    member.getName(),
                    member.getAddress(),
                    member.getGender(),
                    member.getTel(),
                    member.getEmailAddress(),
                    member.getIDNumber(),
                    member.getFeeId(),
                    member.getSNumber())
            );
        }
        return memberDtos;
    }

    @Override
    public List<BookDto> getAllBooks() throws SQLException {
        ArrayList<Book> allBooks = bookDAO.getAll();
        ArrayList<BookDto> bookDtos = new ArrayList<>();
//        List<BookDto> booksList =  bookDAO.getAll();

        for (Book book : allBooks){
            bookDtos.add(new BookDto(
                    book.getISBN(),
                    book.getBookName(),
                    book.getCategory(),
                    book.getQtyOnHand(),
                    book.getRackCode(),
                    book.getAuthorId())
            );
        }
        return bookDtos;
    }

    @Override
    public List<ReservationDto> getAllReservation() throws SQLException {
        List<Reservation> allReservations = reservationDAO.getAll();
        ArrayList<ReservationDto> reservationDtos = new ArrayList<>();

        for (Reservation reservation : allReservations){
            reservationDtos.add(new ReservationDto(
                    reservation.getReservationId(),
                    reservation.getBorrowedDate(),
                    reservation.getDueDate(),
                    reservation.getBookReturnDate(),
                    reservation.getFineStatus(),
                    reservation.getFineAmount(),
                    reservation.getMid(),
                    reservation.getISBN())
            );
        }
        return reservationDtos;
    }
}
