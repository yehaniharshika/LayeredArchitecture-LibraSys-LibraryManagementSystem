package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.SQLUtil;
import lk.ijse.LibraSys.dao.custom.BookRackDAO;
import lk.ijse.LibraSys.dto.AuthorDto;
import lk.ijse.LibraSys.dto.BookRackDto;
import lk.ijse.LibraSys.entity.BookRack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRackDAOImpl implements BookRackDAO {

    @Override
    public String getCount() throws SQLException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT  rackCode FROM bookRack ORDER BY rackCode DESC LIMIT 1");

        if (resultSet.next()){
            return splitRackCode(resultSet.getString(1));
        }
        return splitRackCode(null);
    }

    private String splitRackCode(String currentRackCode) {
        if(currentRackCode != null) {
            String[] strings = currentRackCode.split("R0");
            int rackCode = Integer.parseInt(strings[1]);
            rackCode++;
            String ID = String.valueOf(rackCode);
            int length = ID.length();
            if (length < 2){
                return "R00"+rackCode;
            }else {
                if (length < 3){
                    return "R0"+rackCode;
                }else {
                    return "R"+rackCode;
                }
            }
        }
        return "R001";
    }

    @Override
    public boolean save(BookRack entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO  bookRack VALUES (?,?,?,?)",
                entity.getRackCode(),
                entity.getQtyBooks(),
                entity.getCategoryOfBooks(),
                entity.getNameOfBooks()
        );

    }

    @Override
    public  boolean update(BookRack entity) throws SQLException {
        return SQLUtil.execute("UPDATE bookRack SET qtyBooks=?,nameOfBooks =? ,categoryOfBooks=? WHERE rackCode =?",
                entity.getQtyBooks(),
                entity.getNameOfBooks(),
                entity.getCategoryOfBooks(),
                entity.getRackCode()
        );
    }

    @Override
    public boolean delete(String rackCode) throws SQLException {
        return SQLUtil.execute("DELETE FROM bookRack WHERE rackCode=?",rackCode);

    }

    @Override
    public BookRack search(String rackCode) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM bookRack WHERE rackCode=?",rackCode);

        BookRack entity = null;

        if(resultSet.next()){
            entity = new BookRack(
                  resultSet.getString(1),
                  resultSet.getInt(2),
                  resultSet.getString(3),
                  resultSet.getString(4)
            );
        }
        return entity;
    }

    @Override
    public ArrayList<BookRack> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM bookRack");

        List<BookRack> rackList = new ArrayList<>();

        while(resultSet.next()){
            rackList.add(new BookRack(
                resultSet.getString(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4)
            ));

        }
        return (ArrayList<BookRack>) rackList;
    }


     @Override
     public boolean updateQtyBooks(String rackCode,int qtyBooks) throws SQLException {
         return SQLUtil.execute("UPDATE bookRack SET qtyBooks = qtyBooks + CAST(? AS SIGNED) WHERE rackCode = ?",
                 qtyBooks,
                 rackCode
         );


     }

}
