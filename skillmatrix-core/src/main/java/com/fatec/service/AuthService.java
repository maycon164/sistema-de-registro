package com.fatec.service;

import com.fatec.dto.GoogleInfoDTO;
import com.fatec.dto.LoginDTO;
import com.fatec.exceptions.GoogleLoginException;
import com.fatec.model.AuthResponseDTO;
import com.fatec.model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final TokenService tokenService;
    private final UserService userService;
    @Value("${google.clientId}")
    private String CLIENT_ID;

    @Value("${google.baseUrl}")
    private String GOOGLE_BASE_URL;

    public AuthResponseDTO makeLoginWithGoogle(LoginDTO loginDTO) {
        GoogleInfoDTO info = getGoogleInfo(loginDTO);
        User user = userService.findByEmail(info.email())
                .withPicture(info.picture());
        String token = tokenService.generateToken(user);
        log.info("jwt token has been sent!!!");
        return new AuthResponseDTO(user, token);
    }

    private GoogleInfoDTO getGoogleInfo(LoginDTO loginDTO)  {
        log.info("Validating google information");
        String url =  GOOGLE_BASE_URL + loginDTO.token();

        final HttpTransport transport = new NetHttpTransport();
        final Gson gson = new Gson();

        var headers = new HttpHeaders().set("Metadata-Flavor", "Google");
        headers.set("Authorization", "Bearer " + loginDTO.token());
        headers.set("Accept", "application/json");

        try{
            var request = transport.createRequestFactory()
                    .buildGetRequest(new GenericUrl(url))
                    .setHeaders(headers);

            HttpResponse response = request.execute();
            GoogleInfoDTO googleInfoDTO = gson.fromJson(response.parseAsString(), GoogleInfoDTO.class);

            return googleInfoDTO;
        }catch (IOException exception){
            log.error("Error on login");
            throw new GoogleLoginException();
        }
    }

    private User getGoogleUserInfo(LoginDTO loginDTO){
        final HttpTransport transport = new NetHttpTransport();
        final JsonFactory jsonFactory = new GsonFactory();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singleton(CLIENT_ID))
                .build();

        try {
            GoogleIdToken idToken = verifier.verify(loginDTO.token());
            var payload = idToken.getPayload();
            System.out.println(payload);
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String picture = (String) payload.get("picture");
        } catch (GeneralSecurityException|IOException exception){
            log.warn(exception.getMessage());
            throw new GoogleLoginException();
        }
        throw new RuntimeException("Method not implemented!!!");
    }
}

