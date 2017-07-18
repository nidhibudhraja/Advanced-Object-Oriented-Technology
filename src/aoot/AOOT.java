
package aoot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AOOT {

    public static void main(String[] args) {
   
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AOOT.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","HR","hr");
            String sql="insert into student values (?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setString(2, "Ram");
            ps.setString(3,"CSE");
            ps.setInt(4,90 );
            int i=ps.executeUpdate();
            System.out.println(i+" record(s) inserted");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AOOT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
