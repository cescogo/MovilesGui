package Vista;

import java.util.Scanner;
import Control.Control;
import Modelo.*;
import java.util.ArrayList;

public class Interfaz {

    private Control ctrl;

    public Interfaz(Control cont) {
        ctrl = cont;
    }

    public void BienvInter(int t) {
        switch (t) {
            case 1:
                System.out.println("Area de adinistrador");
                break;
            case 2:
                System.out.println("Area de Matriculador");
                break;
            case 3:
                System.out.println("Area de Profesor");
                break;
            case 4:
                System.out.println("Area de Alumno");
                break;
        }
    }

    public void initB() {
        int opc = 0;
        while (opc != 2) {
            System.out.println("1--> ENTRAR");
            System.out.println("2--> SALIR");
            opc = leerI();
            if (opc == 1) {
                init();
            }
        }
    }

    public void init() {
        System.out.println("Bienvenido al programa de gestion academica");
        System.out.println("---------LOGIN---------");
        String usr = solicitaUsr();
        String pass = solicitaPass();
        int tipo = ctrl.verificaUsuario(usr, pass);
        switch (tipo) {
            case 1:
                FuncAdministrativas();
                break;
            case 2:
                int opc = 0;
                while ((opc = nuevamatricula()) != 2) {
                    if (opc == 1) {
                        AdminMatricula();
                    }
                }
                break;
            case 3:
                FuncProfesor(usr);
                break;
            case 4:
                FuncAlumno();
                //
                break;
            case 0:
                break;

        }

    }

    public int solicitaAlopc() {
        System.out.println("1->historial");
        System.out.println("2->salir");
        return leerI();
    }

    private void FuncAlumno() {
        BienvInter(4);
        int opc = 0;
        while ((opc = solicitaAlopc()) != 2) {
            if (opc == 1) {
                ConsultaHistorial();
            }
        }
    }

    public String solicitaUsr() {
        System.out.println("Introdusca Usuario: ");
        String pass = "";
        Scanner entradaEscaner = new Scanner(System.in);
        pass = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto Scanner
        return pass;

    }

    public String solicitaPass() {
        System.out.println("digite la contraseña:");
        String pass = "";
        Scanner entradaEscaner = new Scanner(System.in);
        pass = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto Scanner
        return pass;
    }

    public void FuncAdministrativas() {
        BienvInter(1);
        int opc = -1;
        while (opc != 10) {
            MenuAdministrador();
            switch (opc = leerI()) {
                case 1:
                    AdminCarrera();
                    break;
                case 2:
                    AdminCurso();
                    break;
                case 3:
                    AdminProfes();
                    break;
                case 4:
                    AdminEst();
                    break;
                case 5:
                    //Adminciclo();
                    break;
                case 6:
                    Oferta();
                    break;
                case 7:
                    AdminMatricula();
                    break;
                case 8:
                    ConsultaHistorial();
                    break;
                case 9:
                    AgregarAcceso();
                    break;
                default:
                    ERROR();
                    break;
            }
        }
    }
//

    private void AdminCarrera() {
        int opc = 0;
        while (opc != 6) {
            menuCarr();
            switch (opc = leerI()) {
                case 1:
                    crearCarrera();
                    break;
                case 2:
                    BorrarCarrera();
                    break;
                case 3:
                    MostrarCarrera();
                    break;
                case 4:
                    MostrarCarreras();
                    break;
                case 5:
                    ActualizarCarrera();
                    break;
                case 6:
                    opc = 5;
                    break;
            }
        }
    }

    private void crearCarrera() {
        String nomcar = SolicitaNomCarrera();
        String codCAr = SolicitaCodCarrera();
        if (ctrl.existeCarrera(codCAr)) {
            System.out.println("Carrera ya existe, Error");
        } else {
            ctrl.agregarCarrera(nomcar, codCAr);
        }
    }

    public void BorrarCarrera() {
        String c = SolicitaCodCarrera();
        if (!ctrl.existeCarrera(c)) {
            System.out.println("Carrera No existe, Error");
        } else {
            ctrl.BorrarCarrera(c);
        }
    }

    public void MostrarCarrera() {
        int p = 0;
        Carrera c = new Carrera();
        while (p != 3) {
            BusqCar();
            switch (p = leerI()) {
                case 1:
                    ctrl.MostrarCarreraC(c, SolicitaCodCarrera());
                    break;
                case 2:
                    ctrl.MostrarCarreraN(c, SolicitaNomCarrera());
                    break;
                default:
                    ERROR();
            }
            System.out.println(c.toString());
        }
    }

    public void MostrarCarreras() {
        ArrayList<Carrera> l = new ArrayList<>();
        ctrl.MostrarCarreras(l);
        System.out.println(l.toString());
    }

    public void ActualizarCarrera() {
        Carrera a = new Carrera(SolicitaNomCarrera(), SolicitaCodCarrera());
        ctrl.ActualizarCarrera(a);
    }
//

    public void errorLogin() {
        System.out.println("Error Usuario o Contraseña incorrecta: ");
    }

    public void ERROR() {
        System.out.println("ERROR....");
    }

    private void AdminCurso() {
        int opc = 0;
        while (opc != 5) {
            menuCurso();
            switch (opc = leerI()) {
                case 1:
                    crearCurso();
                    break;
                case 2:
                    BorrarCurso();
                    break;
                case 3:
                    MostrarCurso();
                    break;
                case 4:
                    ActualizarCurso();
                    break;
                default:
                    ERROR();
            }
        }
    }

    private void crearCurso() {
        String carrera = SolicitaCodCarrera();
        String Ccurso = SolicitaCodCurso();

        if (!ctrl.existeCarrera(carrera)) {
            System.out.println("Carrera no existe agreguela por favor, Error");
        } else if (ctrl.existeCur(Ccurso)) {
            System.out.println("Curso ya existe, Error");
        } else {
            ctrl.agregarCurso(Ccurso, SolicitaNomCurso(), SolicitaCreditos(), SolicitaHsemanal(), carrera, SolicitaCiclo());
        }

    }

    private void BorrarCurso() {
        String curso = SolicitaCodCurso();
        if (!ctrl.existeCur(curso)) {
            System.out.println("Curso existe, Error");
        } else if (ctrl.BorrarCurso(curso)) {
            SucceDel();
        } else {
            ErrorDel();
        }
    }

    private void MostrarCurso() {
        int p = 0;
        Curso C = new Curso();
        while (p != 4) {
            BusqCurso();
            switch (p = leerI()) {
                case 1:
                    ctrl.MostrarCurso(C, SolicitaNomCurso(), 1);
                    System.out.println(C.toString());
                    break;
                case 2:
                    ctrl.MostrarCurso(C, SolicitaCodCurso(), 2);
                    System.out.println(C.toString());
                    break;
                case 3:
                    ArrayList<Curso> l = new ArrayList<>();
                    ctrl.MostrarCursos(l, SolicitaCodCarrera());
                    System.out.println(l.toString());
                    break;
                default:
                    ERROR();
            }
        }
    }

    private void ActualizarCurso() {
        Curso s = new Curso();
        ctrl.MostrarCurso(s, SolicitaCodCurso(), 2);
        if (s.getCodigo() != "") {
            int p = 0;
            while (p != 5) {

                switch (p = ModifProf()) {
                    case 1://nombre
                        String nom = SolicitaNomCurso();
                        if (!nom.isEmpty()) {
                            s.setNombre(nom);
                        }
                        break;
                    case 2:
                        int cr = SolicitaCreditos();
                        if (cr > 2 || cr < 5) {
                            s.setCreditos(cr);
                        }
                        break;
                    case 3://correo
                        int hr = SolicitaHsemanal();
                        if (hr > 1) {
                            s.setHsemanales(hr);
                        }
                        break;
                    case 4:
                        int ci = SolicitaCiclo();
                        if (ci == 1 || ci == 2) {
                            s.setNum_ciclo(ci);
                        }
                        break;
                    default:
                        ERROR();
                }
            }
            ctrl.ActualizarCurso(s);

        } else {
            ERROR();
            System.out.println(s.toString());
            System.out.println("no existe ese curso");
        }

    }

    private void AdminProfes() {
        int opc = 0;
        while (opc != 5) {
            MenuProfesor();
            switch (opc = leerI()) {
                case 1:
                    crearProfe();
                    break;
                case 2:
                    BorrarPersona();
                    break;
                case 3:
                    MostrarProfe();
                    break;
                case 4:
                    ActualizarProfe();
                    break;
                default:
                    ERROR();
                    break;
            }
        }
    }

    private void crearProfe() {
        String ced = SolicitaCedulas("profesor");
        if (ctrl.existePro(ced)) {
            System.out.println("Profesor ya existe, Error");
        } else {
            ctrl.agregarProfesor(SolicitaNombres(), ced, SolicitaTelefono(), SolicitaEmail(), solicitaPass());
        }
    }

    private void BorrarPersona() {
        if (ctrl.borrarP(SolicitaCedulas("persona"))) {
            SucceDel();
        } else {
            ErrorDel();
        }
    }

    private void MostrarProfe() {
        int p = 0;
        Profesor per = new Profesor();
        while (p != 3) {
            BusqProf();
            switch (p = leerI()) {
                case 1:
                    ctrl.mostrarPCed(per, SolicitaCedulas("profesor"));
                    System.out.println(per.toString());
                    break;
                case 2:
                            ArrayList<Profesor> l = new ArrayList<>();
                    ctrl.mostrarProNom(SolicitaNombres(), l);
                    System.out.println(l.toString());
                    break;
                default:
                    ERROR();
            }
        }
    }

    private void ActualizarProfe() {
        Profesor pro = new Profesor();
        ctrl.mostrarPCed(pro, SolicitaCedulas("profesor"));
        if (!pro.getCedula().isEmpty()) {
            int p = 0;
            while (p != 5) {
                switch (p = ModifProf()) {
                    case 1://nombre
                        String nom = SolicitaNombres();
                        if (nom != "") {
                            pro.setNombre(nom);
                        }

                        break;
                    case 2://pass
                        String pass = solicitaPass();
                        if (pass != "") {
                            pro.setClave(pass);
                        }
                        break;
                    case 3://correo
                        String correo = SolicitaEmail();
                        if (correo != "") {
                            pro.setEmail(correo);
                        }
                        break;
                    case 4://telefono
                        int tel = SolicitaTelefono();
                        if (tel != 0) {
                            pro.setTelefono(tel);
                        }
                        break;
                    default:
                        ERROR();
                }
            }
        } else {
            ERROR();
            return;
        }
        ctrl.actualizaP(pro);
    }

    private void AdminEst() {
        int opc = 0;
        while (opc != 5) {
            MenuAlumno();
            switch (opc = leerI()) {
                case 1:
                    crearEst();
                    break;
                case 2:
                    BorrarPersona();
                    break;
                case 3:
                    MostrarEst();
                    break;
                case 4:
                    ActualizarEst();
                    break;
                default:
                    ERROR();
                    break;
            }
        }
    }

    private void crearEst() {
        String ced = SolicitaCedulas("Estudiante");
        if (ctrl.existeEst(ced)) {
            System.out.println("Estudiante ya existe, Error");
        } else {
            ctrl.agregarAlumno(SolicitaNombres(), ced, SolicitaFec_Nac(), SolicitaTelefono(), SolicitaEmail(), solicitaPass(), SolicitaCodCarrera());
        }

    }

    private void MostrarEst() {
        int p = 0;
        Alumno per = new Alumno();
        while (p != 4) {
            BusqAlmn();
            switch (p = leerI()) {
                case 1:
                    ctrl.mostrarPCed(per, SolicitaCedulas("Estudiante"));
                    System.out.println(per.toString());
                    break;
                case 2:
                           ArrayList<Alumno> l = new ArrayList<>();
                    ctrl.mostrarEstNom(SolicitaNombres(), l);
                    System.out.println(l.toString());
                    break;
                case 3:
                    // buscar carrera
                           ArrayList<Alumno> w = new ArrayList<>();
                    ctrl.mostrarPCar(SolicitaCodCarrera(), w);
                    System.out.println(w.toString());
                    break;
                default:
                    ERROR();
            }
        }
    }

    private void ActualizarEst() {
        Alumno al = new Alumno();
        ctrl.mostrarPCed(al, SolicitaCedulas("Estudiante"));

        if (al.getCedula() != "") {
            System.out.println(al.toString());
            int p = 0;
            while (p != 5) {
                p = ModifAlmn();
                switch (p) {
                    case 1://nombre
                        String nom = SolicitaNombres();
                        if (nom != "") {
                            al.setNombre(nom);
                        }
                        break;
                    case 2://pass
                        String pass = solicitaPass();
                        if (pass != "") {
                            al.setClave(pass);
                        }
                        break;
                    case 3://correo
                        String correo = SolicitaEmail();
                        if (correo != "") {
                            al.setEmail(correo);
                        }
                        break;
                    case 4://telefono
                        int tel = SolicitaTelefono();
                        if (tel != 0) {
                            al.setTelefono(tel);
                        }
                        break;
                    default:
                        ERROR();
                }
            }
        } else {
            ERROR();
            System.out.println("estudianre no existe");
            return;
        }
        ctrl.actualizaP(al);
    }

    private void AgregarAcceso() {
        int opc = 0;

        while (opc != 2) {
            System.out.println("2__agregar accesos");
            System.out.println("1__salir");
            opc = leerI();
            if (opc == 1) {
                AddAcceso();
            } else {
                ERROR();
            }
        }

    }

    private void AdminAdmin() {
        int opc = 0;
        while (opc != 5) {
            MenuAdmisn();
            switch (opc = leerI()) {
                case 1:
                    crearAdm();
                    break;
                case 2:
                    BorrarPersona();
                    break;
                case 3:
                    MostrarAdm();
                    break;
                case 4:
                    ActualizarAdm();
                    break;
                case 5:
                    break;
                default:
                    ERROR();
                    break;
            }
        }
    }

    private void crearAdm() {
        String ced = SolicitaCedulas("administrador");
        if (ctrl.existeAdmi(ced)) {
            System.out.println("Administrador ya existe, Error");
        } else {
            ctrl.agregarAdministrador(SolicitaNombres(), ced, SolicitaTelefono(), SolicitaEmail(), solicitaPass());
        }
    }

    private void MostrarAdm() {
        int p = 0;
        Administrador per = new Administrador();
        while (p != 2) {
            BusqAcc();
            switch (p = leerI()) {
                case 1:
                    ctrl.mostrarPCed(per, SolicitaCedulas("administrador"));
                    System.out.println(per.toString());
                    break;
                default:
                    ERROR();
            }
        }
    }

    private void ActualizarAdm() {
        Administrador al = new Administrador();
        ctrl.mostrarPCed(al, SolicitaCedulas("administrador"));

        if (al.getCedula() != "") {
            System.out.println(al.toString());
            int p = 0;
            while (p != 5) {
                p = ModifAlmn();
                switch (p) {
                    case 1://nombre
                        String nom = SolicitaNombres();
                        if (nom != "") {
                            al.setNombre(nom);
                        }
                        break;
                    case 2://pass
                        String pass = solicitaPass();
                        if (pass != "") {
                            al.setClave(pass);
                        }
                        break;
                    case 3://correo
                        String correo = SolicitaEmail();
                        if (correo != "") {
                            al.setEmail(correo);
                        }
                        break;
                    case 4://telefono
                        int tel = SolicitaTelefono();
                        if (tel != 0) {
                            al.setTelefono(tel);
                        }
                        break;
                    default:
                        ERROR();
                }
            }
        } else {
            ERROR();
            System.out.println("no existe persona");
            return;
        }
        ctrl.actualizaP(al);
    }

    private void AdminMatr() {
        int opc = 0;
        while (opc != 5) {
            MenuMatriculador();
            switch (opc = leerI()) {
                case 1:
                    crearMatr();
                    break;
                case 2:
                    BorrarPersona();
                    break;
                case 3:
                    MostrarMatr();
                    break;
                case 4:
                    ActualizarMatr();
                    break;
                default:
                    ERROR();
                    break;
            }
        }
    }

    private void crearMatr() {
        String ced = SolicitaCedulas("Matriculador");
        if (ctrl.existeMat(ced)) {
            System.out.println("Matriculador ya existe, Error");
        } else {
            ctrl.agregarMatriculador(SolicitaTelefono(), SolicitaEmail(), SolicitaNombres(), ced, solicitaPass());
        }
    }

    private void MostrarMatr() {
        int p = 0;
        Matriculador per = new Matriculador();
        while (p != 2) {
            BusqAcc();
            switch (p = leerI()) {
                case 1:
                    ctrl.mostrarPCed(per, SolicitaCedulas("Matriculador"));
                    System.out.println(per.toString());
                    break;
                default:
                    ERROR();
            }
        }
    }

    private void ActualizarMatr() {
        Matriculador al = new Matriculador();
        ctrl.mostrarPCed(al, SolicitaCedulas("Matriculador"));

        if (al.getCedula() != "") {
            System.out.println(al.toString());
            int p = 0;
            while (p != 5) {
                p = ModifAlmn();
                switch (p) {
                    case 1://nombre
                        String nom = SolicitaNombres();
                        if (nom != "") {
                            al.setNombre(nom);
                        }
                        break;
                    case 2://pass
                        String pass = solicitaPass();
                        if (pass != "") {
                            al.setClave(pass);
                        }
                        break;
                    case 3://correo
                        String correo = SolicitaEmail();
                        if (correo != "") {
                            al.setEmail(correo);
                        }
                        break;
                    case 4://telefono
                        int tel = SolicitaTelefono();
                        if (tel != 0) {
                            al.setTelefono(tel);
                        }
                        break;
                    default:
                        ERROR();
                }
            }
        } else {
            ERROR();
            return;
        }
        ctrl.actualizaP(al);
    }

    public void moferta() {
        System.out.println("1-->Oferta academica");
        System.out.println("2-->salir");
    }

    public int SolicitaNumgrp() {
        System.out.println("digite el numero de grupo");
        return leerI();
    }

    private void AgregarGrupo(String curso) {
        int t_numero = SolicitaNgrupo();
        String aux = curso + "-" + t_numero;
        String t_profesor = SolicitaCedulas("profesor");

        if (!ctrl.existeCur(curso)) {
            System.out.println("Curso no existe agreguelo por favor, Error");
        } else if (!ctrl.existePro(t_profesor)) {
            System.out.println("Profesor no existe agreguelo por favor, Error");
        } else if (ctrl.existeGrupo(aux)) {
            System.out.println("Grupo ya existe, Error");

        } else {
            ctrl.agregarGrupo(t_numero, SolicitaHorario(), t_profesor, curso);
        }
    }

    public void Oferta() {
        int opc = 0;
        while (opc != 2) {
            opc = Oferta2();
            if (opc == 1) {
                AdminOferta();
            }
        }
    }

    public void AdminOferta() {
        String Carrera = SolicitaCodCarrera();
        int ciclo = SolicitaCiclo();
                ArrayList<Curso> cursos = new ArrayList<>();
        ctrl.ofertaAcd(Carrera, ciclo, cursos);
        System.out.println(cursos.toString());

        int opc = sbmoferta();

        if (opc == 1) {
            String curso = SolicitaCodCurso();
                   ArrayList<Grupo> grupos = new ArrayList<>();
            ctrl.BuscarGrpCrs(curso, grupos);
            System.out.println(grupos.toString());
            int ccc = sbmgrpsCrs();
            if (ccc == 1) {
                AgregarGrupo(curso);
            }
            if (ccc == 2) {
                Grupo g = new Grupo();
                ctrl.Bcgrupo(curso, SolicitaNgrupo(), g);

                if (g.getId() != "") {
                    int p = 0;
                    String nom = SolicitaCedulas("profesor");
                    if (nom != "") {
                        g.setProfesor(nom);
                    }
                } else {
                    ERROR();
                }
                ctrl.actualizar(g);
            }
        }
    }

    public int sbmgrpsCrs() {
        System.out.println("1--> agregar grupo ");
        System.out.println("2--> modificar grupo");
        System.out.println("otros salir...");
        return leerI();
    }

    public int sbmoferta() {
        System.out.println("1--> seleccionar un curso");
        System.out.println("2--> atras");
        return leerI();
    }

    private void ConsultaHistorial() {
                ArrayList<Nota> l = new ArrayList<>();
        ctrl.ConsultaHistorial(SolicitaCedulas("estudiante"), l);
        System.out.println(l.toString());

    }

    public void AddAcceso() {

        int opc = 0;
        while (opc != 3) {
            System.out.println("1-> Administrador");
            System.out.println("2-> Matriculador");
            System.out.println("3->Salir");
            switch (opc = leerI()) {
                case 1:
                    AdminAdmin();
                    break;
                case 2:
                    AdminMatr();
                    break;
                default:
                    ERROR();
            }
        }
    }

    public void MenuAdministrador() {
        System.out.println("-----Menu Adminstrador-----");
        System.out.println("1--> Admisntrar Carreras");
        System.out.println("2--> Admisntrar cursos");
        System.out.println("3--> Admisntrar profesores");
        System.out.println("4--> Admisntrar Alumnos");
        System.out.println("5--> Admisntrar Ciclo");
        System.out.println("6--> Admisntrar Oferta Academica");
        System.out.println("7--> Admisntrar Matricula");
        System.out.println("8--> Admisntrar Consultar Historial");
        System.out.println("9--> Administrar Acceso de Seguridad");
        System.out.println("");
        System.out.println("10-> SALIR");
    }

    public void ErrorDel() {
        System.out.println("Problemas en la eliminacion");
    }

    public void SucceDel() {
        System.out.println("Eliminacion Correcta");
    }

    public void MenuProfesor() {
        System.out.println("-----Menu Profesor-----");
        System.out.println("1--> agregar Profesor");
        System.out.println("2--> Borrar Profesor");
        System.out.println("3--> mostrar Profesor");
        System.out.println("4--> actualizar Profesor");
        System.out.println("5-->SALIR");
    }

    public void MenuAlumno() {
        System.out.println("-----Menu Alumno-----");
        System.out.println("1--> agregar Alumno");
        System.out.println("2--> Borrar Alumno");
        System.out.println("3--> mostrar Alumno");
        System.out.println("4--> actualizar Alumno");
        System.out.println("5-->SALIR");
    }

    public void MenuMatriculador() {
        System.out.println("-----Menu Matriculador-----");
        System.out.println("1--> agregar Matriculador");
        System.out.println("2--> Borrar Matriculador");
        System.out.println("3--> mostrar Matriculador");
        System.out.println("4--> actualizar Matriculador");
        System.out.println("5-->SALIR");
    }

    public void MenuAdmisn() {
        System.out.println("-----Menu Administrador-----");
        System.out.println("1--> agregar Administrador");
        System.out.println("2--> Borrar Administrador");
        System.out.println("3--> mostrar Administrador");
        System.out.println("4--> actualizar Administrador");
        System.out.println("5-->SALIR");
    }

    public void menuCarr() {
        System.out.println("-----Menu Carrera-----");
        System.out.println("1--> agregar Carreras");
        System.out.println("2--> Borrar Carreras");
        System.out.println("3--> mostrar Carrera");
        System.out.println("4--> mostrar Carreras");
        System.out.println("5--> actualizar Carreras");
        System.out.println("6--> SALIR");

    }

    public void menuCurso() {
        System.out.println("-----Menu Curso-----");
        System.out.println("1--> agregar Curso");
        System.out.println("2--> Borrar Curso");
        System.out.println("3--> mostrar Curso");
        System.out.println("4--> actualizar Curso");
        System.out.println("5--> SALIR");
    }

    public void BusqCurso() {
        System.out.println("1--Nombre");
        System.out.println("2--Codigo");
        System.out.println("3--Carrera");
        System.out.println("4--salir");

    }

    public void BusqAcc() {
        System.out.println("1--Cedula");
        System.out.println("2--salir");
    }

    public void BusqProf() {
        System.out.println("1--Cedula");
        System.out.println("2--Nombre");
        System.out.println("3--salir");
    }

    public void BusqAlmn() {
        System.out.println("1--Cedula");
        System.out.println("2--Nombre");
        System.out.println("3--Carrera");
        System.out.println("4--salir");
    }

    public int ModifProf() {
        System.out.println("1--Nombre");
        System.out.println("2--Contraseña");
        System.out.println("3--correo");
        System.out.println("4--telefono");
        System.out.println("5--salir");
        return leerI();
    }

    public int ModifAlmn() {
        System.out.println("1--Nombre");
        System.out.println("2--Contraseña");
        System.out.println("3--correo");
        System.out.println("4--telefono");
        System.out.println("5--salir");
        return leerI();
    }

    public void BusqCar() {
        System.out.println("1--Codigo");
        System.out.println("2--Nombre");
        System.out.println("3--salir");

    }

    public String SolicitaNombres() {
        System.out.println("digite el nombre de la persona:");
        String nombre = "";
        Scanner entradaEscaner = new Scanner(System.in);
        nombre = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto Scanner
        return nombre;
    }

    public String SolicitaCedulas(String persona) {
        System.out.println("digite la cedula de" + persona + ":");
        String cedula = "";
        Scanner entradaEscaner = new Scanner(System.in);
        cedula = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto Scanner
        return cedula;
    }

    public String SolicitaEmail() {
        System.out.println("digite el correo correspondiente:");
        String correo = "";
        Scanner entradaEscaner = new Scanner(System.in);
        correo = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto Scanner
        return correo;
    }

    public int SolicitaTelefono() {
        System.out.println("digite el telefono de la persona:");
        int telefono = 0;
        Scanner entradaEscaner = new Scanner(System.in);
        telefono = entradaEscaner.nextInt(); //Invocamos un método sobre un objeto Scanner
        return telefono;
    }

    public String SolicitaFec_Nac() {
        System.out.println("digite la fecha de nacimiento del estudiante:");
        String fecNac = "";
        Scanner entradaEscaner = new Scanner(System.in);
        fecNac = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto Scanner
        return fecNac;
    }

    public String SolicitaCodCarrera() {
        System.out.println("digite el codigo correrspondiente a la carrera:");
        return leerS();
    }

    public String SolicitaNomCarrera() {
        System.out.println("digite el nombre correrspondiente a la carrera:");
        String pass = "";
        Scanner entradaEscaner = new Scanner(System.in);
        pass = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto Scanner
        return pass;
    }

    public int Oferta2() {
        System.out.println("1--> ingrese Codigo para ver grupos");
        System.out.println("2--> salir");
        return leerI();
    }

    public String SolicitaCodCurso() {
        System.out.println("digite el codigo correrspondiente al curso:");
        return leerS();
    }

    public String SolicitaNomCurso() {
        System.out.println("digite el nombre del curso:");
        return leerS();
    }

    public int SolicitaCreditos() {
        System.out.println("digite  el numero de creditos del curso:");
        return leerI();
    }

    public int SolicitaHsemanal() {
        System.out.println("digite  el numero de horas semanales:");
        return leerI();
    }

    public int SolicitaCiclo() {
        System.out.println("digite el numero de ciclo en que impartera el curso:");
        return leerI();
    }

    public void Solicitaidgrupo() {
        System.out.println("digite el id del grupo:");
    }

    public int Solicitaiciclogrupo() {
        System.out.println("digite el ciclo para grupo:");
        int ciclogr = 0;
        Scanner entradaEscaner = new Scanner(System.in);
        ciclogr = entradaEscaner.nextInt(); //Invocamos un método sobre un objeto Scanner
        return ciclogr;
    }

    public int SolicitaNgrupo() {
        System.out.println("digite el numero para grupo:");
        int ngrpo = 0;
        Scanner entradaEscaner = new Scanner(System.in);
        ngrpo = entradaEscaner.nextInt(); //Invocamos un método sobre un objeto Scanner
        return ngrpo;
    }

    public String SolicitaHorario() {
        System.out.println("digite el horario del grupo:");
        String hgrp = "";
        Scanner entradaEscaner = new Scanner(System.in);
        hgrp = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto Scanner
        return hgrp;
    }

    public int leerI() {
        int datoI = 0;
        Scanner entradaEscaner = new Scanner(System.in);
        datoI = entradaEscaner.nextInt();
        return datoI;
    }

    public String leerS() {
        String datoS = "";
        Scanner entradaEscaner = new Scanner(System.in);
        datoS = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto Scanner
        return datoS;
    }

    private void AdminMatricula() {
        Alumno a = new Alumno();
        ctrl.mostrarPCed(a, SolicitaCedulas("alumno"));
        if (!a.getCedula().isEmpty()) {
                    ArrayList<Nota> l = new ArrayList<>();
            int op = 0;
            while (op != 3) {
                ctrl.matriculados(a.getCedula(), l);
                System.out.println(l.toString());
                op = opcMA();
                if (op == 1) {
                    if (matriculaEst(a, SolicitaCodCurso())) {
                        System.out.println("Matriculado");
                    } else {
                        System.out.println("no Matriculado");
                    }
                }
                if (op == 2) {
                    if (desmatriculaEst(a, SolicitaCodCurso())) {
                        System.out.println("DesMatriculado");
                    } else {
                        System.out.println("no puede desmatricular");
                    }
                    //dematricula
                }
            }

        }
    }

    private boolean matriculaEst(Alumno a, String curso) {
        int ngrupo = SolicitaNumgrp();
        String id_grupo = curso + "-" + ngrupo;

        if (a.getTipo() != 4) {
            System.out.println("Estudiante no existe");
            return false;
        }
        //else
        Grupo g = new Grupo();
        Curso cc = new Curso();
        ctrl.Buscar(g, id_grupo);
        ctrl.cursoNCarrera(cc, a.getCarrera(), curso);
        if (cc.getCodigo().isEmpty()) {
            System.out.println("Grupo no existe, Error");
            return false;
        } else {
            Nota n = new Nota();
            ctrl.cursado(a.getCedula(), curso, n);
            if (n.getCondision() == "encurso" || n.getCondision() == "aprovado") {
                System.out.println("Estudiante ya esta matriculado o aprobo el curso, Error");
                return false;
            } else {
                n.setCURSO(curso);
                n.setCondision("encurso");
                n.setESTUDIANTE(a.getCedula());
                n.setGrupo(id_grupo);
                ctrl.Matricular(n);
            }

        }
        return true;
    }

    private boolean desmatriculaEst(Persona a, String curso) {
        Nota n = new Nota();
        ctrl.encurso(a.getCedula(), curso, n);
        if (n.getCondision().equals("encurso")) {
            ctrl.desmatricula(a.getCedula(), curso);
            return true;
        }
        System.out.println("Estudiante no esta matriculado");
        return false;
    }

    public int nuevamatricula() {
        System.out.println("1--> nueva matricula");
        System.out.println("2--> salir");
        return leerI();
    }

    public int opcMA() {
        System.out.println("Agregar");
        System.out.println("eliminar");
        return leerI();
    }

    private void FuncProfesor(String usr) {
                ArrayList<Grupo> grupos = new ArrayList<>();
        ctrl.gruposProfe(usr, grupos);
        System.out.println(grupos.toString());
        int opc =0;
        while(opc != 2){
            opc = menunotas();
            if(opc == 1){
                String codGrp = soliCodGRUPO();
                       ArrayList<Nota> notas = new ArrayList<>();
                ctrl.notasPgrupo(codGrp, notas);
                System.out.println(notas.toString());
                int op = 0;
                while(op!=2){
                    op = sbmenunota();
                    if(op ==1){
                        String Est = SolicitaCedulas("estudiante");
                        Nota n = new Nota();
                        ctrl.notaEst(codGrp, Est, n);
                        System.out.println(n.toString());
                        n.setNOTA(solicitanota(Est));
                        ctrl.actualiza(n);
                    }
                }
            }
        }
    }
    
    public String soliCodGRUPO(){
        System.out.println("digite el codigo de grupo");
        return leerS();
    }
    
    public int sbmenunota(){
     System.out.println("1 seleccione 1 Estudiante");
        System.out.println("2 salir ");
       return leerI();
    }
    
    public int menunotas(){
     System.out.println("1 seleccione 1 grupo");
        System.out.println("2 salir ");
       return leerI();
    }

    private float solicitanota(String Est) {
        System.out.println("Digite la nota para "+Est);
          float datoI = 0;
        Scanner entradaEscaner = new Scanner(System.in);
        datoI = entradaEscaner.nextFloat();
        return datoI;
    }
}
