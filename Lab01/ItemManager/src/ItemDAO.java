import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO {
    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;database:Item;";
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        return con;
    }
    public void printItems() throws Exception {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String sql = "select ItemID, ItemName, Quantity from Items";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.format("%-10s %-15s %5d %n",
                        rs.getString(1), rs.getString("ItemName"), rs.getInt("Quantity"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage()); 
    } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null ) {
                con.close();
            }
        }
    }
    
    public void addItems(String itemID, String itemName, int Quantity) throws Exception {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = getConnection();
            String sql = "insert Items(ItemID, ItemName, Quantity) values (?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, itemID);
            ps.setString(2, itemName);
            ps.setInt(3, Quantity);
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            
        } finally {
            if (ps != null ) {import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO {
    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;database:Item;";
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        return con;
    }
    public void printItems() throws Exception {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            String sql = "select ItemID, ItemName, Quantity from Items";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.format("%-10s %-15s %5d %n",
                        rs.getString(1), rs.getString(ItemName), rs.getString(),args);
            }
        }
    }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO {
    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;database:Item;";
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        return con;
    }
    public void printItems() throws Exception {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            String sql = "select ItemID, ItemName, Quantity from Items";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.format("%-10s %-15s %5d %n",
                        rs.getString(1), rs.getString(ItemName), rs.getString(),args);
            }
        }
    }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO {
    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;database:Item;";
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        return con;
    }
    public void printItems() throws Exception {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            String sql = "select ItemID, ItemName, Quantity from Items";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.format("%-10s %-15s %5d %n",
                        rs.getString(1), rs.getString(ItemName), rs.getString(),args);
            }
        }
    }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO {
    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;database:Item;";
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        return con;
    }
    public void printItems() throws Exception {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            String sql = "select ItemID, ItemName, Quantity from Items";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.format("%-10s %-15s %5d %n",
                        rs.getString(1), rs.getString(ItemName), rs.getString(),args);
            }
        }
    }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO {
    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;database:Item;";
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        return con;
    }
    public void printItems() throws Exception {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            String sql = "select ItemID, ItemName, Quantity from Items";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.format("%-10s %-15s %5d %n",
                        rs.getString(1), rs.getString(ItemName), rs.getString(),args);
            }
        }
    }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO {
    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;database:Item;";
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        return con;
    }
    public void printItems() throws Exception {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            String sql = "select ItemID, ItemName, Quantity from Items";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.format("%-10s %-15s %5d %n",
                        rs.getString(1), rs.getString(ItemName), rs.getString(),args);
            }
        }
    }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO {
    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;database:Item;";
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        return con;
    }
    public void printItems() throws Exception {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            String sql = "select ItemID, ItemName, Quantity from Items";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.format("%-10s %-15s %5d %n",
                        rs.getString(1), rs.getString(ItemName), rs.getString(),args);
            }
        }
    }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO {
    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;database:Item;";
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        return con;
    }
    public void printItems() throws Exception {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            String sql = "select ItemID, ItemName, Quantity from Items";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.format("%-10s %-15s %5d %n",
                        rs.getString(1), rs.getString(ItemName), rs.getString(),args);
            }
        }
    }
}
v
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void DeleteItems(String itemID) throws Exception {
       PreparedStatement ps = null;
       Connection con = null;
       try {
           con = getConnection();
           String sql = "Delete from Items where ItemID= ?";
           ps = con.prepareStatement(sql);
           ps.setString(1, itemID);
           ps.executeUpdate();
           
           
       }
    }
}
