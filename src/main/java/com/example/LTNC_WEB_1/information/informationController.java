//package com.example.LTNC_WEB_1.information;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/information")
//public class informationController {
//
//    @Autowired
//    private informationService informationService;
//
//    @GetMapping("/{studentId}")
//    private information getInformationById(@PathVariable Integer studentId){
//        if(informationService.getInformationById(studentId)==null) System.out.println("No Student Id");
//        return informationService.getInformationById(studentId);
//    }
//
//    @PutMapping("/updateName")
//    private information updateInformationById(){
//        String newName = "rename";
//        return informationService.setNameByInformation(newName,2);
//    }
//
//    @PostMapping("/add")
//    private information addNewInformation(){
//        information newIn4= new information(100, "New student","newStudent@gmail.com","Khoa");
//        return informationService.addInformation(newIn4);
//    }
//
//
//
//
//
//
//
//
//}
