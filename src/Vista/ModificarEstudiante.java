/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.Control;
import Modelo.Persona;
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

/**
 *
 * @author ccg
 */
public class ModificarEstudiante extends JFrame{
     public ModificarEstudiante(Control c) {
            super("modificar Persona");
        ajustarComponentes(getContentPane()); 
        gestor=c;
        setMinimumSize(new Dimension(600,450));
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
        gc.gridy=1;        
        buscar= new JLabel("cedula de la persona a modificar:");
        formulario.add(buscar,gc);
        
        gc.gridx=0;
        gc.gridy=2;        
        nombre= new JLabel("Nombre:");
        formulario.add(nombre,gc);
        
       
        
      
        gc.gridx=0;
        gc.gridy=3;
        formulario.add(clave =new JLabel("Clave:"),gc);
        
         gc.gridx=0;
        gc.gridy=4;
        formulario.add( telefono=new JLabel("Telefono:"),gc);
        
        gc.gridx=0;
        gc.gridy=5;
        formulario.add(email =new JLabel("Email:"),gc);
        
       
        gc.gridx=1;
        gc.gridy=1;
        formulario.add(t_buscar=new JTextField(20),gc);
        
        gc.gridx=1;
        gc.gridy=2;
        formulario.add(t_nombre=new JTextField(20),gc);
        
       
       
        gc.gridx=1;
        gc.gridy=3;
        formulario.add(t_clave=new JTextField(20),gc);
        
        gc.gridx=1;
        gc.gridy=4;
        formulario.add(t_telefono=new JTextField(20),gc);
        
        gc.gridx=1;
        gc.gridy=5;
        formulario.add(t_email=new JTextField(20),gc);
        
        gc.gridx=2;
        gc.gridy=1;
               
                b_buscar=new JButton("buscar");
                b_buscar.addActionListener((ActionEvent e)->{buscar();});
        formulario.add(b_buscar,gc);
        
        gc.gridx=0;
        gc.gridy=8;
                aceptar=new JButton("Aceptar");
                aceptar.addActionListener((ActionEvent e)->{modificar();});
        formulario.add(aceptar,gc);
        
        gc.gridx=1;
        gc.gridy=8;
               
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
     private void buscar()
     {
        
        if(t_buscar.getText().isEmpty())
        {
             JOptionPane.showMessageDialog(null, "campo vacio","Error",JOptionPane.ERROR_MESSAGE);
        }
             if(!gestor.existeEst(t_buscar.getText()))
             {
                 JOptionPane.showMessageDialog(null, "usuario no existe","Error",JOptionPane.ERROR_MESSAGE);
             }
         else
         {
                  Persona a=new Persona(0);
         gestor.buscarPer(a,t_buscar.getText());
             t_nombre.setText(a.getNombre());
              t_clave.setText(a.getClave());
              t_email.setText(a.getEmail());
              t_telefono.setText(String.valueOf(a.getTelefono()));
         }
     }
     
     private boolean blanco()
    {
        if(t_nombre.getText().isEmpty()||
           t_clave.getText().isEmpty()||
           t_email.getText().isEmpty()||
           t_telefono.getText().isEmpty())
        {
            return true;
        }
        else 
            return false;
    }
      
     private void modificar()
     {
         
          if(blanco())
         {
               JOptionPane.showMessageDialog(null, "Campos vacios","Error",JOptionPane.ERROR_MESSAGE);
           
         }
         else 
          {
              Persona a= new Persona(0);
              a.setClave(t_clave.getText());
              a.setEmail(t_email.getText());
              a.setNombre(t_nombre.getText());
              a.setTelefono(Integer.parseInt(t_telefono.getText()));
              a.setCedula(t_buscar.getText());
              gestor.actualizaP(a);
            salir();
          }
     }
     
    public void init() {
        setVisible(true);
    }
      private JPanel principal;
    private JPanel formulario;
    private GridBagConstraints gc;
    private JLabel buscar;
    private JLabel nombre;
    private JLabel telefono;
    private JLabel clave;
    private JLabel email;
    private JLabel cedula;
    private JLabel fe_nac;
    private JTextField t_buscar;
    private JTextField t_nombre;
    private JTextField t_clave;
    private JTextField t_email;
    private JTextField t_telefono;
   
    
    private JButton b_buscar;
    private JButton aceptar;
    private JButton cancel;
    private Control gestor;
    
}
