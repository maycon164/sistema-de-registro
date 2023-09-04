package com.fatec.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fatec.model.User;
import com.fatec.model.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Slf4j
public class TokenService {

    private String secret = "SKILL_MATRIX_API";

    private String NAME_CLAIM = "name";
    private String ROLE_CLAIM = "role";

    private String issuer = "SKILL_MATRIX_API";

    public String generateToken(User user) {
        log.info("Generating user token...");
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withIssuer(issuer)
                .withSubject(user.email())
                .withClaim(NAME_CLAIM, user.name())
                .withClaim(ROLE_CLAIM, RoleEnum.ADMIN.toString())
                //.withClaim(ROLE_CLAIM, String.valueOf(user.role()))
                .withExpiresAt(expirationDate())
                .sign(algorithm);
        return token;
    }

    public User validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        return User.builder()
                .name(decodedJWT.getClaim(NAME_CLAIM).toString())
                .email(decodedJWT.getSubject())
                .role(RoleEnum.valueOf(decodedJWT.getClaim(ROLE_CLAIM).toString()))
                .build();

    }

    private Instant expirationDate() {
        String offSetId = "-03:00";
        return LocalDateTime.now().plusMinutes(100000).toInstant(ZoneOffset.of(offSetId));
    }

}
