import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.Border;

/** Colores y tipografía compartidos por las ventanas del sistema. */
public final class EstiloVentanas {
    private static final Color FONDO = new Color(239, 245, 250);
    private static final Color PANEL = Color.WHITE;
    private static final Color TEXTO = new Color(43, 58, 74);
    private static final Color AZUL = new Color(48, 105, 157);
    private static final Color VERDE = new Color(35, 139, 112);
    private static final Color ROJO = new Color(198, 75, 86);
    private static final Color GRIS = new Color(101, 119, 139);
    private static final Font NORMAL = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font NEGRITA = new Font("Segoe UI", Font.BOLD, 13);
    private static final Border CAMPO = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(194, 208, 221)),
            BorderFactory.createEmptyBorder(4, 7, 4, 7));

    private EstiloVentanas() { }

    public static void aplicar(Container ventana) {
        ventana.setBackground(FONDO);
        estilizar(ventana);
    }

    private static void estilizar(Container contenedor) {
        for (Component componente : contenedor.getComponents()) {
            if (componente instanceof JToolBar) {
                componente.setBackground(new Color(35, 65, 94));
                ((JToolBar) componente).setBorder(BorderFactory.createEmptyBorder(4, 6, 4, 6));
            } else if (componente instanceof JToggleButton) {
                AbstractButton boton = (AbstractButton) componente;
                boton.setFont(NEGRITA);
                boton.setForeground(Color.WHITE);
                boton.setBackground(new Color(35, 65, 94));
                boton.setOpaque(true);
                boton.setBorder(BorderFactory.createEmptyBorder(7, 10, 7, 10));
            } else if (componente instanceof JButton) {
                estilizarBoton((JButton) componente);
            } else if (componente instanceof JLabel) {
                JLabel etiqueta = (JLabel) componente;
                etiqueta.setForeground(TEXTO);
                etiqueta.setFont(NORMAL);
                if (esTitulo(etiqueta.getText())) {
                    etiqueta.setForeground(new Color(35, 75, 111));
                    etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 19));
                }
            } else if (componente instanceof JTextField || componente instanceof JTextArea
                    || componente instanceof JComboBox || componente instanceof JSpinner) {
                JComponent campo = (JComponent) componente;
                campo.setFont(NORMAL);
                campo.setBackground(PANEL);
                campo.setForeground(TEXTO);
                campo.setBorder(CAMPO);
            } else if (componente instanceof JTable) {
                JTable tabla = (JTable) componente;
                tabla.setFont(NORMAL);
                tabla.setForeground(TEXTO);
                tabla.setBackground(PANEL);
                tabla.setGridColor(new Color(220, 229, 237));
                tabla.setRowHeight(24);
                tabla.getTableHeader().setFont(NEGRITA);
                tabla.getTableHeader().setBackground(new Color(218, 232, 244));
                tabla.getTableHeader().setForeground(TEXTO);
            } else if (componente instanceof JScrollPane) {
                componente.setBackground(PANEL);
                ((JScrollPane) componente).getViewport().setBackground(PANEL);
            } else if (componente instanceof JPanel) {
                componente.setBackground(FONDO);
            }
            if (componente instanceof Container) {
                estilizar((Container) componente);
            }
        }
    }

    private static void estilizarBoton(JButton boton) {
        String texto = boton.getText() == null ? "" : boton.getText().trim().toLowerCase();
        Color fondo = AZUL;
        if (texto.contains("registrar") || texto.contains("nuevo")) fondo = VERDE;
        else if (texto.contains("borrar")) fondo = ROJO;
        else if (texto.contains("cancelar")) fondo = GRIS;
        else if (texto.contains("hoy")) fondo = new Color(193, 137, 48);
        boton.setFont(NEGRITA);
        boton.setForeground(Color.WHITE);
        boton.setBackground(fondo);
        boton.setOpaque(true);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(7, 13, 7, 13));
    }

    private static boolean esTitulo(String texto) {
        if (texto == null) return false;
        String t = texto.trim().toLowerCase();
        return t.equals("infantes") || t.equals("educadores") || t.equals("responsables")
                || t.equals("bitacora") || t.equals("datos del infante");
    }
}
