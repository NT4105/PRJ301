package DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private static final String hostname = "localhost";
    private static final String port = "1433";
    private static final String databaseName = "Lab02_JDBC_SE193851";
    private static final String username = "sa";
    private static final String password = "12345";

    private static final String url = "jdbc:sqlserver://" + hostname + ":" + port + ";databaseName=" + databaseName
            + ";trustServerCertificate=true";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User Login(String userName, String password) throws Exception {
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
                user = new User(userName, password, lastName, isAdmin);
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

    public List<User> getUserList() throws Exception {
        String userName, password, lastName;
        boolean isAdmin;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        try {
            con = getConnection();
            String sql = "select UserName, Password, LastName, isAdmin from Registration";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                userName = rs.getString(1);
                password = rs.getString(2);
                lastName = rs.getString(3);
                isAdmin = rs.getBoolean(4);
                User user = new User(userName, password, lastName, isAdmin);
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

    public boolean addUser(User user) throws Exception {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = getConnection();
            String sql = "Insert Registration values (?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getLastName());
            ps.setBoolean(4, user.isIsAdmin());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean updateUser(User user) throws Exception {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = getConnection();
            String sql = "Update Registration set password=?, lastName=?, isAdmin=? where userName=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getLastName());
            ps.setBoolean(3, user.isIsAdmin());
            ps.setString(4, user.getUserName());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteUser(String userName) throws Exception {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = getConnection();
            String sql = "Delete from Registration where userName=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
