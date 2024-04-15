package com.example.LTNC_WEB_1.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class courseService {

    @Autowired
    private courseRepository courseRepository;

    public course getCourseById(String courseId){
        if(courseRepository.findCourseByCourseId(courseId)==null)System.out.println("no course with id");
        return courseRepository.findCourseByCourseId(courseId);
    }

}
