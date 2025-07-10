package Models.DAO.Services;

import Models.DAO.DaoFactory;
import Models.DAO.IUserDao;
import Models.DTO.User;
import java.util.List;


public class UserService implements IService<User>{
    IUserDao userDao;
    
    public UserService() {
        this.userDao = new DaoFactory().userDao();
    }
    
    @Override
    public User checkAccount(String userName, String password) throws Exception {
        return userDao.login(userName, password);
    }

    @Override
    public User getUserByUserName(String userName) throws Exception {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public List<User> getUsersByKeywordofLastName(String value) throws Exception {
        return userDao.searchUserByLastName(value);
    }

    @Override
    public boolean addNewUser(User user) throws Exception {
        return userDao.addUser(user);
    }

    @Override
    public boolean removeUser(String userName) throws Exception {
        return userDao.deleteUser(userName);
    }

    @Override
    public boolean updateUser(User user) throws Exception {
        return userDao.updateUser(user);
    }
    
}
