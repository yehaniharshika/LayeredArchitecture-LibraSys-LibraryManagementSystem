package lk.ijse.LibraSys.dao;

import lk.ijse.LibraSys.dao.custom.Impl.*;

public class DAOFactory {
    private  static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public  static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
       AUTHOR ,BOOK ,BOOKRACK ,BOOKS_SUPPLIER_DETAILS ,LOGIN ,MEMBER ,MEMBERSHIP_FEE ,RESERVATION ,SIGNUP ,SUPPLIER
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case AUTHOR:
                return new AuthorDAOImpl();
            case BOOK:
                return new BookDAOImpl();
            case BOOKRACK:
                return new BookRackDAOImpl();
            case BOOKS_SUPPLIER_DETAILS:
                return new BooksSupplierDetailsDAOImpl();
            case MEMBER:
                return new MemberDAOImpl();
            case MEMBERSHIP_FEE:
                return new MembershipFeeDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case SIGNUP:
                return new SignupDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            default:
                return null;
        }
    }
}
