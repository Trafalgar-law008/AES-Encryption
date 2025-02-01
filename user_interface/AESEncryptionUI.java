import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;

public class AESEncryptionUI {

    
    public static String encrypt(String password, String secretKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedPassword = cipher.doFinal(password.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedPassword);
    }

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("AES Encryption Tool");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        
        JLabel titleLabel = new JLabel("AES Encryption Tool");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(50, 20, 500, 30);
        frame.add(titleLabel);

        
        JLabel usernameLabel = new JLabel("Enter Username:");
        usernameLabel.setBounds(50, 80, 150, 25);
        frame.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(200, 80, 300, 25);
        frame.add(usernameField);

        
        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setBounds(50, 130, 150, 25);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(200, 130, 300, 25);
        frame.add(passwordField);

        // AES Key Label and Field
        JLabel keyLabel = new JLabel("Enter AES Key (16 chars):");
        keyLabel.setBounds(50, 180, 180, 25);
        frame.add(keyLabel);

        JTextField keyField = new JTextField();
        keyField.setBounds(200, 180, 300, 25);
        frame.add(keyField);

       
        JButton encryptButton = new JButton("Encrypt");
        encryptButton.setBounds(250, 230, 100, 30);
        frame.add(encryptButton);

        
        JLabel resultLabel = new JLabel("Encrypted Password:");
        resultLabel.setBounds(50, 280, 150, 25);
        frame.add(resultLabel);

        JTextField resultField = new JTextField();
        resultField.setBounds(200, 280, 300, 25);
        resultField.setEditable(false);
        frame.add(resultField);

        
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String secretKey = keyField.getText().trim();

               
                if (secretKey.length() != 16) {
                    JOptionPane.showMessageDialog(frame, "AES key must be exactly 16 characters long!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    String encryptedPassword = encrypt(password, secretKey);
                    resultField.setText(encryptedPassword);
                    JOptionPane.showMessageDialog(frame, "Encryption Successful!\nEncrypted password for " + username + " generated.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Encryption Failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        frame.setVisible(true);
    }
}
