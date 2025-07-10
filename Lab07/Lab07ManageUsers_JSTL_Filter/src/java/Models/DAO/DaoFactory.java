package Models.DAO;



public class DaoFactory implements IDaoFactory{
    public DaoFactory() {
    }
    
    @Override
    public IUserDao userDao() {
        return new UserDao();
    }
}
