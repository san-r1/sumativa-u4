/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD_Alumnos;

/**
 *
 * @author chati
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Crud {
    
    public void insertDatos(String nombre,String ap,String am, String num_cuenta, String carrera){
        
        String query= "insert into alumnos(nombre,ap,am,num_cuenta,carrera) values(?,?,?,?,?)";
        
        try(Connection con = dbConnection.conectar()){
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setString(1, nombre);
            ps.setString(2, ap);
            ps.setString(3, am);
            ps.setString(4, num_cuenta);
            ps.setString(5, carrera);
            
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error al insertar Datos");
            e.printStackTrace();
        }
    }
    public List<Object []> leerDatos(){
        List<Object []>lista = new ArrayList<>();
        
        String query="select * from alumnos";
        try(Connection con = dbConnection.conectar()){
            PreparedStatement ps = con.prepareStatement(query);
           
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Object[] fila = {
                  rs.getInt("id_alumno"),
                  rs.getString("nombre"),
                  rs.getString("ap"),
                  rs.getString("am"),
                  rs.getString("num_cuenta"),
                  rs.getString("carrera")
                };
                
                lista.add(fila);
            }
        }
        catch(SQLException e){
            System.out.println("Error al ejecutar consulta");
            e.printStackTrace();
        }
        return lista;
    }
    public void eliminarDatos(String n_cuenta){
    Connection con = null;

    try{
        con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement("delete from alumnos WHERE num_cuenta = ?");
        ps.setString(1, n_cuenta);
        ps.executeUpdate();

        System.out.println("Datos eliminados correctamente");

    } catch (SQLException e) {
        System.out.println("Error al eliminar: " + e);
    }
}
    public void actualizarDatos(String nombre, String ape_paterno, String ape_materno, String n_cuenta, String carrera){
    Connection con = null;

    try{
        con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement("UPDATE alumnos SET nombre=?, ap=?, am=?, carrera=? WHERE num_cuenta=?");
        ps.setString(1, nombre);
        ps.setString(2, ape_paterno);
        ps.setString(3, ape_materno);
        ps.setString(4, carrera);
        ps.setString(5, n_cuenta); 
        ps.executeUpdate();

        System.out.println("Datos actualizados correctamente");

    } catch (SQLException e) {
        System.out.println("Error al actualizar: " + e);
    }
}
}
