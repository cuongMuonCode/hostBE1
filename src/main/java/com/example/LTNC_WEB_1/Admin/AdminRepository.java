package com.example.LTNC_WEB_1.Admin;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin,Integer> {
    public Admin findAdminByAdminId(Integer adminId);
    public void deleteAdminByAdminId(Integer adminId);
}
