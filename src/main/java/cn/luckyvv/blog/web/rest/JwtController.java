package cn.luckyvv.blog.web.rest;

import cn.luckyvv.blog.security.JwtTokenProvider;
import cn.luckyvv.blog.web.rest.vm.UserVM;
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
 * Life is not just about survival, but VV and distance.
 */
@RestController
@RequestMapping("/api")
public class JwtController {
    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/auth")
    public ResponseEntity<?> login(@Valid @RequestBody UserVM userVM){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userVM.getUsername(),userVM.getPassword()));
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(token);
    }
}
