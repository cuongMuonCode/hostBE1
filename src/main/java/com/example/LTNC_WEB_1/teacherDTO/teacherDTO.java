package com.example.LTNC_WEB_1.teacherDTO;

import com.example.LTNC_WEB_1.TKB.TKB;
import com.example.LTNC_WEB_1.information.information;
import com.example.LTNC_WEB_1.teacher.teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class teacherDTO {
    private teacher tech;
    private information in4;
    private TKB tkb;
}
