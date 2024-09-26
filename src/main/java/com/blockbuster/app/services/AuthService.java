package com.blockbuster.app.services;

import com.blockbuster.app.dtos.AuthDto;
import com.blockbuster.app.models.User;
import com.blockbuster.app.repositories.UserRepository;
import com.blockbuster.app.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthService(JwtUtil  jwtUtil){
        this.jwtUtil = jwtUtil;
    }
    public AuthDto signIn(AuthDto authDto){
        AuthDto response = new AuthDto();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authDto.getEmail(),
                    authDto.getPassword())
            );
            var user = userRepository.findByEmail(authDto.getEmail()).orElseThrow();
            System.out.println("USER IS: "+ user);
            var jwt = jwtUtil.generateToken(user);
            var refreshToken = jwtUtil.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Signed In");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }
    public AuthDto signUp(AuthDto registrationRequest){
        AuthDto resp = new AuthDto();
        try {
            User ourUsers = new User();
            ourUsers.setEmail(registrationRequest.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            ourUsers.setRole(registrationRequest.getRole());
            User ourUserResult = userRepository.save(ourUsers);
            if (ourUserResult != null && ourUserResult.getId()>0) {
                //resp.setOurUsers(ourUserResult);
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }
    public AuthDto refreshToken(AuthDto refreshTokenReqiest){
        AuthDto response = new AuthDto();
        String ourEmail = jwtUtil.extractionUsername(refreshTokenReqiest.getToken());
        User users = userRepository.findByEmail(ourEmail).orElseThrow();
        if (jwtUtil.isTokenValid(refreshTokenReqiest.getToken(), users)) {
            var jwt = jwtUtil.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenReqiest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}
