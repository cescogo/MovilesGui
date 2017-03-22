/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author ccg
 */
public class AgregarCarrera extends JFrame {
     public AgregarCarrera(Control c) {
            super("agregar Carrera");
        ajustarComponentes(getContentPane());   
        setMinimumSize(new Dimension(500,250));
        gestor=c;
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
        formulario.add(nombre =new JLabel("nombre de la carrera:"),gc);
        
      
        gc.gridx=0;
        gc.gridy=1;
        formulario.add(codigo =new JLabel("codigo de la carrera:"),gc);
        
        
        
        gc.gridx=1;
        gc.gridy=0;
        formulario.add(t_nombre=new JTextField(20),gc);
       
        gc.gridx=1;
        gc.gridy=1;
        formulario.add(t_codigo=new JTextField(20),gc);
        
        
        gc.gridx=0;
        gc.gridy=3;
                aceptar=new JButton("Aceptar");
                aceptar.addActionListener((ActionEvent e)->{agregar();});
        formulario.add(aceptar,gc);
        
        gc.gridx=1;
        gc.gridy=3;
               
                cancel=new JButton("cancelar");
                cancel.addActionListener((ActionEvent e)->{salir();});
        formulario.add(cancel,gc);
        
       principal.add(formulario,BorderLayout.CENTER);
       
       c.add(principal,BorderLayout.CENTER);
        
    }
    private boolean blanco()
    {
        if(t_nombre.getText().isEmpty()||
           t_codigo.getText().isEmpty())
        {
            return true;
        }
        else 
            return false;
    }
      
    private void agregar()
    {
          
     
         if(blanco())
         {
               JOptionPane.showMessageDialog(null, "Campos vacios","Error",JOptionPane.ERROR_MESSAGE);
           
         }
         else
         if(gestor.existeCarrera(t_codigo.getText()))
         {
             JOptionPane.showMessageDialog(null, "Carrera ya existe","Error",JOptionPane.ERROR_MESSAGE);
         }
         else 
         {
             
             gestor.agregarCarrera(t_nombre.getText(), t_codigo.getText());
             salir();
         }
     
    }
     private void salir()
    {
        VenOpcCarrera vi = new VenOpcCarrera(gestor);
        vi.init();
        this.dispose();
    }
    
    public void init() {
        setVisible(true);
    }
      private JPanel principal;
    private JPanel formulario;
    private GridBagConstraints gc;
   private Control gestor;
    private JLabel nombre;
    private JLabel codigo;
    
    private JTextField t_nombre;
    private JTextField t_codigo;
    
    private JButton aceptar;
    private JButton cancel;
}
