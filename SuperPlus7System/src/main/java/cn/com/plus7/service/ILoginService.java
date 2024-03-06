package cn.com.plus7.service;

import cn.com.plus7.pojo.SysUser;


public interface ILoginService {
    SysUser login(String username, String password);
    int signup(String username, String password, String role);
    int writeoff(String username, String password);
}
