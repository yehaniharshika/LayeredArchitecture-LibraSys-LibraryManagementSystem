package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.SQLUtil;
import lk.ijse.LibraSys.dao.custom.MemberDAO;
import lk.ijse.LibraSys.dto.AuthorDto;
import lk.ijse.LibraSys.dto.MemberDto;
import lk.ijse.LibraSys.entity.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAOImpl implements MemberDAO {
    private MembershipFeeDAOImpl membershipFeeModel =new MembershipFeeDAOImpl();

   //for dashboard update
    @Override
    public   String getCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(mid) FROM  member");

        String count = null;
        if (resultSet.next()){
            count = resultSet.getString(1);
        }
        return count;
    }

    @Override
    public  String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT mid FROM member ORDER BY  mid DESC LIMIT 1");

        if (resultSet.next()){
            return splitMemberId(resultSet.getString(1));
        }
        return splitMemberId(null);
    }

    private String splitMemberId(String currentMemberId) {
        if(currentMemberId != null) {
            String[] strings = currentMemberId.split("M0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "M00"+id;
            }else {
                if (length < 3){
                    return "M0"+id;
                }else {
                    return "M"+id;
                }
            }
        }
        return "M001";
    }

    @Override
    public boolean save(Member entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO member VALUES(?, ?, ?, ?, ?, ? ,?,?,?)",
                entity.getMid(),
                entity.getName(),
                entity.getAddress(),
                entity.getGender(),
                entity.getTel(),
                entity.getEmailAddress(),
                entity.getIDNumber(),
                entity.getFeeId(),
                entity.getSNumber()
        );
    }

    @Override
    public  boolean update(Member entity) throws SQLException {
        return SQLUtil.execute("UPDATE member SET name=?, address=?, gender=? ,tel =? ,EmailAddress =?,IDNumber=?, feeId=?,sNumber=? WHERE  mid=?",
                entity.getName(),
                entity.getAddress(),
                entity.getGender(),
                entity.getTel(),
                entity.getEmailAddress(),
                entity.getIDNumber(),
                entity.getFeeId(),
                entity.getSNumber(),
                entity.getMid()
        );
    }

    @Override
    public boolean delete(String mid) throws SQLException {
        return SQLUtil.execute("DELETE  FROM  member WHERE mid=?",mid);

    }

    @Override
    public Member search(String mid) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT  * FROM  member WHERE mid=?",mid);

        Member entity = null;

        if(resultSet.next()){
            entity = new Member(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8),
                resultSet.getString(9)
            );
        }
        return entity;
    }

    @Override
    public ArrayList<Member> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM member");

        ArrayList<Member> memberList = new ArrayList<>();

        while(resultSet.next()){
           memberList.add(new Member(
                  resultSet.getString(1),
                  resultSet.getString(2),
                  resultSet.getString(3),
                  resultSet.getString(4),
                  resultSet.getString(5),
                  resultSet.getString(6),
                  resultSet.getString(7),
                  resultSet.getString(8),
                  resultSet.getString(9)
           ));

        }
        return memberList;
    }



}
