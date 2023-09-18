import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChutadorDeNumeros extends JFrame {
    private int numeroAleatorio;
    private int numeroTentativas;

    private JTextField textField;
    private JLabel infoLabel;

    public ChutadorDeNumeros() {
        setTitle("Chutador de Números");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        numeroAleatorio = gerarNumeroAleatorio();
        numeroTentativas = 0;

        infoLabel = new JLabel("Tente adivinhar o número entre 1 e 100!");
        textField = new JTextField(10);
        JButton botaoChutar = new JButton("Chutar");
        botaoChutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarChute();
            }
        });

        JPanel panel = new JPanel();
        panel.add(infoLabel);
        panel.add(textField);
        panel.add(botaoChutar);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private int gerarNumeroAleatorio() {
        return (int) (Math.random() * 100) + 1;
    }

    private void verificarChute() {
        try {
            int chute = Integer.parseInt(textField.getText());
            numeroTentativas++;

            if (chute == numeroAleatorio) {
                JOptionPane.showMessageDialog(this, "Parabéns, você acertou o número em " + numeroTentativas + " tentativas!");
                reiniciarJogo();
            } else if (chute < numeroAleatorio) {
                infoLabel.setText("Tente novamente. O número é maior que " + chute + ".");
            } else {
                infoLabel.setText("Tente novamente. O número é menor que " + chute + ".");
            }

            textField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido!");
        }
    }

    private void reiniciarJogo() {
        numeroAleatorio = gerarNumeroAleatorio();
        numeroTentativas = 0;
        infoLabel.setText("Tente adivinhar o número entre 1 e 100!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChutadorDeNumeros();
            }
        });
    }
}
