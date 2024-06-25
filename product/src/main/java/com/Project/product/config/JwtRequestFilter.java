package com.Project.product.config;

import com.Project.product.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.catalina.filters.RequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
        public  class JwtRequestFilter extends OncePerRequestFilter {
        static final Logger log =
                LoggerFactory.getLogger(RequestFilter.class);
        @Autowired
        private JwtUserDetailsService jwtUserDetailsService;

        @Autowired
        private JwtTokenUtil jwtTokenUtil;
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws ServletException, IOException {

                final String requestTokenHeader = request.getHeader("Authorization");

                String userEmail = null;
                String jwtToken = null;
                // jwt token có dạng "Bearer token". Remove Bearer word and get
                // only the Token
                if (requestTokenHeader != null ){
                      if(requestTokenHeader.startsWith("Bearer ")) {
                        jwtToken = requestTokenHeader.substring(7);
                        try {
                                userEmail= jwtTokenUtil.getUsernameFromToken(jwtToken);
                        } catch (IllegalArgumentException e) {
                                log.error("Unable to get JWT Token");
                        } catch (ExpiredJwtException e) {
                                log.error("JWT Token has expired");
                        }
                } else {
                        logger.warn("JWT Token does not begin with Bearer String");
                         }
                }

                // Khi chúng tôi nhận được mã thông báo, hãy xác thực nó.
                if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                        UserDetails userDetails = this. jwtUserDetailsService.loadUserByUsername(userEmail);

                        // if token is valid configure Spring Security to manually set
                        // nếu mã thông báo hợp lệ, hãy định cấu hình spring Security để đặt thủ công
                        // authentication
                        if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                        userDetails, null, userDetails.getAuthorities());
                                usernamePasswordAuthenticationToken
                                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                // After setting the Authentication in the context, we specify
                                // that the current user is authenticated. So it passes the
                                // Spring Security Configurations successfully.
                                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                        }
                }
                chain.doFilter(request, response);
           }
        }