package lk.ijse.LibraSys.bo.custom.Impl;

import lk.ijse.LibraSys.bo.custom.MemberBO;
import lk.ijse.LibraSys.dao.DAOFactory;
import lk.ijse.LibraSys.dao.custom.Impl.MemberDAOImpl;
import lk.ijse.LibraSys.dao.custom.Impl.MembershipFeeDAOImpl;
import lk.ijse.LibraSys.dao.custom.MemberDAO;
import lk.ijse.LibraSys.dao.custom.MembershipFeeDAO;
import lk.ijse.LibraSys.dto.MemberDto;
import lk.ijse.LibraSys.dto.MembershipFeeDto;
import lk.ijse.LibraSys.entity.Member;
import lk.ijse.LibraSys.entity.MembershipFee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberBOImpl implements MemberBO {

    MemberDAO memberDAO = (MemberDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBER);
    MembershipFeeDAO membershipFeeDAO = (MembershipFeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBERSHIP_FEE);
    @Override
    public String generateNextMemberId() throws SQLException {
        return memberDAO.generateNextId();
    }

    @Override
    public boolean saveMember(MemberDto dto) throws SQLException {
        return memberDAO.save(new Member(dto.getMid(),
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
        return memberDAO.update(new Member(dto.getMid(),
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
        Member member = memberDAO.search(mid);
        if (member != null){
            return new MemberDto(member);
        }
        return null;
    }

    @Override
    public List<MemberDto> getAllMember() throws SQLException {
        List<Member> allMembers = memberDAO.getAll();
        ArrayList<MemberDto> memberDtos = new ArrayList<>();

        for (Member member : allMembers){
            memberDtos.add(new MemberDto(
                    member.getMid(),
                    member.getName(),
                    member.getAddress(),
                    member.getGender(),
                    member.getTel(),
                    member.getEmailAddress(),
                    member.getIDNumber(),
                    member.getFeeId(),
                    member.getSNumber())
            );
        }
        return memberDtos;
    }

    @Override
    public MembershipFeeDto searchMembershipFee(String id) throws SQLException {
        MembershipFee membershipFee =  membershipFeeDAO.search(id);

        if (membershipFee != null){
            return new MembershipFeeDto(membershipFee);
        }
        return null;    }

    @Override
    public List<MembershipFeeDto> getAllMemberShipFee() throws SQLException {
        ArrayList<MembershipFee> allMembershipFees = membershipFeeDAO.getAll();
        ArrayList<MembershipFeeDto> membershipFeeDtos =  new ArrayList<>();

        for (MembershipFee membershipFee : allMembershipFees){
            membershipFeeDtos.add(new MembershipFeeDto(
                    membershipFee.getId(),
                    membershipFee.getName(),
                    membershipFee.getAmount(),
                    membershipFee.getDate(),
                    membershipFee.getStatus())
            );
        }
        return membershipFeeDtos;
    }
}
