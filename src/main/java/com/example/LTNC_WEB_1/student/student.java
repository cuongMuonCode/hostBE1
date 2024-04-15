package com.example.LTNC_WEB_1.student;

import com.example.LTNC_WEB_1.TKB.TKB;
import com.example.LTNC_WEB_1.information.information;
import com.example.LTNC_WEB_1.learning.learningProgress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class student {
    private information in4;
    private learningProgress progress;
    private TKB tkb;
}
