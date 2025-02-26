
package Controlador;

import java.util.List;
import java.util.Map;


public interface CRUD {
    public List read();
    public boolean create  (Map formData);
    public int  update(Map formData);
    public int delete(int id); 
}
