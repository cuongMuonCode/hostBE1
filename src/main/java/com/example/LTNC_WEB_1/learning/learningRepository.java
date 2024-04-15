package com.example.LTNC_WEB_1.learning;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface learningRepository extends MongoRepository<learningProgress,Integer> {
    public learningProgress findLearningProgressByStudentId(Integer studentId);
   public void deleteLearningProgressByStudentId(Integer studentId);
}
