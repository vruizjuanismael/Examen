/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

//import database.Conexion;
import datos.PropiedadesDAO;
//import datos.CategoriaDAO;
import entidades.Propiedades;
//import entidades.Categoria;
//import java.io.File;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
/*import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;*/

/**
 *
 * @author JcarlosAd7
 */
public class PropiedadesControl {
    private final PropiedadesDAO DATOS;
    //private final CategoriaDAO DATOSCAT;
    private Propiedades obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public PropiedadesControl(){
        this.DATOS=new PropiedadesDAO();
        //this.DATOSCAT=new CategoriaDAO();
        this.obj=new Propiedades();
        this.registrosMostrados=0;
    }
    
   public DefaultTableModel listar(String texto, int totalPorPagina, int numPagina) {
    List<Propiedades> lista = new ArrayList();
    lista.addAll(DATOS.listar(texto, totalPorPagina, numPagina));

    String[] titulos = {"Id", "Nombre", "Direccion", "Caracteristicas", "Estado", "Precio Alquiler"};
    this.modeloTabla = new DefaultTableModel(null, titulos);

    String[] registro = new String[6];

    this.registrosMostrados = 0;
    
    for (Propiedades item : lista) {
        registro[0] = Integer.toString(item.getId());
        registro[1] = item.getNombre();
        registro[2] = item.getDireccion();
        registro[3] = item.getCaracteristicas();
        registro[4] = item.isEstado() ? "Activo" : "Inactivo"; // Si estado es un booleano
        registro[5] = Double.toString(item.getPrecioAlquiler());

        this.modeloTabla.addRow(registro);
        this.registrosMostrados = this.registrosMostrados + 1;
    }

    return this.modeloTabla;
}
    /*
    public DefaultComboBoxModel seleccionar(){
        DefaultComboBoxModel items= new DefaultComboBoxModel();
        List<Categoria> lista=new ArrayList();
        lista=DATOSCAT.seleccionar();
        for (Categoria item: lista){
            items.addElement(new Categoria(item.getId(),item.getNombre()));
        }
        return items;
    }*/

    public String insertar(String nombre, String direccion, String caracteristicas, boolean estado, double precioAlquiler) {
    //Producto obj = new Producto(); // Asegúrate de tener un objeto Producto disponible

    // Verifica si el registro ya existe
    if (DATOS.existe(nombre)) {
        return "El registro ya existe.";
    } else {
        // Configura los atributos del objeto Producto
        obj.setNombre(nombre);
        obj.setDireccion(direccion);
        obj.setCaracteristicas(caracteristicas);
        obj.setEstado(estado);
        obj.setPrecioAlquiler(precioAlquiler);
        // Intenta insertar el producto en la base de datos
        if (DATOS.insertar(obj)) {
            return "OK";
        } else {
            return "Error en el registro.";
        }
    }
}
    
    public String actualizar(String nombre, String direccion, String caracteristicas, boolean estado, double precioAlquiler){
        if (nombre.equals(nombre)){
            obj.setNombre(nombre);
            obj.setDireccion(direccion);
            obj.setCaracteristicas(caracteristicas);
            obj.setEstado(estado);
            obj.setPrecioAlquiler(precioAlquiler);
            if(DATOS.actualizar(obj)){
                return "OK";
            }else{
                return "Error en la actualización.";
            }
        }else{
            if (DATOS.existe(nombre)){
                return "El registro ya existe.";
            }else{
                obj.setNombre(nombre);
                obj.setDireccion(direccion);
                obj.setCaracteristicas(caracteristicas);
                obj.setEstado(estado);
                obj.setPrecioAlquiler(precioAlquiler);
                if (DATOS.actualizar(obj)){
                    return "OK";
                }else{
                    return "Error en la actualización.";
                }
            }
        }
    }
    
    public String eliminar(int id) {
        if (DATOS.eliminar(id)) {
            return "OK";
        } else {
            return "Error en la eliminación de datos.";
        }
    }
    
    /*
    public String desactivar(int id){
        if (DATOS.desactivar(id)){
            return "OK";
        }else{
            return "No se puede desactivar el registro";
        }
    }
    
    public String activar(int id){
        if (DATOS.activar(id)){
            return "OK";
        }else{
            return "No se puede activar el registro";
        }
    }
    
    public int total(){
        return DATOS.total();
    }
    
    public int totalMostrados(){
        return this.registrosMostrados;
    }*/
    /*
    public void reporteArticulos(){
        Map p=new HashMap();
        JasperReport report;
        JasperPrint print;
        
        Conexion cnn=Conexion.getInstancia();
        
        try {
            report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+
                    "/src/reportes/RptArticulos.jrxml");
            print=JasperFillManager.fillReport(report, p,cnn.conectar());
            JasperViewer view=new JasperViewer(print,false);
            view.setTitle("Reporte de Artículos");
            view.setVisible(true);
        } catch (JRException e) {
            e.getMessage();
        }
    }
    *//*
    public void reporteArticulosBarras(){
        Map p=new HashMap();
        JasperReport report;
        JasperPrint print;
        
        Conexion cnn=Conexion.getInstancia();
        
        try {
            report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+
                    "/src/reportes/RptArticulosBarras.jrxml");
            print=JasperFillManager.fillReport(report, p,cnn.conectar());
            JasperViewer view=new JasperViewer(print,false);
            view.setTitle("Reporte de Artículos");
            view.setVisible(true);
        } catch (JRException e) {
            e.getMessage();
        }
    }*/
}
