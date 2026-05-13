package vallegrande.edu.pe.view;

import vallegrande.edu.pe.controller.ClienteController;
import vallegrande.edu.pe.model.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteView extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private ClienteController controller = new ClienteController();
    private JTextField txtNom, txtApe, txtDni, txtCor, txtTel, txtPai;

    private Color rojoTitulo = new Color(168, 51, 40);

    public ClienteView() {
        setTitle("Mantenimiento de Clientes - AGREHIMA");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);

        // Cabecera
        JLabel titulo = new JLabel("LISTADO DE USUARIOS", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(rojoTitulo);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        // Tabla
        modelo = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellidos", "DNI", "Correo", "Teléfono", "País"}, 0);
        tabla = new JTable(modelo);
        tabla.setRowHeight(30);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(rojoTitulo);
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tabla.getSelectedRow();
                if (fila != -1) {
                    txtNom.setText(modelo.getValueAt(fila, 1).toString());
                    txtApe.setText(modelo.getValueAt(fila, 2).toString());
                    txtDni.setText(modelo.getValueAt(fila, 3).toString());
                    txtCor.setText(modelo.getValueAt(fila, 4).toString());
                    txtTel.setText(modelo.getValueAt(fila, 5).toString());
                    txtPai.setText(modelo.getValueAt(fila, 6).toString());
                }
            }
        });

        panelPrincipal.add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Formulario
        JPanel panelInferior = new JPanel(new BorderLayout());
        JPanel pnlCampos = new JPanel(new GridLayout(2, 6, 10, 10));
        pnlCampos.setBackground(Color.WHITE);
        pnlCampos.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        txtNom = new JTextField(); txtApe = new JTextField(); txtDni = new JTextField();
        txtCor = new JTextField(); txtTel = new JTextField(); txtPai = new JTextField();

        pnlCampos.add(new JLabel("Nombre:")); pnlCampos.add(txtNom);
        pnlCampos.add(new JLabel("Apellidos:")); pnlCampos.add(txtApe);
        pnlCampos.add(new JLabel("DNI:")); pnlCampos.add(txtDni);
        pnlCampos.add(new JLabel("Correo:")); pnlCampos.add(txtCor);
        pnlCampos.add(new JLabel("Teléfono:")); pnlCampos.add(txtTel);
        pnlCampos.add(new JLabel("País:")); pnlCampos.add(txtPai);

        // Botones
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        pnlBotones.setBackground(rojoTitulo);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnVolver = new JButton("Volver");

        // --- ACCIÓN AGREGAR CON VALIDACIÓN ---
        btnAgregar.addActionListener(e -> {
            String mensaje = controller.validarFormulario(txtNom.getText(), txtApe.getText(), txtDni.getText(), txtCor.getText(), txtTel.getText(), txtPai.getText());

            if (mensaje.equals("OK")) {
                controller.agregarCliente(txtNom.getText(), txtApe.getText(), txtDni.getText(), txtCor.getText(), txtTel.getText(), txtPai.getText());
                JOptionPane.showMessageDialog(this, "Cliente registrado con éxito.");
                cargarDatos();
                limpiarCampos();
            } else {
                // AQUÍ SE MUESTRA EL ERROR SI HAY NÚMEROS EN EL NOMBRE O CORREO MALO
                JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);
            }
        });

        // --- ACCIÓN ACTUALIZAR CON VALIDACIÓN ---
        btnActualizar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                String mensaje = controller.validarFormulario(txtNom.getText(), txtApe.getText(), txtDni.getText(), txtCor.getText(), txtTel.getText(), txtPai.getText());
                if (mensaje.equals("OK")) {
                    int id = (int) modelo.getValueAt(fila, 0);
                    controller.editarCliente(id, txtNom.getText(), txtApe.getText(), txtDni.getText(), txtCor.getText(), txtTel.getText(), txtPai.getText());
                    JOptionPane.showMessageDialog(this, "Cliente actualizado.");
                    cargarDatos();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un cliente de la tabla.");
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                controller.eliminarCliente((int) modelo.getValueAt(fila, 0));
                cargarDatos();
                limpiarCampos();
            }
        });

        btnVolver.addActionListener(e -> { new InicioView().setVisible(true); dispose(); });

        pnlBotones.add(btnAgregar); pnlBotones.add(btnActualizar);
        pnlBotones.add(btnEliminar); pnlBotones.add(btnVolver);

        panelInferior.add(pnlCampos, BorderLayout.CENTER);
        panelInferior.add(pnlBotones, BorderLayout.SOUTH);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);
        cargarDatos();
    }

    private void cargarDatos() {
        modelo.setRowCount(0);
        List<Cliente> lista = controller.obtenerClientes();
        for (Cliente c : lista) {
            modelo.addRow(new Object[]{c.getId(), c.getNombre(), c.getApellidos(), c.getDni(), c.getCorreo(), c.getTelefono(), c.getPais()});
        }
    }

    private void limpiarCampos() {
        txtNom.setText(""); txtApe.setText(""); txtDni.setText("");
        txtCor.setText(""); txtTel.setText(""); txtPai.setText("");
    }
}