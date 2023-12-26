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

public class MembershipFeeDAOImpl {

    public String generateNextMembershipFeeId(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT fee_id FROM membershipFee ORDER BY fee_id DESC LIMIT 1");

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return splitFeeId(resultSet.getString(1));
        }
        return splitFeeId(null);
    }

    private String splitFeeId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("F0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "F00"+id;
            }else {
                if (length < 3){
                    return "F0"+id;
                }else {
                    return "F"+id;
                }
            }
        }
        return "F001";
    }
    public static String getTotalAmount() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT SUM(amount)  FROM membershipFee");
        ResultSet resultSet = pstm.executeQuery();

        String amount = null;
        if (resultSet.next()){
            amount = resultSet.getString(1);
        }
        return amount;
    }

    public boolean saveMembersipFee(MembershipFeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO membershipFee VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, String.valueOf(dto.getAmount()));
        pstm.setString(4, String.valueOf(dto.getDate()));
        pstm.setString(5, dto.getStatus());

        boolean isSaved = pstm.executeUpdate() > 0;


        return isSaved;
    }


    public boolean updateMembershipfee(MembershipFeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE membershipFee SET name = ?, amount = ? ,date = ?, status = ?  WHERE fee_id =?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, String.valueOf(dto.getAmount()));
        pstm.setString(3, String.valueOf(dto.getDate()));
        pstm.setString(4, dto.getStatus());
        pstm.setString(5, dto.getId());

        return pstm.executeUpdate() > 0 ;

    }

    public MembershipFeeDto searchMembershipFee(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql ="SELECT * FROM membershipFee WHERE fee_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        ResultSet resultSet = pstm.executeQuery();

        MembershipFeeDto dto = null ;

        if(resultSet.next()){
            String feeid = resultSet.getString(1);
            String mName = resultSet.getString(2);
            double feeAmount = Double.parseDouble(resultSet.getString(3));
            LocalDate feedate = LocalDate.parse(resultSet.getString(4));
            String mStatus = resultSet.getString(5);

            dto = new MembershipFeeDto(feeid,mName,feeAmount,feedate,mStatus);

        }
        return dto;

    }

    public boolean deleteMembershipFee(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql ="DELETE FROM membershipFee WHERE fee_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);

        return pstm.executeUpdate() > 0 ;
    }

    public List<MembershipFeeDto> getAllMemberShipFee() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM membershipFee";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet =pstm.executeQuery();

        ArrayList<MembershipFeeDto> feeList = new ArrayList<>();

        while (resultSet.next()){
            feeList.add(
                    new MembershipFeeDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            resultSet.getDate(4).toLocalDate(),
                            resultSet.getString(5)

            ));

        }
        return  feeList;
    }


}