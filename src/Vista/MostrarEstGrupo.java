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
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ccg
 */
public class MostrarEstGrupo extends JFrame {
    
public MostrarEstGrupo(Control c, String curso, int grupo,String ced_prof) {
            super("Mostrar Estudiantes de un Grupo");
            tabla= new ModeloTabla5();
            this.curso=curso;    
            this.grupo=grupo;
            this.ced_prof=ced_prof;
            l = new ArrayList<>();
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
        modifica= new JPanel(new FlowLayout());
       
        nota = new JLabel("nota a modificar");

        t_nota = new JTextField(10);

        
        agregar= new JButton("agregar nota");
        agregar.addActionListener((ActionEvent e)->{agregar();});

        
        modifica.add(nota);
        modifica.add(t_nota);
        modifica.add(agregar);
        modifica.setVisible(false);
         formulario =new JPanel(new BorderLayout());
       
         formulario.add(modifica,BorderLayout.NORTH);
        
       JPanel pTabla=new JPanel();
       
        pTabla.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        pTabla.setLayout(new BorderLayout());
        

          JScrollPane desplazamientoTabla = new JScrollPane(
                  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        tablaDatos = new JTable();        
        tablaDatos.setModel(tabla);
        
        desplazamientoTabla.setViewportView(tablaDatos);
        tablaDatos.addMouseListener(new MouseAdapter() 
        {
            
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(e.getClickCount()==2 && tablaDatos.getSelectedColumn()==2)
                {
                    colum= tablaDatos.getSelectedColumn();
                    row= tablaDatos.getSelectedRow();
                    modifica.setVisible(true);
                   
                }
            }
        });
        
    
        pTabla.add(BorderLayout.CENTER, desplazamientoTabla);
        salir= new JButton("salir");
        salir.addActionListener((ActionEvent e)->{salir();});
        modificar= new JButton("aceptar");
        modificar.addActionListener((ActionEvent e)->{modificarNotas();});
        
        
        JPanel cancelar= new JPanel(new FlowLayout());
        cancelar.add(modificar);
        cancelar.add(salir);
         
        formulario.add(cancelar,BorderLayout.SOUTH);
        formulario.add(pTabla,BorderLayout.CENTER);
        principal.add(formulario);
        c.add(principal);
        
    }
    
    private void modificarNotas()
    {
        Iterator<Nota> ite=l.iterator();
        
       while(ite.hasNext())
        {
            gestor.actualiza(ite.next());
            
        }
       salir();
    }
    
    private void agregar()
    {
        tablaDatos.setValueAt(Float.parseFloat(t_nota.getText()), row, colum);
          
        float aux=Float.parseFloat(t_nota.getText());
        if(aux> 67.6 && aux< 70)
        {
            l.get(row).setNOTA(70);
            l.get(row).setCondision("aprobado");
        }
        else
        {
            if(aux< 67.6)
            {
                 l.get(row).setCondision("aplazado");
            }
        else
            {
                 l.get(row).setCondision("aprobado");
            }
             l.get(row).setNOTA(Float.parseFloat(t_nota.getText()));
        }
        t_nota.setText("");   
        modifica.setVisible(false);
    }
     private void salir()
    {
        MostrarGruProf vi = new MostrarGruProf(gestor,ced_prof);
        vi.init();
        l.clear();
        this.dispose();
    }
     
     
    public void init() {
        setVisible(true);
        mostrar();
    }
    
   
    private void mostrar()
            
    { 
        
        l.clear();
            gestor.EstGrupo(curso, grupo, l);
            Alumno c= new Alumno();
            Nota p= new Nota();
            for(int i=0; i< l.size();i++)
            {
                p= l.get(i);
                gestor.buscarPer(c, p.getESTUDIANTE());
                tabla.addRow(new Object[]{c.getCedula(),c.getNombre(),p.getNOTA()});
            }  
            
    }
    
   
      private JPanel principal;
      private Control gestor;
    private JPanel formulario;
   private JPanel modifica;
    private JButton salir;
   private JTable tablaDatos;
   public ModeloTabla5 tabla;
    private JTextField t_nota;
    private JButton agregar;
    private JButton modificar;
    private JLabel nota;
    private ArrayList<Nota> l ;
    private String curso;
    private int grupo;
    private int row;
    private int colum;
    private String ced_prof;
    
    
}
 class ModeloTabla5 extends DefaultTableModel {

        public ModeloTabla5() {
            super(new Object[][]{},
                    new String[]{"cedula Est","nombre Est","nota "});
            
            }
        
        @Override
        public boolean isCellEditable(int filas, int columnas)
        {
            return false;
        }
    }


