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
public class EliminarEstudiante extends JFrame{
    public EliminarEstudiante(Control c) {
            super("eliminar Persona");
        ajustarComponentes(getContentPane()); 
        gestor=c;
        setMinimumSize(new Dimension(500,150));
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
        formulario.add(cedula =new JLabel("Cedula de la persona a eliminar:"),gc);    
      
        gc.gridx=1;
        gc.gridy=0;
        formulario.add(t_cedula=new JTextField(20),gc);
      
        gc.gridx=0;
        gc.gridy=2;
                aceptar=new JButton("Aceptar");
                aceptar.addActionListener((ActionEvent e)->{eliminar();});
        formulario.add(aceptar,gc);
        
        gc.gridx=1;
        gc.gridy=2;
               
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
        if(t_cedula.getText()=="")
        {
            return true;
        }
        else 
            return false;
    }
       private void eliminar()
     {
         if(blanco())
         {
               JOptionPane.showMessageDialog(null, "Campo vacio","Error",JOptionPane.ERROR_MESSAGE);
           
         }
         else 
             
         {
            boolean aux= gestor.borrarP(t_cedula.getText());
            if(!aux)
            {
                JOptionPane.showMessageDialog(null, "Persona no existe","Error",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
             salir();
            }
         }
     }
      private JPanel principal;
    private JPanel formulario;
    private GridBagConstraints gc;  
    private JLabel cedula;
    private JTextField t_cedula;    
    private JButton aceptar;
    private JButton cancel;
    private Control gestor;
    
}
