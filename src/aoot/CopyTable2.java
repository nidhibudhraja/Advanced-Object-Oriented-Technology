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

public class CopyTable2 {
    public static void main(String arg[]){
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter table name");
        String tb_o=sc.next();
        String tb_n=tb_o.concat("Copy");
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr");
            Statement s=con.createStatement();
            String sql="Select * from "+tb_o;
            ResultSet rs=s.executeQuery(sql);
            ResultSetMetaData rsmd=rs.getMetaData();
            String sql_cpy="create table "+tb_n+" (";
            int cnt=rsmd.getColumnCount();
            for(int i=1;i<=cnt;i++)
            {
                String cname=rsmd.getColumnName(i);
                String ctype=rsmd.getColumnTypeName(i);
                int size=rsmd.getPrecision(i);
                String c=", ";
                String col_def=cname+" "+ctype+"("+size+")";
                if (i<cnt)
                    col_def=col_def.concat(", ");
                else
                    col_def=col_def.concat(")");
                sql_cpy=sql_cpy.concat(col_def);                
            }            
            System.out.println(sql_cpy);
            s.executeUpdate(sql_cpy);
            
        }catch(ClassNotFoundException e){
            Logger.getLogger(CopyTable.class.getName()).log(Level.SEVERE, null, e);                       
        }catch(SQLException e){
            Logger.getLogger(CopyTable.class.getName()).log(Level.SEVERE, null, e);
        }
    }
            
    
}
