package com.example.LTNC_WEB_1.teacher;
import com.example.LTNC_WEB_1.information.information;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "teacher")
public class teacher {
    private Integer information;
    private List<String> idClass;
    private List<String> idCourse;
    private List<String> diploma;

}
