package com.example.LTNC_WEB_1.course;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface courseRepository extends MongoRepository<course,String> {
    public course findCourseByCourseId(String courseId);

    public void deleteCourseByCourseId(String courseId);

}
