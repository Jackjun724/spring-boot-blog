package com.jacknoob.blog.web.rest;

import com.jacknoob.blog.security.JwtTokenProvider;
import com.jacknoob.blog.web.rest.vm.UserVM;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * @author JackJun
 * 2019/7/1 19:44
 * Life is not just about survival.
 */
@RestController
@RequestMapping("/api")
public class JwtController {
    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private JwtTokenProvider jwtTokenProvider;

    @Inject
    private RedisTemplate<String,String> redisTemplate;

    @PostMapping("/auth")
    public ResponseEntity<?> login(@Valid @RequestBody UserVM userVM){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userVM.getUsername(),userVM.getPassword()));
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(token);
    }
}
