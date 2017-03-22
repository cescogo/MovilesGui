/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.Control;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

/**
 *
 * @author ccg
 */
public class VenOpcCurso extends JFrame{
     public VenOpcCurso(Control c) {
        
        super("Opciones de Curso");
        ajustarComponentes(getContentPane());
        setMinimumSize(new Dimension(440,250));
        gestor=c;
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void ajustarComponentes(Container c) {
           c.setLayout(new BorderLayout());
        JPanel principal = new JPanel();
        principal.setLayout(new BorderLayout());
        principal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        principal.setBackground(Color.WHITE);

        JPanel central = new JPanel(new GridLayout(2,2,30,30) );
        central.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        central.setBackground(Color.WHITE);
        
        
        principal.add(central, BorderLayout.CENTER);
        c.add(principal, BorderLayout.CENTER);
    
				agregar= new JButton("agregar curso"); 
                                agregar.addActionListener((ActionEvent e)->{agregar();});
				central.add(agregar);
				
				modificar= new JButton("modificar curso");
                                modificar.addActionListener((ActionEvent e)->{modificar();});
				central.add(modificar);
				
				consultar= new JButton("Ver curso");
                                consultar.addActionListener((ActionEvent e)->{verCursos();});
				central.add(consultar);
				
				eliminar= new JButton("eliminar curso");
                                eliminar.addActionListener((ActionEvent e)->{eliminar();});
				central.add(eliminar);
                                
                                   salir= new JButton("salir");
                                salir.addActionListener((ActionEvent e)->{salir();});
                                cancelar =new JPanel(new BorderLayout());       
                                cancelar.add(salir ,BorderLayout.EAST);
                                cancelar.setBackground(Color.WHITE);
                                
				principal.add(cancelar,BorderLayout.SOUTH);
    
        }

   private void agregar()
    {
    	AgregarCurso vi=new AgregarCurso(gestor);
        vi.init();
        this.dispose();
    }
   
private void eliminar()
    {
        EliminarCurso vi= new EliminarCurso(gestor);
        vi.init();
        this.dispose();
    }
    private void modificar()
     {
         ModificarCurso vi= new ModificarCurso(gestor);
         vi.init();
         this.dispose();
     }
    public void init() {
        setVisible(true);
       
    }
    
    private void verCursos()
    {
        MostrarCursos vi= new MostrarCursos(gestor);
        vi.init();
        this.dispose();
    }
    
    private void salir()
    {
        VentanaAdministrador vi= new VentanaAdministrador(gestor);
        vi.init();
        this.dispose();
    }
    
    private JButton agregar;
    private JButton consultar; 
    private JButton modificar;
    private JButton eliminar;
    private JButton salir;
    private JPanel cancelar;
    private Control gestor;
}
