package lk.ijse.LibraSys.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Supplier {
    private String supplierId;
    private String supplierName;
    private String contactNumber;
    private String  email;
}
