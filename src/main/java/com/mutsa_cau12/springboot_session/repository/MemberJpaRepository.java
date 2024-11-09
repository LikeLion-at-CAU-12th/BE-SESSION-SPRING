package com.mutsa_cau12.springboot_session.repository;

import com.mutsa_cau12.springboot_session.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member,Long> {
    Member findByUsername(String username);

    Optional<Member> findByEmail(String email); // 이메일 기반 검색 메서드 추가
    boolean existsByUsername(String username);
}
