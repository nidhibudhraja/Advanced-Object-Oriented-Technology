
package aoot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CopyTable {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter table name");
        String tb_name=sc.next();
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr");
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("Select * from "+tb_name);
            while(rs.next())
            {
                if(rs.getString(1).endsWith("MGR"))
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getInt(3) );
            }
            ResultSetMetaData rsmd=rs.getMetaData();
            String tb_copy=tb_name.concat("Copy");           
            ResultSet rs2=s.executeQuery("SELECT dbms_metadata.get_ddl( 'TABLE', '"+tb_name+"') FROM DUAL");
            rs2.next();
            String ss=rs2.getString(1);
            String r = ss.replace(tb_name, tb_copy);
            r=r.replace("JOB","JOB2");
            System.out.println(r);
            s.executeQuery(r);
            //s.executeUpdate("Insert "+tb_copy+" select * from "+tb_name);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CopyTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CopyTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
