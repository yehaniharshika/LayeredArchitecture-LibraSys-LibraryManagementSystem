package lk.ijse.LibraSys.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Author {
    private  String authorId;
    private String  authorName;
    private String text;
    private String nationality;
    private int currentlyBooksWrittenQty;
}
