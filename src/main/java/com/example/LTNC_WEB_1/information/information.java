package com.example.LTNC_WEB_1.information;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "information")
public class information {
    private Integer informationId;
    private String name;
    private String email;
    private String faculty;
}
