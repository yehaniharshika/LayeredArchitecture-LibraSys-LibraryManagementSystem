package lk.ijse.LibraSys.dto;

import lk.ijse.LibraSys.entity.Author;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthorDto {
    private  String authorId;
    private String  authorName;
    private String text;
    private String nationality;
    private int currentlyBooksWrittenQty;

    public AuthorDto(Author author) {
        this.authorId = author.getAuthorId();
        this.authorName = author.getAuthorName();
        this.text = author.getText();
        this.nationality = author.getNationality();
        this.currentlyBooksWrittenQty = author.getCurrentlyBooksWrittenQty();
    }
}
