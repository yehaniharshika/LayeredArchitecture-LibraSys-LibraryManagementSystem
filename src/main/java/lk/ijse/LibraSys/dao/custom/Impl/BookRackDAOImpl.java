package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.SQLUtil;
import lk.ijse.LibraSys.dao.custom.BookRackDAO;
import lk.ijse.LibraSys.dto.AuthorDto;
import lk.ijse.LibraSys.dto.BookRackDto;

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
       /* Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT  rackCode FROM bookRack ORDER BY rackCode DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();*/
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
    public boolean save(BookRackDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO  bookRack VALUES (?,?,?,?)",
                dto.getRackCode(),
                dto.getQtyBooks(),
                dto.getCategoryOfBooks(),
                dto.getNameOfBooks()
        );
       /* Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO  bookRack VALUES (?,?,?,?)");
        pstm.setString(1,dto.getRackCode());
        pstm.setString(2, String.valueOf(dto.getQtyBooks()));
        pstm.setString(3, dto.getCategoryOfBooks());
        pstm.setString(4, dto.getNameOfBooks());

        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;*/
    }

    @Override
    public  boolean update(BookRackDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE bookRack SET qtyBooks=?,nameOfBooks =? ,categoryOfBooks=? WHERE rackCode =?",
                dto.getQtyBooks(),
                dto.getNameOfBooks(),
                dto.getCategoryOfBooks(),
                dto.getRackCode()
        );
       /* Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE bookRack SET qtyBooks=?,nameOfBooks =? ,categoryOfBooks=? WHERE rackCode =?");
        pstm.setString(1, String.valueOf(dto.getQtyBooks()));
        pstm.setString(2, dto.getNameOfBooks());
        pstm.setString(3, dto.getCategoryOfBooks());
        pstm.setString(4, dto.getRackCode());

        boolean isUpdated = pstm.executeUpdate() > 0;
        return isUpdated;*/
    }

    @Override
    public boolean delete(String rackCode) throws SQLException {
        return SQLUtil.execute("DELETE FROM bookRack WHERE rackCode=?",rackCode);
        /*Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM bookRack WHERE rackCode=?");
        pstm.setString(1,rackCode);

        boolean isDeleted = pstm.executeUpdate() > 0;
        return isDeleted;*/
    }

    @Override
    public BookRackDto search(String rackCode) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM bookRack WHERE rackCode=?",rackCode);
       /* Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM bookRack WHERE rackCode=?");
        pstm.setString(1,rackCode);

        ResultSet resultSet = pstm.executeQuery();*/

        BookRackDto dto = null;

        if(resultSet.next()){
            dto = new BookRackDto(
                  resultSet.getString(1),
                  resultSet.getInt(2),
                  resultSet.getString(3),
                  resultSet.getString(4)
            );
        }
        return dto;
    }

    @Override
    public ArrayList<BookRackDto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM bookRack");
     /*   Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM bookRack");*/

        List<BookRackDto> rackList = new ArrayList<>();

        while(resultSet.next()){
            rackList.add(new BookRackDto(
                resultSet.getString(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4)
            ));

        }
        return (ArrayList<BookRackDto>) rackList;
    }

    /*public  boolean updateBooks(List<BookRackTm> bookTmList) throws SQLException {
        for (BookRackTm Tm : bookTmList){
            System.out.println("Book : "+ Tm);

            if (!updateQtyBooks(Tm.getRackCode(), Tm.getQtyBooks())){
                return false;
            }
        }
        return true;
    }*/

     @Override
     public boolean updateQtyBooks(String rackCode,int qtyBooks) throws SQLException {
         return SQLUtil.execute("UPDATE bookRack SET qtyBooks = qtyBooks + CAST(? AS SIGNED) WHERE rackCode = ?",
                 qtyBooks,
                 rackCode
         );

       /* Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm= connection.prepareStatement("UPDATE bookRack SET qtyBooks = qtyBooks+? WHERE rackCode =?");

        pstm.setInt(1,qtyBooks);
        pstm.setString(2,rackCode);

        boolean isqtyUpdated = pstm.executeUpdate() > 0;

         return isqtyUpdated;*/
     }

     /*@Override
     public boolean updatenameOfBooks(String rackCode,String nameOfBooks) throws SQLException {
         Connection connection = DbConnection.getInstance().getConnection();
         PreparedStatement pstm = connection.prepareStatement("UPDATE bookrack SET nameOfBooks= nameOfBooks +? WHERE rackCode=?");
         pstm.setString(1,nameOfBooks);
         pstm.setString(2,rackCode);

         boolean isNameOfBooksUpdated = pstm.executeUpdate() > 0;
         return isNameOfBooksUpdated;
     }*/
}