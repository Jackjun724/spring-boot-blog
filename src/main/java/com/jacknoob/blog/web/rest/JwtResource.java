package com.jacknoob.blog.web.rest;

import com.jacknoob.blog.security.JwtTokenProvider;
import com.jacknoob.blog.web.exception.WebException;
import com.jacknoob.blog.web.vm.UserVM;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * @author JackJun
 * 2019/7/1 19:44
 * Life is not just about survival.
 */
@RestController
@RequestMapping("/api")
public class JwtResource {
    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private JwtTokenProvider jwtTokenProvider;

    @Inject
    private RedisTemplate<String,String> redisTemplate;

    @PostMapping("/auth")
    @ApiOperation("登录")
    public ResponseEntity<?> login(@ApiParam @Valid @RequestBody UserVM userVM) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userVM.getUsername(),userVM.getPassword()));
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/userinfo")
    @ApiOperation("获取当前Token用户信息")
    public ResponseEntity<?> getUserInfo(@RequestParam String token) {
        throw new WebException("接口未开放", HttpStatus.BAD_REQUEST);
    }
}
