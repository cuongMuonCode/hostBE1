package com.example.LTNC_WEB_1.teacher;
import com.example.LTNC_WEB_1.teacher.teacher;
import com.example.LTNC_WEB_1.teacher.teacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.web.bind.annotation.*;
import com.example.LTNC_WEB_1.course.course;
import com.example.LTNC_WEB_1.course.courseService;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class teacherController {
    @Autowired
    private teacherService teacherService;
    @Autowired
    private courseService courseService;

    @Autowired
    private teacherRepository teacherRepository;

    @GetMapping("/get/{teacherId}")
    public teacher getTeacher(@PathVariable Integer teacherId){
        return  teacherService.getTeacherById(teacherId);
    }
   /* @GetMapping("/{classId}/printstudent")
    public void PrintStudent(@PathVariable String classId){
         teacherService.PrintStudent(classId);
    }

    // update information
    @PutMapping("/{courseId}/{Book}/rename")
    public course UpdateCourse(@PathVariable String courseId,@PathVariable String Book ){
        teacherService.UpdateCourse(courseId,Book);
        return courseService.getCourseById(courseId);
    }*/

    @PutMapping("/{courseId}/{classId}/{studentId}/{teacherId}/{mark}/setmark")
    public void setMark(@PathVariable String courseId,@PathVariable String classId,@PathVariable Integer studentId,@PathVariable Integer teacherId,@PathVariable Double mark){
//        athV
        teacherService.SetMark(courseId,classId,studentId,teacherId,mark);

    }
    @GetMapping("/{classId}/{courseId}/{teacherId}/printclass")
    public List<Integer> printclass(@PathVariable String classId,@PathVariable String courseId,@PathVariable Integer teacherId)
    {return teacherService.PrintStudent(classId,courseId,teacherId);}

    @PutMapping("/{teacherId}/{courseId}/{Book}/updateCourse")
    public void updateCourse(@PathVariable Integer teacherId,@PathVariable String courseId,@PathVariable String Book){
        teacherService.UpdateCourse(teacherId,courseId,Book);
    }


    // testing delete teacher

    @PutMapping("/{id}/{classId}/teacherRegister")
    public void teacherRegister(@PathVariable Integer id,@PathVariable String classId){
        teacherService.teacherCourseRegister(id, classId);
    }


}

