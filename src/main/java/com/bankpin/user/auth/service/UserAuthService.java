package com.bankpin.user.auth.service;

import com.bankpin.user.auth.model.mapper.AuthMapper;
import com.bankpin.user.auth.model.type.AuthorityType;
import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.auth.model.dto.UserDTO;
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
    private AuthMapper authMapper;

    public UserAuth selectUser(String username)
    {
        return authMapper.findByUsername(username);
    }
    public UserDTO.Detail selectCustCiNo(String custCiNo)
    {
        return authMapper.findByCustCiNo(custCiNo);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserAuth user = authMapper.findByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("UserDTO");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(AuthorityType.USER_WRITE_REMOVE_PRINT.getKey()));
        user.setAuthorities(authorities);
        return user;
	}

    @Transactional
	public void join(UserDTO.Create user)
    {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCustActvGbcd(Boolean.TRUE);
        authMapper.save(user);
	}

}
