package com.bankpin.user.config.auth.service;

import com.bankpin.user.config.auth.model.dto.CustomerAuth;
import com.bankpin.user.config.auth.model.dto.CustomerDTO;
import com.bankpin.user.config.auth.model.mapper.UserMapper;
import com.bankpin.user.config.auth.model.type.AuthorityType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class UserAuthService implements UserDetailsService
{
    @Autowired
    private UserMapper userMapper;

    public CustomerAuth selectUser(String username)
    {
        return userMapper.findByUsername(username);
    }


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        CustomerAuth user = userMapper.findByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("UserDTO");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(AuthorityType.USER.getKey()));
        user.setAuthorities(authorities);
        return user;
	}


    @Transactional
	public void join(CustomerDTO.Create user)
    {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.save(user);
	}

}
