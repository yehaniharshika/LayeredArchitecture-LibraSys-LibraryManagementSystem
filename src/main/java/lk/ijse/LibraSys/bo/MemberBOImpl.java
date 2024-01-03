package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.dao.custom.Impl.MemberDAOImpl;
import lk.ijse.LibraSys.dao.custom.Impl.MembershipFeeDAOImpl;
import lk.ijse.LibraSys.dao.custom.MemberDAO;
import lk.ijse.LibraSys.dao.custom.MembershipFeeDAO;
import lk.ijse.LibraSys.dto.MemberDto;
import lk.ijse.LibraSys.dto.MembershipFeeDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberBOImpl implements MemberBO{

    MemberDAO memberDAO = new MemberDAOImpl();
    MembershipFeeDAO membershipFeeDAO = new MembershipFeeDAOImpl();
    @Override
    public String generateNextMemberId() throws SQLException {
        return memberDAO.generateNextId();
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

    @Override
    public MembershipFeeDto searchMembershipFee(String id) throws SQLException {
        return membershipFeeDAO.search(id);
    }

    @Override
    public List<MembershipFeeDto> getAllMemberShipFee() throws SQLException {
        ArrayList<MembershipFeeDto> membershipFeeDtos =  new ArrayList<>();
        List<MembershipFeeDto> membershipFeeList = membershipFeeDAO.getAll();

        for (MembershipFeeDto  dto : membershipFeeList){
            membershipFeeDtos.add(new MembershipFeeDto(
                    dto.getId(),
                    dto.getName(),
                    dto.getAmount(),
                    dto.getDate(),
                    dto.getStatus())
            );
        }
        return membershipFeeDtos;
    }
}
