package lk.ijse.LibraSys.bo.custom;

import lk.ijse.LibraSys.bo.SuperBO;
import lk.ijse.LibraSys.dto.MemberDto;
import lk.ijse.LibraSys.dto.MembershipFeeDto;

import java.sql.SQLException;
import java.util.List;

public interface MemberBO extends SuperBO {
    String generateNextMemberId() throws SQLException;

    boolean saveMember(MemberDto dto) throws SQLException;

    boolean updateMember(MemberDto dto) throws SQLException;

    boolean deleteMember(String mid) throws SQLException;

    MemberDto searchMember(String mid) throws SQLException;

    List<MemberDto> getAllMember() throws SQLException;

    MembershipFeeDto searchMembershipFee(String id) throws SQLException;

    List<MembershipFeeDto> getAllMemberShipFee() throws SQLException;

}
