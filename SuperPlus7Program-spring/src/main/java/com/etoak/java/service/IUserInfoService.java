package com.etoak.java.service;

import com.etoak.java.entity.SysUser;

public interface IUserInfoService {
    SysUser get(Long id);

    int update(SysUser userInfo);

}
