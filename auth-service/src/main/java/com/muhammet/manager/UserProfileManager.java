package com.muhammet.manager;

import com.muhammet.dto.request.UserProfileSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.muhammet.constants.ApiUrls.SAVE;

/**   //SınıflarArasıIletisim-4
 * buraya iletişim kuracağımız sınıfın interface ini oluşturacağız.
 * DİKKAT!!
 * name -> benzersiz bir isim olmalıdır. diğer türlü hata alacaksınız.
 */
@FeignClient(name = "user-profile-service",
        url= "http://localhost:9092/api/v1/user",
        decode404 = true)
public interface UserProfileManager {

    @PostMapping(SAVE)

        //SınıflarArasıIletisim-5 Burası diğer sınıfta iletişim kuracağımız methodun parametresiz body si
    ResponseEntity<Boolean> save(@RequestBody UserProfileSaveRequestDto dto);

}
