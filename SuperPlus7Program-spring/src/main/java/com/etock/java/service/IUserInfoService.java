package com.etock.java.service;

import com.etock.java.entity.SysUser;

public interface IUserInfoService {
    SysUser get(Long id);

    int update(SysUser userInfo);

}
