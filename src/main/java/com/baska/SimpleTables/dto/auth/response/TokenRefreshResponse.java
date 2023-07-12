package com.baska.SimpleTables.dto.auth.response;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenRefreshResponse {

    private String accessToken;
    private String refreshToken;
}
