package lk.ijse.LibraSys.dao.custom;

import lk.ijse.LibraSys.dao.CrudDAO;
import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.MembershipFeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface MembershipFeeDAO extends CrudDAO<MembershipFeeDto> {

    String getTotalAmount() throws SQLException;
//    String generateNextMembershipFeeId() throws SQLException;
//
//    boolean saveMembershipFee(MembershipFeeDto dto) throws SQLException;
//
//    boolean updateMembershipfee(MembershipFeeDto dto) throws SQLException;
//
//    MembershipFeeDto searchMembershipFee(String id) throws SQLException;
//
//    boolean deleteMembershipFee(String id) throws SQLException;
//
//    List<MembershipFeeDto> getAllMemberShipFee() throws SQLException;

}
