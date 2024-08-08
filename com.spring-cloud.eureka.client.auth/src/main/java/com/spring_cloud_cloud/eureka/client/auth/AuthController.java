package com.spring_cloud_cloud.eureka.client.auth;

import com.spring_cloud_cloud.eureka.client.auth.core.User;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 사용자 ID를 받아 JWT 액세스 토큰을 생성하여 응답합니다.
     *
     * @param user_id 사용자 ID
     * @return JWT 액세스 토큰을 포함한 AuthResponse 객체를 반환합니다.
     */
    @PostMapping("/auth/signIn")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody SignInRequest signInRequest){
        String token = authService.signIn(signInRequest.getUser_id(), signInRequest.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/auth/signUp")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        User createdUser = authService.signUp(user);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * JWT 액세스 토큰을 포함하는 응답 객체입니다.
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class AuthResponse {
        private String access_token;

    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class SignInRequest {
        private String user_id;
        private String password;
    }
}