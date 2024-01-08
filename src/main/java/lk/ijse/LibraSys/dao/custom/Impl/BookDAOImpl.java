package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.SQLUtil;
import lk.ijse.LibraSys.dao.custom.BookDAO;
import lk.ijse.LibraSys.dto.AuthorDto;
import lk.ijse.LibraSys.dto.BookDto;
import lk.ijse.LibraSys.dto.tm.SupplierCartTm;
import lk.ijse.LibraSys.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl  implements BookDAO {

    @Override
    public String getCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(ISBN) FROM  book");

        String count = null;
        if (resultSet.next()){
            count = resultSet.getString(1);
        }
        return count;
    }

    @Override
    public  String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT ISBN FROM book ORDER BY ISBN DESC LIMIT 1");
        if (resultSet.next()){
            return splitBookISBN(resultSet.getString(1));

        }
        return splitBookISBN(null);
    }

    private String splitBookISBN(String currentBookISBN) {
        if(currentBookISBN != null) {
            String[] strings = currentBookISBN.split("B0");
            int ISBN = Integer.parseInt(strings[1]);
            ISBN++;
            String ID = String.valueOf(ISBN);
            int length = ID.length();
            if (length < 2){
                return "B00"+ISBN;
            }else {
                if (length < 3){
                    return "B0"+ISBN;
                }else {
                    return "B"+ISBN;
                }
            }
        }
        return "B001";
    }

    @Override
    public  boolean save(Book entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO book VALUES (?,?,?,?,?,?)",
                entity.getISBN(),
                entity.getBookName(),
                entity.getCategory(),
                entity.getQtyOnHand(),
                entity.getRackCode(),
                entity.getAuthorId()
        );

    }

    @Override
    public boolean update(Book entity) throws SQLException {
        return SQLUtil.execute("UPDATE book SET bookName=?, category =?,qtyOnHand=?, rackCode=?,authorId=? WHERE ISBN=?",
                entity.getBookName(),
                entity.getCategory(),
                entity.getQtyOnHand(),
                entity.getRackCode(),
                entity.getAuthorId(),
                entity.getISBN()
        );

    }

    @Override
    public boolean delete(String ISBN) throws SQLException {
        return SQLUtil.execute("DELETE  FROM  book WHERE ISBN=?",ISBN);

    }

    @Override
    public Book search(String ISBN) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM book WHERE ISBN=?",
                ISBN
        );

        Book entity= null;
        if(resultSet.next()){
             entity = new Book(
                 resultSet.getString(1),
                 resultSet.getString(2),
                 resultSet.getString(3),
                 resultSet.getInt(4),
                 resultSet.getString(5),
                 resultSet.getString(6)
            );
        }
        return entity;
    }

    @Override
    public ArrayList<Book> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT  * FROM  book");


        List<Book> bookList = new ArrayList<>();

        while (resultSet.next()){
            Book entity = new Book(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            bookList.add(entity);
        }
        return (ArrayList<Book>) bookList;
    }

    //transaction ekata
    @Override
    public  boolean updateBooks(List<SupplierCartTm> supplierCartTmList) throws SQLException {
        for (SupplierCartTm Tm : supplierCartTmList){
            System.out.println("Book : "+ Tm);

            if (!updateQty(Tm.getISBN(), Tm.getQty())){
                return false;
            }
        }
        return true;
    }

    @Override
    public  boolean updateQty(String ISBN , int  qtyOnHand) throws SQLException {
//        System.out.println("SQL Query: " + "UPDATE book SET qtyOnHand = qtyOnHand + CAST(? AS SIGNED) WHERE ISBN = ?");
        return SQLUtil.execute("UPDATE book SET qtyOnHand = qtyOnHand + CAST(? AS SIGNED) WHERE ISBN = ?", qtyOnHand, ISBN);

    }


}
