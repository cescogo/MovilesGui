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
public class ModificarGrupo extends JFrame{
      public ModificarGrupo(Control c) {
            super("modificar Grupo");
            gestor=c;
        ajustarComponentes(getContentPane());   
        setMinimumSize(new Dimension(700,250));
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
        formulario.add(buscar =new JLabel("codigo del curso mas numeri de grupo (ejemplo 411-1:"),gc);
        
      
//        gc.gridx=0;
//        gc.gridy=1;
//        formulario.add(horario =new JLabel("horario:"),gc);
        
         gc.gridx=0;
        gc.gridy=2;
        formulario.add( profesor=new JLabel("identificacion del profesor:"),gc);
        
        
        
      gc.gridx=1;
        gc.gridy=0;
        formulario.add(t_buscar=new JTextField(20),gc);
      
       
//        gc.gridx=1;
//        gc.gridy=1;
//        formulario.add(t_horario=new JTextField(20),gc);
        
        gc.gridx=1;
        gc.gridy=2;
        formulario.add(t_profesor=new JTextField(20),gc);
        
        
        gc.gridx=3;
        gc.gridy=0;
                b_buscar=new JButton("Buscar");
                b_buscar.addActionListener((ActionEvent e)->{buscar();});
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
    
    
    private void buscar()
    {
        if(t_buscar.getText().isEmpty())
        {
             JOptionPane.showMessageDialog(null, "Campos vacios","Error",JOptionPane.ERROR_MESSAGE);
           
        }
        else
        {
            
              if(!gestor.existeGrupo(t_buscar.getText()))
              {
                   JOptionPane.showMessageDialog(null, "grupo no existe agreguelo por favor ","Error",JOptionPane.ERROR_MESSAGE);
                   salir();
              }
              else
              {Grupo g= new Grupo();
              gestor.Buscar(g, t_buscar.getText());
//                  t_horario.setText(g.getHorario());
                  t_profesor.setText(g.getProfesor());
              }
        }
    }
    
   private void modificar()
   {
       if(  t_profesor.getText().isEmpty())
       {
            JOptionPane.showMessageDialog(null, "Campos vacios","Error",JOptionPane.ERROR_MESSAGE);
       }
       else
       {
           Persona p= new Persona(0);
           gestor.buscarPer(p, t_profesor.getText());
           if(p.getTipo() != 3)
           {
               JOptionPane.showMessageDialog(null, "No existe el profesor por favor agreguelo","Error",JOptionPane.ERROR_MESSAGE);
               AgregarProfesor vi= new AgregarProfesor(gestor);
               vi.init();
               this.dispose();
           }
           else
           {
           Grupo c= new Grupo();
           c.setProfesor(t_profesor.getText());
           c.setId(t_buscar.getText());
           gestor.actualizar(c);
           salir();
           }
       }
   }
    
     private void salir()
    {
        VenOpcGrupo vi = new VenOpcGrupo(gestor);
        vi.init();
        this.dispose();
    }
    public void init() {
        setVisible(true);
    }
      private JPanel principal;
    private JPanel formulario;
    private GridBagConstraints gc;
//    private JLabel ciclo;
 
//    private JLabel horario;
    private JLabel profesor;
    private JLabel buscar;
    
//    private JTextField t_ciclo;
   
//    private JTextField t_horario;
    private JTextField t_profesor;
   private JTextField t_buscar;
   
    private Control gestor;
    private JButton aceptar;
    private JButton cancel;
    private JButton b_buscar;
}
