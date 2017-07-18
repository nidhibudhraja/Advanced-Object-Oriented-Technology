
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RS_Manipulation {
 
    public static void main(String[] args) {

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Found...");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");
            System.out.println("Connection established...");
            Statement ps=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            ResultSet rs = ps.executeQuery("select * from jobs");
            /*rs.moveToInsertRow();
            rs.updateString(1, "FI_CLERK");
            rs.updateString(2, "Finance Clerk");
            rs.updateInt(3, 2500);
            rs.updateInt(4, 5500);
            rs.insertRow();
            */
            ResultSetMetaData rsmd=rs.getMetaData();
            System.out.println("No. of Columns="+rsmd.getColumnCount());
            for(int i=1;i<=rsmd.getColumnCount();i++)
            System.out.println(rsmd.getColumnName(i)+" "+rsmd.getColumnTypeName(i));
            System.out.println("--CLIENTS--");
            rs.beforeFirst();
            while(rs.next())
            {
                if(rs.getString(1).endsWith("CLERK"))
                {                   
                    System.out.println(rs.getRow()+" "+rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getInt(4) );
                }
            }
            System.out.println("--MANAGERS--");
            rs.beforeFirst();
            while(rs.next())
            {
                if(rs.getString(1).endsWith("MGR"))
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getInt(3) );
            }
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(RS_Manipulation.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
}
