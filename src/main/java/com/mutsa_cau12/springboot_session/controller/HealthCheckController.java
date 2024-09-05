package com.mutsa_cau12.springboot_session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HealthCheckController {
    @GetMapping("/check")
    public String healthCheck() {
        return "백엔드는 오늘도 정상 작동 중 ~";
    }
}
