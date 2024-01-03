package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.dto.MemberDto;

import java.sql.SQLException;
import java.util.List;

public interface MemberBO {
    String generateNextMemberId() throws SQLException;

    //for dashboard update
    String getMemberCount() throws SQLException;

    boolean saveMember(MemberDto dto) throws SQLException;

    boolean updateMember(MemberDto dto) throws SQLException;

    boolean deleteMember(String mid) throws SQLException;

    MemberDto searchMember(String mid) throws SQLException;

    List<MemberDto> getAllMember() throws SQLException;
}
