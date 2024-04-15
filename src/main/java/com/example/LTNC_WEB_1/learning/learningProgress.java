package com.example.LTNC_WEB_1.learning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "learningProgress")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class learningProgress {
    private Integer studentId;
    private List<String> courseId;
    private List<Double> courseGpa;
}
