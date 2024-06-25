package com.Project.product.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

        private static final long serialVersionUID = -2550185165626007488L;

        //Thời gian sống của Token theo định dạng milisecond(1/1000 giay)
        public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

        @Value("${jwt.secret}")
        private String secret;

        //truy xuất tên người dùng từ  jwt token
        public String getUsernameFromToken(String token) {
            return getClaimFromToken(token, Claims::getSubject);
        }

        //truy xuất thời gian hết hạn từ jwt token
        public Date getExpirationDateFromToken(String token) {
            return getClaimFromToken(token, Claims::getExpiration);
        }

        //Lấy Claim(yeu cau) từ jwt Token
        public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = getAllClaimsFromToken(token);
            return claimsResolver.apply(claims);
        }
        //để lấy thông tin từ jwt Token , ta cần 1 secret key đã được định nghĩa ở trên
        private Claims getAllClaimsFromToken(String token) {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }

        //kiem tra token co het han k
        private Boolean isTokenExpired(String token) {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        }

        // Tạo ra token cho người dùng
        public String generateToken(UserDetails userDetails) {
            Map<String, Object> claims = new HashMap<>();
            return doGenerateToken(claims, userDetails.getUsername());
        }

        //while creating the token -
        //1. Xác định , xác nhận quyền sở hữu của jwt token, Issuer, Expiration, Subject, and the ID
        //2. Sign the JWT using the HS512 algorithm and secret key.
        //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
        //   nén JWT thành chuỗi an toàn cho URL
        private String doGenerateToken(Map<String, Object> claims, String subject) {

            return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                    .signWith(SignatureAlgorithm.HS512, secret).compact();
        }

        //xác thực token có hợp lệ hay không
        public Boolean validateToken(String token, UserDetails userDetails) {
            final String username = getUsernameFromToken(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }
}
