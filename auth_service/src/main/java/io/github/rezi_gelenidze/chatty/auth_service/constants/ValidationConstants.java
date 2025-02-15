package io.github.rezi_gelenidze.chatty.auth_service.constants;

public class ValidationConstants {
    // Username Constraints
    public static final int USERNAME_MIN_LENGTH = 1;
    public static final int USERNAME_MAX_LENGTH = 50;
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9._-]+$";

    public static final String USERNAME_REQUIRED_MESSAGE = "\"username\" field is required.";
    public static final String USERNAME_REGEX_MESSAGE = "Username can only contain letters, numbers, dots, underscores, and hyphens";
    public static final String USERNAME_LENGTH_MESSAGE = "Username must be between " + USERNAME_MIN_LENGTH + " and " + USERNAME_MAX_LENGTH + " characters.";

    // Password Constraints
    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 100;
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";

    public static final String PASSWORD_REQUIRED_MESSAGE = "\"password\" field is required.";
    public static final String PASSWORD_LENGTH_MESSAGE = "Password must be between " + PASSWORD_MIN_LENGTH + " and " + PASSWORD_MAX_LENGTH + " characters.";
    public static final String PASSWORD_REGEX_MESSAGE = "Password must contain at least one digit, one lowercase, one uppercase, and one special character.";

    // Email Constraints
    public static final int EMAIL_MAX_LENGTH = 255;

    public static final String EMAIL_REQUIRED_MESSAGE = "\"email\" field is required.";
    public static final String EMAIL_INVALID_MESSAGE = "Email should be valid.";
    public static final String EMAIL_LENGTH_MESSAGE = "Email must not exceed " + EMAIL_MAX_LENGTH + " characters.";

    // Name Constraints
    public static final int NAME_MIN_LENGTH = 2;
    public static final int NAME_MAX_LENGTH = 255;
    public static final String NAME_REGEX = "^[\\p{L}\\s'-]+$";

    public static final String NAME_REQUIRED_MESSAGE = "Name field is required.";
    public static final String NAME_REGEX_MESSAGE = "Name can only contain letters from any language, spaces, and hyphens.";
    public static final String NAME_LENGTH_MESSAGE = "Name must be between " + NAME_MIN_LENGTH + " and " + NAME_MAX_LENGTH + " characters.";

    // Date of Birth Constraints
    public static final int DOB_MIN_AGE = 16;

    public static final String DOB_REQUIRED_MESSAGE = "Date of birth is mandatory.";
    public static final String DOB_PAST_MESSAGE = "Date of birth must be in the past.";
    public static final String DOB_AGE_MESSAGE = "You must be at least " + DOB_MIN_AGE + " years old.";

    // Password Match Constraint
    public static final String PASSWORD_MISMATCH_MESSAGE = "Passwords do not match.";
    public static final String PASSWORD_REPEAT_REQUIRED_MESSAGE = "\"passwordRepeat\" field is required.";

    // Token Constraints
    public static final String ACCESS_TOKEN_REQUIRED_MESSAGE = "\"accessToken\" field is required";
    public static final String REFRESH_TOKEN_REQUIRED_MESSAGE = "\"refreshToken\" field is required";
}
