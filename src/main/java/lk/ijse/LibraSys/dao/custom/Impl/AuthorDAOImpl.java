package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.SQLUtil;
import lk.ijse.LibraSys.dao.custom.AuthorDAO;
import lk.ijse.LibraSys.dto.AuthorDto;
import lk.ijse.LibraSys.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorDAOImpl implements AuthorDAO {

    @Override
    public String getCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(authorId) FROM author");
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(authorId) FROM author");

        String Count = null;
        //ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            Count = resultSet.getString(1);
        }
        return  Count;
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT authorId FROM author ORDER BY authorId DESC LIMIT 1;");
        /*Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("SELECT authorId FROM author ORDER BY authorId DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();*/
        if (resultSet.next()){
//            String authorId = resultSet.getString("authorId");
//            int newAuthorId = Integer.parseInt(authorId.replace("A00", "")) + 1;
//            return String.format("A00%01d", newAuthorId);
            return splitAuthorId(resultSet.getString("authorId"));
        }
        return splitAuthorId(null);

    }


    private String splitAuthorId(String currentAuthorId) {
        if(currentAuthorId != null) {
            String[] strings = currentAuthorId.split("A0");
            int authorid = Integer.parseInt(strings[1]);
            authorid++;
            String ID = String.valueOf(authorid);
            int length = ID.length();
            if (length < 2){
                return "A00"+authorid;
            }else {
                if (length < 3){
                    return "A0"+authorid;
                }else {
                    return "A"+authorid;
                }
            }
        }
        return "A001";
    }

    @Override
    public boolean save(Author entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO author VALUES (?,?,?,?,?)",
                entity.getAuthorId(),entity.getAuthorName(),entity.getText(),entity.getNationality(),entity.getCurrentlyBooksWrittenQty()
        );

//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("INSERT INTO author VALUES (?,?,?,?,?)");
//
//        pstm.setString(1, dto.getAuthorId());
//        pstm.setString(2, dto.getAuthorName());
//        pstm.setString(3, dto.getText());
//        pstm.setString(4, dto.getNationality());
//        pstm.setInt(5,dto.getCurrentlyBooksWrittenQty());
//
//        boolean isSaved = pstm.executeUpdate() > 0;
//
//        return isSaved;
    }

    @Override
    public  boolean update(Author entity) throws SQLException {

        return SQLUtil.execute("UPDATE author SET authorName=?,text=?,nationality=?,currentlyBooksWrittenQty=? WHERE authorId=?",
                entity.getAuthorName(),entity.getText(),entity.getNationality(),entity.getCurrentlyBooksWrittenQty(),entity.getAuthorId()
        );
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("UPDATE author SET authorName=?,text=?,nationality=?,currentlyBooksWrittenQty=? WHERE authorId=?");
//        pstm.setString(1, dto.getAuthorName());
//        pstm.setString(2, dto.getText());
//        pstm.setString(3, dto.getNationality());
//        pstm.setInt(4,dto.getCurrentlyBooksWrittenQty());
//        pstm.setString(5, dto.getAuthorId());
//
//        boolean isUpdated = pstm.executeUpdate() > 0;
//        return isUpdated;
    }

    @Override
    public boolean delete(String authorId) throws SQLException {
        return SQLUtil.execute("DELETE  FROM  author WHERE authorId =?",authorId);
        /*Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE  FROM  author WHERE authorId =?");
        pstm.setString(1,authorId);

        boolean isDeleted = pstm.executeUpdate() > 0;

        return isDeleted;*/
    }

    @Override
    public Author search(String authorId) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM  author WHERE authorId=?",
                authorId
        );
        Author entity = null;
        if (resultSet.next()){
            entity = new Author(
                    resultSet.getString("authorId"),
                    resultSet.getString("authorName"),
                    resultSet.getString("text"),
                    resultSet.getString("nationality"),
                    resultSet.getInt("currentlyBooksWrittenQty")
                    );
        }
        return entity;
        /*Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM  author WHERE authorId=?");
        pstm.setString(1,authorId);

        ResultSet resultSet = pstm.executeQuery();

        AuthorDto dto = null;
        if (resultSet.next()){
            dto = new AuthorDto(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getInt(5)
            );
        }
        return dto;*/
    }

    @Override
    public ArrayList<Author> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM author");
        ArrayList<Author> authorDtoList = new ArrayList<>();

        while (resultSet.next()){
            Author entity = new Author(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)

            );
            authorDtoList.add(entity);

        }
        return authorDtoList;

/*      Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM author");

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<AuthorDto> authorDtoList = new ArrayList<>();
        while (resultSet.next()){
            authorDtoList.add(new AuthorDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            ));
        }
        return authorDtoList;*/
    }

}
