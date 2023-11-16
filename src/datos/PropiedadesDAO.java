/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.Conexion;
import datos.interfaces.CrudPaginadoInterface;
import entidades.Propiedades;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PropiedadesDAO implements CrudPaginadoInterface<Propiedades> {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public PropiedadesDAO(){
        CON=Conexion.getInstancia();
    }
    
    
    @Override
    public List<Propiedades> listar(String texto, int totalPorPagina, int numPagina) {
    List<Propiedades> registros = new ArrayList<>();
    
    try {
        ps = CON.conectar().prepareStatement("SELECT a.id, a.nombre, a.direccion, a.caracteristicas, a.estado, a.precioAlquiler FROM propiedades a WHERE a.nombre LIKE ? ORDER BY a.id ASC LIMIT ?,?");
        ps.setString(1, "%" + texto + "%");
        ps.setInt(2, (numPagina - 1) * totalPorPagina);
        ps.setInt(3, totalPorPagina);
        rs = ps.executeQuery();
        while (rs.next()) {
            registros.add(new Propiedades(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getDouble(6)));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            CON.desconectar();
        }
    }
    return registros;
}

    @Override
    public boolean insertar(Propiedades obj) {
    resp = false;
    try {
        ps = CON.conectar().prepareStatement("INSERT INTO propiedades (nombre, direccion, caracteristicas, estado, precioAlquiler) VALUES (?,?,?,?,?)");
        ps.setString(1, obj.getNombre());
        ps.setString(2, obj.getDireccion());
        ps.setString(3, obj.getCaracteristicas());
        ps.setBoolean(4, obj.isEstado());
        ps.setDouble(5, obj.getPrecioAlquiler());

        if (ps.executeUpdate() > 0) {
            resp = true;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            CON.desconectar();
        }
    }
    return resp;
}
    @Override
    public boolean existe(String texto) {
        resp=false;
        try {
            ps=CON.conectar().prepareStatement("SELECT nombre FROM propiedades WHERE nombre=?");
            ps.setString(1, texto);
            rs=ps.executeQuery();
            rs.last();
            if(rs.getRow()>0){
                resp=true;
            }           
            ps.close();
            rs.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return resp;
    }
    
    @Override
     public boolean actualizar(Propiedades obj) {
        resp=false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE propiedades SET nombre=?, direccion=?, caracteristicas=?, estado=?, Precioalquiler=? WHERE nombre=?");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDireccion());
            ps.setString(3, obj.getCaracteristicas());
            ps.setBoolean(4, obj.isEstado());
            ps.setDouble(5, obj.getPrecioAlquiler());
            
            if (ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean eliminar(int id) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("DELETE FROM propiedades WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

}
