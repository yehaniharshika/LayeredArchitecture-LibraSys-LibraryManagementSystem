package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.dto.MembershipFeeDto;

import java.sql.SQLException;
import java.util.List;

public interface MembershipFeeBO {
    String getTotalAmount() throws SQLException;

    String generateNextMembershipFeeId() throws SQLException;

    boolean saveMembershipFee(MembershipFeeDto dto) throws SQLException;

    boolean updateMembershipfee(MembershipFeeDto dto) throws SQLException;

    MembershipFeeDto searchMembershipFee(String id) throws SQLException;

    boolean deleteMembershipFee(String id) throws SQLException;

    List<MembershipFeeDto> getAllMemberShipFee() throws SQLException;
}
