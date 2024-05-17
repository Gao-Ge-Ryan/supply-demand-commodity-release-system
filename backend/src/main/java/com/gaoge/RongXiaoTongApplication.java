package com.gaoge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages = "com.gaoge.dao")
@EnableSwagger2
public class RongXiaoTongApplication {
    public static void main(String[] args) {
       System.out.println("\n:      (♥◠‿◠)ﾉﾞ   启动成功   (♥◠‿◠)ﾉﾞ    \n" +
                " .--------------.          .--------------.    \n" +
                " |   _ _ _ _ ___|          |   _ _ _ _ ___|    \n" +
                " |  |   .______.           |  |   .______.     \n" +
                " |  |   |_____ |           |  |   |_____ |      \n" +
                " |  ( ' )    | |           |  ( ' )    | |       \n" +
                " |  (_ o _)_ |_|           |  (_ o _)_ |_|        \n" +
                " |__(_,_)_____.|     /     |__(_,_)_____.|      /           \n" +

         " `-..-'  ''-'   `'-'    `-..-'     ''-'   `'-'    `-..-' "     );
        SpringApplication.run(RongXiaoTongApplication.class,args);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
