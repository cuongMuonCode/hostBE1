package com.example.LTNC_WEB_1.Admin;

import com.example.LTNC_WEB_1.Login.login;
import com.example.LTNC_WEB_1.Login.loginRepository;
import com.example.LTNC_WEB_1.TKB.TKB;
import com.example.LTNC_WEB_1.TKB.TKBRepository;
import com.example.LTNC_WEB_1.classRoom.classRoom;
import com.example.LTNC_WEB_1.classRoom.classRoomRepository;
import com.example.LTNC_WEB_1.course.course;
import com.example.LTNC_WEB_1.course.courseRepository;
import com.example.LTNC_WEB_1.information.information;
import com.example.LTNC_WEB_1.information.informationRepository;
import com.example.LTNC_WEB_1.learning.learningProgress;
import com.example.LTNC_WEB_1.learning.learningRepository;
import com.example.LTNC_WEB_1.student.student;
import com.example.LTNC_WEB_1.student.studentService;
import com.example.LTNC_WEB_1.teacher.teacher;
import com.example.LTNC_WEB_1.teacher.teacherRepository;
import org.springframework.beans.StandardBeanInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.LTNC_WEB_1.Admin.Admin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private classRoomRepository classRoomRepository;
    @Autowired
    private informationRepository informationRepository;
    @Autowired
    private learningRepository learningRepository;
    @Autowired
    private studentService studentService;
    @Autowired
    private teacherRepository teacherRepository;
    @Autowired
    private loginRepository loginRepository;
    @Autowired
    private TKBRepository TKBRepository;
    @Autowired
    private courseRepository courseRepository;
    @Autowired
    private AdminRepository AdminRepository;

    public List<classRoom> all(){
        return classRoomRepository.findAll();
    }
    //xoa hoc sinh
    public information deleteStudent(Integer studentId){
        if (informationRepository.findInformationByInformationId(studentId)==null){
            return null;
        };

        loginRepository.deleteLoginByYourId(studentId);
        learningRepository.deleteLearningProgressByStudentId(studentId);
        TKBRepository.deleteTKBByPersonId(studentId);
        return informationRepository.deleteInformationByInformationId(studentId);
    }
    //xoa giao vien
    public information deleteTeacher(Integer Id){
        if (informationRepository.findInformationByInformationId(Id)==null){
            return null;
        };

        loginRepository.deleteLoginByYourId(Id);
        teacherRepository.deleteTeacherByInformation(Id);
        TKBRepository.deleteTKBByPersonId(Id);
        return informationRepository.deleteInformationByInformationId(Id);
    }
    //tim lop hoc theo courseid va class id
    public classRoom getClassAndCourseId(String courseId,String classId){
        return classRoomRepository.findClassRoomByClassIdAndCourseId(classId,courseId);
    }
    //tim lop hoc theo classid
    public classRoom getClass(String classId){
        return classRoomRepository.findClassRoomByClassId(classId);
    }

    public classRoom createClassRoom(String classId,String courseId,Integer day, Integer shift){
        if(classRoomRepository.findClassRoomByClassId(classId)!=null) {
            // thong bao ton tai lop hoc nay
            System.out.println("da ton tai lop hoc nay");
            return null ;
        }
        classRoom newClass = new classRoom(classId,courseId,new ArrayList<>(),day, shift,50,false);
        //add teacher vo khoa hoc

        course tempcourse=courseRepository.findCourseByCourseId(courseId);

        if (tempcourse==null) {
            System.out.println("Khong ton tai khoa hoc");
            return null ;
        }

        tempcourse.getListClass().addLast(classId);
        courseRepository.deleteCourseByCourseId(courseId);
        courseRepository.save(tempcourse);

        return classRoomRepository.save(newClass);
    }
    //them hoc sinh moi
    public void createStudent(Integer informationId, String name, String email, String falcuty, String password){
        if (informationRepository.findInformationByInformationId(informationId)!=null){
            System.out.println("da ton tai Hoc Sinh nay");
            return;
        }

        information in4= new information(informationId,name,email,falcuty);
        login newLogin= new login(informationId,password,1);
        learningProgress newlP= new learningProgress(informationId,new ArrayList<>(),new ArrayList<>());
        TKB time = new TKB(informationId,new ArrayList<>(),new ArrayList<>());
        for(int i=0;i<7;i++){time.getCa1().add("null");time.getCa2().add("null");}

//        //support ham newsemester() (student)
//        Admin ad= AdminRepository.findAdminByAdminId(0);
//        ad.getStudentList().add(informationId);
//        //delete old admin
//        AdminRepository.deleteAdminByAdminId(0);
//        //add to repo
//        AdminRepository.save(ad);

        //save vo database
        loginRepository.save(newLogin);
        learningRepository.save(newlP);
        informationRepository.save(in4);
        TKBRepository.save(time);

    }
    public void newsemester(Integer id){
        Admin tmp= AdminRepository.findAdminByAdminId(0);
        //delete all classRoom

        //reset tkb ve null for (chay het lisst stu cua ad)

    }
    public void createTeacher(Integer informationId, String name, String email, String falcuty,  String password){
        if (informationRepository.findInformationByInformationId(informationId)!=null){
            System.out.println("da ton tai Giang Vien nay");
            return;
        }

        information in4= new information(informationId,name,email,falcuty);
        login newLogin= new login(informationId,password,2);
        teacher newTeacher=new teacher(informationId,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),newLogin);
        TKB time = new TKB(informationId,new ArrayList<>(),new ArrayList<>());
        for(int i=0;i<7;i++){time.getCa1().add("null");time.getCa2().add("null");}

//        //support ham newsemester() (teacher)
//        Admin ad= AdminRepository.findAdminByAdminId(0);
//        ad.getTeacherList().add(informationId);
//        //delete old admin
//        AdminRepository.deleteAdminByAdminId(0);
//        //add to repo
//        AdminRepository.save(ad);

        //save vo database
        loginRepository.save(newLogin);
        teacherRepository.save(newTeacher);
        informationRepository.save(in4);
        TKBRepository.save(time);
    }
    public void addStudent(String classId,Integer studentId){
        student temp=studentService.getStudentById(studentId);
        classRoom tmp=classRoomRepository.findClassRoomByClassId(classId);
        for(int i=0;i<temp.getProgress().getCourseGpa().size();i++){
            if(tmp.getCourseId().equals(temp.getProgress().getCourseId().get(i))&&temp.getProgress().getCourseGpa().get(i)==11)
                tmp.getStudentList().add(studentId);

            classRoomRepository.deleteClassRoomByClassId(classId);
            classRoomRepository.save(tmp);
        }
    }
}
