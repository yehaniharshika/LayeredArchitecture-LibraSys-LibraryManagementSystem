package lk.ijse.LibraSys.dao.custom;

import lk.ijse.LibraSys.db.DbConnection;
import lk.ijse.LibraSys.dto.MemberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MemberDAO {
    String generateNextMemberId(String mid) throws SQLException;

    //for dashboard update
    String getMemberCount() throws SQLException;

    boolean saveMember(MemberDto dto) throws SQLException;

    boolean updateMember(MemberDto dto) throws SQLException;

    boolean deleteMember(String mid) throws SQLException;

    MemberDto searchMember(String mid) throws SQLException;

    List<MemberDto> getAllMember() throws SQLException;


}
