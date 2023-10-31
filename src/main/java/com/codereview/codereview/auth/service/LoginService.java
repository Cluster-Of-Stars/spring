package com.codereview.codereview.auth.service;

import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.model.entity.UserDetailsImpl;
import com.codereview.codereview.global.repository.UserRepository;
import com.codereview.codereview.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user = repository.findByEmail(username)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("유저를 찾을수 없음");
                });
        return new UserDetailsImpl(user);
    }

}
