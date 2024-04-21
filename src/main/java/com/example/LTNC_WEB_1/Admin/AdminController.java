package com.example.LTNC_WEB_1.Admin;

import com.example.LTNC_WEB_1.classRoom.classRoom;
import com.example.LTNC_WEB_1.classRoom.classRoomService;
import com.example.LTNC_WEB_1.course.course;
import com.example.LTNC_WEB_1.information.information;
import com.example.LTNC_WEB_1.information.informationRepository;
import com.example.LTNC_WEB_1.information.informationService;
import com.example.LTNC_WEB_1.learning.learningProgress;
import com.example.LTNC_WEB_1.learning.learningRepository;
import com.example.LTNC_WEB_1.student.student;
import com.example.LTNC_WEB_1.student.studentService;
import com.example.LTNC_WEB_1.teacher.teacher;
import com.example.LTNC_WEB_1.teacher.teacherRepository;
import com.example.LTNC_WEB_1.teacher.teacherService;
import com.example.LTNC_WEB_1.teacherDTO.teacherDTO;
import com.example.LTNC_WEB_1.teacherDTO.teacherDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.JstlUtils;

import java.util.List;

@RestController
@RequestMapping("/Admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private classRoomService classRoomService;
    @Autowired
    private studentService studentService;
    @Autowired
    private teacherService teacherService;
    @Autowired
    private informationRepository informationRepository;
    @Autowired
    private learningRepository learningRepository;
    @Autowired
    private teacherDTOService teacherDTOService;
    @Autowired
    private informationService informationService;
    @Autowired
    private AdminService AdminService;
    @Autowired
    private teacherRepository teacherRepository;

    /*@GetMapping("/showStudent")
    public List<Integer> getStudentInClass(){

    }*/
    //tao acc, them hoc sinh
    @PostMapping("/createStudent")
    public void createStudent(@RequestParam Integer id,@RequestParam String name, @RequestParam String email, @RequestParam String faculty,@RequestParam String password){
        AdminService.createStudent(id ,name,email,faculty,password);
    }
    //xoa hoc sinh
    @DeleteMapping("/deleteStudent/{studentId}")
    public information deleteStudent(@PathVariable Integer studentId){
       return AdminService.deleteStudent(studentId);
       // return studentService.getStudentById(studentId);
    }
    // thay doi thong tin  hoc sinh
    @PutMapping("/updateStudent/rename")
    public information updateNameOfStudent( @RequestParam Integer studentId,@RequestParam  String name ){
        studentService.reName(studentId,name);
        return studentService.getStudentById(studentId).getIn4();
    }
    @PutMapping("/updateStudent/reemail")
    public information updateEmailOfStudent( @RequestParam Integer studentId,@RequestParam String email){
        studentService.reEmail(studentId,email);
        return studentService.getStudentById(studentId).getIn4();
    }


    @PutMapping("/updateStudent/refalcuty")
    public information updateFalcutyOfStudent(@RequestParam Integer studentId,@RequestParam String faculty){
        studentService.reFaculty(studentId,faculty);
        return studentService.getStudentById(studentId).getIn4();
    }
    //    @PutMapping("/updateStudent/{studentId}/email") // viet tiep doi faculty
//    public student updateEmailOfStudent(@PathVariable Integer studentId,String email){
//
//
//    }
    //tao moi teacher
    @PostMapping("/createTeacher")
    public void createTeacher(@RequestParam Integer id , @RequestParam String name,@RequestParam String email,@RequestParam String faculty, @RequestParam String password){
        AdminService.createTeacher(id,name,email,faculty,password);
    }
    //xoa teacher
    @DeleteMapping("/deleteTeacher/{Id}")
    public information deleteTeacher(@PathVariable Integer Id){
        return AdminService.deleteTeacher(Id);
    }
    // thay doi thong tin giao vien
    @PutMapping("/updateTeacher/rename")
    public information updateNameOfTeacher( @RequestParam Integer teacherId,@RequestParam String name ){
        teacherDTOService.reName(teacherId,name);
        return teacherDTOService.getTeacherById(teacherId).getIn4();
    }
    @PutMapping("/updateTeacher/reemail")
    public information updateEmailOfTeacher( @RequestParam Integer teacherId,@RequestParam String email ){
        teacherDTOService.reEmail(teacherId,email);
        return teacherDTOService.getTeacherById(teacherId).getIn4();
    }
    @PutMapping("/updateTeacher/refalcuty")
    public information updateFalcutyOfTeacher(@RequestParam Integer teacherId, @RequestParam String faculty ){
        teacherDTOService.reFaculty(teacherId,faculty);
        return teacherDTOService.getTeacherById(teacherId).getIn4();
    }
    //CRUD lop hoc
    @GetMapping("/getClassRoom")
    public classRoom getClassRoom(@RequestParam String courseId,@RequestParam String classId){ // tim theo courseId va classId
        return AdminService.getClassAndCourseId(courseId,classId);
    }

    @PostMapping("/createClass")
    public classRoom createClassRoom(@RequestParam String classId,@RequestParam String courseId,@RequestParam Integer day,@RequestParam Integer shift){
        //  System.out.println("vo day");
        return AdminService.createClassRoom(classId,courseId,day,shift);
    }
    @GetMapping("/newclass")
    public classRoom dhd(@RequestParam String courseId,@RequestParam String classId,
                         @RequestParam Integer day,@RequestParam Integer shirt){
        return AdminService.createClassRoom(classId,courseId,day,shirt);
    }
    @GetMapping("/allCourse")
    public List<course> PrintCourse()   {   return AdminService.allcourse();}
    @PutMapping("/addDiploma")
    public teacher addDiploma(@RequestParam String diploma,@RequestParam Integer teacherId){
        return AdminService.addDiploma(diploma,teacherId);
    }
    //them hoc sinh vao lop hoc
//    @DeleteMapping("/deleted")
//    public void deleteTeach()  {
//        teacherRepository.deleteTeacherByInformation(44444);
//    }
}
