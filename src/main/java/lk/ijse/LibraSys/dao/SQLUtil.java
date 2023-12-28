package lk.ijse.LibraSys.dao;

import lk.ijse.LibraSys.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public  static <T> T execute(String sql,Object... ob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        for (int i=0;i< ob.length;i++){
            pstm.setObject(i+1,ob[i]);
        }
        if (sql.startsWith("SELECT") || sql.startsWith("select")){
            return (T) pstm.executeQuery();
        }else {
            return (T)(Boolean) (pstm.executeUpdate()>0);
        }
    }
}
