import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCrypt {
    public static void main(String[] args) {
        String rawPassword = "trapo2020";
        String hashedPassword = "$2a$10$UoHrqUXgmrtH.1JZtYfPLeK7tkCSGsy/3pVbUmdkNwfiYDAZ1puq6"; // aquí pones el que está en tu base

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matches = encoder.matches(rawPassword, hashedPassword);

        System.out.println("Coincide? " + matches);
    }
}
