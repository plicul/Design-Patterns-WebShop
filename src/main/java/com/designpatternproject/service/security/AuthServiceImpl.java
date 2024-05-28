package com.designpatternproject.service.security;

import com.designpatternproject.dto.security.LoginDto;
import com.designpatternproject.entity.User;
import com.designpatternproject.repository.UserRepository;
import com.designpatternproject.repository.UserTypeRepository;
import com.designpatternproject.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final AuthMapperImpl authMapper;
    private final UserTypeRepository userTypeRepository;
    private final UserRepository userRepository;

    @Override
    public String login(LoginDto loginDto) {

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());

            //Check password
            if(!loginDto.getPassword().equals(userDetails.getPassword())){
                return null;
            };

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()
            ));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);

            return token;
        }
        catch (Exception e){
            return null;
        }

    }

    @Override
    public void register(LoginDto loginDto) throws Exception {
            User newUser = authMapper.loginDtotoEntity(loginDto);
            newUser.setType(userTypeRepository.findByAccountType("user"));
            userRepository.save(newUser);
    }
}