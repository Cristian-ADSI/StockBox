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

    public void userCreatedSuccessfully() {

        JOptionPane.showMessageDialog(null, "Usuario creado correctamente");
    }

    public void userUpdatedSuccessfully() {

        JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
    }

    public void userDeletdSuccessfully() {

        JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
    }

    public void userCreationFailed() {

        JOptionPane.showMessageDialog(null, "No se pudo crear el usuario, verifique que los campos tengan valores validos");
    }

    public void userUpdateFailed() {

        JOptionPane.showMessageDialog(null, "No se pudo actualizar el usuario, error a nivel de sistema");
    }

    public void userDeleteFailed() {

        JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario, error a nivel de sistema");
    }

    public void productCreatedSuccessfully() {
        JOptionPane.showMessageDialog(null, "Producto creado  correctamente");
    }

    public void productUpdatedSuccessfully() {
        JOptionPane.showMessageDialog(null, "Producto actualizado  correctamente");
    }

    public void productDeletedSuccessfully() {
        JOptionPane.showMessageDialog(null, "Producto eliminado  correctamente");
    }

    public void productCreateFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo crear el producto, error a nivel de sistema");
    }

    public void productUpdateFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar el producto, error a nivel de sistema");
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

    public void productDeleteFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto, error a nivel de sistema");
    }

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

    public void searchCustomerFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo encontrar cliente. error de sistema");
    }

    public void readCustomersFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo listar clientes. error de sistema");
    }

    public void createCustomerSuccess() {
        JOptionPane.showMessageDialog(null, "Cliente creado con exito");
    }

    public void createCustomerFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo crear el cliente. error de sistema");
    }

    public void updateCustomerSuccess() {
        JOptionPane.showMessageDialog(null, "Cliente actualizado con exito");
    }

    public void updateCustomerFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo actuializar el cliente. error de sistema");
    }

    public void deleteCustomerSuccess() {
        JOptionPane.showMessageDialog(null, "Cliente elminado con exito");
    }

    public void deleteCustomerFailed() {
        JOptionPane.showMessageDialog(null, "No se pudo eliminar el cliente. error de sistema");
    }
}
