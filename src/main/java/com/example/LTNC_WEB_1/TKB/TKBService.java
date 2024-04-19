package com.example.LTNC_WEB_1.TKB;

import com.example.LTNC_WEB_1.classRoom.classRoom;
import com.example.LTNC_WEB_1.classRoom.classRoomRepository;
import com.example.LTNC_WEB_1.classRoom.classRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TKBService {
    @Autowired
    TKBRepository TKBRepository;

    @Autowired
    classRoomRepository classRoomRepository;


    public TKB getTKB(Integer id){
        return TKBRepository.findTKBByPersonId(id);
    }
    public List <classRoom> returnclassRoomCa1(Integer id){
        List<classRoom> temp=new ArrayList<>();
        TKB time=TKBRepository.findTKBByPersonId(id);

        if (time==null){
            return null;
        }

        for(int i=0;i<7;i++){temp.add(classRoomRepository.findClassRoomByClassId(time.getCa1().get(i))); }
        return temp;
    }
    public List <classRoom> returnclassRoomCa2(Integer id){
        List<classRoom> temp=new ArrayList<>();
        TKB time=TKBRepository.findTKBByPersonId(id);

        if (time==null){
            return null;
        }

        for(int i=0;i<7;i++){temp.add(classRoomRepository.findClassRoomByClassId(time.getCa2().get(i))); }
        return temp;
    }
//    public void updateTKB (String classId,Integer id){
//        classRoom tmp=classRoomRepository.findClassRoomByClassId(classId);
//        if(TKBRepository.findTKBById(id).getCa1().get(tmp.getDay()-1).equals("null")&&tmp.getShift()==1){
//            TKB temp=TKBRepository.findTKBById(id);
//            TKBRepository.deleteTKBById(id);
//            temp.getCa1().set(tmp.getDay()-1,classId);
//            TKBRepository.save(temp);
//
//        }
//        if(TKBRepository.findTKBById(id).getCa2().get(tmp.getDay()-1).equals("null")&&tmp.getShift()==2){
//            TKB temp=TKBRepository.findTKBById(id);
//            TKBRepository.deleteTKBById(id);
//            temp.getCa2().set(tmp.getDay()-1,classId);
//            TKBRepository.save(temp);
//        }
//
//    }



}
