package com.example.demo.service.user;

import com.example.demo.model.AppUser;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public AppUser getUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = this.getUserByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(user.getRoll());

        UserDetails userDetails = new User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );

        return userDetails;
    }

    @Override
    public AppUser getCurrentUser() {
        AppUser user;
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        user = this.getUserByUsername(userName);
        return user;
    }

    @Override
    public List<AppUser> findAll() {
        return (List<AppUser>) userRepository.findAll();
    }

    @Override
    public Page<AppUser> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public AppUser findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public AppUser save(AppUser appUser) {
        return userRepository.save(appUser);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<AppUser> findByName(String name) {
        return null;
    }

}
