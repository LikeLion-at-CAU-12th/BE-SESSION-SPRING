package com.mutsa_cau12.springboot_session.controller;

import com.mutsa_cau12.springboot_session.dto.JoinRequest;
import com.mutsa_cau12.springboot_session.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final MemberService memberService;

    @PostMapping("/join")
    public void join(@RequestBody JoinRequest joinRequest) {
        memberService.join(joinRequest);
    }
}
