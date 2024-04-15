package com.example.LTNC_WEB_1.information;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface informationRepository extends MongoRepository<information,Integer> {
    public information findInformationByInformationId(Integer id);
    public information deleteInformationByInformationId(Integer id);
}
