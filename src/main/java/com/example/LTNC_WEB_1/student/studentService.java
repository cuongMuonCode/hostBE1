package com.example.LTNC_WEB_1.student;

import com.example.LTNC_WEB_1.Login.loginRepository;
import com.example.LTNC_WEB_1.TKB.TKBService;
import com.example.LTNC_WEB_1.classRoom.classRoom;
import com.example.LTNC_WEB_1.course.courseRepository;
import com.example.LTNC_WEB_1.information.information;
import com.example.LTNC_WEB_1.information.informationRepository;
import com.example.LTNC_WEB_1.learning.learningProgress;
import com.example.LTNC_WEB_1.learning.learningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.LTNC_WEB_1.TKB.TKB;
import com.example.LTNC_WEB_1.TKB.TKBRepository;
import java.util.ArrayList;
import java.util.List;
import com.example.LTNC_WEB_1.classRoom.classRoomRepository;
@Service
public class studentService {
    @Autowired
    private informationRepository informationRepository;
    @Autowired
    private learningRepository learningRepository;
    @Autowired
    private TKBService TKBService;
    @Autowired
    private courseRepository courseRepository;
    @Autowired
    private classRoomRepository classRoomRepository;
    @Autowired
    private TKBRepository TKBRepository;
    @Autowired
    private loginRepository loginRepository;

    public student getStudentById(Integer id){
        if(learningRepository.findLearningProgressByStudentId(id)==null || informationRepository.findInformationByInformationId(id)==null)System.out.println("No student");
        return new student(informationRepository.findInformationByInformationId(id),learningRepository.findLearningProgressByStudentId(id),TKBService.getTKB(id),null);
    }
    public Double getAverageGPA(Integer id){
        learningProgress tmp=  learningRepository.findLearningProgressByStudentId(id);
        if(tmp==null)System.out.println("NO ID");
        int sumCredits=0;
        double sum =  0.0;
        for(int i=0;i<tmp.getCourseGpa().size();i++){
            if(tmp.getCourseGpa().get(i)==11)continue;// diem 11 thi chua co diem
            Integer credits = courseRepository.findCourseByCourseId(tmp.getCourseId().get(i)).getCredits();
            double a=tmp.getCourseGpa().get(i);
            while(a>=100){a-=100;}
                    sum = sum+ a*credits;
            sumCredits +=credits;
        }
        return sum/sumCredits;
    }
    // xem diem tung mon
    public List<GPA_Course> showALlGPA(Integer id){
        learningProgress tmp=  learningRepository.findLearningProgressByStudentId(id);
        List<GPA_Course> res= new ArrayList<>();
        if(tmp==null)System.out.println("NO ID");
       // if(tmp.getCourseGpa().isEmpty())return null;// new khhong co hoc sinh nay thi sao
        for(int i=0;i<tmp.getCourseGpa().size();i++){
            GPA_Course tmp2= new GPA_Course(courseRepository.findCourseByCourseId(tmp.getCourseId().get(i)).getCourseName(),
                                courseRepository.findCourseByCourseId(tmp.getCourseId().get(i)).getCredits(),
                                tmp.getCourseGpa().get(i));
            res.add(tmp2);
        }
        return res;
    }

    // update name email faculty

    public void reName(Integer id , String newName){
        information tmp= informationRepository.findInformationByInformationId(id);
        informationRepository.deleteInformationByInformationId(id);
        tmp.setName(newName);
        informationRepository.save(tmp);
    }

    public  void reEmail(Integer id,String newEmail){
        information tmp= informationRepository.findInformationByInformationId(id);
        informationRepository.deleteInformationByInformationId(id);
        tmp.setEmail(newEmail);
        informationRepository.save(tmp);
    }
    public  void reFaculty(Integer id,String newFalcuty){
        information tmp= informationRepository.findInformationByInformationId(id);
        informationRepository.deleteInformationByInformationId(id);
        tmp.setFaculty(newFalcuty);
        informationRepository.save(tmp);
    }
    public classRoom courseRegister(String classId,Integer id ){
        // goi ham liet ke class cua pdt
        classRoom temp=classRoomRepository.findClassRoomByClassId(classId);
        if(temp==null){
            System.out.println(classId);
            System.out.println("Khong co lop nay");
            return null;
        }
        learningProgress tmp=learningRepository.findLearningProgressByStudentId(id);
        TKB time= TKBService.getTKB(id);
        if(temp.getHaveTeacher()==false){
            System.out.println("Lop nay chua co giao vien");
            return null;
        }
        if(temp.getStudentList().size()==temp.getMaxStudent()){
            System.out.println("Lop da du si so");
            return null;
        }
        for(int i=0;i<7;i++){
            classRoom ca1=classRoomRepository.findClassRoomByClassId(time.getCa1().get(i));
            classRoom ca2=classRoomRepository.findClassRoomByClassId(time.getCa2().get(i));
        if(ca1==null&&ca2==null)
            continue;
            if(ca1!=null&&ca1.getCourseId().equals(temp.getCourseId()))return null;
            if(ca2!=null&&ca2.getCourseId().equals(temp.getCourseId()))return null;
        }
        boolean firsttime=true;
        //bien bool
        for(int i=0;i<learningRepository.findLearningProgressByStudentId(id).getCourseGpa().size();i++){
            if(tmp.getCourseId().get(i).equals(temp.getCourseId())){
                firsttime=false;
                //diem >100 tuc dang hoc return
                if(tmp.getCourseGpa().get(i)>=100.0){
                    System.out.println("Mon nay dang hoc");
                    return null;
                }
                //trung lich hoc return
                if(!(time.getCa1().get(temp.getDay()-1).equals("null"))&&temp.getShift()==1){
                    System.out.println("Trung lichhh");
                    return null;
                }
                if(!(time.getCa2().get(temp.getDay()-1).equals("null"))&&temp.getShift()==2){
                    System.out.println("Trung lich");
                    return null;
                }
                if(tmp.getCourseGpa().get(i)<100.0){
                    tmp.getCourseGpa().set(i,100.0+tmp.getCourseGpa().get(i));
                    learningRepository.deleteLearningProgressByStudentId(id);
                    learningRepository.save(tmp);
                }
            }
        }
        if(firsttime){
            tmp.getCourseId().add(temp.getCourseId());
            tmp.getCourseGpa().add(111.0);//diem 11 hoc lan dau hoac chua qua mon, chua co diem
            //add mon moi
            learningRepository.deleteLearningProgressByStudentId(id);
            learningRepository.save(tmp);
        }//hell
        temp.getStudentList().add(id);
        classRoomRepository.deleteClassRoomByClassId(classId);
        classRoomRepository.save(temp);
        if(time.getCa1().get(temp.getDay()-1).equals("null")&&temp.getShift()==1){
            // setHaveteacher
            TKBRepository.deleteTKBByPersonId(id);
            time.getCa1().set(temp.getDay()-1,classId);
            TKBRepository.save(time);
        }else if(time.getCa2().get(temp.getDay()-1).equals("null")&&temp.getShift()==2){
            TKBRepository.deleteTKBByPersonId(id);
            time.getCa2().set(temp.getDay()-1,classId);
            TKBRepository.save(time);
        }
    return temp;
    }


    public List<classRoom>showAllClass(){
        return classRoomRepository.findAll();
    }

}
