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
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ccg
 */
public class HistorialAcademico extends JFrame {
    
  public HistorialAcademico(Control c, String ced) {
            super("Mostrar cursos de un estudiante");
            tabla= new ModeloTabla6();
            cedula=ced;    
            l = new ArrayList<Nota>();
        ajustarComponentes(getContentPane());   
        gestor=c;
        setMinimumSize(new Dimension(600,400));
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
       
        
         formulario =new JPanel(new BorderLayout());
       
         
        
       JPanel pTabla=new JPanel();
       
        pTabla.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        pTabla.setLayout(new BorderLayout());
        

          JScrollPane desplazamientoTabla = new JScrollPane(
                  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        tablaDatos = new JTable();        
        tablaDatos.setModel(tabla);
        
        desplazamientoTabla.setViewportView(tablaDatos);
        
            
        pTabla.add(BorderLayout.CENTER, desplazamientoTabla);
        salir= new JButton("salir");
        salir.addActionListener((ActionEvent e)->{salir();});
        JPanel cancelar= new JPanel(new BorderLayout());
        cancelar.add(salir,BorderLayout.EAST);
        formulario.add(cancelar,BorderLayout.SOUTH);
        formulario.add(pTabla,BorderLayout.CENTER);
        principal.add(formulario);
        c.add(principal);
        
    }
     private void salir()
    {
        VentanaInicio vi = new VentanaInicio();
        vi.init();
        l.clear();
        this.dispose();
    }
     
     
    public void init() {
        setVisible(true);
        mostrar();
    }
    
    private void venGrupos(String cur, int gru)
    {
        MostrarEstGrupo vi = new MostrarEstGrupo(gestor,cur,gru,cedula);
        vi.init();
        this.dispose();
        
    }
   
    private void mostrar()
    { 
        
        
       gestor.ConsultaHistorial(cedula,l);
          
            Curso p= new Curso();
          Nota c= new Nota();
           for(int i=0; i< l.size();i++)
            {     
                c=l.get(i);
                gestor.MostrarCurso(p,c.getCURSO(),2);
                tabla.addRow(new Object[]{c.getCURSO(),p.getNombre(),c.getNOTA(),c.getCondision()});
            }  
            
    }
    
   
      private JPanel principal;
      private Control gestor;
    private JPanel formulario;
   
    private JButton salir;
   private JTable tablaDatos;
   public ModeloTabla6 tabla;
    
    private  ArrayList<Nota> l;
    private String cedula;
    
    
}
 class ModeloTabla6 extends DefaultTableModel {

        public ModeloTabla6() {
            super(new Object[][]{},
                    new String[]{"codigo","nombre Curso","nota","condicion"});
            
            }
        
        @Override
        public boolean isCellEditable(int filas, int columnas)
        {
            return false;
        }
    }


