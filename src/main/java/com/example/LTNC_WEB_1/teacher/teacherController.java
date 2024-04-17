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

    @GetMapping("/{teacherId}/getTeacher")
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

    @PutMapping("/{teacherId}/setMark")
    public void setMark(@RequestParam String courseId,@RequestParam String classId,@RequestParam Integer studentId
                        ,@PathVariable Integer teacherId,@RequestParam Double mark){
        teacherService.SetMark(courseId,classId,studentId,teacherId,mark);
    }
    // viet lai print class ra ten cua cac hoc sinh
//    @GetMapping("/{teacherId}/{courseId}/{classId}/printClass")
//    public List<Integer> printclass(@PathVariable String classId,@PathVariable String courseId,@PathVariable Integer teacherId)
//    {
//        return teacherService.PrintStudent(classId,courseId,teacherId);
//    }

    @GetMapping("/{teacherId}/printClass")
    public List<String> printclass(@RequestParam String classId,@RequestParam String courseId,@PathVariable Integer teacherId)
    {
        return teacherService.printListStudent(classId,courseId,teacherId);
    }

    @PutMapping("/{teacherId}/updateCourse")
    public void updateCourse(@PathVariable Integer teacherId,@RequestParam String courseId,@RequestParam String Book){
        teacherService.UpdateCourse(teacherId,courseId,Book);
    }


    // testing delete teacher

    @PutMapping("/{id}/teacherRegister")
    public void teacherRegister(@PathVariable Integer id,@RequestParam String classId){
        teacherService.teacherCourseRegister(id, classId);
    }


}

