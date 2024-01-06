package lk.ijse.LibraSys.dto;

import lk.ijse.LibraSys.entity.MembershipFee;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@Setter
@ToString

public class MembershipFeeDto {

    private  String id;
    private String name;
    private  double amount;
    private LocalDate date;
    private  String status;

    public  MembershipFeeDto(){}

    public MembershipFeeDto(String id,String name,double amount,LocalDate date,String status) {
        this.id =id;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    public MembershipFeeDto(MembershipFee membershipFee) {
        this.id = membershipFee.getId();
        this.name = membershipFee.getName();
        this.amount = membershipFee.getAmount();
        this.date = membershipFee.getDate();
        this.status = membershipFee.getStatus();

    }
}
