package com.jv.demo.trade.backend.service.authapi.controller;

import com.jv.demo.trade.backend.service.authapi.mapper.UserAuthMapper;
import com.jv.demo.trade.backend.service.authapi.service.UserAuthService;
import com.jv.demo.trade.backend.service.authapi.user.domain.dto.AuthRequest;
import com.jv.demo.trade.backend.service.authapi.user.domain.dto.TokenDto;
import com.jv.demo.trade.backend.service.authapi.user.domain.dto.UserAuthDto;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CommonsLog
public class AuthApIController {

    @Autowired
    private UserAuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserAuthDto addNewUser(@RequestBody UserAuthDto user) {
        return userAuthMapper.mapTo(service.saveUser(userAuthMapper.mapFrom(user)));
    }

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    public String getToken(@RequestBody AuthRequest authRequest) {
        log.error("############# In getToken()");
        System.out.println("In token: " + authRequest.getUsername());
        System.out.println("In token: " + authRequest.getPassword());
        String encodedPwd = passwordEncoder.encode(authRequest.getPassword());
        System.out.println("In token: " + encodedPwd);

        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authenticate.isAuthenticated()) {
                log.error("############# In getToken()" + authenticate.isAuthenticated());
                System.out.println("In token: " + authenticate.isAuthenticated());
                return service.generateToken(authRequest.getUsername());
            } else {
                log.error("############# In getToken(): RTE ");
                System.out.println("In token: RTE " + authenticate.isAuthenticated());
                throw new RuntimeException("invalid access");
            }
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/validate")
    @ResponseStatus(HttpStatus.OK)
    public String validateToken(@RequestBody TokenDto tokenDto) {
        log.error("#####Token in validateToken():" + tokenDto);
        System.out.println("#####Token in validateToken():" + tokenDto);
        return service.validateToken(tokenDto.getToken());
        //return "Token is valid";
    }
}
