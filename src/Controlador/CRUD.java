
package Controlador;

import java.util.List;


public interface CRUD {
    public List Read();
    public int  Create  (Object[] ob);
    public int  Update(Object[] ob);
    public void Delete(int id); 
}
