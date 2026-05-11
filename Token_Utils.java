package utils;

public class TokenUtils {

    // Method to Tamper Token
    public static String tamperToken(String token) {

        // Null Validation
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException(
                    "Token cannot be null or empty"
            );
        }

        // Minimum Length Validation
        if (token.length() < 5) {
            throw new IllegalArgumentException(
                    "Invalid token length"
            );
        }

        // Tamper Last Characters
        return token.substring(0,
                token.length() - 3) + "XYZ";
    }

    // Expired Token Simulation
    public static String expiredToken() {

        return "expired.jwt.token.demo";
    }

    // Completely Invalid Token
    public static String invalidToken() {

        return "invalid_token_123";
    }
}