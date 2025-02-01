import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;

public class AESDecryptionUI {

   
    public static String decrypt(String encryptedPassword, String secretKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedPassword = Base64.getDecoder().decode(encryptedPassword);
        byte[] originalPassword = cipher.doFinal(decodedPassword);
        return new String(originalPassword);
    }

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("AES Decryption Tool");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        
        JLabel titleLabel = new JLabel("AES Decryption Tool");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(50, 20, 500, 30);
        frame.add(titleLabel);

        
        JLabel encryptedLabel = new JLabel("Encrypted Password:");
        encryptedLabel.setBounds(50, 80, 150, 25);
        frame.add(encryptedLabel);

        JTextField encryptedField = new JTextField();
        encryptedField.setBounds(200, 80, 300, 25);
        frame.add(encryptedField);

        
        JLabel keyLabel = new JLabel("AES Key (16 chars):");
        keyLabel.setBounds(50, 130, 150, 25);
        frame.add(keyLabel);

        JTextField keyField = new JTextField();
        keyField.setBounds(200, 130, 300, 25);
        frame.add(keyField);

        
        JButton decryptButton = new JButton("Decrypt");
        decryptButton.setBounds(250, 180, 100, 30);
        frame.add(decryptButton);

        
        JLabel resultLabel = new JLabel("Decrypted Password:");
        resultLabel.setBounds(50, 230, 150, 25);
        frame.add(resultLabel);

        JTextField resultField = new JTextField();
        resultField.setBounds(200, 230, 300, 25);
        resultField.setEditable(false);
        frame.add(resultField);

        
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String encryptedPassword = encryptedField.getText().trim();
                String secretKey = keyField.getText().trim();

                try {
                    
                    if (secretKey.length() != 16) {
                        JOptionPane.showMessageDialog(frame, "AES key must be exactly 16 characters!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Decrypt the password
                    String decryptedPassword = decrypt(encryptedPassword, secretKey);
                    resultField.setText(decryptedPassword);
                    JOptionPane.showMessageDialog(frame, "Decryption Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Decryption failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        frame.setVisible(true);
    }
}
