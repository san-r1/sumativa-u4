
package CRUD_Alumnos;

import java.sql.*;

public class dbConnection {
    
    static String url="jdbc:mysql://localhost:3306/ejemplo_alumno";
    static String user="root";
    static String pass="";
    
    public static Connection conectar(){
        
        Connection con=null;
        
        try{
           con=DriverManager.getConnection(url,user,pass);
           System.out.println("Conexion exitosa");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        return con;
    }
    
}
