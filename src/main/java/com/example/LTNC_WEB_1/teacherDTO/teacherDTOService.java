package com.example.LTNC_WEB_1.teacherDTO;

import com.example.LTNC_WEB_1.TKB.TKBService;
import com.example.LTNC_WEB_1.information.information;
import com.example.LTNC_WEB_1.information.informationRepository;
import com.example.LTNC_WEB_1.information.informationService;
import com.example.LTNC_WEB_1.teacher.teacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Service;

@Service
public class teacherDTOService {
    @Autowired
    private informationService informationService;
    @Autowired
    private teacherService teacherService;
    @Autowired
    private informationRepository informationRepository;
    @Autowired
    private TKBService TKBService;
    public  teacherDTO getTeacherById(Integer id){
        return new teacherDTO(teacherService.getTeacherById(id),informationService.getInformationById(id), TKBService.getTKB(id));
    }
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
}
