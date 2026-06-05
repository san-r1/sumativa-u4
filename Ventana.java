
package CRUD_Alumnos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Ventana extends JFrame{
    
    JPanel panel;
    JTextField nombre,ape_paterno,ape_materno,n_cuenta,carrera;
    JButton btn_reg, btn_eliminar, btn_actualizar;
    JTable tabla;
    DefaultTableModel est;
    JScrollPane scroll;
    
    private Crud db_op;
    
    public Ventana(Crud db_op){
        this.db_op = db_op;
        
        setTitle("Alumnos");
        setSize(450,600);
        setLocationRelativeTo(null);
        Components();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void Components(){
        panel();
        etiquetas();
        entradas();
        botones(); 
        resultado();
        
        cargarTabla();
    }
    
    private void panel(){
        panel=new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
    }
        
    private void etiquetas(){
        JLabel intro=new JLabel();
        intro.setBounds(20,40,400,25);
        intro.setText("REGISTRO DE ALUMNOS");
        intro.setFont(new Font("Times New Roman",Font.BOLD,14));
        intro.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel nomb=new JLabel();
        nomb.setBounds(20,80,80,30);
        nomb.setText("Nombre: ");
        
        JLabel ap_p=new JLabel();
        ap_p.setBounds(20,120,120,30);
        ap_p.setText("Apellido Paterno: ");
        
        JLabel ap_m=new JLabel();
        ap_m.setBounds(20,160,120,30);
        ap_m.setText("Apellido Materno: ");
        
        JLabel num_c=new JLabel();
        num_c.setBounds(20,200,120,30);
        num_c.setText("Número de Cuenta: ");
        
        JLabel carr=new JLabel();
        carr.setBounds(20,240,120,30);
        carr.setText("Carrera: ");
        
        panel.add(intro);
        panel.add(nomb);
        panel.add(ap_p);
        panel.add(ap_m);
        panel.add(num_c);
        panel.add(carr);
    }
    
    private void entradas(){
        nombre=new JTextField();
        nombre.setBounds(135,80,200,30);
        
        ape_paterno=new JTextField();
        ape_paterno.setBounds(135,120,200,30);
        
        ape_materno=new JTextField();
        ape_materno.setBounds(135,160,200,30);
        
        n_cuenta=new JTextField();
        n_cuenta.setBounds(135,200,200,30);
        
        carrera=new JTextField();
        carrera.setBounds(135,240,200,30);
        
        panel.add(nombre);
        panel.add(ape_paterno);
        panel.add(ape_materno);
        panel.add(n_cuenta);
        panel.add(carrera);
    }
        
    private void resultado(){
        String[] titulos = {"ID", "Nombre","Ap Paterno","Ap Materno","Num Cuenta","Carrera"};
        est = new DefaultTableModel(null,titulos){
        
        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
           }
        };
              
        tabla=new JTable();
        tabla.setModel(est);
        
        scroll = new JScrollPane(tabla);
        scroll.setBounds(20,340,400,200);
        
        panel.add(scroll);
       
    }
        
    private void botones(){
        btn_reg=new JButton();
        btn_reg.setBounds(30,290,100,30);
        btn_reg.setText("Registrar");
        
        btn_reg.addActionListener((ActionEvent)->{
            db_op.insertDatos(nombre.getText(), ape_paterno.getText(), ape_materno.getText(), n_cuenta.getText(), carrera.getText());
        
        nombre.setText("");
        ape_paterno.setText("");
        ape_materno.setText("");
        n_cuenta.setText("");
        carrera.setText("");
        });
        
        btn_eliminar=new JButton();
        btn_eliminar.setBounds(150,290,100,30);
        btn_eliminar.setText("eliminar");
        btn_eliminar.addActionListener((ActionEvent)->{
            db_op.eliminarDatos(n_cuenta.getText());
            nombre.setText("");
            ape_paterno.setText("");
            ape_materno.setText("");
            n_cuenta.setText("");
            carrera.setText("");
        });
        
        btn_actualizar=new JButton();
        btn_actualizar.setBounds(300,290,100,30);
        btn_actualizar.setText("actualizar");       
                btn_actualizar.addActionListener((ActionEvent) -> {
            db_op.actualizarDatos(nombre.getText(), ape_paterno.getText(), ape_materno.getText(), n_cuenta.getText(), carrera.getText());
            nombre.setText("");
            ape_paterno.setText("");
            ape_materno.setText("");
            n_cuenta.setText("");
            carrera.setText("");
        });
        
        panel.add(btn_reg);
        panel.add(btn_eliminar);
        panel.add(btn_actualizar);
    }
    
    private void cargarTabla(){
        
        est.setRowCount(0);
        
        List<Object[]> alumnos = db_op.leerDatos();
        
        for(Object [] filas : alumnos){
         est.addRow(filas);
    }
    }
       
}
