package cn.tomisme.config;

import cn.tomisme.dataobject.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashMap;

@Configuration
public class TokenConfig {

    @Value("${jwt.sign}")
    private String SIGN;

    @Value("${jwt.expireSecTime}")
    private Integer EXPIRE_SECTIME;

    @Value("${jwt.updateTime}")
    private Integer UPDATE_TIME;

    private HashMap<String, Object> header = null;

    private Algorithm algorithm = null;

    public String createToken(User user) {
        if (header == null || header.isEmpty()) {
            header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
        }
        if (algorithm == null)
            algorithm = Algorithm.HMAC256(SIGN);
        Date date = new Date(System.currentTimeMillis() + EXPIRE_SECTIME * 1000L);
        return JWT.create()
                .withHeader(header)
                .withClaim("username", user.getUsername())
                .withExpiresAt(date).sign(algorithm);
    }

    public boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * 是否马上过期
     * @param token token
     * @return 是否
     */
    public boolean isSoonExpire(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getExpiresAt().getTime() - new Date().getTime() < UPDATE_TIME;
        } catch (Exception e) {
            return true;
        }
    }
}
