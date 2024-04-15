package com.example.LTNC_WEB_1.Login;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface loginRepository extends MongoRepository<login,Integer> {

    public login findLoginByYourIdAndPassWord(Integer yourId,String passWord);

}
