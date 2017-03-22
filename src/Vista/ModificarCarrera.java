/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.Control;
import Modelo.Carrera;
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
public class ModificarCarrera extends JFrame {
    public ModificarCarrera(Control c) {
            super("modificar Carrera");
            gestor=c;
        ajustarComponentes(getContentPane());   
        setMinimumSize(new Dimension(500,250));
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
        formulario.add(codigo =new JLabel("codigo de la carrera:"),gc);
        
        gc.gridx=0;
        gc.gridy=2;
        formulario.add(nombre =new JLabel("nombre de la carrera:"),gc);
        
      
       
        
         gc.gridx=1;
        gc.gridy=1;
        formulario.add(t_codigo=new JTextField(20),gc);
        
        gc.gridx=1;
        gc.gridy=2;
        formulario.add(t_nombre=new JTextField(20),gc);
       
        
        gc.gridx=2;
        gc.gridy=1;
                buscar=new JButton("buscar");
                buscar.addActionListener((ActionEvent e)->{buscar();});
        formulario.add(buscar,gc);
        
        
        gc.gridx=0;
        gc.gridy=3;
                aceptar=new JButton("Aceptar");
                aceptar.addActionListener((ActionEvent e)->{modificar();});
        formulario.add(aceptar,gc);
        
        gc.gridx=1;
        gc.gridy=3;
               
                cancel=new JButton("cancelar");
                cancel.addActionListener((ActionEvent e)->{salir();});
        formulario.add(cancel,gc);
        
       principal.add(formulario,BorderLayout.CENTER);
       
       c.add(principal,BorderLayout.CENTER);
        
    }
    
    
    private void buscar()
    {
        if(t_codigo.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Campos vacios","Error",JOptionPane.ERROR_MESSAGE);
        }else 
            if(!gestor.existeCarrera(t_codigo.getText()))
            {
                 JOptionPane.showMessageDialog(null, "Carrera no existe","Error",JOptionPane.ERROR_MESSAGE);
            }
        else
        {
            Carrera c= new Carrera();
            gestor.MostrarCarreraC(c, t_codigo.getText());
            t_nombre.setText(c.getNombre());
        }
    }
    
    private void modificar()
    {
        if(t_nombre.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Campos vacios","Error",JOptionPane.ERROR_MESSAGE);
        }else 
        {
            Carrera c= new Carrera();
            c.setNombre(t_nombre.getText());
            c.setCodigo(t_codigo.getText());
            gestor.ActualizarCarrera(c);
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
    private JButton buscar;
    private JButton aceptar;
    private JButton cancel;
}
