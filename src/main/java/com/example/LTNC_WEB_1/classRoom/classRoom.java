package com.example.LTNC_WEB_1.classRoom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "classRoom")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class classRoom {
    private String classId;
    private String courseId;
    private List<Integer> studentList;
    private Integer day;
    private Integer shift;
    private Integer maxStudent;
    private Boolean haveTeacher;
}
