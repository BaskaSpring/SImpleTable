package com.baska.SimpleTables.controller;

import com.baska.SimpleTables.dto.auth.request.LoginRequest;
import com.baska.SimpleTables.dto.auth.request.SignUpRequest;
import com.baska.SimpleTables.service.Impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    final private AuthServiceImpl authService;

    @PostMapping("/SignUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authService.registerUser(signUpRequest));
    }

    @PostMapping("/SignIn")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

//    @PostMapping("/RefreshToken")
//    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest tokenRefreshRequest)
//            throws RefreshTokenExpired, RefreshTokenNotDatabase {
//        return ResponseEntity.ok(authService.refreshToken(tokenRefreshRequest));
//    }

//    @PostMapping("/Logout")
//    public ResponseEntity<?> logoutUser(@RequestBody LogOutRequest logOutRequest) {
//        return ResponseEntity.ok(authService.logoutUser(logOutRequest));
//    }


}
