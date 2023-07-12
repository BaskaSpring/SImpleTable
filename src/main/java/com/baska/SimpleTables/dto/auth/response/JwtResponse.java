package com.baska.SimpleTables.dto.auth.response;


import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String refreshToken;
    private String id;
    private String username;
    private String email;
    private List<String> roles;
}