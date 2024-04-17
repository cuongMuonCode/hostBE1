package com.example.LTNC_WEB_1.Admin;

import com.example.LTNC_WEB_1.Login.login;
import com.example.LTNC_WEB_1.information.information;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Admin")
public class Admin {
    private Integer adminId;
    private List<Integer> teacherList;
    private List<Integer> studentList;
    private login loginAdmin;
}
