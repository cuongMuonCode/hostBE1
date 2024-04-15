package com.example.LTNC_WEB_1.TKB;

import com.example.LTNC_WEB_1.teacher.teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TKBRepository extends MongoRepository<TKB,Integer> {
    public  TKB findTKBByPersonId(Integer Id);
    public void deleteTKBByPersonId(Integer Id);
}
