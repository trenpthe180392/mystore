package mo.project.sellbook.configuration;

import mo.project.sellbook.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
public class JwtDecoderConfiguration implements JwtDecoder {

    private final String secretKey;
    private final JwtService jwtService;
    private NimbusJwtDecoder nimbusJwtDecoder;

    // SỬ DỤNG CONSTRUCTOR INJECTION ĐỂ NẠP KEY CHUẨN
    public JwtDecoderConfiguration(
            @Value("${jwt.secret}") String secretKey,
            JwtService jwtService) {
        this.secretKey = secretKey;
        this.jwtService = jwtService;
    }

    @Override
    public Jwt decode(String token) throws JwtException {
        // IN RA ĐỂ KIỂM TRA KEY TRONG CONSOLE
        System.out.println(">>> DEBUG - Secret Key hiện tại: " + secretKey);

        if (Objects.isNull(nimbusJwtDecoder)) {
            synchronized (this) {
                if (Objects.isNull(nimbusJwtDecoder)) {
                    // CHÚ Ý: Dùng getBytes vì ta dùng chuỗi text thuần (không phải Base64)
                    byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

                    // Kiểm tra độ dài key: HS512 yêu cầu tối thiểu 64 bytes (512 bits)
                    if (keyBytes.length < 64) {
                        throw new JwtException("Secret Key quá ngắn, HS512 cần tối thiểu 64 ký tự!");
                    }

                    SecretKey secretKeySpec = new SecretKeySpec(keyBytes, "HmacSHA512");

                    nimbusJwtDecoder = NimbusJwtDecoder
                            .withSecretKey(secretKeySpec)
                            .macAlgorithm(MacAlgorithm.HS512)
                            .build();
                }
            }
        }

        try {
            // ĐẶT BREAKPOINT TẠI DÒNG DƯỚI ĐÂY
            return nimbusJwtDecoder.decode(token);
        } catch (Exception e) {
            System.err.println(">>> LỖI DECODE TOKEN: " + e.getMessage());
            throw new JwtException("Token không hợp lệ hoặc đã hết hạn!");
        }

    }
}