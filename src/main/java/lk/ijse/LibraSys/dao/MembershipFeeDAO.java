package lk.ijse.LibraSys.dao;

import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.MembershipFeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface MembershipFeeDAO {
    String generateNextMembershipFeeId(String id) throws SQLException;


   /* public static String getTotalAmount() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT SUM(amount)  FROM membershipFee");
        ResultSet resultSet = pstm.executeQuery();

        String amount = null;
        if (resultSet.next()){
            amount = resultSet.getString(1);
        }
        return amount;
    }*/
    String getTotalAmount() throws SQLException;

    boolean saveMembersipFee(MembershipFeeDto dto) throws SQLException;

    boolean updateMembershipfee(MembershipFeeDto dto) throws SQLException;

    MembershipFeeDto searchMembershipFee(String id) throws SQLException;

    boolean deleteMembershipFee(String id) throws SQLException;

    List<MembershipFeeDto> getAllMemberShipFee() throws SQLException;

}
