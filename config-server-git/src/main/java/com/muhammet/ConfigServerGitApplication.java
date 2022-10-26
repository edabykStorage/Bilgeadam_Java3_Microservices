package com.muhammet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


//GitBagla2 Annotationları tanımlanır
@SpringBootApplication
@EnableConfigServer
public class ConfigServerGitApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerGitApplication.class, args);
    }
}

//GitBagla3 bootstrap git olusturulur. application yml olusturulur.
//uri : githubda yeni repo olusturulur. repodaki adres kopyalanır buraya yapistirilir
//https://github.com/bilgeadamankaraboostjava3/ url deki bilgeadam... =username
//setting-developersetting-personelaccesstokenda Tokens(classic) den yeni token üretilir=password