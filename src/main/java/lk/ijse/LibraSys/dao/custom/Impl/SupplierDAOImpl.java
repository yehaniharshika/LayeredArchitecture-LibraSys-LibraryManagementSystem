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
        /*Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT supplierId FROM supplier ORDER BY supplierId DESC LIMIT 1");

        ResultSet resultSet = pstm.executeQuery();*/
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
       /* Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(supplierId) FROM supplier");
*/
        String count = null;
//        ResultSet resultSet = pstm.executeQuery();
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
        /*Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO supplier VALUES (?,?,?,?)");

        pstm.setString(1,supplierId);
        pstm.setString(2,supName);
        pstm.setString(3,contactNumber);
        pstm.setString(4,email);

        return pstm.executeUpdate() > 0;*/
    }

    @Override
    public boolean update(Supplier entity) throws SQLException {
        return SQLUtil.execute("UPDATE supplier SET supplierName=?,contactNumber=?,email=? WHERE supplierId=?",
                entity.getSupplierName(),
                entity.getContactNumber(),
                entity.getEmail(),
                entity.getSupplierId()
        );
       /* Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE supplier SET supplierName=?,contactNumber=?,email=? WHERE supplierId=?");

        pstm.setString(1, dto.getSupplierName());
        pstm.setString(2, dto.getContactNumber());
        pstm.setString(3, dto.getEmail());
        pstm.setString(4, dto.getSupplierId());

        boolean isUpdated = pstm.executeUpdate()>0;
        return  isUpdated;*/
    }

    @Override
    public boolean delete(String supplierId) throws SQLException {
        return SQLUtil.execute("DELETE  FROM  supplier WHERE  supplierId=?",supplierId);
       /* Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE  FROM  supplier WHERE  supplierId=?");
        pstm.setString(1,supplierId);

        boolean isDeleted = pstm.executeUpdate()>0;
        return  isDeleted;*/
    }

    @Override
    public Supplier search(String supplierId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT  * FROM supplier WHERE supplierId=?",supplierId);
        /*Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT  * FROM supplier WHERE supplierId=?");
        pstm.setString(1,supplierId);

        ResultSet resultSet = pstm.executeQuery();*/
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
       /* Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM supplier");
*/
        List<Supplier> supplierList = new ArrayList<>();
//        ResultSet resultSet = pstm.executeQuery();
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
