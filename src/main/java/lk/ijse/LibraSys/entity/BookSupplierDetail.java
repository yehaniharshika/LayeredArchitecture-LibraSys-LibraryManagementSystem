package lk.ijse.LibraSys.entity;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookSupplierDetail {
    private String supplierId;
    private String ISBN;
    private  String bookName;
    private int qty;
    private LocalDate supplierDate;

}
