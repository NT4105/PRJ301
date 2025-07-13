package Models.DAO;

import Models.DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDao implements IUserDao {
    public static Connection getConnection() throws Exception {
        Connection con;
        try {
            Context currentContext = new InitialContext();

            Context tomcatContext = (Context) currentContext.lookup("java:comp/env");

            DataSource ds = (DataSource) tomcatContext.lookup("SampleDB");

            con = ds.getConnection();
            return con;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public User login(String userName, String password) throws Exception {
        User user = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String lastName;
        boolean isAdmin;

        try {
            con = getConnection();
            String sql = "select LastName, isAdmin from Registration where [UserName]=? and [Password]=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

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

            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return user;
    }

    @Override
    public User getUserByUserName(String userName) throws Exception {
        User user = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String lastName, password;
        boolean isAdmin;

        try {
            con = getConnection();
            String sql = "select Password, LastName, isAdmin from Registration where [UserName]=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                password = rs.getString(1);
                lastName = rs.getString(2);
                isAdmin = rs.getBoolean(3);
                user = new User(userName, password, lastName, isAdmin);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return user;
    }

    @Override
    public List<User> searchUserByLastName(String searchValue) throws Exception {
        String userName, password, lastName;
        boolean isAdmin;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList();

        try {
            con = getConnection();
            String sql = "select UserName, Password, LastName, isAdmin from Registration "
                    + " where LastName like ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + searchValue + "%");
            rs = pstmt.executeQuery();
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

            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return userList;
    }

    @Override
    public boolean addUser(User user) throws Exception {
        PreparedStatement pstmt = null;
        Connection con = null;

        try {
            con = getConnection();
            String sql = "Insert Registration values (?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getLastName());
            pstmt.setBoolean(4, user.isIsAdmin());
            return pstmt.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public boolean deleteUser(String userName) throws Exception {
        PreparedStatement pstmt = null;
        Connection con = null;

        try {
            con = getConnection();
            String sql = "delete Registration where UserName=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userName);
            return pstmt.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public boolean updateUser(User user) throws Exception {
        PreparedStatement pstmt = null;
        Connection con = null;

        try {
            con = getConnection();
            String sql = "Update Registration Set Password=?, LastName=?, isAdmin=? Where [UserName]=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getLastName());
            pstmt.setBoolean(3, user.isIsAdmin());
            pstmt.setString(4, user.getUserName());
            return pstmt.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
