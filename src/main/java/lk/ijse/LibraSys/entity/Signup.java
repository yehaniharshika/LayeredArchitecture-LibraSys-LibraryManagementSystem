package lk.ijse.LibraSys.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Signup {
    private String sNumber;
    private String firstName;
    private String lastName;
    private String nic;
    private String eAddress;
    private String username;
    private String pw;
}
