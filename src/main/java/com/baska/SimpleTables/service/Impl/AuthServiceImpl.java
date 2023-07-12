package com.baska.SimpleTables.service.Impl;

import com.baska.SimpleTables.dto.auth.request.LoginRequest;
import com.baska.SimpleTables.dto.auth.request.SignUpRequest;
import com.baska.SimpleTables.dto.auth.response.JwtResponse;
import com.baska.SimpleTables.model.Role;
import com.baska.SimpleTables.model.User;
import com.baska.SimpleTables.repository.UserRepository;
import com.baska.SimpleTables.security.JwtUtils;
import com.baska.SimpleTables.security.UserDetailsImpl;
import com.baska.SimpleTables.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    final private AuthenticationManager authenticationManager;

    final private UserRepository userRepository;

    final private JwtUtils jwtUtils;

//    final private RefreshTokenService refreshTokenService;

    @Override
    @SneakyThrows
    public String registerUser(SignUpRequest signUpRequest){
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new Exception("Error: Username is already taken!");
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new Exception("Error: Email is already in use!");
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        user.setRole(Role.WRITE);
        userRepository.save(user);

        return "User registered successfully!";
    }

    @Override
    @SneakyThrows
    public JwtResponse authenticateUser(LoginRequest loginRequest){
        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());
        if (optionalUser.isEmpty()){
            throw new Exception("Error: User not found!");
        }
        User user = optionalUser.get();
        if (!user.getEnabled()){
            throw new Exception("Error: User not active!");
        }
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
//        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return new JwtResponse(jwt, "RefresToken", userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }

//    @Override
//    public TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) throws RefreshTokenNotDatabase, RefreshTokenExpired {
//        TokenRefreshResponse tokenRefreshResponse = new TokenRefreshResponse();
//        Optional<RefreshToken> optionalRefreshToken = refreshTokenService.findByToken(tokenRefreshRequest.getRefreshToken());
//        if (optionalRefreshToken.isEmpty()){
//            throw new RefreshTokenNotDatabase("Error: Refresh token is not find or expired!");
//        }
//        RefreshToken refreshToken = optionalRefreshToken.get();
//        if (!refreshTokenService.verifyExpiration(refreshToken)){
//           throw new RefreshTokenExpired("Error: Refresh token expired!");
//        }
//        tokenRefreshResponse.setAccessToken(jwtUtils.generateTokenFromUsername(refreshToken.getUser().getUsername()));
//        tokenRefreshResponse.setRefreshToken(refreshTokenService.createRefreshToken(refreshToken.getUser().getId()).getToken());
//        return tokenRefreshResponse;
//    }
//
//    @Override
//    public MessageResponse logoutUser(LogOutRequest logOutRequest) {
//        refreshTokenService.deleteByUserId(logOutRequest.getUserId());
//        MessageResponse messageResponse = new MessageResponse();
//        messageResponse.setMessage("Log out successful!");
//        return messageResponse;
//    }
}
