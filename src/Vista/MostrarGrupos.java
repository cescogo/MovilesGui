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
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class MostrarGrupos extends JFrame {
      public MostrarGrupos(Control c) {
            super("Mostrar Grupos");
            tabla= new ModeloTabla2();
            
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
        JPanel busca= new JPanel();
        busca.setLayout(new FlowLayout());
        
         formulario =new JPanel(new BorderLayout());
         bus= new JLabel("codigo del curso que desea ver los grupos");
         busca.add(bus);
         buscar= new JTextField(10);
         busca.add(buscar);
         b_buscar= new JButton("Buscar");
         b_buscar.addActionListener((ActionEvent e)->{mostrar();});
         busca.add(b_buscar);
         
         formulario.add(busca,BorderLayout.NORTH);
         
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
        formulario.add(pTabla,BorderLayout.CENTER);
         salir= new JButton("salir");
                                salir.addActionListener((ActionEvent e)->{salir();});
                                cancelar =new JPanel(new BorderLayout());       
                                cancelar.add(salir ,BorderLayout.EAST);
                                principal.add(cancelar,BorderLayout.SOUTH);
        principal.add(formulario);
        c.add(principal);
       
        
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
    
    private void limpiaTabla()
    {
      for(int i = tabla.getRowCount()-1;i>=0;--i)
      {  
          tabla.removeRow(0);
      }
       
    }
   
    private void mostrar()
    { 
        limpiaTabla();
        
       if(buscar.getText().isEmpty())
       {
           JOptionPane.showMessageDialog(null, "Campo vacio","Error",JOptionPane.ERROR_MESSAGE);
       }
       else
        if(!gestor.existeCur(buscar.getText()))
        {
            JOptionPane.showMessageDialog(null, "Curso no existe","Error",JOptionPane.ERROR_MESSAGE);
        }
        else
        { 
             ArrayList<Grupo> l = new ArrayList<>();
            gestor.BuscarGrpCrs(buscar.getText(),l);
            Grupo c= new Grupo();
            Persona p= new Persona(0);
            for(int i=0; i< l.size();i++)
            {
                c= l.get(i);
                gestor.buscarPer(p, c.getProfesor());
                tabla.addRow(new Object[]{c.getNumero(),c.getProfesor(),p.getNombre()});
            }
            l.clear();
        }
       
    }
    
      private JPanel principal;
      private Control gestor;
    private JPanel formulario;
   
    private JButton aceptar;
   private JTable tablaDatos;
   public ModeloTabla2 tabla;
   private JTextField buscar;
   private JLabel bus;
    private JButton b_buscar;
    private JButton salir;
    private JPanel cancelar;
    
}
 class ModeloTabla2 extends DefaultTableModel {

        public ModeloTabla2() {
            super(new Object[][]{},
                    new String[]{"numero", "id Prof","Profesor"});
            
            }
        
        @Override
        public boolean isCellEditable(int filas, int columnas)
        {
            return false;
        }
    }




