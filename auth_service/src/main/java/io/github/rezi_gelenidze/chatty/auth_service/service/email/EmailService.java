 package io.github.rezi_gelenidze.chatty.auth_service.service.email;

import io.github.rezi_gelenidze.chatty.auth_service.service.rabbitmq.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {
    private final RabbitMQProducer rabbitMQProducer;

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
    public EmailService(RabbitMQProducer rabbitMQProducer, TemplateEngine templateEngine) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.templateEngine = templateEngine;
    }

    /**
     * Sends an email using RabbitMQ after processing the Thymeleaf template.
     *
     * @param to           Recipient email address
     * @param subject      Email subject
     * @param templateName Thymeleaf template name (without .html)
     * @param context      Thymeleaf context with variables
     */
    private void sendEmail(String to, String subject, String templateName, Context context) {
        String htmlContent = templateEngine.process(templateName, context);
        rabbitMQProducer.sendEmailNotification(to, subject, htmlContent);
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
