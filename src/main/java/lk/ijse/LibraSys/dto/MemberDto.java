package lk.ijse.LibraSys.dto;

import lk.ijse.LibraSys.entity.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@Setter
@ToString
public class MemberDto {

    private String mid;
    private String name;
    private String address;
    private String gender;
    private String tel;
    private String EmailAddress;
    private String IDNumber;
    private String feeId;
    private String sNumber;



    public MemberDto(){

    }

    public MemberDto( String mid, String name, String address, String gender, String tel,String EmailAddress,String IDNumber,String feeId, String sNumber ){
        this.mid = mid;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.tel = tel;
        this.EmailAddress = EmailAddress;
        this.IDNumber = IDNumber;
        this.feeId = feeId;
        this.sNumber = sNumber;
    }

    public MemberDto(Member member) {
        this.mid = member.getMid();
        this.name = member.getName();
        this.address = member.getAddress();
        this.gender = member.getGender();
        this.tel = member.getTel();
        this.EmailAddress = member.getEmailAddress();
        this.IDNumber = member.getIDNumber();
        this.feeId = member.getFeeId();
        this.sNumber = member.getSNumber();
    }
}
