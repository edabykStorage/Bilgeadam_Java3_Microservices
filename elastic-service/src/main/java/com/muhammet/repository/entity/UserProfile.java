package com.muhammet.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(indexName = "userprofile")
public class UserProfile {
    @Id
    Long id;
    /**
     * Auth servisinden kayıt olan kişinin auth id sini buraya eşitliyoruz.
     */
    Long authid;
    String username;
    String name;
    String surname;
    String email;
    String phone;
    String address;
    String avatar;
}
