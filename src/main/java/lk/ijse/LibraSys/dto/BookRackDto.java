package lk.ijse.LibraSys.dto;

import lk.ijse.LibraSys.entity.BookRack;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookRackDto {
    private  String rackCode;
    private  int qtyBooks;
    private String categoryOfBooks;
    private String nameOfBooks;

    public BookRackDto(BookRack bookRack) {
        this.rackCode = bookRack.getRackCode();
        this.qtyBooks = bookRack.getQtyBooks();
        this.categoryOfBooks = bookRack.getCategoryOfBooks();
        this.nameOfBooks = bookRack.getNameOfBooks();
    }
}
