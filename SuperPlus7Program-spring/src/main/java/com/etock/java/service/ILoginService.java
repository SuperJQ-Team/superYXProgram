package com.etock.java.service;

import com.etock.java.entity.SysUser;


public interface ILoginService {
    SysUser login(String username, String password);
    int signup(String username, String password, String role);
    int writeoff(String username, String password);
}
