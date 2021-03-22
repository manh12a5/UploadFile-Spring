package com.example.demo.service.user;

import com.example.demo.model.AppUser;
import com.example.demo.service.IService;

public interface IUserService extends IService<AppUser> {

    AppUser getUserByUsername(String userName);

    AppUser getCurrentUser();

}
