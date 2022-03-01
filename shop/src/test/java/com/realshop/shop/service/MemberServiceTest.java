package com.realshop.shop.service;

import com.realshop.shop.dto.MemberFormDto;
import com.realshop.shop.entity.Member;
import com.realshop.shop.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@email.com");
        memberFormDto.setName("hello");
        memberFormDto.setAddress("where?");
        memberFormDto.setPassword("1234");
        System.out.println("..");
        return Member.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("테스트!")
    public void saveMemberTest(){
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {memberService.saveMember(member2);});

        assertEquals("중복 회원입니다.", e.getMessage());
    }
}