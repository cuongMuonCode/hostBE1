package com.example.LTNC_WEB_1.teacher;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface teacherRepository extends MongoRepository<teacher,Integer> {
       public  teacher findTeacherByInformation(Integer information);
       public void deleteTeacherByInformation(Integer information);
}
