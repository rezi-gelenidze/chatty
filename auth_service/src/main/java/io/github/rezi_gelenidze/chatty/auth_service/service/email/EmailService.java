package io.github.rezi_gelenidze.chatty.auth_service.service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    @Value("${app.app-name}")
    private String appName;

    @Value("${app.base-url}")
    private String baseUrl;

    @Value("${app.redirects.email-activation-redirect-path}")
    private String emailActivationRedirectPath;

    @Value("${app.redirects.password-reset-redirect-path}")
    private String passwordResetRedirectPath;

    @Autowired
    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    /**
     * Sends an email using a Thymeleaf template.
     *
     * @param to           Recipient email address
     * @param subject      Email subject
     * @param templateName Thymeleaf template name (without .html)
     * @param context      Thymeleaf context with variables
     */
    private void sendEmail(String to, String subject, String templateName, Context context) {
        String htmlContent = templateEngine.process(templateName, context);

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    /**
     * Sends an account activation email.
     *
     * @param to    User email
     * @param token Unique verification token
     */
    public void sendActivationEmail(String to, String token) {
        Context context = new Context();
        context.setVariable("app_name", appName);
        context.setVariable("target_url", baseUrl + emailActivationRedirectPath + token);

        sendEmail(to, appName + " - Account Activation", "email-activation", context);
    }

    /**
     * Sends an email confirmation success message.
     *
     * @param to User email
     */
    public void sendActivationSuccessEmail(String to) {
        Context context = new Context();
        context.setVariable("app_name", appName);

        sendEmail(to, appName + " - Your account has successfully activated!", "email-activation-success", context);
    }

    /**
     * Sends a password reset email with a reset link.
     *
     * @param to       User email
     * @param token    Password reset token
     * @param username Username of the recipient
     */
    public void sendPasswordResetEmail(String to, String token, String username) {
        Context context = new Context();
        context.setVariable("app_name", appName);
        context.setVariable("target_url", baseUrl + passwordResetRedirectPath + token);
        context.setVariable("user_name", username);

        sendEmail(to, appName + " - Request for Changing Password", "password-reset", context);
    }

    /**
     * Sends a password change confirmation email.
     *
     * @param to User email
     */
    public void sendPasswordResetSuccessEmail(String to) {
        Context context = new Context();
        context.setVariable("app_name", appName);

        sendEmail(to, appName + " - Your password has changed successfully!", "password-reset-success", context);
    }

}
