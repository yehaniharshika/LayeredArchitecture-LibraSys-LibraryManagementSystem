package lk.ijse.LibraSys.dao.custom.Impl;

import lk.ijse.LibraSys.dao.SQLUtil;
import lk.ijse.LibraSys.dao.custom.SupplierDAO;
import lk.ijse.LibraSys.dto.AuthorDto;
import lk.ijse.LibraSys.dto.SupplierDto;
import lk.ijse.LibraSys.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT supplierId FROM supplier ORDER BY supplierId DESC LIMIT 1");

        if (resultSet.next()){
            return splitSupplierId(resultSet.getString(1));

        }
        return  splitSupplierId(null);
    }

    @Override
    public boolean save(Supplier dto) throws SQLException {
        return false;
    }


    private String splitSupplierId(String currentId) {
            if(currentId != null) {
                String[] strings = currentId.split("SP00");
                int id = Integer.parseInt(strings[1]);
                id++;
                String ID = String.valueOf(id);
                int length = ID.length();
                if (length < 2){
                    return "SP000"+id;
                }else {
                    if (length < 3){
                        return "SP00"+id;
                    }else {
                        if (length < 4){
                            return "SP0"+id;
                        }else {
                            return "SP"+id;
                        }
                    }
                }
            }
            return "SP0001";
        }


    @Override
    public String getCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(supplierId) FROM supplier");

        String count = null;
        if (resultSet.next()){
            count = resultSet.getString(1);
        }
        return count;

    }

    @Override
    public boolean saveSupplier(String supplierId,String supName,String contactNumber,String email) throws SQLException {
        return SQLUtil.execute("INSERT INTO supplier VALUES (?,?,?,?)",
                supplierId,
                supName,
                contactNumber,
                email
        );

    }

    @Override
    public boolean update(Supplier entity) throws SQLException {
        return SQLUtil.execute("UPDATE supplier SET supplierName=?,contactNumber=?,email=? WHERE supplierId=?",
                entity.getSupplierName(),
                entity.getContactNumber(),
                entity.getEmail(),
                entity.getSupplierId()
        );

    }

    @Override
    public boolean delete(String supplierId) throws SQLException {
        return SQLUtil.execute("DELETE  FROM  supplier WHERE  supplierId=?",supplierId);

    }

    @Override
    public Supplier search(String supplierId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT  * FROM supplier WHERE supplierId=?",supplierId);

        Supplier entity = null;
        if (resultSet.next()){
            entity = new Supplier(
              resultSet.getString(1),
              resultSet.getString(2),
              resultSet.getString(3),
              resultSet.getString(4)
            );
        }
        return entity;
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier");

        List<Supplier> supplierList = new ArrayList<>();

        while (resultSet.next()){
            supplierList.add(new Supplier(
                 resultSet.getString(1),
                 resultSet.getString(2),
                 resultSet.getString(3),
                 resultSet.getString(4)
            ));
        }
        return (ArrayList<Supplier>) supplierList;
    }


}
