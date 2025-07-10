package Models.DAO.Services;

import Models.DTO.User;
import java.util.List;

public interface IService<T> {
    
    User checkAccount(String userName, String password) throws Exception;
    User getUserByUserName (String userName) throws Exception;
    List<User> getUsersByKeywordofLastName (String value) throws Exception;
    boolean addNewUser (User user) throws Exception;
    boolean removeUser(String userName) throws Exception;
    boolean updateUser (User user) throws Exception;
}
