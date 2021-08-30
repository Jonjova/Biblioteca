package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import modelo.Libros;

public class LibrosDao {

    Conexion conn;
    String sql;
    PreparedStatement ps;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public LibrosDao(Conexion conn) {
        this.conn = conn;
    }

    public void ptc() {
        System.out.println("Presione enter para continuar");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    public boolean insert(Libros libro) {
        sql = "insert into Libros values(?, ?, ?)";
        try {

            ps = conn.conectar().prepareStatement(sql);
            ps.setInt(1, libro.getId());
            ps.setString(2, libro.getNombreLibro());
            ps.setString(3, sdf.format(libro.getFecha()));

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("ERROR " + e);
            return false;
        }
    }

    public List<Libros> selectAll() {
        sql = "select * from libros";
        try {
            ps = conn.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Libros> lista = new LinkedList<>();
            Libros lb;
            while (rs.next()) {
                lb = new Libros(rs.getInt("id"));
                lb.setNombreLibro(rs.getString("nombre"));
                lb.setFecha(rs.getDate("fecha"));

                lista.add(lb);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean delete(int id) {
        sql = "delete from libros where id = ?";
        try {
            ps = conn.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Libros> selectById(int id) {
        sql = "select * from libros where id = ?";
        try {
            ps = conn.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Libros> lista = new LinkedList<>();
            Libros lb;
            while (rs.next()) {
                lb = new Libros(rs.getInt("id"));
                lb.setNombreLibro(rs.getString("nombre"));
                lb.setFecha(rs.getDate("fecha"));
                lista.add(lb);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean update(Libros lb) {
       sql = "update libros set nombre = ?, "
                + "fecha = ? "
                + "where id = ?";
        try {
            ps = conn.conectar().prepareStatement(sql);
            ps.setString(1, lb.getNombreLibro());
            ps.setString(2, sdf.format(lb.getFecha()));
            ps.setInt(3, lb.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("ERROR " + e);
            return false;
        }
    }

}
