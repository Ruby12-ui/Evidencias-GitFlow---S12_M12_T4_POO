package vallegrande.edu.pe.controller;

import vallegrande.edu.pe.model.Cliente;
import vallegrande.edu.pe.model.ClienteDAO;
import java.util.List;

public class ClienteController {
    private final ClienteDAO dao = new ClienteDAO();

    public List<Cliente> obtenerClientes() {
        return dao.listar();
    }

    // --- SISTEMA DE VALIDACIÓN ---
    public String validarFormulario(String nom, String ape, String dni, String cor, String tel, String pai) {
        // 1. Verificar campos vacíos
        if (nom.trim().isEmpty() || ape.trim().isEmpty() || dni.trim().isEmpty() ||
                cor.trim().isEmpty() || tel.trim().isEmpty() || pai.trim().isEmpty()) {
            return "Error: Todos los campos deben estar llenos.";
        }

        // 2. Validar que Nombre y Apellido no tengan números
        if (!nom.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$") || !ape.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            return "Error: Nombre y Apellidos solo deben contener letras.";
        }

        // 3. Validar DNI (Exactamente 8 dígitos numéricos)
        if (!dni.matches("\\d{8}")) {
            return "Error: El DNI debe tener exactamente 8 números.";
        }

        // 4. Validar Correo (Debe tener @ y un dominio)
        if (!cor.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {
            return "Error: El correo electrónico no tiene un formato válido (ejemplo@dominio.com).";
        }

        // 5. Validar Teléfono (Solo números, mínimo 9 dígitos para celular Perú)
        if (!tel.matches("\\d{9,15}")) {
            return "Error: El teléfono debe ser numérico y tener al menos 9 dígitos.";
        }

        return "OK"; // Si pasa todo, devuelve OK
    }

    public void agregarCliente(String nom, String ape, String dni, String cor, String tel, String pai) {
        Cliente c = new Cliente();
        c.setNombre(nom);
        c.setApellidos(ape);
        c.setDni(dni);
        c.setCorreo(cor);
        c.setTelefono(tel);
        c.setPais(pai);
        dao.insertar(c);
    }

    public void editarCliente(int id, String nom, String ape, String dni, String cor, String tel, String pai) {
        Cliente c = new Cliente();
        c.setId(id);
        c.setNombre(nom);
        c.setApellidos(ape);
        c.setDni(dni);
        c.setCorreo(cor);
        c.setTelefono(tel);
        c.setPais(pai);
        dao.actualizar(c);
    }

    public void eliminarCliente(int id) {
        dao.eliminar(id);
    }

    public Cliente buscarCliente(String dni) {
        return dao.buscar(dni);
    }
}