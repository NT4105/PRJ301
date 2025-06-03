
package DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionString = "jdbc:sqlserver://localhost:1443;database:Lab03_SearchUsers";
            Connection con = DriverManager.getConnection(connectionString, "sa", "12345");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }
    
    public User login(String userName, String password) throws Exception {
        User user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String lastName;
        boolean isAdmin;
        
        
        try {
            con = getConnection();
            String sql = "select LastName, isAdmin from Registration where [UserName] = ? and [Password] = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                lastName = rs.getString(1);
                isAdmin = rs.getBoolean(2);
                user = new User (userName, password, lastName, isAdmin);
            }
            
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            
            if (ps != null) {
                ps.close();
            }
            
            if (con != null) {
                con.close();
            }
        }
        return user;
    }
    
    public List<User> searchUserbyLastName(String searchValue) throws Exception {
        String userName, password, lastName;
        boolean isAdmin;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList();
        
        try {
            con = getConnection();
            String sql = "select UserName, Password, LastName, isAdmin from Registration"+"where LastName like ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+searchValue+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                userName = rs.getString(1);
                password = rs.getString(2);
                lastName = rs.getString(3);
                isAdmin = rs.getBoolean(4);
                User user = new User (userName, password, lastName, isAdmin);
                userList.add(user);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            
            if (ps != null) {
                ps.close();
            }
            
            if (con != null) {
                con.close();
            }
        }
        if (userList.isEmpty()) {
            return null;
        }
        return userList;
    }
    
    public boolean deleteUser(String userName) throws Exception {
        PreparedStatement ps = null;
        Connection con = null;
        
        try {
            con = getConnection();
            String sql = "delete Registration where UserName = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (ps != null) {
                ps.close();
            }
            
            if (con != null ) {
                con.close();
            }
        }
    }
}
