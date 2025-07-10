package Models.DAO;

import Models.DTO.User;
import java.util.List;


public interface IUserDao {
    User login (String userName, String password) throws Exception;
    User getUserByUserName (String userName) throws Exception;
    List<User> searchUserByLastName (String searchValue) throws Exception;
    boolean addUser (User user) throws Exception;
    boolean deleteUser (String userName) throws Exception;
    boolean updateUser (User user) throws Exception;
    
}
