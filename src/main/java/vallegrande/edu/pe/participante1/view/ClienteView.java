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

    // TUS COLORES SOLICITADOS
    private Color rojoCabecera = new Color(234, 99, 86); // Rojo claro
    private Color rojoTitulo = new Color(168, 51, 40);   // Rojo oscuro

    public ClienteView() {
        setTitle("Mantenimiento de Clientes");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);

        // --- TÍTULO PRINCIPAL ---
        JLabel titulo = new JLabel("LISTADO DE USUARIOS", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(rojoTitulo);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        // --- TABLA Y MODELO ---
        modelo = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellidos", "DNI", "Correo", "Teléfono", "País"}, 0);
        tabla = new JTable(modelo);
        tabla.setRowHeight(30);

        // ============================================================
        // PINTANDO LOS CUADROS DE ARRIBA (ID, NOMBRE, DNI, ETC)
        // ============================================================
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(rojoTitulo); // Fondo Rojo Oscuro
        headerRenderer.setForeground(Color.WHITE); // Letra Blanca
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 15));
        headerRenderer.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        for (int i = 0; i < tabla.getModel().getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        tabla.getTableHeader().setPreferredSize(new Dimension(0, 40));
        tabla.getTableHeader().setReorderingAllowed(false);

        // Llenar campos al hacer clic en la tabla
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

        JScrollPane scrollPane = new JScrollPane(tabla);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // --- PANEL INFERIOR (DATOS Y BOTONES) ---
        JPanel panelInferior = new JPanel(new BorderLayout());

        // 1. Panel de Campos de Texto
        JPanel pnlCampos = new JPanel(new GridLayout(2, 6, 10, 10));
        pnlCampos.setBackground(Color.WHITE);
        pnlCampos.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        txtNom = new JTextField(); txtApe = new JTextField(); txtDni = new JTextField();
        txtCor = new JTextField(); txtTel = new JTextField(); txtPai = new JTextField();

        JLabel lblNom = new JLabel("Nombre:", JLabel.RIGHT); lblNom.setForeground(rojoTitulo);
        JLabel lblApe = new JLabel("Apellidos:", JLabel.RIGHT); lblApe.setForeground(rojoTitulo);
        JLabel lblDni = new JLabel("DNI:", JLabel.RIGHT); lblDni.setForeground(rojoTitulo);
        JLabel lblCor = new JLabel("Correo:", JLabel.RIGHT); lblCor.setForeground(rojoTitulo);
        JLabel lblTel = new JLabel("Teléfono:", JLabel.RIGHT); lblTel.setForeground(rojoTitulo);
        JLabel lblPai = new JLabel("País:", JLabel.RIGHT); lblPai.setForeground(rojoTitulo);

        pnlCampos.add(lblNom); pnlCampos.add(txtNom);
        pnlCampos.add(lblApe); pnlCampos.add(txtApe);
        pnlCampos.add(lblDni); pnlCampos.add(txtDni);
        pnlCampos.add(lblCor); pnlCampos.add(txtCor);
        pnlCampos.add(lblTel); pnlCampos.add(txtTel);
        pnlCampos.add(lblPai); pnlCampos.add(txtPai);

        // 2. PANEL DE BOTONES (FONDO ROJO SOLICITADO)
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        pnlBotones.setBackground(rojoTitulo); // Fondo Rojo Oscuro

        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnVolver = new JButton("Volver");

        // Estilo de botones para resaltar sobre el fondo rojo
        JButton[] botones = {btnAgregar, btnActualizar, btnEliminar, btnBuscar, btnVolver};
        for(JButton btn : botones) {
            btn.setBackground(Color.WHITE); // Botón blanco
            btn.setForeground(rojoTitulo);  // Texto rojo oscuro
            btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        }

        // --- ACCIONES ---
        btnAgregar.addActionListener(e -> {
            controller.agregarCliente(txtNom.getText(), txtApe.getText(), txtDni.getText(), txtCor.getText(), txtTel.getText(), txtPai.getText());
            cargarDatos(); limpiarCampos();
        });

        btnActualizar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                int id = (int) modelo.getValueAt(fila, 0);
                controller.editarCliente(id, txtNom.getText(), txtApe.getText(), txtDni.getText(), txtCor.getText(), txtTel.getText(), txtPai.getText());
                cargarDatos(); limpiarCampos();
            } else { JOptionPane.showMessageDialog(this, "Seleccione una fila para editar"); }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                controller.eliminarCliente((int) modelo.getValueAt(fila, 0));
                cargarDatos();
            }
        });

        btnBuscar.addActionListener(e -> {
            Cliente c = controller.buscarCliente(txtDni.getText());
            if (c != null) {
                txtNom.setText(c.getNombre()); txtApe.setText(c.getApellidos());
                txtCor.setText(c.getCorreo()); txtTel.setText(c.getTelefono()); txtPai.setText(c.getPais());
            } else { JOptionPane.showMessageDialog(this, "DNI no encontrado"); }
        });

        btnVolver.addActionListener(e -> { new InicioView().setVisible(true); dispose(); });

        pnlBotones.add(btnAgregar); pnlBotones.add(btnActualizar);
        pnlBotones.add(btnEliminar); pnlBotones.add(btnBuscar); pnlBotones.add(btnVolver);

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