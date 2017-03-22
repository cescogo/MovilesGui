package Vista;


import Control.Control;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ccg
 */
public class AgregarAdministrador extends JFrame {
    
     public AgregarAdministrador(Control c) {
            super("agregar administrador");
        ajustarComponentes(getContentPane());   
        gestor=c;
        setMinimumSize(new Dimension(500,350));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void ajustarComponentes(Container c)
    {
        c.setLayout(new BorderLayout());
         principal = new JPanel();
        principal.setLayout(new BorderLayout());
        principal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
         formulario =new JPanel(new GridBagLayout());
        gc=new GridBagConstraints();
        
        gc.insets=new Insets(4,4,4,4);
        
        gc.gridx=0;
        gc.gridy=0;        
        nombre= new JLabel("Nombre:");
        formulario.add(nombre,gc);
        
        gc.gridx=0;
        gc.gridy=1;
        formulario.add(cedula =new JLabel("Cedula:"),gc);
        
      
        gc.gridx=0;
        gc.gridy=2;
        formulario.add(clave =new JLabel("Clave:"),gc);
        
         gc.gridx=0;
        gc.gridy=3;
        formulario.add( telefono=new JLabel("Telefono:"),gc);
        
        gc.gridx=0;
        gc.gridy=4;
        formulario.add(email =new JLabel("Email:"),gc);
        
        gc.gridx=1;
        gc.gridy=0;
        formulario.add(t_nombre=new JTextField(20),gc);
        
        gc.gridx=1;
        gc.gridy=1;
        formulario.add(t_cedula=new JTextField(20),gc);
       
        gc.gridx=1;
        gc.gridy=2;
        formulario.add(t_clave=new JTextField(20),gc);
        
        gc.gridx=1;
        gc.gridy=3;
        formulario.add(t_telefono=new JTextField(20),gc);
        
        gc.gridx=1;
        gc.gridy=4;
        formulario.add(t_email=new JTextField(20),gc);
        
        gc.gridx=0;
        gc.gridy=7;
                aceptar=new JButton("Aceptar");
                aceptar.addActionListener((ActionEvent e)->{Agregar();});
        formulario.add(aceptar,gc);
        
        gc.gridx=1;
        gc.gridy=7;
               
                cancel=new JButton("cancelar");
                cancel.addActionListener((ActionEvent e)->{salir();});
        formulario.add(cancel,gc);
        
       principal.add(formulario,BorderLayout.CENTER);
       
       c.add(principal,BorderLayout.CENTER);
        
    }
     private void salir()
    {
        VenOpcEstudiante vi = new VenOpcEstudiante(gestor);
        vi.init();
        this.dispose();
    }
    public void init() {
        setVisible(true);
    }
     private boolean blanco()
    {
        if(t_nombre.getText()==""||
           t_clave.getText()==""||
           t_email.getText()==""||
           t_telefono.getText()==""||
           t_cedula.getText()=="")
        {
            return true;
        }
        else 
            return false;
    }
       private void Agregar()
     {
         if(blanco())
         {
               JOptionPane.showMessageDialog(null, "Campos vacios","Error",JOptionPane.ERROR_MESSAGE);
           
         }
         else 
             if(gestor.existeAdmi(t_cedula.getText()))
             {
                 JOptionPane.showMessageDialog(null, "Administrador ya existe","Error",JOptionPane.ERROR_MESSAGE);
             }
         else
         {
             int aux=Integer.parseInt(t_telefono.getText());
             gestor.agregarAdministrador(t_nombre.getText(), t_cedula.getText(), aux, t_email.getText(), t_clave.getText());
             salir();
         }
     }
      private JPanel principal;
    private JPanel formulario;
    private GridBagConstraints gc;
    private JLabel nombre;
    private JLabel telefono;
    private JLabel clave;
    private JLabel email;
    private JLabel cedula;
    private JTextField t_nombre;
    private JTextField t_clave;
    private JTextField t_email;
    private JTextField t_telefono;
    private JTextField t_cedula;
    private JButton aceptar;
    private JButton cancel;
    private Control gestor;
}
