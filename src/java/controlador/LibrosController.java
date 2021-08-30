package controlador;

import conexion.Conexion;
import dao.LibrosDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import modelo.Libros;

public class LibrosController {

    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        Conexion conn = new Conexion();
        LibrosDao lbdao = new LibrosDao(conn);
        Libros lb = new Libros(0);
        String accion;
        boolean resp;
        Date fecha;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        do {

            System.out.println("\t\t\tCRUD TABLA LIBROS\n\n"
                    + "\t\t\tPRESIONE I PARA INSERTAR\n"
                    + "\t\t\tPRESIONE U PARA ACTUALIZAR\n"
                    + "\t\t\tPRESIONE C PARA CONSULTAR TODOS\n"
                    + "\t\t\tPRESIONE B PARA BUSCAR POR ID\n"
                    + "\t\t\tPRESIONE D PARA BORRAR\n "
                    + "\t\t\tPRESIONE X PARA SALIR\n ");
            System.out.print("Comando: ");
            accion = sc.next().toUpperCase();

            switch (accion) {
                case "I":

                    System.out.print("Ingrese Nombre de Libro: ");
                    lb.setNombreLibro(sc.next());
                    System.out.print("Ingrese Fecha en este formato yyyy/MM/dd ");

                    fecha = sdf.parse(sc.next());
                    lb.setFecha(fecha);
                    resp = lbdao.insert(lb);
                    if (resp) {
                        System.out.println("\nRegistro guardado\n");
                    } else {
                        System.out.println("\nError de registro\n");
                    }
                    lbdao.ptc();
                    break;
                case "U":
                    System.out.print("Ingrese Nombre de libro: ");
                    lb.setNombreLibro(sc.next());
                    System.out.print("Ingrese Fecha en este formato yyyy/MM/dd ");
                    fecha = sdf.parse(sc.next());
                    lb.setFecha(fecha);
                    System.out.print("Ingrese ID: ");
                    lb.setId(sc.nextInt());

                    resp = lbdao.update(lb);
                    if (resp) {
                        System.out.println("\nRegistro Actualizado correctamente\n");
                    } else {
                        System.out.println("\nError de registro\n");
                    }
                    lbdao.ptc();
                    break;
                case "C":
                    System.out.println("\n\t#ID \t LIBRO \t FECHA \n");

                    for (Libros lb2 : lbdao.selectAll()) {
                        System.out.print("\t " + lb2.getId());
                        System.out.print("\t " + lb2.getNombreLibro());
                        System.out.print(" \t " + lb2.getFecha());
                        System.out.println("");
                    }
                    lbdao.ptc();
                    break;
                case "B":
                    System.out.print("\nIngrese id del registro a buscar: ");
                    int id = sc.nextInt();
                    System.out.println("\n\t#ID \t LIBRO \t FECHA \n");
                    for (Libros lb2 : lbdao.selectById(id)) {
                        System.out.print("\t " + lb2.getId());
                        System.out.print("\t " + lb2.getNombreLibro());
                        System.out.print(" \t " + lb2.getFecha());

                        System.out.println("");
                    }
                    lbdao.ptc();
                    break;
                case "D":
                    System.out.print("\nIngrese id del registro a borrar: ");
                    resp = lbdao.delete(sc.nextInt());
                    if (resp) {
                        System.out.println("\nRegistro borrado correctamente \n");
                    } else {
                        System.out.println("\nError de registro\n");
                    }
                    lbdao.ptc();
                    break;
                default:
                    System.out.println("Ingrese una de las opciones");
                    break;
            }

        } while (!accion.equals("X"));

    }

}
