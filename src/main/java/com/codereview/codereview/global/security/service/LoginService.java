package com.codereview.codereview.global.security.service;

import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.model.repository.UserRepository;
import com.codereview.codereview.global.security.model.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("로그 DB 갔다옴~");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Not Found " + email);
                });

        return new UserDetailsImpl(user);
    }

}
