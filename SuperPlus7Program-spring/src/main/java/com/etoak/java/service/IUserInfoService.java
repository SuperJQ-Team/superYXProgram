package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.SysUser;

public interface IUserInfoService extends IService<SysUser> {
    SysUser get(Long id);

    int update(SysUser userInfo);

}
