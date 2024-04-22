package com.example.LTNC_WEB_1.Login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginService {
    @Autowired
    private loginRepository loginRepository;

    public Boolean isExists(Integer id, String pass , Integer Role){
        String newPass=decrypt(pass);
        login tmp = loginRepository.findLoginByYourIdAndPassWord(id,newPass);
        if(tmp!=null && tmp.getRole().equals(Role))return true;
        return false;
    }
    public String encrypt(String str){
        String Pass="";
        for (int i=str.length()-1;i>=0;i--){
            Pass=Pass+str.charAt(i);
        }
        return Pass;
    }
    public String decrypt(String str){
        String Pass="";
        for (int i=str.length()-1;i>=0;i--){
            Pass=Pass+str.charAt(i);
        }
        return Pass;
    }
}
