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
public class Principal {
    public static void main(String[] args) {
        Crud datos = new Crud();
        
        Ventana alumnos = new Ventana(datos);
        
        alumnos.setVisible(true);
    }
}
