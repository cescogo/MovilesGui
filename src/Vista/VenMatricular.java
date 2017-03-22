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
public class VenMatricular extends JFrame {

    public VenMatricular(Control c) {
        super("Matricular Estudiante");
        ajustarComponentes(getContentPane());
        gestor = c;
        setMinimumSize(new Dimension(600, 450));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void ajustarComponentes(Container c) {
        c.setLayout(new BorderLayout());
        principal = new JPanel();
        principal.setLayout(new BorderLayout());
        principal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formulario = new JPanel(new GridBagLayout());
        gc = new GridBagConstraints();

        gc.insets = new Insets(4, 4, 4, 4);

        gc.gridx = 0;
        gc.gridy = 1;
        estudiante = new JLabel("cedula del estudiante :");
        formulario.add(estudiante, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        curso = new JLabel("codigo del curso:");
        formulario.add(curso, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        grupo = new JLabel("numero de grupo:");
        formulario.add(grupo, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        formulario.add(id_estudiante = new JTextField(20), gc);

        gc.gridx = 1;
        gc.gridy = 2;
        formulario.add(id_curso = new JTextField(20), gc);

        gc.gridx = 1;
        gc.gridy = 3;
        formulario.add(num_grupo = new JTextField(20), gc);

        gc.gridx = 0;
        gc.gridy = 8;
        aceptar = new JButton("Aceptar");
        aceptar.addActionListener((ActionEvent e) -> {
            matricular();
        });
        formulario.add(aceptar, gc);

        gc.gridx = 1;
        gc.gridy = 8;

        cancel = new JButton("cancelar");
        cancel.addActionListener((ActionEvent e) -> {
            salir();
        });
        formulario.add(cancel, gc);

        principal.add(formulario, BorderLayout.CENTER);

        c.add(principal, BorderLayout.CENTER);

    }

    private void salir() {
        VenMatriculador vi = new VenMatriculador(gestor);
        vi.init();
        this.dispose();
    }

    private boolean blanco() {
        if (id_estudiante.getText().isEmpty()
                || id_curso.getText().isEmpty()
                || num_grupo.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private void matricular() {

        if (blanco()) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "Error", JOptionPane.ERROR_MESSAGE);

        } else {

            Persona a = new Persona(0);
            gestor.buscarPer(a, id_estudiante.getText());
            String id_grupo = id_curso.getText() + "-" + num_grupo.getText();

            if (a.getTipo() != 4) {
                JOptionPane.showMessageDialog(null, "Estudiante no existe", "Error", JOptionPane.ERROR_MESSAGE);
                id_estudiante.setText("");

            }
            //else
            Grupo g = new Grupo();
            gestor.Buscar(g, id_grupo);
            if (g.getId().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Grupo no existe", "Error", JOptionPane.ERROR_MESSAGE);
                num_grupo.setText("");
            } else {
                Nota n = new Nota();

                gestor.cursado(a.getCedula(), id_curso.getText(), n);
                if (n.getCondision() == "encurso" || n.getCondision() == "aprovado") {
                    JOptionPane.showMessageDialog(null, "Estudiante ya esta matriculado o aprobo el curso", "Error", JOptionPane.ERROR_MESSAGE);
                    id_estudiante.setText("");
                } else {
                    n.setCURSO(id_curso.getText());
                    n.setCondision("encurso");
                    n.setESTUDIANTE(id_estudiante.getText());
                    n.setGrupo(id_grupo);
                    gestor.Matricular(n);
                    salir();
                }

            }

        }
    }

    public void init() {
        setVisible(true);
    }
    private JPanel principal;
    private JPanel formulario;
    private GridBagConstraints gc;
    private JLabel estudiante;
    private JLabel grupo;
    private JLabel curso;

    private JTextField id_estudiante;
    private JTextField num_grupo;
    private JTextField id_curso;

    private JButton aceptar;
    private JButton cancel;
    private Control gestor;
}
