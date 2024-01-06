package lk.ijse.LibraSys.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reservation {
    private String reservationId;
    private String borrowedDate;
    private  String  dueDate;
    private String bookReturnDate;
    private  String fineStatus;
    private double fineAmount;
    private String mid;
    private  String ISBN;
}
