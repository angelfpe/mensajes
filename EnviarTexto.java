import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.JLabel;

public class EnviarTexto implements ActionListener {

    public final static int PUERTO_SERVIDOR = 5000;
    public final static String IP_SERVIDOR = "192.168.100.237";

    private final JTextField txtCampo;
    private JLabel JLabelCesar;

    public EnviarTexto(JTextField txtCampo, JLabel JLabelCesar) {
        this.txtCampo = txtCampo;
        this.JLabelCesar = JLabelCesar;
    }

    public EnviarTexto(JTextField txtCampo) {
        this.txtCampo = txtCampo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Socket socket = new Socket(IP_SERVIDOR, PUERTO_SERVIDOR);
            try (DataOutputStream flujosalida = new DataOutputStream(socket.getOutputStream())) {
                String mensaje = JLabelCesar.getText() + ": " + txtCampo.getText() + "\n";
                flujosalida.writeUTF(mensaje);
                txtCampo.setText("");
            }
        } catch (IOException ex) {
            System.out.println("Mensaje de error: " + ex.getMessage());
        }
    }

}