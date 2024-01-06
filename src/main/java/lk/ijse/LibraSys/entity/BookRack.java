package lk.ijse.LibraSys.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookRack {
    private  String rackCode;
    private  int qtyBooks;
    private String categoryOfBooks;
    private String nameOfBooks;
}
