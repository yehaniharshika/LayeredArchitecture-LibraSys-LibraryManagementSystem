package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.SQLUtil;
import lk.ijse.LibraSys.dao.custom.MembershipFeeDAO;
import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.MembershipFeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MembershipFeeDAOImpl implements MembershipFeeDAO {

    @Override
    public String generateNextMembershipFeeId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT fee_id FROM membershipFee ORDER BY fee_id DESC LIMIT 1");
       /* Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT fee_id FROM membershipFee ORDER BY fee_id DESC LIMIT 1");

        ResultSet resultSet = pstm.executeQuery();*/
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

    @Override
    public String getTotalAmount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT SUM(amount)  FROM membershipFee");
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT SUM(amount)  FROM membershipFee");
//        ResultSet resultSet = pstm.executeQuery();

        String amount = null;
        if (resultSet.next()){
            amount = resultSet.getString(1);
        }
        return amount;
    }

    @Override
    public boolean saveMembershipFee(MembershipFeeDto dto) throws SQLException {
        return SQLUtil.execute( "INSERT INTO membershipFee VALUES(?,?,?,?,?)",
                dto.getId(),
                dto.getName(),
                dto.getAmount(),
                dto.getDate(),
                dto.getStatus()
        );
       /* Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO membershipFee VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, String.valueOf(dto.getAmount()));
        pstm.setString(4, String.valueOf(dto.getDate()));
        pstm.setString(5, dto.getStatus());

        boolean isSaved = pstm.executeUpdate() > 0;


        return isSaved;*/
    }

    @Override
    public boolean updateMembershipfee(MembershipFeeDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE membershipFee SET name = ?, amount = ? ,date = ?, status = ?  WHERE fee_id =?",
                dto.getName(),
                dto.getAmount(),
                dto.getDate(),
                dto.getStatus(),
                dto.getId()
        );
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "UPDATE membershipFee SET name = ?, amount = ? ,date = ?, status = ?  WHERE fee_id =?";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        pstm.setString(1, dto.getName());
//        pstm.setString(2, String.valueOf(dto.getAmount()));
//        pstm.setString(3, String.valueOf(dto.getDate()));
//        pstm.setString(4, dto.getStatus());
//        pstm.setString(5, dto.getId());
//
//        return pstm.executeUpdate() > 0 ;

    }

    @Override
    public MembershipFeeDto searchMembershipFee(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM membershipFee WHERE fee_id = ?",id);
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql ="SELECT * FROM membershipFee WHERE fee_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        ResultSet resultSet = pstm.executeQuery();*/

        MembershipFeeDto dto = null ;

        if(resultSet.next()){
            String feeid = resultSet.getString(1);
            String mName = resultSet.getString(2);
            double feeAmount = Double.parseDouble(resultSet.getString(3));
            LocalDate paidDate = LocalDate.parse(resultSet.getString(4));
            String feeStatus = resultSet.getString(5);

            dto = new MembershipFeeDto(feeid,mName,feeAmount,paidDate,feeStatus);

        }
        return dto;

    }

    @Override
    public boolean deleteMembershipFee(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM membershipFee WHERE fee_id = ?",id);
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql ="DELETE FROM membershipFee WHERE fee_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);

        return pstm.executeUpdate() > 0 ;*/
    }

    @Override
    public List<MembershipFeeDto> getAllMemberShipFee() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM membershipFee");
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM membershipFee";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet =pstm.executeQuery();*/

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