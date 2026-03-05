package mo.project.sellbook.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.PostConstruct;
import mo.project.sellbook.model.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateAccessToken(Users user) {

        try {

            JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

            Date issuedTime = new Date();
            Date expiredTime = Date.from(
                    issuedTime.toInstant().plus(30, ChronoUnit.MINUTES)
            );

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUserName())
                    .claim("id", user.getId())
                    .claim("scope", user.getRole().name())
                    .issueTime(issuedTime)
                    .expirationTime(expiredTime)
                    .build();

            Payload payload = new Payload(claimsSet.toJSONObject());

            JWSObject jwsObject = new JWSObject(header, payload);

            byte[] keyBytes = secretKey.getBytes(java.nio.charset.StandardCharsets.UTF_8);
            JWSSigner signer = new MACSigner(keyBytes);

            jwsObject.sign(signer);   // QUAN TRỌNG

            return jwsObject.serialize();

        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateRefreshToken(Users user) {

        try {

            JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

            Date issuedTime = new Date();
            Date expiredTime = Date.from(
                    issuedTime.toInstant().plus(30, ChronoUnit.DAYS)
            );

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUserName())
                    .claim("id", user.getId())
                    .claim("scope", user.getRole().name())
                    .issueTime(issuedTime)
                    .expirationTime(expiredTime)
                    .build();

            Payload payload = new Payload(claimsSet.toJSONObject());

            JWSObject jwsObject = new JWSObject(header, payload);

            byte[] keyBytes = secretKey.getBytes(java.nio.charset.StandardCharsets.UTF_8);
            JWSSigner signer = new MACSigner(keyBytes);

            jwsObject.sign(signer);   // QUAN TRỌNG

            return jwsObject.serialize();

        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateToken(String token) throws ParseException, JOSEException {

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        if (expirationTime.before(new Date())) {
            return false;
        }

        byte[] keyBytes = secretKey.getBytes(java.nio.charset.StandardCharsets.UTF_8);

        return signedJWT.verify(new MACVerifier(keyBytes));
    }
}
