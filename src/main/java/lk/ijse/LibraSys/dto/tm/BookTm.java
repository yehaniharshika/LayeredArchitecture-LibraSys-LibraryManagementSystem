package lk.ijse.LibraSys.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class BookTm {
    private String ISBN;
    private String bookName;
    private String category;
    private int qtyOnHand;
    private String authorId;
    private String rackCode;

}
