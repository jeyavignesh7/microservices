package com.jv.demo.trade.backend.service.authapi.service;

import com.jv.demo.trade.backend.service.authapi.mapper.UserAuthMapper;
import com.jv.demo.trade.backend.service.authapi.user.domain.entity.UserCredEntity;
import com.jv.demo.trade.backend.service.authapi.user.domain.repos.UserCredRepos;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Optional;

@Service
public class UserAuthService implements UserDetailsService {
    @Autowired
    private UserCredRepos userCredRepos;

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    public Optional<UserCredEntity> findById(String userId) {
        return userCredRepos.findById(userId);
    }

    public UserCredEntity saveUser(UserCredEntity credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        return userCredRepos.save(credential);
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token, String userId) {
        jwtService.isTokenValid(token, userId);
    }

    public String validateToken(String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        return jwtService.extractUsername(token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredEntity> credential = this.findById(username);
        return credential.map(cred -> userAuthMapper.mapTo(cred)).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
