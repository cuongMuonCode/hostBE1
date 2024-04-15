package com.example.LTNC_WEB_1.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("login")
public class login {
    private Integer yourId;
    private String passWord;
    private Integer role;
}
