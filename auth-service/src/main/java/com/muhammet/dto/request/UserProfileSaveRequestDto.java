package com.muhammet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
//SınıflarArasıIletisim-6 bu dtoyu iletisim kurmak icin olusturduk
public class UserProfileSaveRequestDto {
    String username;
    String email;
    Long authid;
    String token;
}
