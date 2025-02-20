package io.github.rezi_gelenidze.chatty.auth_service.service.token;

import io.github.rezi_gelenidze.chatty.auth_service.entity.User;
import io.github.rezi_gelenidze.chatty.auth_service.exception.TokenInvalidException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

@Service
public class TokenService {

    @Value("${app.secrets.hmac-secret-key}")
    private String secretKey;

    private static final String ALGORITHM = "HmacSHA256";

    private static final long EMAIL_VERIFICATION_EXPIRY = 86400; // 24 hours
    private static final long PASSWORD_RESET_EXPIRY = 3600; // 1 hour

    public enum TokenType {
        EMAIL_VERIFICATION, PASSWORD_RESET
    }

    public String generateToken(User user, TokenType type) {
        try {
            long expiryTime = type.equals(TokenType.PASSWORD_RESET) ? PASSWORD_RESET_EXPIRY : EMAIL_VERIFICATION_EXPIRY;
            Instant expiryInstant = Instant.now().plusSeconds(expiryTime);
            String payload = user.getEmail() + "|" + expiryInstant.toString() + "|" + type;

            Mac hmac = Mac.getInstance(ALGORITHM);
            hmac.init(new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), ALGORITHM));
            byte[] hash = hmac.doFinal(payload.getBytes(StandardCharsets.UTF_8));

            return Base64.getUrlEncoder().withoutPadding()
                    .encodeToString((payload + "|" + Base64.getEncoder().encodeToString(hash)).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Error generating HMAC token", e);
        }
    }

    public String verifyToken(String token, TokenType expectedType) {
        try {
            String decodedToken = new String(Base64.getUrlDecoder().decode(token), StandardCharsets.UTF_8);
            String[] parts = decodedToken.split("\\|");

            if (parts.length != 4)
                throw new TokenInvalidException("Invalid token format.");

            String email = parts[0];
            Instant expiry = Instant.parse(parts[1]);
            String tokenType = parts[2];
            String receivedHash = parts[3];

            if (!tokenType.equals(expectedType.name()))
                throw new TokenInvalidException("Invalid token type.");

            if (Instant.now().isAfter(expiry))
                throw new TokenInvalidException("Token expired.");

            String payload = email + "|" + expiry + "|" + tokenType;
            Mac hmac = Mac.getInstance(ALGORITHM);
            hmac.init(new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), ALGORITHM));
            byte[] expectedHash = hmac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            String expectedHashBase64 = Base64.getEncoder().encodeToString(expectedHash);

            return expectedHashBase64.equals(receivedHash) ? email : null;
        } catch (Exception e) {
            throw new TokenInvalidException(e.getMessage());
        }
    }
}
