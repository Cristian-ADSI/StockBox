package Vista;

import java.awt.Component;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Mensaje {

    public void emptyFields(Component component) {

        JOptionPane.showMessageDialog(component, "Error de acceso, no debe dejar campos vacios");
    }

    public void invalidUSer(Component component) {

        JOptionPane.showMessageDialog(component, "Error de acceso, Debe ingresar usuarios ualidos");
    }

    public void invalidStatus(Component component, String status, String role) {

        JOptionPane.showMessageDialog(component, "El usuario que intenta usar se encuentra inactivo o no ha sido asignado\n"
                + "para mas informacion pongase en contacto con el administrdor del sistema\n"
                + "Estado: " + status + "\n"
                + "Role " + role);
    }

    public void LoginSuccesfully(Component component, String role) {

        JOptionPane.showMessageDialog(component, "Login exitoso, \nacceso como " + role);
    }

    public void errorInSQLQuery(String text, SQLException error) {
        System.err.println(text);
        System.err.println(error);
    }
//==========================================
    public void usuarioUpdatedSuccessfully() {

        JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
    }
    public void usuarioUpdateFailed() {

        JOptionPane.showMessageDialog(null, "No se pudo actualizar el usuario, error a nivel de sistema");
    }
    public int  usuarioUpdateConfirmation() {

        return JOptionPane.showConfirmDialog(null, "Estas seguro que deseas actualizar el usuario seleccionado?");
    }

    public void usuarioDeletedSuccessfully() {

        JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
    }
    public void usuarioDeleteFailed() {

        JOptionPane.showMessageDialog(null, "No se pudo eliminar el Usuario, error a nivel de sistema");
    }
    public int  usuarioDeleteConfirmation(){
        return JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar el Usuario seleccionado?");
    }
    
    
    public void usuarioCreatedSuccessfully() {

        JOptionPane.showMessageDialog(null, "Usuario creado correctamente");
    }
    public void usuarioCreateFailed() {

        JOptionPane.showMessageDialog(null, "No se pudo crear el usuario, error a nivel de sistema");
    }
    public int  usuarioCreateConfirmation() {
        return JOptionPane.showConfirmDialog(null, "Deseas crear un nuevo registor de Empleado?");
    }
//==========================================
    
    public void clienteSearchFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo encontrar cliente. error de sistema");
    }
    public void clienteReadFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo listar clientes. error de sistema");
    }
    public void clienteDNIEmpty(){
        JOptionPane.showMessageDialog(null, "Debe Ingresar el DNI del Cliente");
    }
    public int clienteRegisterConfirmation(){
        return JOptionPane.showConfirmDialog(null, "Cliente No Registrado.\nDesea Registrarlo?");
    }
    
    public void clienteUpdateSuccessfully() {

        JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente");
    }
    public void clienteUpdateFailed() {

        JOptionPane.showMessageDialog(null, "No se pudo actualizar el Cliente, error a nivel de sistema");
    }
    public int  clienteUpdateConfirmation(){
        return JOptionPane.showConfirmDialog(null, "Estas seguro que deseas actualizar el Cliente seleccionado?");
    }
    
    public void clienteDeletedSuccessfully() {
        JOptionPane.showMessageDialog(null, "Cliente elminado con exito");
    }
    public void clienteDeleteFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo eliminar el cliente. error de sistema");
    }
    public int  clienteDeleteConfirmation() {
        return JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar el Cliente seleccionado?");
    }
    
    
    public void clienteCreatedSuccessfully() {
        JOptionPane.showMessageDialog(null, "Cliente creado con exito");
    }
    public void ClienteCreateFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo crear el cliente. error de sistema");
    }
    public int  clientesCreateConfirmation() {
        return JOptionPane.showConfirmDialog(null, "Deseas crear un nuevo registor de Cliente? ?");
    }
    
 //    =================================================================   
    public void productoCreateSuccessfully() {
        JOptionPane.showMessageDialog(null, "Producto creado  correctamente");
    }
    public void productoCreateFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo crear el Producto, error a nivel de sistema");
    }
    public int  productoCreateConfirmation() {
        return JOptionPane.showConfirmDialog(null, "Deseas agregar un nuevo Producto?");
    }
    
    public void productoUpdatedSuccessfully() {
        JOptionPane.showMessageDialog(null, "Producto actualizado  correctamente");
    }
    public void productoUpdateFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar el Producto, error a nivel de sistema");
    } 
    public int  productoUpdateConfirmation() {
        return JOptionPane.showConfirmDialog(null, "Deseas actualizar el Producto seleccionado?");
    }
    
    public void stockUpdateFailed() {

        JOptionPane.showMessageDialog(null, "No se pudo actualizar el Stock, error a nivel de sistema");
    }

    public void searchProductFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo encontrar el producto, error a nivel de sistema");
    }

    public void productReadFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo cargar los productos, error a nivel de sistema");
    }

    public void productoDeletedSuccessfully() {
        JOptionPane.showMessageDialog(null, "Producto eliminado  correctamente");
    }
    public void productDeleteFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto, error a nivel de sistema");
    }
    public int  productoDeleteConfirmation() {
        return JOptionPane.showConfirmDialog(null, "Deseas eliminar el Producto seleccionado?");
    }

//    =================================================================

    public void saleCanceledSuccessfully() {
        JOptionPane.showMessageDialog(null, "Venta Cancelada Correctamente");
    }

    public void saleCanceleFailed() {
        JOptionPane.showMessageDialog(null, "No se Pudo Cancelar la Venta, Error de sistema");
    }

    public void getMaxSaleIdFailed() {
        JOptionPane.showMessageDialog(null, "No se Pudo continuar la operacion, error de sistema");
    }

    public void saleSavedSuccessfully() {
        JOptionPane.showMessageDialog(null, "Venta guardada Correctamente");
    }

    public void saleSaveFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo guardar la venta. error de sistema");
    }

    public void saleSaveDetailFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo guardar el detalle de la venta. error de sistema");
    }

    public void getMaxSerialNumberFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo cargar el numero de serie. error de sistema");
    }


   
//    ========================================================
    public void noSelectedRow(){
        JOptionPane.showMessageDialog(null, "Debes seleccionar un registro de la tabla");
    }
    public void operationCanceled() {
        JOptionPane.showMessageDialog(null, "Operacion Cancelada");
    }
    public void emptyFields(){
        JOptionPane.showMessageDialog(null, "No se pudo realizar la operacion, verifique que los campos tengan valores validos");
    }

}   
