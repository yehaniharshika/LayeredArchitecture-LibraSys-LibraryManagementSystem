package lk.ijse.LibraSys.bo.custom.Impl;

import lk.ijse.LibraSys.bo.custom.BookRackBO;
import lk.ijse.LibraSys.dao.DAOFactory;
import lk.ijse.LibraSys.dao.custom.BookRackDAO;
import lk.ijse.LibraSys.dao.custom.Impl.BookRackDAOImpl;
import lk.ijse.LibraSys.dto.BookRackDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRackBOImpl implements BookRackBO {

    BookRackDAO bookRackDAO = (BookRackDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOKRACK);
    @Override
    public String generateNextRackCode() throws SQLException {
        return bookRackDAO.generateNextId();
    }

    @Override
    public boolean saveBookRack(BookRackDto dto) throws SQLException {
        return bookRackDAO.save(new BookRackDto(dto.getRackCode(),
                dto.getQtyBooks(),
                dto.getCategoryOfBooks(),
                dto.getNameOfBooks())
        );
    }

    @Override
    public boolean updateBookRack(BookRackDto dto) throws SQLException {
        return bookRackDAO.update(new BookRackDto(dto.getRackCode(),
                dto.getQtyBooks(),
                dto.getCategoryOfBooks(),
                dto.getNameOfBooks())
        );
    }

    @Override
    public boolean deleteBookRack(String rackCode) throws SQLException {
        return bookRackDAO.delete(rackCode);
    }

    @Override
    public BookRackDto searchBookRack(String rackCode) throws SQLException {
        return bookRackDAO.search(rackCode);
    }

    @Override
    public List<BookRackDto> getAllBookRack() throws SQLException {
        ArrayList<BookRackDto> bookRackDtos = new ArrayList<>();
        List<BookRackDto> bookRackList = bookRackDAO.getAll();

        for (BookRackDto dto : bookRackList){
            bookRackDtos.add(new BookRackDto(dto.getRackCode(),
                    dto.getQtyBooks(),
                    dto.getCategoryOfBooks(),
                    dto.getNameOfBooks())
            );
        }
        return bookRackDtos;
    }


}
