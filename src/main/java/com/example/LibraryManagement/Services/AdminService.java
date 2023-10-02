package com.example.LibraryManagement.Services;


import com.example.LibraryManagement.Models.Admin;
import com.example.LibraryManagement.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;


    public void createAdmin(Admin admin)
    {
        adminRepository.save(admin);
    }

    public  Admin findAdminById(String id)
    {
        return adminRepository.findById(id).orElse(null);
    }

    public void createOrUpdateAdmin(Admin admin) {
        adminRepository.save(admin);
    }
}
