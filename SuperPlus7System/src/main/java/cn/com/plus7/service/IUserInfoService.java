package cn.com.plus7.service;

import cn.com.plus7.pojo.SysUser;

public interface IUserInfoService {
    SysUser get(Long id);

    int updata(SysUser userInfo);

}
