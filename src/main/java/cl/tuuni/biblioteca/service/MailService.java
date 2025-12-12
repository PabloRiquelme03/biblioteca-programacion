package cl.tuuni.biblioteca.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.from}")
    private String from;

    /**
     * Versión "bonita" del envío.
     * Mantiene la misma firma que tenías antes: to, subject, text.
     * El parámetro text puede ser texto plano (con \n) y lo convertimos a HTML.
     */
    public void sendSimple(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name()
            );

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);

            String htmlBody = buildHtmlTemplate(subject, text);
            helper.setText(htmlBody, true); // true => es HTML

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo", e);
        }
    }

    /**
     * Construye un HTML sencillo y profesional alrededor del contenido.
     * - Usa colores oscuros similares a la app.
     * - Convierte saltos de línea en <br>.
     * - Escapa el contenido para evitar problemas de HTML.
     */
    private String buildHtmlTemplate(String subject, String rawText) {
        // escapamos texto y convertimos \n a <br>
        String safeText = HtmlUtils.htmlEscape(rawText)
                .replace("\n", "<br>");

        return """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <title>%s</title>
                </head>
                <body style="margin:0;padding:0;background-color:#0b1120;font-family:system-ui,-apple-system,BlinkMacSystemFont,'Segoe UI',sans-serif;">
                
                <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" style="background-color:#0b1120;padding:24px 0;">
                    <tr>
                        <td align="center">
                            <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" style="max-width:600px;background-color:#020617;border-radius:16px;border:1px solid #1f2937;box-shadow:0 18px 45px rgba(15,23,42,0.8);overflow:hidden;">
                                <!-- Header -->
                                <tr>
                                    <td style="padding:20px 28px;border-bottom:1px solid #1f2937;background:linear-gradient(90deg,#0f172a,#1d4ed8);">
                                        <h1 style="margin:0;font-size:20px;color:#e5e7eb;">
                                            Biblioteca
                                        </h1>
                                        <p style="margin:4px 0 0;font-size:13px;color:#cbd5f5;">
                                            Plataforma de recursos y cursos
                                        </p>
                                    </td>
                                </tr>
                
                                <!-- Contenido principal -->
                                <tr>
                                    <td style="padding:24px 28px 8px 28px;color:#e5e7eb;font-size:15px;line-height:1.6;">
                                        <h2 style="margin:0 0 12px 0;font-size:18px;color:#f9fafb;">%s</h2>
                                        <p style="margin:0 0 16px 0;color:#9ca3af;font-size:14px;">
                                            A continuación encontrarás la información solicitada:
                                        </p>
                                        <div style="padding:14px 16px;border-radius:10px;background-color:#020617;border:1px solid #1f2937;color:#e5e7eb;font-size:14px;">
                                            %s
                                        </div>
                                    </td>
                                </tr>
                
                                <!-- Footer -->
                                <tr>
                                    <td style="padding:16px 28px 20px 28px;font-size:11px;color:#6b7280;border-top:1px solid #1f2937;">
                                        <p style="margin:0 0 6px 0;">
                                            Si tú no solicitaste este correo, puedes ignorarlo de forma segura.
                                        </p>
                                        <p style="margin:0;color:#4b5563;">
                                            &copy; %d Programy. Todos los derechos reservados.
                                        </p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                
                </body>
                </html>
                """.formatted(
                HtmlUtils.htmlEscape(subject),
                HtmlUtils.htmlEscape(subject),
                safeText,
                java.time.Year.now().getValue()
        );
    }
}
