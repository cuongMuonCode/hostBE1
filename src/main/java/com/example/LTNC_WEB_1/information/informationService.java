package com.example.LTNC_WEB_1.information;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class informationService {
    @Autowired
    private informationRepository informationRepository;

    public information getInformationById(Integer id){
        return informationRepository.findInformationByInformationId(id);
    }

    public information setNameByInformation(String newName , Integer id){// set name boi id
        information tmp= informationRepository.findInformationByInformationId(id);
        if(tmp==null) {
            System.out.println("No id in db");
            return null;
        }
        tmp.setName(newName);
        informationRepository.deleteInformationByInformationId(id);
        return  informationRepository.save(tmp);
    }

    public information addInformation( information information1){
        return informationRepository.save(information1);
    }

}
