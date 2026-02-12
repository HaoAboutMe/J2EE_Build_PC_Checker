package com.j2ee.buildpcchecker.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class EmailService
{
    final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    String fromEmail;

    @Value("${app.base-url:http://localhost:8080/identity}")
    String baseUrl;

    public void sendVerificationEmail(String toEmail, String token)
    {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject("Xác thực tài khoản - Build PC Checker");

            String verificationLink = baseUrl + "/auth/verify-email?token=" + token;

            String htmlContent = buildEmailTemplate(verificationLink);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            log.info("Verification email sent to: {}", toEmail);
        }
        catch (MessagingException e) {
            log.error("Failed to send verification email to: {} - Error: {}", toEmail, e.getMessage());
            throw new RuntimeException("Failed to send verification email", e);
        }
    }

    private String buildEmailTemplate(String verificationLink)
    {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <style>
                        * {
                            margin: 0;
                            padding: 0;
                            box-sizing: border-box;
                        }
                        body {
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            line-height: 1.6;
                            color: #333;
                            background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%);
                            padding: 40px 20px;
                        }
                        .container {
                            max-width: 600px;
                            margin: 0 auto;
                            background-color: #ffffff;
                            border-radius: 16px;
                            overflow: hidden;
                            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
                        }
                        .header {
                            background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%);
                            padding: 40px 30px;
                            text-align: center;
                            color: white;
                        }
                        .header h1 {
                            font-size: 28px;
                            margin-bottom: 10px;
                            font-weight: 600;
                        }
                        .header p {
                            font-size: 16px;
                            opacity: 0.95;
                        }
                        .content {
                            padding: 40px 30px;
                            background-color: #ffffff;
                        }
                        .content h2 {
                            color: #667eea;
                            margin-bottom: 20px;
                            font-size: 24px;
                        }
                        .content p {
                            margin-bottom: 15px;
                            color: #555;
                            font-size: 16px;
                        }
                        .button-container {
                            text-align: center;
                            margin: 35px 0;
                        }
                        .button {
                            display: inline-block;
                            padding: 16px 40px;
                            background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%);
                            color: white !important;
                            text-decoration: none;
                            border-radius: 50px;
                            font-size: 18px;
                            font-weight: 600;
                            box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
                            transition: transform 0.2s, box-shadow 0.2s;
                        }
                        .button:hover {
                            transform: translateY(-2px);
                            box-shadow: 0 6px 20px rgba(102, 126, 234, 0.5);
                        }
                        .link-box {
                            background-color: #f8f9fa;
                            padding: 15px;
                            border-radius: 8px;
                            margin: 20px 0;
                            border-left: 4px solid #667eea;
                        }
                        .link-box p {
                            word-break: break-all;
                            color: #667eea;
                            font-size: 14px;
                            margin: 0;
                        }
                        .warning-box {
                            background-color: #fff3cd;
                            border-left: 4px solid #ffc107;
                            padding: 15px;
                            border-radius: 8px;
                            margin: 20px 0;
                        }
                        .warning-box p {
                            color: #856404;
                            margin: 0;
                        }
                        .info-box {
                            background-color: #e7f3ff;
                            border-left: 4px solid #2196F3;
                            padding: 15px;
                            border-radius: 8px;
                            margin: 20px 0;
                        }
                        .info-box p {
                            color: #0c5460;
                            margin: 0;
                        }
                        .footer {
                            background-color: #f8f9fa;
                            text-align: center;
                            padding: 30px;
                            border-top: 1px solid #e9ecef;
                        }
                        .footer p {
                            font-size: 14px;
                            color: #6c757d;
                            margin: 5px 0;
                        }
                        .footer .social-links {
                            margin-top: 15px;
                        }
                        .footer .social-links a {
                            color: #667eea;
                            text-decoration: none;
                            margin: 0 10px;
                            font-weight: 600;
                        }
                        @media only screen and (max-width: 600px) {
                            body {
                                padding: 20px 10px;
                            }
                            .header h1 {
                                font-size: 24px;
                            }
                            .content {
                                padding: 30px 20px;
                            }
                            .button {
                                padding: 14px 30px;
                                font-size: 16px;
                            }
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>🖥️ Build PC Checker</h1>
                            <p>Xác thực tài khoản của bạn</p>
                        </div>
                        
                        <div class="content">
                            <h2>Chào mừng bạn đến với Build PC Checker!</h2>
                            <p>Cảm ơn bạn đã đăng ký tài khoản. Để hoàn tất quá trình đăng ký và bắt đầu sử dụng dịch vụ, vui lòng xác thực địa chỉ email của bạn.</p>
                            
                            <div class="button-container">
                                <a href="%s" class="button">✓ Xác Thực Email Ngay</a>
                            </div>
                            
                            <div class="info-box">
                                <p><strong>📌 Lưu ý:</strong> Nếu nút không hoạt động, bạn có thể copy link bên dưới và dán vào trình duyệt.</p>
                            </div>
                            
                            <div class="link-box">
                                <p>%s</p>
                            </div>
                            
                            <div class="warning-box">
                                <p><strong>⏰ Thời gian hiệu lực:</strong> Link này sẽ hết hạn sau 24 giờ kể từ khi nhận email.</p>
                            </div>
                            
                            <p style="margin-top: 30px; font-size: 14px; color: #888;">Nếu bạn không tạo tài khoản này, vui lòng bỏ qua email này. Tài khoản sẽ không được kích hoạt nếu không xác thực.</p>
                        </div>
                        
                        <div class="footer">
                            <p><strong>Build PC Checker</strong></p>
                            <p>Hệ thống kiểm tra tương thích linh kiện PC</p>
                            <p style="margin-top: 15px;">&copy; 2026 Build PC Checker. All rights reserved.</p>
                            <div class="social-links">
                                <a href="#">Hỗ trợ</a> | 
                                <a href="#">Điều khoản</a> | 
                                <a href="#">Chính sách</a>
                            </div>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(verificationLink, verificationLink);
    }
}



