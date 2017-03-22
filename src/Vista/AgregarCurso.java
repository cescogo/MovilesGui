/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.Control;
import Modelo.*;
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
public class AgregarCurso extends JFrame {
    
        public AgregarCurso(Control c) {
            super("agregar Curso");
        ajustarComponentes(getContentPane());   
        setMinimumSize(new Dimension(500,350));
        setResizable(false);
        gestor=c;
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
        formulario.add(codigo =new JLabel("Codigo:"),gc);
        
      
        gc.gridx=0;
        gc.gridy=2;
        formulario.add(creditos =new JLabel("Creditos:"),gc);
        
         gc.gridx=0;
        gc.gridy=3;
        formulario.add( h_semanales=new JLabel("horas semanales:"),gc);
        
        gc.gridx=0;
        gc.gridy=4;
        formulario.add(carrera =new JLabel("codigo de Carrera:"),gc);
        
        gc.gridx=0;
        gc.gridy=5;
        formulario.add(num_ciclo =new JLabel("número de ciclo:"),gc);
        
        gc.gridx=1;
        gc.gridy=0;
        formulario.add(t_nombre=new JTextField(20),gc);
        
        gc.gridx=1;
        gc.gridy=1;
        formulario.add(t_codigo=new JTextField(20),gc);
       
        gc.gridx=1;
        gc.gridy=2;
        formulario.add(t_creditos=new JTextField(20),gc);
        
        gc.gridx=1;
        gc.gridy=3;
        formulario.add(t_h_semanales=new JTextField(20),gc);
        
        gc.gridx=1;
        gc.gridy=4;
        formulario.add(t_carrera=new JTextField(20),gc);
        
        gc.gridx=1;
        gc.gridy=5;
        formulario.add(t_num_ciclo=new JTextField(20),gc);
        
        gc.gridx=0;
        gc.gridy=7;
                aceptar=new JButton("Aceptar");
                aceptar.addActionListener((ActionEvent e)->{agregar();});
        formulario.add(aceptar,gc);
        
        gc.gridx=1;
        gc.gridy=7;
               
                cancel=new JButton("cancelar");
                cancel.addActionListener((ActionEvent e)->{salir();});
        formulario.add(cancel,gc);
        
       principal.add(formulario,BorderLayout.CENTER);
       
       c.add(principal,BorderLayout.CENTER);
        
    }
    
    private boolean blanco()
    {
        if( t_nombre.getText().isEmpty()||
            t_codigo.getText().isEmpty()||
            t_creditos.getText().isEmpty()||
            t_h_semanales.getText().isEmpty()||
            t_carrera.getText().isEmpty()||
            t_num_ciclo.getText().isEmpty())
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
        {
            if(!gestor.existeCarrera(t_carrera.getText()))
            {
                JOptionPane.showMessageDialog(null, "Carrera no existe agreguela por favor","Error",JOptionPane.ERROR_MESSAGE);
                salirCar();
            }
            else
                if(gestor.existeCur(t_codigo.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Curso ya existe ","Error",JOptionPane.ERROR_MESSAGE);
                }
            else
            {
                gestor.agregarCurso(t_codigo.getText(),  t_nombre.getText(), Integer.parseInt(t_creditos.getText()), Integer.parseInt( t_h_semanales.getText()), t_carrera.getText(), Integer.parseInt(t_num_ciclo.getText()));
               salir();   
            }
        }
    }
    
     private void salir()
    {
        VenOpcCurso vi = new VenOpcCurso(gestor);
        vi.init();
        this.dispose();
    }
     
      private void salirCar()
    {
        AgregarCarrera vi = new AgregarCarrera(gestor);
        vi.init();
        this.dispose();
    }
     public void init() {
        setVisible(true);
    }
      private JPanel principal;
      private Control gestor;
    private JPanel formulario;
    private GridBagConstraints gc;
    private JLabel nombre;
    private JLabel creditos;
    private JLabel h_semanales;
    private JLabel carrera;
    private JLabel codigo;
    private JLabel num_ciclo;
    private JTextField t_nombre;
    private JTextField t_codigo;
    private JTextField t_creditos;
    private JTextField t_h_semanales;
    private JTextField t_carrera;
    private JTextField t_num_ciclo;
    private JButton aceptar;
    private JButton cancel;
}
