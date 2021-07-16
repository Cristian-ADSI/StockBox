
package Controlador;

import java.util.List;


public interface CRUD {
    public List Listar();
    public int Crear  (Object[] ob);
    public int Actualizar(Object[] ob);
    public void Eliminar(int id);
    
    
}
