package com.example.LibraryManagement.Services;

import com.example.LibraryManagement.Models.SecuredUser;
import com.example.LibraryManagement.Models.Users;
import com.example.LibraryManagement.Repository.SecuredUserRepository;
import com.example.LibraryManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {


    PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    SecuredUserRepository securedUserRepository;





    public SecuredUser saveSecuredUser(SecuredUser securedUser) {
        SecuredUser user = securedUserRepository.findByUsername(securedUser.getUsername());
        if (user == null) {
            String encrypted = encoder.encode(securedUser.getPassword());
            securedUser.setPassword(encrypted);
            return this.securedUserRepository.save(securedUser);
        }
        else
        {
            return user;
        }
    }

    public void createOrUpdateUser(Users users)
    {
        SecuredUser Secureduser1=users.getSecuredUser();
        Secureduser1=saveSecuredUser(Secureduser1);
        users.setSecuredUser(Secureduser1);
        userRepository.save(users);
    }

    public SecuredUser getUserByUsername(String username) {
        return securedUserRepository.findByUsername(username);
    }

    public Users findUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return securedUserRepository.findByUsername(username);
    }
}
