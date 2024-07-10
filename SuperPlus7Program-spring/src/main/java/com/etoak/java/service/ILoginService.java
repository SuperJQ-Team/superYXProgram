package com.etoak.java.service;

import com.etoak.java.entity.SysUser;


public interface ILoginService {
    SysUser login(String username, String password);
    int signup(String username, String password, String role);
    int writeoff(String username, String password);
}
