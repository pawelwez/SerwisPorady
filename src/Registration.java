import java.sql.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Registration {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nazwa użytkownika:");
        String username = scanner.nextLine();

        System.out.println("Wprowadź hasło:");
        String password = scanner.nextLine();

        // Sprawdzenie czy hasło spełnia określone wymagania (np. długość)
        if (password.length() < 8) {
            System.out.println("Hasło musi zawierać min. 8 znaków");
            return;
        }

        System.out.println("Wprowadź e-mail:");
        String email = scanner.nextLine();

        // Sprawdzenie poprawności adresu e-mail
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        Matcher matcher = pat.matcher(email);
        if (!matcher.matches()) {
            System.out.println("Niepoprawny adres e-mail");
            return;
        }

        // Hashowanie hasła
        byte[] salt = new byte[16];
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.nextBytes(salt);
        int iterations = 10000;
        int keyLength = 512;
        char[] passwordChars = password.toCharArray();
        PBEKeySpec spec = new PBEKeySpec(passwordChars, salt, iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hashedPassword = skf.generateSecret(spec).getEncoded();

        // Zapisanie użytkownika do bazy danych
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://hostname:port/dbname", "username", "password");

            // Create a statement
            Statement statement = connection.createStatement();

            // Insert the new user into the users table
            String sql = "INSERT INTO users (username, password, email, salt, iterations) VALUES ('" +
                    username + "', '" + Base64.getEncoder().encodeToString(hashedPassword) + "', '" + email + "', '" + Base64.getEncoder().encodeToString(salt) + "', " + iterations + ")";
            statement.executeUpdate(sql);

            System.out.println("User added successfully to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            boolean connection = false;
            if (connection = Boolean.parseBoolean(null)) {
                System.exit(0);
            }
        }
    }
}

