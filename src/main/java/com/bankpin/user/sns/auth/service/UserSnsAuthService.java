package com.bankpin.user.sns.auth.service;


import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.auth.model.mapper.AuthMapper;
import com.bankpin.user.sns.auth.model.dto.KakaoAuth;
import com.bankpin.user.sns.auth.model.dto.NaverAuth;
import com.bankpin.user.sns.auth.model.dto.SnsUserDTO;
import com.bankpin.user.sns.auth.model.mapper.SnsAuthDetailMapper;
import com.bankpin.user.sns.auth.model.mapper.SnsAuthMapper;
import com.bankpin.user.sns.auth.model.type.SnsType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSnsAuthService {

    private final SnsAuthMapper snsAuthMapper;
    private final SnsAuthDetailMapper snsAuthDetailMapper;
    private final AuthMapper authMapper;


    /**
    * 네이버 액세스토큰 가져오기
    * */
    public NaverAuth.TokenRes getNaverAccessToken(NaverAuth.TokenReq tokenReq) {

        String uri = NaverAuth.Property.getAccessTokenUri(tokenReq);

        return WebClient
                .create()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(NaverAuth.TokenRes.class)
                .block();

    }

    /**
    * 네이버 유저 프로필 가져오기
    * */
    public NaverAuth.Profile getNaverUserProfile(NaverAuth.TokenRes tokenRes) {

        return WebClient
                .create()
                .post()
                .uri(NaverAuth.Property.userInfoUri)
                .header("authorization", "Bearer " + tokenRes.getAccessToken())
                .retrieve()
                .bodyToMono(NaverAuth.Profile.class)
                .block();

    }

    public SnsUserDTO.Create findById(String id) {
        return snsAuthMapper.findByUsername(id);
    }

    public SnsUserDTO.Create findByUsername(String id) {
        return snsAuthMapper.findByUsername(id);
    }

    public SnsUserDTO.Create findByCustId(String custId) {
        return snsAuthMapper.findByCustId(custId);
    }

    public SnsUserDTO.Column findByCustCiNo(String custCiNo) {
        return snsAuthMapper.findByCustCiNo(custCiNo);
    }

    public SnsUserDTO.Create duplicateCheck(String custCiNo, SnsType type) {
        return snsAuthMapper.duplicateCheck(custCiNo, type);
    }


    public String findCustCiNoByCustId(String custId) {
        return snsAuthMapper.findCustCiNoByCustId(custId);
    }


    /**
    * 중복검사
    * */
    public Boolean is(String id) {
        return snsAuthMapper.existsById(id);
    }


    /**
     * 회원가입하기
    * */
    public void join(SnsUserDTO.Create user, SnsType type) {
        user.setCustActvGbcd(Boolean.TRUE);
        snsAuthMapper.save(user);
        String custCiNo = snsAuthMapper.findCustCiNoByCustId(user.getId());
        SnsUserDTO.Detail detail = new SnsUserDTO.Detail(custCiNo, type);
        snsAuthDetailMapper.save(detail);

    }


    public void createDetail(SnsUserDTO.Detail detail) {
        snsAuthDetailMapper.save(detail);
    }


    public String encrypt(String str) {
        return snsAuthMapper.fnEncrypt(str);
    }

    public String decrypt(String str) {
        return snsAuthMapper.fnDecrypt(str);
    }

    public void compareUpdate(SnsUserDTO.Create isUser, SnsUserDTO.Create user) {
        snsAuthMapper.update(isUser);
    }




    public UserAuth loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserAuth user = authMapper.findByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("UserDTO");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getAuthority()));
        user.setAuthorities(authorities);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);

        return user;
    }


    public KakaoAuth.TokenResponse getKakaoToken(String code) {

        return WebClient
                .create()
                .post()
                .uri(KakaoAuth.Property.tokenUri)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(KakaoAuth.Property.toMultiValueMap(code))
                .retrieve()
                .bodyToMono(KakaoAuth.TokenResponse.class)
                .block();

    }

}