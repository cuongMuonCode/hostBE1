package com.example.LTNC_WEB_1.teacherDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacherDTO")
public class teacherDTOController {

    @Autowired
    private teacherDTOService teacherDTOService;
    @GetMapping("/{id}")
    public  teacherDTO getTeacherById(@PathVariable Integer id){
        return teacherDTOService.getTeacherById(id);
    }

}
