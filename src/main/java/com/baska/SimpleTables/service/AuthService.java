package com.baska.SimpleTables.service;


import com.baska.SimpleTables.dto.auth.request.LogOutRequest;
import com.baska.SimpleTables.dto.auth.request.LoginRequest;
import com.baska.SimpleTables.dto.auth.request.SignUpRequest;
import com.baska.SimpleTables.dto.auth.request.TokenRefreshRequest;
import com.baska.SimpleTables.dto.auth.response.JwtResponse;
import com.baska.SimpleTables.dto.auth.response.MessageResponse;
import com.baska.SimpleTables.dto.auth.response.TokenRefreshResponse;

public interface AuthService {

    String registerUser(SignUpRequest signUpRequest);

    JwtResponse authenticateUser(LoginRequest loginRequest);

//    TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest);
//    MessageResponse logoutUser(LogOutRequest logOutRequest);
}
