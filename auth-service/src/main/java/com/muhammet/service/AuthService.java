package com.muhammet.service;

import antlr.Token;
import com.muhammet.dto.request.LoginRequestDto;
import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.dto.request.UserProfileSaveRequestDto;
import com.muhammet.exception.AuthServiceException;
import com.muhammet.exception.ErrorType;
import com.muhammet.manager.UserProfileManager;
import com.muhammet.repository.IAuthRepository;
import com.muhammet.repository.entity.Auth;
import com.muhammet.repository.enums.Roles;
import com.muhammet.utility.JwtTokenManager;
import com.muhammet.utility.ServiceManager;
import com.muhammet.utility.TokenManager;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;

    // TokenOlusturma5- class sınıfa inject edilir.
    private final JwtTokenManager tokenManager;
    //SınıflarArasıIletisim-7 iletisim kurabilmek icin olusturdugun manager interface ini constructor a enjekte etmelisin
    private final UserProfileManager userProfileManager;

    public AuthService(IAuthRepository repository,
                       UserProfileManager userProfileManager,
                       JwtTokenManager tokenManager) {
        super(repository);
        this.repository = repository;
        this.userProfileManager = userProfileManager;
        this.tokenManager = tokenManager;
    }

    public Boolean save(RegisterRequestDto dto){
        Auth auth = Auth.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .roles(Roles.ROLE_USER)
                .build();
        if(dto.getAdmincode()!=null)
            if(dto.getAdmincode().equals("Adm!n"))
                auth.setRoles(Roles.ROLE_ADMIN);
        save(auth);

        //SınıflarArasıIletisim-8 kendi clasına kaydettikten sonra diğer sınıfa da kaydetmesi icin yolla
        if(auth.getId() != null){
            userProfileManager.save(UserProfileSaveRequestDto.builder()
                            .authid(auth.getId())
                            .email(auth.getEmail())
                            .username(auth.getUsername())
                    .build());
            return true;
        }
        return false;
    }

    public String doLogin(LoginRequestDto dto){
        Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(
                dto.getUsername(),dto.getPassword());
        if(auth.isEmpty()) throw new AuthServiceException(ErrorType.LOGIN_ERROR_001);

        // TokenOlusturma6 login islemi basarılı ise token olusturulur
        return tokenManager.createToken(auth.get().getId());
    }

}
