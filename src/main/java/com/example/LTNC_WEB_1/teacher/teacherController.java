package com.example.LTNC_WEB_1.teacher;
import com.example.LTNC_WEB_1.TKB.TKBService;
import com.example.LTNC_WEB_1.classRoom.classRoom;
import com.example.LTNC_WEB_1.classRoom.classRoomService;
import com.example.LTNC_WEB_1.information.information;
import com.example.LTNC_WEB_1.information.informationService;
import com.example.LTNC_WEB_1.teacher.teacher;
import com.example.LTNC_WEB_1.teacher.teacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.web.bind.annotation.*;
import com.example.LTNC_WEB_1.course.course;
import com.example.LTNC_WEB_1.course.courseService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "*")
public class teacherController {
    @Autowired
    private teacherService teacherService;
    @Autowired
    private informationService informationService;

    @Autowired
    private classRoomService classroomService;

    @Autowired
    private TKBService tKBService;

    @GetMapping("/{teacherId}")
    public information getTeacher(@PathVariable Integer teacherId){
        return  informationService.getInformationById(teacherId) ;
    }


    @GetMapping("/{teacherId}/tkb1")
    public List<classRoom> gettkb1(@PathVariable Integer teacherId ) {
        return tKBService.returnclassRoomCa1(teacherId);
    }
    @GetMapping("/{teacherId}/tkb2")
    public List<classRoom>gettkb2(@PathVariable Integer teacherId){

        return tKBService.returnclassRoomCa2(teacherId);
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
                        ,@RequestParam Double mark,@PathVariable Integer teacherId){
        teacherService.SetMark(courseId,classId,studentId,teacherId,mark);
    }
    // viet lai print class ra ten cua cac hoc sinh
//    @GetMapping("/{teacherId}/{courseId}/{classId}/printClass")
//    public List<Integer> printclass(@PathVariable String classId,@PathVariable String courseId,@PathVariable Integer teacherId)
//    {
//        return teacherService.PrintStudent(classId,courseId,teacherId);
//    }


    @PutMapping("/{teacherId}/updateCourse")
    public void updateCourse(@PathVariable Integer teacherId,@RequestParam String courseId,@RequestParam String Book){
        teacherService.UpdateCourse(teacherId,courseId,Book);
    }


    // testing delete teacher

    @PutMapping("/{id}/teacherRegister")
    public void teacherRegister(@PathVariable Integer id,@RequestParam String classId){
        teacherService.teacherCourseRegister(id, classId);
    }


    @GetMapping("/{teacherId}/getAllClassRoom")
    public List<classRoom>getAllCourse( @PathVariable Integer teacherId){

        List<classRoom> res = new ArrayList<classRoom>();
        List<String> allClassId = teacherService.getTeacherById(teacherId).getIdClass();
        for(int i=0;i<allClassId.size();i++){
            res.add(classroomService.getClass(allClassId.get(i)));
        }
        return res;
    }



    @GetMapping("/{teacherId}/{classId}/getAllStudent")
    public List<information> ListStudent(@PathVariable Integer teacherId,
                                         @PathVariable String classId){
        List<information>res= new ArrayList<information>();
        List<Integer> temp= classroomService.getClass(classId).getStudentList();
        for(int i=0;i<temp.size();i++){
            res.add(informationService.getInformationById(temp.get(i)));
        }
        return res;
    }


}

