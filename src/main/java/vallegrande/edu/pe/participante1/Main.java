package vallegrande.edu.pe;

import vallegrande.edu.pe.view.InicioView;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        java.awt.EventQueue.invokeLater(() -> {
            new InicioView().setVisible(true);
        });
    }
}