package lk.ijse.LibraSys.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    private String ISBN;
    private String bookName;
    private String category;
    private int qtyOnHand;
    private String rackCode;
    private String authorId;
}
