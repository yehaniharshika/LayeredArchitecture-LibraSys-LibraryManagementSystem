package lk.ijse.LibraSys.entity;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MembershipFee {
    private  String id;
    private String name;
    private  double amount;
    private LocalDate date;
    private  String status;
}
