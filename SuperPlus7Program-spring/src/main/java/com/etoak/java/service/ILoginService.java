package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.SysUser;


public interface ILoginService extends IService<SysUser> {
    SysUser login(String username, String password);
    int signup(String username, String password, String role);
    int writeoff(String username, String password);
}
