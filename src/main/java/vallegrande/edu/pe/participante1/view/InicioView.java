package vallegrande.edu.pe.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InicioView extends JFrame {
    public InicioView() {
        setTitle("Sistema de Gestión AGREHIMA");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        // Borde decorativo
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(168, 51, 40), 3),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titulo = new JLabel("AGREHIMA");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        titulo.setForeground(new Color(168, 51, 40));

        JLabel subtitulo = new JLabel("Gestión Administrativa de Clientes");
        subtitulo.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        subtitulo.setForeground(Color.DARK_GRAY);

        JButton btnIr = new JButton("INGRESAR");
        btnIr.setPreferredSize(new Dimension(250, 50));
        btnIr.setBackground(new Color(168, 51, 40));
        btnIr.setForeground(Color.WHITE);
        btnIr.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnIr.setFocusPainted(false);
        btnIr.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto Hover
        btnIr.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnIr.setBackground(new Color(234, 99, 86)); }
            public void mouseExited(MouseEvent e) { btnIr.setBackground(new Color(168, 51, 40)); }
        });

        btnIr.addActionListener(e -> {
            new ClienteView().setVisible(true);
            dispose();
        });

        gbc.gridy = 0; panel.add(titulo, gbc);
        gbc.insets = new Insets(10, 0, 40, 0);
        gbc.gridy = 1; panel.add(subtitulo, gbc);
        gbc.gridy = 2; panel.add(btnIr, gbc);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InicioView().setVisible(true));
    }
}