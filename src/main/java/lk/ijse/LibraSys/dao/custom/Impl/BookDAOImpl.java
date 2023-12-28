package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.SQLUtil;
import lk.ijse.LibraSys.dao.custom.BookDAO;
import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.BookDto;
import lk.ijse.LibraSys.dto.tm.SupplierCartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl  implements BookDAO {

    @Override
    public String getBookCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(ISBN) FROM  book");
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(ISBN) FROM  book");

        String count = null;
        //ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            count = resultSet.getString(1);
        }
        return count;
    }

    @Override
    public  String generateNextBookISBN() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT ISBN FROM book ORDER BY ISBN DESC LIMIT 1");
       /* Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("SELECT ISBN FROM book ORDER BY ISBN DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();*/
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
    public  boolean saveBook(BookDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO book VALUES (?,?,?,?,?,?)",
                dto.getISBN(),
                dto.getBookName(),
                dto.getCategory(),
                dto.getQtyOnHand(),
                dto.getRackCode(),
                dto.getAuthorId()
        );
        /*Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO book VALUES (?,?,?,?,?,?)");
        pstm.setString(1, dto.getISBN());
        pstm.setString(2, dto.getBookName());
        pstm.setString(3, dto.getCategory());
        pstm.setString(4, dto.getQtyOnHand());
        pstm.setString(5,dto.getRackCode());
        pstm.setString(6, dto.getAuthorId());

        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;*/
    }

    @Override
    public boolean updateBook(BookDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE book SET bookName=?, category =?,qtyOnHand=?, rackCode=?,authorId=? WHERE ISBN=?",
                dto.getBookName(),
                dto.getCategory(),
                dto.getQtyOnHand(),
                dto.getRackCode(),
                dto.getAuthorId(),
                dto.getISBN()
        );
        /*Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE book SET bookName=?, category =?,qtyOnHand=?, rackCode=?,authorId=? WHERE ISBN=?");
        pstm.setString(1, dto.getBookName());
        pstm.setString(2, dto.getCategory());
        pstm.setString(3, dto.getQtyOnHand());
        pstm.setString(4, dto.getRackCode());
        pstm.setString(5, dto.getAuthorId());
        pstm.setString(6, dto.getISBN());

        boolean isUpdated = pstm.executeUpdate() > 0;
        return isUpdated;*/
    }

    @Override
    public boolean deleteBook(String ISBN) throws SQLException {
        return SQLUtil.execute("DELETE  FROM  book WHERE ISBN=?",ISBN);
       /* Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE  FROM  book WHERE ISBN=?");
        pstm.setString(1,ISBN);

        boolean isDeleted = pstm.executeUpdate() > 0;
        return isDeleted;*/
    }

    @Override
    public BookDto searchBook(String ISBN) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM book WHERE ISBN=?",
                ISBN
        );
       /* Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM book WHERE ISBN=?");
        pstm.setString(1,ISBN);*/
       // System.out.println("search book id +"+ISBN);

        //ResultSet resultSet = pstm.executeQuery();

        BookDto dto= null;
        if(resultSet.next()){
            dto = new BookDto(
                 resultSet.getString(1),
                 resultSet.getString(2),
                 resultSet.getString(3),
                 resultSet.getString(4),
                 resultSet.getString(5),
                 resultSet.getString(6)
            );
        }
        return dto;
    }

    @Override
    public List<BookDto> getAllBooks() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT  * FROM  book");
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT  * FROM  book");

        List<BookDto> bookList = new ArrayList<>();

//        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            bookList.add(new BookDto(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6)
            ));
        }
        return bookList;
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
        return SQLUtil.execute("UPDATE book SET qtyOnHand = qtyOnHand + ? WHERE ISBN = ?",ISBN,qtyOnHand);
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql ="UPDATE book SET qtyOnHand = qtyOnHand + ? WHERE ISBN = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1,qtyOnHand);
        pstm.setString(2,ISBN);

        return  pstm.executeUpdate() > 0;*/
    }


}
