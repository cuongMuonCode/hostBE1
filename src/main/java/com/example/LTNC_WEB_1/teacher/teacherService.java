package com.example.LTNC_WEB_1.teacher;
import com.example.LTNC_WEB_1.TKB.TKBRepository;
import com.example.LTNC_WEB_1.information.informationRepository;
import com.example.LTNC_WEB_1.information.informationRepository;
import com.example.LTNC_WEB_1.learning.learningProgress;
import com.example.LTNC_WEB_1.student.studentService;
import com.example.LTNC_WEB_1.student.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.LTNC_WEB_1.classRoom.classRoom;
import com.example.LTNC_WEB_1.classRoom.classRoomService;
import com.example.LTNC_WEB_1.course.courseService;
import com.example.LTNC_WEB_1.learning.learningRepository;
import com.example.LTNC_WEB_1.course.course;
import java.util.Scanner;
import com.example.LTNC_WEB_1.course.courseRepository;
import com.example.LTNC_WEB_1.TKB.TKB;
import com.example.LTNC_WEB_1.classRoom.classRoomRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class teacherService {

@Autowired
private learningRepository learningRepository;
    @Autowired
    private teacherRepository teacherRepository;
    @Autowired
    private classRoomService classRoomService;
    @Autowired
    private studentService studentService;
    @Autowired
    private courseService courseService;
    @Autowired
    private courseRepository courseRepository;
    @Autowired
    private TKBRepository TKBRepository;
    @Autowired
    private classRoomRepository classRoomRepository;

   /* public teacher getTeacherById(Integer id){
        if(informationRepository.findInformationByInformationId(id)==null)
            return new teacher()
    }*/
    public teacher getTeacherById(Integer id){
        return teacherRepository.findTeacherByInformation(id);
    }


    public void SetMark(String courseId,String classId,Integer studentId,Integer teacherId,Double mark){
       classRoom temp=  classRoomService.getClassAndCourseId(courseId,classId);
       teacher tempTeacher=teacherRepository.findTeacherByInformation(teacherId);
       boolean stu_in_class=false;
       boolean tea_in_class=false;
       for(int i=0;i<temp.getStudentList().size();i++){
           if(temp.getStudentList().get(i)==studentId){stu_in_class=true;break;}
       }
        for(int i=0;i<tempTeacher.getIdClass().size();i++){
            if(tempTeacher.getIdCourse().get(i).equals(courseId)&& tempTeacher.getIdClass().get(i).equals(classId)){tea_in_class=true;break;}
        }
        if(!(stu_in_class&&tea_in_class))return;
       student stu=studentService.getStudentById(studentId);int a=-1;
       for(int i=0;;i++){if(stu.getProgress().getCourseId().get(i).equals(courseId)){a=i;break;}}
       if(a==-1){System.out.println("khong tim ra mon nay");
       return;}
       stu.getProgress().getCourseGpa().set(a,mark);
        learningProgress tem2=stu.getProgress();
    learningRepository.deleteLearningProgressByStudentId(studentId);
        learningRepository.save(tem2);


    }
   /* public void SetMark1(String courseId,String classId, Integer studentId){

    }*/
    public List<Integer> PrintStudent(String ClassId,String courseId,Integer teacherId){
     teacher tempTeacher=teacherRepository.findTeacherByInformation(teacherId);
     boolean tea_in_class=false;
        for(int i=0;i<tempTeacher.getIdClass().size();i++){
            if(tempTeacher.getIdCourse().get(i).equals(courseId)&& tempTeacher.getIdClass().get(i).equals(ClassId)){tea_in_class=true;break;}
        }
        if(!tea_in_class)return null;
        classRoom temp=classRoomService.getClassAndCourseId(courseId,ClassId);
       /* for(int i=0;i<temp.getStudentList().size();i++){
            System.out.println(temp.getStudentList().get(i));
        }*/return temp.getStudentList();

    }
    public void UpdateCourse(Integer teacherId, String courseId,String Book){
        teacher tempTeacher=teacherRepository.findTeacherByInformation(teacherId);
        boolean tea_in_class=false;
        for(int i=0;i<tempTeacher.getIdClass().size();i++){
            if(tempTeacher.getIdCourse().get(i).equals(courseId)){tea_in_class=true;break;}
        }
        if(!tea_in_class)return ;
       // course tmp1=null;
        /*for(int i=0;i<tempTeacher.getIdClass().size();i++){
            if(classRoomService.getClass(tempTeacher.getIdClass().get(i)).equals(courseId)&&
                    classRoomService){
                classRoom tmp=classRoomService.getClass(teacher.getIdClass().get(i));
                tmp1= courseService.getCourseById(tmp.getCourseId());

            }
              }if(tmp1==null){
            System.out.println("No course");
        return null;
        }*/
      course  tmp1=courseService.getCourseById(courseId);

        courseRepository.deleteCourseByCourseId(courseId);
        tmp1.setRefBook(Book);
         courseRepository.save(tmp1);
    }
    public void teacherCourseRegister(Integer id,String classId){
        classRoom Class=classRoomService.getClass(classId);
        teacher temp=teacherRepository.findTeacherByInformation(id);
        TKB time= TKBRepository.findTKBByPersonId(id);
        if(temp==null||Class.getHaveTeacher())return;
        for(int i=0;i<temp.getDiploma().size();i++){
            if(temp.getDiploma().get(i).equals(Class.getCourseId())){
                if(time.getCa1().get(Class.getDay()-1).equals("null")&&Class.getShift()==1){
// setHaveteacher
                    Class.setHaveTeacher(true);
                    classRoomRepository.deleteClassRoomByClassId(classId);
                    classRoomRepository.save(Class);
                    TKBRepository.deleteTKBByPersonId(id);
                    time.getCa1().set(Class.getDay()-1,classId);
                    TKBRepository.save(time);
                    temp.getIdClass().add(classId);
                    temp.getIdCourse().add(Class.getCourseId());
                    teacherRepository.deleteTeacherByInformation(id);
                    teacherRepository.save(temp);

                }else
                if(time.getCa2().get(Class.getDay()-1).equals("null")&&Class.getShift()==2){
                    Class.setHaveTeacher(true);
                    classRoomRepository.deleteClassRoomByClassId(classId);
                    classRoomRepository.save(Class);

                    TKBRepository.deleteTKBByPersonId(id);
                    time.getCa2().set(Class.getDay()-1,classId);
                    TKBRepository.save(time);
                    temp.getIdClass().add(classId);
                    temp.getIdCourse().add(Class.getCourseId());
                    teacherRepository.deleteTeacherByInformation(id);
                    teacherRepository.save(temp);
                }break;
            }

        }
    }
}
