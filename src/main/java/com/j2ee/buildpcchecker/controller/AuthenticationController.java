package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.*;
import com.j2ee.buildpcchecker.dto.request.*;
import com.j2ee.buildpcchecker.dto.response.AuthenticationResponse;
import com.j2ee.buildpcchecker.dto.response.IntrospectResponse;
import com.j2ee.buildpcchecker.dto.response.UserResponse;
import com.j2ee.buildpcchecker.service.AuthenticationService;
import com.j2ee.buildpcchecker.service.UserService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController
{
    AuthenticationService authenticationService;
    UserService userService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogOutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request) throws ParseException, JOSEException
    {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @GetMapping(value = "/verify-email", produces = MediaType.TEXT_HTML_VALUE)
    String verifyEmail(@RequestParam String token)
    {
        try {
            var result = userService.verifyEmail(token);
            // Trả về HTML page với thông báo thành công
            return buildSuccessPage(result.getEmail());
        } catch (Exception e) {
            // Trả về HTML page với thông báo lỗi
            return buildErrorPage(e.getMessage());
        }
    }

    private String buildSuccessPage(String email) {
        return """
                <!DOCTYPE html>
                <html lang="vi">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Xác thực thành công - Build PC Checker</title>
                    <style>
                        * {
                            margin: 0;
                            padding: 0;
                            box-sizing: border-box;
                        }
                        body {
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%);
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            min-height: 100vh;
                            padding: 20px;
                        }
                        .container {
                            background: white;
                            border-radius: 20px;
                            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
                            max-width: 500px;
                            width: 100%%;
                            padding: 40px;
                            text-align: center;
                            animation: slideIn 0.5s ease-out;
                        }
                        @keyframes slideIn {
                            from {
                                opacity: 0;
                                transform: translateY(-30px);
                            }
                            to {
                                opacity: 1;
                                transform: translateY(0);
                            }
                        }
                        .success-icon {
                            width: 80px;
                            height: 80px;
                            background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%);
                            border-radius: 50%%;
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            margin: 0 auto 20px;
                            animation: pulse 2s infinite;
                        }
                        @keyframes pulse {
                            0%%, 100%% {
                                transform: scale(1);
                            }
                            50%% {
                                transform: scale(1.05);
                            }
                        }
                        .success-icon svg {
                            width: 50px;
                            height: 50px;
                            stroke: white;
                            fill: none;
                            stroke-width: 3;
                            stroke-linecap: round;
                            stroke-linejoin: round;
                        }
                        h1 {
                            color: #667eea;
                            font-size: 28px;
                            margin-bottom: 15px;
                        }
                        p {
                            color: #555;
                            font-size: 16px;
                            line-height: 1.6;
                            margin-bottom: 10px;
                        }
                        .email {
                            color: #667eea;
                            font-weight: 600;
                        }
                        .btn {
                            display: inline-block;
                            margin-top: 25px;
                            padding: 14px 40px;
                            background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%);
                            color: white;
                            text-decoration: none;
                            border-radius: 50px;
                            font-size: 16px;
                            font-weight: 600;
                            box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
                            transition: transform 0.2s, box-shadow 0.2s;
                        }
                        .btn:hover {
                            transform: translateY(-2px);
                            box-shadow: 0 6px 20px rgba(102, 126, 234, 0.5);
                        }
                        .info-box {
                            background: #e7f3ff;
                            border-left: 4px solid #2196F3;
                            padding: 15px;
                            border-radius: 8px;
                            margin-top: 20px;
                            text-align: left;
                        }
                        .info-box p {
                            margin: 0;
                            color: #0c5460;
                            font-size: 14px;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="success-icon">
                            <svg viewBox="0 0 24 24">
                                <polyline points="20 6 9 17 4 12"></polyline>
                            </svg>
                        </div>
                        <h1>🎉 Xác thực thành công!</h1>
                        <p>Tài khoản của bạn đã được kích hoạt.</p>
                        <p>Email: <span class="email">%s</span></p>
                        <div class="info-box">
                            <p>✓ Bạn có thể đăng nhập ngay bây giờ để sử dụng dịch vụ Build PC Checker.</p>
                        </div>
                        <a href="#" class="btn" onclick="alert('Đăng ký thành công! Bạn có thể đăng nhập.'); return false;">Đóng</a>
                    </div>
                    <script>
                        // Tự động hiện alert khi trang load
                        window.onload = function() {
                            alert('🎉 Đăng ký thành công! Bạn có thể đăng nhập ngay bây giờ.');
                        };
                    </script>
                </body>
                </html>
                """.formatted(email);
    }

    private String buildErrorPage(String errorMessage) {
        return """
                <!DOCTYPE html>
                <html lang="vi">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Xác thực thất bại - Build PC Checker</title>
                    <style>
                        * {
                            margin: 0;
                            padding: 0;
                            box-sizing: border-box;
                        }
                        body {
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%);
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            min-height: 100vh;
                            padding: 20px;
                        }
                        .container {
                            background: white;
                            border-radius: 20px;
                            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
                            max-width: 500px;
                            width: 100%%;
                            padding: 40px;
                            text-align: center;
                            animation: slideIn 0.5s ease-out;
                        }
                        @keyframes slideIn {
                            from {
                                opacity: 0;
                                transform: translateY(-30px);
                            }
                            to {
                                opacity: 1;
                                transform: translateY(0);
                            }
                        }
                        .error-icon {
                            width: 80px;
                            height: 80px;
                            background: linear-gradient(135deg, #f44336 0%%, #e91e63 100%%);
                            border-radius: 50%%;
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            margin: 0 auto 20px;
                        }
                        .error-icon svg {
                            width: 50px;
                            height: 50px;
                            stroke: white;
                            fill: none;
                            stroke-width: 3;
                            stroke-linecap: round;
                            stroke-linejoin: round;
                        }
                        h1 {
                            color: #f44336;
                            font-size: 28px;
                            margin-bottom: 15px;
                        }
                        p {
                            color: #555;
                            font-size: 16px;
                            line-height: 1.6;
                            margin-bottom: 10px;
                        }
                        .error-message {
                            background: #ffebee;
                            border-left: 4px solid #f44336;
                            padding: 15px;
                            border-radius: 8px;
                            margin: 20px 0;
                            text-align: left;
                        }
                        .error-message p {
                            margin: 0;
                            color: #c62828;
                            font-size: 14px;
                        }
                        .btn {
                            display: inline-block;
                            margin-top: 25px;
                            padding: 14px 40px;
                            background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%);
                            color: white;
                            text-decoration: none;
                            border-radius: 50px;
                            font-size: 16px;
                            font-weight: 600;
                            box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
                            transition: transform 0.2s, box-shadow 0.2s;
                        }
                        .btn:hover {
                            transform: translateY(-2px);
                            box-shadow: 0 6px 20px rgba(102, 126, 234, 0.5);
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="error-icon">
                            <svg viewBox="0 0 24 24">
                                <line x1="18" y1="6" x2="6" y2="18"></line>
                                <line x1="6" y1="6" x2="18" y2="18"></line>
                            </svg>
                        </div>
                        <h1>❌ Xác thực thất bại</h1>
                        <p>Rất tiếc, không thể xác thực tài khoản của bạn.</p>
                        <div class="error-message">
                            <p><strong>Lỗi:</strong> %s</p>
                        </div>
                        <p>Vui lòng kiểm tra lại hoặc yêu cầu gửi lại email xác thực.</p>
                        <a href="#" class="btn" onclick="alert('Vui lòng thử lại hoặc liên hệ hỗ trợ.'); return false;">Đóng</a>
                    </div>
                    <script>
                        window.onload = function() {
                            alert('❌ Xác thực thất bại! ' + '%s');
                        };
                    </script>
                </body>
                </html>
                """.formatted(errorMessage, errorMessage);
    }

    @PostMapping("/resend-verification")
    ApiResponse<String> resendVerificationEmail(@RequestParam String email)
    {
        userService.resendVerificationEmail(email);
        return ApiResponse.<String>builder()
                .result("Verification email has been sent to: " + email)
                .message("Please check your email inbox and spam folder.")
                .build();
    }
}
