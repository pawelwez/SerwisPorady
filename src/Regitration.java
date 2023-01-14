import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regitration {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nazwa użutkownika:");
        String username = scanner.nextLine();

        System.out.println("Hasło:");
        String password = scanner.nextLine();

        // Sprawdzenie czy hasło spełnia określone wymagania
        if (password.length() < 8) {
            System.out.println("Error: hasło musi zawierać min. 8 znaków.");
            return;
        }

        // Hashowanie hasła
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        String hashedPassword = sb.toString();

        System.out.println("E-mail:");
        String email = scanner.nextLine();

        // Sprawdzenie poprawności adresu e-mail
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        Matcher matcher = pat.matcher(email);
        if (!matcher.matches()) {
            System.out.println("Error: Niepoprawny adres e-mail.");
            return;
        }

        // Tutaj można dodać kod do zapisywania użytkownika do bazy danych (z hasłem zahashowanym)

        System.out.println("Rejestracja przebiegła pomyślnie!");
    }
}
