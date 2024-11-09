package com.mutsa_cau12.springboot_session.service;

import com.mutsa_cau12.springboot_session.domain.Member;
import com.mutsa_cau12.springboot_session.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberJpaRepository.findByUsername(username);

        return new User(member.getUsername(), member.getPassword(), new ArrayList<>());
    }
}
