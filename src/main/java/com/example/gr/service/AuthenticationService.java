package com.example.gr.service;

import com.example.gr.entity.Token;
import com.example.gr.entity.TokenType;
import com.example.gr.entity.User;
import com.example.gr.repository.TokenRepository;
import com.example.gr.repository.UserRepository;
import com.example.gr.request.AuthenticationRequest;
import com.example.gr.request.RegisterRequest;
import com.example.gr.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenRepository tokenRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        try {
            var existingUser = userRepository.findByEmail(request.getEmail())
                    .orElse(null);

            System.out.println(existingUser);
            if(existingUser != null) {
                return AuthenticationResponse.builder()
                        .message("Địa chỉ email đã tồn tại.")
                        .status("400")
                        .build();

            }else {
                var user = User.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.USER) - thường đăng kí chỉ cho người dùng tự động làm user
                        .role(0)
                        .build();

                var saveUser = userRepository.save(user);
                var id = saveUser.getUserId();
                var jwtToken = jwtService.generateToken(user);
                saveUserToken(saveUser, jwtToken);
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .user_id(id)
                        .message("REGISTER_SUCCESS")
                        .status("200")
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
                        .build();
            }
        }catch (Exception e){
            return AuthenticationResponse.builder()
                    .message("Địa chỉ email đã tồn tại.")
                    .status("400")
                    .build();
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
//        var refreshToken = jwtService.generateRefreshToken(user);
        var id = user.getUserId();
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user_id(id)
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
                .build();
    }

    private void revokeAllUserTokens(User user) {
        long userIdLong = user.getUserId();
        int userIdInt = (int) userIdLong;
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

}
