package com.example.LTNC_WEB_1.student;

import com.example.LTNC_WEB_1.TKB.TKB;
import com.example.LTNC_WEB_1.TKB.TKBService;
import com.example.LTNC_WEB_1.information.information;
import com.example.LTNC_WEB_1.information.informationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class studentController {
    @Autowired
    private studentService studentService;

    @Autowired
    private informationService informationService;

    @Autowired
    private TKBService tkbService;


    @GetMapping("/tkb/{studentid}")
    public TKB gettkb(@PathVariable Integer studentid ){return tkbService.getTKB(studentid);}
    @GetMapping("/{studentId}")
    public information getStudent(@PathVariable Integer studentId){
        return informationService.getInformationById(studentId);
    }

    @GetMapping("/{studentId}/AVG")
    public Double getAvgGPA(@PathVariable Integer studentId){
        return studentService.getAverageGPA(studentId);
    }

    @GetMapping("/{studentId}/allGPA")
    public List<GPA_Course> allCourseWithGPA(@PathVariable Integer studentId){
        return studentService.showALlGPA(studentId);
    }

    @PutMapping("/{classId}/{id}/courseRegister")
    public void Register(@PathVariable String classId,@PathVariable Integer id){
        studentService.courseRegister(classId,id);
    }


}
