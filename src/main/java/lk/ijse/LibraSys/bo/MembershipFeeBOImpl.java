package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.dao.custom.Impl.MembershipFeeDAOImpl;
import lk.ijse.LibraSys.dao.custom.MembershipFeeDAO;
import lk.ijse.LibraSys.dto.MembershipFeeDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembershipFeeBOImpl implements MembershipFeeBO{
    MembershipFeeDAO membershipFeeDAO =  new MembershipFeeDAOImpl();
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
        return membershipFeeDAO.save(new MembershipFeeDto(dto.getId(),
                dto.getName(),
                dto.getAmount(),
                dto.getDate(),
                dto.getStatus())
        );
    }

    @Override
    public boolean updateMembershipfee(MembershipFeeDto dto) throws SQLException {
        return membershipFeeDAO.update(new MembershipFeeDto(dto.getId(),
                dto.getName(),
                dto.getAmount(),
                dto.getDate(),
                dto.getStatus())
        );
    }

    @Override
    public MembershipFeeDto searchMembershipFee(String id) throws SQLException {
        return membershipFeeDAO.search(id);
    }

    @Override
    public boolean deleteMembershipFee(String id) throws SQLException {
        return membershipFeeDAO.delete(id);
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
