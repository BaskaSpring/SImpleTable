package com.baska.SimpleTables.dto.auth.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LogOutRequest {
    String userId;
}
