package com.codereview.codereview.global.config.service;

import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.model.security.UserDetailsImpl;
import com.codereview.codereview.global.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

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
