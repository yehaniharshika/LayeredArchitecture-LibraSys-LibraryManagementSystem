package lk.ijse.LibraSys.bo;

import lk.ijse.LibraSys.bo.custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        AUTHOR,BOOK,BOOKRACK,DASHBOARD,LOGIN,MEMBER,MEMBERSHIP_FEE,PLACE_BOOKS_SUPPLIER_ORDER,RESERVATION,SIGNUP
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case AUTHOR:
                return new AuthorBOImpl();
            case BOOK:
                return new BookBOImpl();
            case BOOKRACK:
                return new BookRackBOImpl();
            case DASHBOARD:
                return new DashboardBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case MEMBER:
                return new MemberBOImpl();
            case MEMBERSHIP_FEE:
                return new MembershipFeeBOImpl();
            case PLACE_BOOKS_SUPPLIER_ORDER:
                return new PlaceBooksSupplierOrderBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            case SIGNUP:
                return new SignUpBOImpl();
            default:
                return null;
        }
    }

}
