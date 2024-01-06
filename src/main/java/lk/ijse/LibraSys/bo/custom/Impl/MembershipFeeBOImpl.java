package lk.ijse.LibraSys.bo.custom.Impl;

import lk.ijse.LibraSys.bo.custom.MembershipFeeBO;
import lk.ijse.LibraSys.dao.DAOFactory;
import lk.ijse.LibraSys.dao.custom.Impl.MembershipFeeDAOImpl;
import lk.ijse.LibraSys.dao.custom.MembershipFeeDAO;
import lk.ijse.LibraSys.dto.MembershipFeeDto;
import lk.ijse.LibraSys.entity.MembershipFee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembershipFeeBOImpl implements MembershipFeeBO {
    MembershipFeeDAO membershipFeeDAO = (MembershipFeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBERSHIP_FEE);
    @Override
    public String getTotalAmount() throws SQLException {
        return membershipFeeDAO.getTotalAmount();
    }

    @Override
    public String generateNextMembershipFeeId() throws SQLException {
        return membershipFeeDAO.generateNextId();
    }

    @Override
    public boolean saveMembershipFee(MembershipFeeDto dto) throws SQLException {
        return membershipFeeDAO.save(new MembershipFee(dto.getId(),
                dto.getName(),
                dto.getAmount(),
                dto.getDate(),
                dto.getStatus())
        );
    }

    @Override
    public boolean updateMembershipfee(MembershipFeeDto dto) throws SQLException {
        return membershipFeeDAO.update(new MembershipFee(dto.getId(),
                dto.getName(),
                dto.getAmount(),
                dto.getDate(),
                dto.getStatus())
        );
    }

    @Override
    public MembershipFeeDto searchMembershipFee(String id) throws SQLException {
        MembershipFee membershipFee =  membershipFeeDAO.search(id);

        if (membershipFee != null){
            return new MembershipFeeDto(membershipFee);
        }
        return null;
    }

    @Override
    public boolean deleteMembershipFee(String id) throws SQLException {
        return membershipFeeDAO.delete(id);
    }

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
