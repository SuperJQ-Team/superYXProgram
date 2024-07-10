package com.etock.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.etock.java.mapper.SysUserMapper;
import com.etock.java.entity.SysUser;
import com.etock.java.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserInfoScrviceImpl implements IUserInfoService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser get(Long id) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.eq("del_flag", false);

        List<SysUser> userInfos = sysUserMapper.selectList(wrapper);
        if(!userInfos.isEmpty()) {
            return userInfos.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public int update(SysUser userInfo) {
        userInfo.setUpdateBy("system");
        userInfo.setUpdateTime(new Date());

        return sysUserMapper.updateById(userInfo);
    }
}
