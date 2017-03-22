/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.Control;
import Modelo.Curso;
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
public class ModificarCurso extends JFrame{
     public ModificarCurso(Control c) {
            super("modificar Curso");
            gestor=c;
        ajustarComponentes(getContentPane());   
        setMinimumSize(new Dimension(550,300));
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
        buscar= new JLabel("codigo del curso:");
        formulario.add(buscar,gc);
        
        gc.gridx=0;
        gc.gridy=2;        
        nombre= new JLabel("Nombre:");
        formulario.add(nombre,gc);
        
          
      
        gc.gridx=0;
        gc.gridy=3;
        formulario.add(creditos =new JLabel("Creditos:"),gc);
        
         gc.gridx=0;
        gc.gridy=4;
        formulario.add( h_semanales=new JLabel("horas semanales:"),gc);
        
         gc.gridx=0;
        gc.gridy=5;
        formulario.add( num_ciclo=new JLabel("ciclo en el que se imparte:"),gc);   
       
        
         gc.gridx=1;
        gc.gridy=0;
        formulario.add(t_buscar=new JTextField(20),gc);
        
        gc.gridx=1;
        gc.gridy=2;
        formulario.add(t_nombre=new JTextField(20),gc);
        
        
       
        gc.gridx=1;
        gc.gridy=3;
        formulario.add(t_creditos=new JTextField(20),gc);
        
        gc.gridx=1;
        gc.gridy=4;
        formulario.add(t_h_semanales=new JTextField(20),gc);
        
          gc.gridx=1;
        gc.gridy=5;
        formulario.add(t_num_ciclo=new JTextField(20),gc);
        
       
        
         gc.gridx=3;
        gc.gridy=0;
                b_buscar=new JButton("buscar");
                b_buscar.addActionListener((ActionEvent e)->{existe();});
        formulario.add(b_buscar,gc);
        
        
        gc.gridx=0;
        gc.gridy=7;
                aceptar=new JButton("Aceptar");
                aceptar.addActionListener((ActionEvent e)->{modificar();});
        formulario.add(aceptar,gc);
        
        gc.gridx=1;
        gc.gridy=7;
               
                cancel=new JButton("cancelar");
                cancel.addActionListener((ActionEvent e)->{salir();});
        formulario.add(cancel,gc);
        
       principal.add(formulario,BorderLayout.CENTER);
       
       c.add(principal,BorderLayout.CENTER);
        
    }
    
    private void modificar()
    {
        if(blanco())
        {
             JOptionPane.showMessageDialog(null, "campo vacio","Error",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            Curso c = new Curso();
           c.setNombre( t_nombre.getText());
            c.setCreditos(Integer.parseInt(t_creditos.getText()));
            c.setHsemanales(Integer.parseInt(t_h_semanales.getText()));
            c.setNum_ciclo(Integer.parseInt(t_num_ciclo.getText()));
            c.setCodigo(t_buscar.getText());
            gestor.ActualizarCurso(c);
            salir();
        }
           
    }
    
    private void existe()
    {
        if(t_buscar.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "campo vacio","Error",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            
            if(!gestor.existeCur(t_buscar.getText()))
        {
            JOptionPane.showMessageDialog(null, "no existe el curso agreguelo por favor ","Error",JOptionPane.ERROR_MESSAGE);
            salir();
        }
        else
        {
             Curso c= new Curso();
            gestor.MostrarCurso(c, t_buscar.getText(), 2);
            t_nombre.setText(c.getNombre());
            t_creditos.setText(String.valueOf(c.getCreditos()));
            t_h_semanales.setText(String.valueOf(c.getHsemanales()));            
            t_num_ciclo.setText(String.valueOf(c.getNum_ciclo()));
        }
        }
    }
    
    private boolean blanco()
    {
         if(t_nombre.getText().isEmpty()||
            t_creditos.getText().isEmpty()||
            t_h_semanales.getText().isEmpty()||
            t_num_ciclo.getText().isEmpty())
         {
             return true;
         }
         else return false;
    }
    
     private void salir()
    {
        VenOpcCurso vi = new VenOpcCurso(gestor);
        vi.init();
        this.dispose();
    }
     
  
     
    public void init() {
        setVisible(true);
    }
      private JPanel principal;
    private JPanel formulario;
    private GridBagConstraints gc;
    private JLabel nombre;
    private JLabel creditos;
    private JLabel h_semanales;
    private JLabel buscar;
    private JLabel num_ciclo;
    private JTextField t_nombre;
    private JTextField t_buscar;
    private JTextField t_creditos;
    private JTextField t_h_semanales;
    private JTextField t_carrera;
    private JTextField t_num_ciclo;
    private JButton b_buscar;
    private JButton aceptar;
    private JButton cancel;
    private Control gestor;
}
