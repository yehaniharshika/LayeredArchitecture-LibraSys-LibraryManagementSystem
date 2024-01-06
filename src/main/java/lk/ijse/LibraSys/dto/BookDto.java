package lk.ijse.LibraSys.dto;

import lk.ijse.LibraSys.entity.Book;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookDto {
    private String ISBN;
    private String bookName;
    private String category;
    private int qtyOnHand;
    private String rackCode;
    private String authorId;


    public BookDto(Book book) {
        this.ISBN = book.getISBN();
        this.bookName = book.getBookName();
        this.category = book.getCategory();
        this.qtyOnHand = book.getQtyOnHand();
        this.rackCode = book.getRackCode();
        this.authorId = book.getAuthorId();
    }
}
