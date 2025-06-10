package Models.DAO;

import Models.DTO.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection cnn;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String connectionString = "jdbc:sqlserver://localhost:1433;database=SampleDB";

            cnn = DriverManager.getConnection(connectionString, "sa", "12345");

            return cnn;
        } catch (Exception e) {
            throw e;
        }

    }
    
    public User login(String userName, String password) throws Exception{
        User user = null;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String lastName;
        boolean isAdmin;
        try {
            cnn = getConnection();
            String sql = "SELECT LastName, isAdmin FROM Registration WHERE [UserName]=? AND [Password]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userName);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            while(rs.next()) {
                lastName = rs.getString(1);
                isAdmin = rs.getBoolean(2);
                user = new User(userName, password, lastName, isAdmin);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
            if(preStm != null) {
                preStm.close();
            }
            if(cnn != null) {
                cnn.close();
            }
        }
        return user;
    }

    public User getUserByUserName(String userName) throws Exception {
        User user = null;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String password, lastName;
        boolean isAdmin;
        
        try {
            cnn = getConnection();
            String sql = "SELECT Password, LastName, isAdmin FROM Registration WHERE [UserName]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userName);
            rs = preStm.executeQuery();
            while(rs.next()) {
                password = rs.getString("Password");
                lastName = rs.getString("LastName");
                isAdmin = rs.getBoolean("isAdmin");
                user = new User(userName, password, lastName, isAdmin);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
            if(preStm != null) {
                preStm.close();
            }
            if(cnn != null) {
                cnn.close();
            }
        }
        return user;
    }
    
     public List<User> searchUserByLastName(String searchValue) throws Exception {
        String userName, password, lastName;
        boolean isAdmin;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        User user = null;
        List<User> userList = new ArrayList();
        try {
            cnn = getConnection();
            String sql = "SELECT UserName, Password, LastName, isAdmin FROM Registration WHERE LastName LIKE ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + searchValue + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                userName = rs.getString(1);
                password = rs.getString(2);
                lastName = rs.getString(3);
                isAdmin = rs.getBoolean(4);
                user = new User(userName, password, lastName, isAdmin);
                if (user != null) {
                    userList.add(user);
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return userList;
    }

     public boolean deleteUser(String userName) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "DELETE FROM Registration WHERE [UserName]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userName);
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            if(preStm != null) {
                preStm.close();
            }
            if(cnn != null) {
                cnn.close();
            }
        }
    }
     
     public boolean addUser(User user) throws Exception {
         PreparedStatement preStm = null;
         Connection cnn = null;
         try {
             cnn = getConnection();
             String sql = "INSERT INTO Registration (UserName, Password, LastName, isAdmin) VALUES (?, ?, ?, ?)";
             preStm = cnn.prepareStatement(sql);
             preStm.setString(1, user.getUserName());
             preStm.setString(2, user.getPassword());
             preStm.setString(3, user.getLastName());
             preStm.setBoolean(4, user.isIsAdmin());
             return preStm.executeUpdate() > 0;
         } catch (Exception e) {
             throw e;
         } finally {
             if(preStm != null) {
                 preStm.close();
             }
             if(cnn != null) {
                 cnn.close();
             }
         }
     }
     
     public boolean updateUser(User user) throws Exception {
         PreparedStatement preStm = null;
         Connection cnn = null;
         try {
             cnn = getConnection();
             String sql = "UPDATE Registration SET Password=?, LastName=?, isAdmin=? WHERE UserName=?";
             preStm = cnn.prepareStatement(sql);
             preStm.setString(1, user.getPassword());
             preStm.setString(2, user.getLastName());
             preStm.setBoolean(3, user.isIsAdmin());
             preStm.setString(4, user.getUserName());
             return preStm.executeUpdate() > 0;
         } catch (Exception e) {
             throw e;
         } finally {
             if(preStm != null) {
                 preStm.close();
             }
             if(cnn != null) {
                 cnn.close();
             }
         }
     }
     
}
