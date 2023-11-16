/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.interfaces;

import java.util.List;

/**
 *
 * @author JcarlosAd7
 */
public interface CrudPaginadoInterface<T> {
   public List<T> listar(String texto,int totalPorPagina,int numPagina);
   public boolean insertar(T obj);
   public boolean actualizar(T obj);
   public boolean existe(String texto);
   public boolean eliminar(int id);
}
