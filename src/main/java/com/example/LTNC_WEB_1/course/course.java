package com.example.LTNC_WEB_1.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "courseWare")
public class course {
    private String courseId;
    private String courseName;
    private Integer credits;
    private String refBook;
    private String refVideo;
    private List<String> listClass;

}
