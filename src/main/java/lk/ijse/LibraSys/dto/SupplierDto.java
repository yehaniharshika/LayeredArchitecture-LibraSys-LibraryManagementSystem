package lk.ijse.LibraSys.dto;

import lk.ijse.LibraSys.entity.Supplier;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SupplierDto {
    private String supplierId;
    private String supplierName;
    private String contactNumber;
    private String  email;

    public SupplierDto(Supplier supplier) {
        this.supplierId = supplier.getSupplierId();
        this.supplierName = supplier.getSupplierName();
        this.contactNumber = supplier.getContactNumber();
        this.email = supplier.getEmail();
    }
}
