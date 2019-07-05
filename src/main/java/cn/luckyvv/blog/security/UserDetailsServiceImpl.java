package cn.luckyvv.blog.security;

import cn.luckyvv.blog.mapper.LoginMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * @author JackJun
 * 2019/6/27 16:44
 * Life is not just about survival, but VV and distance.
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Inject
    private LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        String password = loginMapper.findPasswordByUsername(username).orElseThrow(()-> new UsernameNotFoundException("帐号或密码错误！"));
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        return new User(username,password,authorities);
    }

}
