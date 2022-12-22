package com.app.rang.project.service.sign;

import com.app.rang.project.entity.User;
import com.app.rang.project.model.AuthUserDTO;
import com.app.rang.project.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class LoginService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("userLogin");

        Optional<User> result = userRepository.findByUsername(username);
        log.info("select by id : " + result.isEmpty());

        if(result.isEmpty()) {
            throw new UsernameNotFoundException("Check Email or Password");
        }
        User member = result.get();
        log.info(member);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        AuthUserDTO authMemberDTO = new AuthUserDTO(
                member.getUsername(),
                member.getPassword(),
                authorities,
                member.getNickname(),
                member.getLocation(),
                member.getJoindate(),
                member.getUseridx()
        );

        return authMemberDTO;
    }
}
