package com.mutsa_cau12.springboot_session.service;

import com.mutsa_cau12.springboot_session.domain.Member;
import com.mutsa_cau12.springboot_session.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberJpaRepository memberRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // super.loadUser 호출 전에 access token을 가져옴
        String accessToken = userRequest.getAccessToken().getTokenValue();
        System.out.println("Access Token: " + accessToken); // 토큰 출력 (확인용)

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String username = oAuth2User.getAttribute("name");


        Member member = memberRepository.findByEmail(email)
                .orElseGet(() -> Member.builder()
                        .email(email)
                        .username(username)
                        .password("") // OAuth 사용자는 비밀번호가 필요하지 않으므로 빈 문자열로 처리
                        .build());

        // 새로운 사용자는 DB에 저장
        if (member.getId() == null) {
            memberRepository.save(member);
        }


//        Member member = memberRepository.findByEmail(email)
//                .map(existingMember -> {
//                    // 필요한 경우 사용자 정보를 업데이트
//                    existingMember.updateUsername(username);
//                    return memberRepository.save(existingMember);
//                })
//                .orElseGet(() -> {
//                    return memberRepository.save(Member.builder()
//                            .email(email)
//                            .username(username)
//                            .password("") // OAuth 사용자는 비밀번호가 필요하지 않으므로 빈 문자열로 처리
//                            .build());
//                });

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                oAuth2User.getAttributes(),
                "sub");
    }
}