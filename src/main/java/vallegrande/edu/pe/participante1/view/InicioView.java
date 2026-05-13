package vallegrande.edu.pe.view;

import javax.swing.*;
import java.awt.*;

public class InicioView extends JFrame {
    public InicioView() {
        setTitle("Sistema de Gestión");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titulo = new JLabel("BIENVENIDO A AGREHIMA");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(168, 51, 40));

        JButton btnIr = new JButton("Gestionar Clientes");
        btnIr.setBackground(new Color(234, 99, 86));
        btnIr.setForeground(Color.BLACK);
        btnIr.setPreferredSize(new Dimension(200, 40));

        btnIr.addActionListener(e -> {
            new vallegrande.edu.pe.view.ClienteView().setVisible(true);
            dispose();
        });

        gbc.gridy = 0; panel.add(titulo, gbc);
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.gridy = 1; panel.add(btnIr, gbc);

        add(panel);
    }
}