package com.example.LTNC_WEB_1.TKB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timetable")
public class TKBController {
    @Autowired
    private TKBService TKBService;
    @GetMapping("/get")
    public TKB gettkb(){
        System.out.println("asdasd");
        return TKBService.getTKB(2);}
//    @PutMapping("/{classId}/{id}/update")
//    public void update(@PathVariable String classId, @PathVariable Integer id){
//        TKBService.updateTKB(classId, id);
//    }
}
