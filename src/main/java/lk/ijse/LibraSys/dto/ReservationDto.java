package lk.ijse.LibraSys.dto;

import javafx.scene.control.Button;
import lk.ijse.LibraSys.entity.Reservation;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationDto {
    private String reservationId;
    private String borrowedDate;
    private  String  dueDate;
    private String bookReturnDate;
    private  String fineStatus;
    private double fineAmount;
    private String mid;
    private  String ISBN;

    public ReservationDto(Reservation reservation) {
        this.reservationId = reservation.getReservationId();
        this.borrowedDate = reservation.getBorrowedDate();
        this.dueDate = reservation.getDueDate();
        this.bookReturnDate = reservation.getBookReturnDate();
        this.fineStatus = reservation.getFineStatus();
        this.fineAmount = reservation.getFineAmount();
        this.mid = reservation.getMid();
        this.ISBN = reservation.getISBN();
    }
}
