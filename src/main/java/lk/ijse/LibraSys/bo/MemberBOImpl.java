package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.dao.custom.Impl.MemberDAOImpl;
import lk.ijse.LibraSys.dao.custom.MemberDAO;
import lk.ijse.LibraSys.dto.MemberDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberBOImpl implements MemberBO{

    MemberDAO memberDAO = new MemberDAOImpl();
    @Override
    public String generateNextMemberId() throws SQLException {
        return memberDAO.generateNextId();
    }

    @Override
    public String getMemberCount() throws SQLException {
        return memberDAO.getCount();
    }

    @Override
    public boolean saveMember(MemberDto dto) throws SQLException {
        return memberDAO.save(new MemberDto(dto.getMid(),
                dto.getName(),
                dto.getAddress(),
                dto.getGender(),
                dto.getTel(),
                dto.getEmailAddress(),
                dto.getIDNumber(),
                dto.getFeeId(),
                dto.getSNumber())
        );
    }

    @Override
    public boolean updateMember(MemberDto dto) throws SQLException {
        return memberDAO.update(new MemberDto(dto.getMid(),
                dto.getName(),
                dto.getAddress(),
                dto.getGender(),
                dto.getTel(),
                dto.getEmailAddress(),
                dto.getIDNumber(),
                dto.getFeeId(),
                dto.getSNumber())
        );
    }

    @Override
    public boolean deleteMember(String mid) throws SQLException {
        return memberDAO.delete(mid);
    }

    @Override
    public MemberDto searchMember(String mid) throws SQLException {
        return memberDAO.search(mid);
    }

    @Override
    public List<MemberDto> getAllMember() throws SQLException {
        ArrayList<MemberDto> memberDtos = new ArrayList<>();
        List<MemberDto> membersList = memberDAO.getAll();

        for (MemberDto dto : membersList){
            memberDtos.add(new MemberDto(dto.getMid(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getGender(),
                    dto.getTel(),
                    dto.getEmailAddress(),
                    dto.getIDNumber(),
                    dto.getFeeId(),
                    dto.getSNumber())
            );
        }
        return memberDtos;
    }
}
