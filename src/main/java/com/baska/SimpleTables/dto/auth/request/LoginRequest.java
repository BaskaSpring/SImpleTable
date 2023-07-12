package com.baska.SimpleTables.dto.auth.request;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

	@NotBlank
	private String username;

	@NotBlank
	private String password;

}
