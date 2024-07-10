package com.etoak.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.service.ILoginService;
import com.etoak.java.mapper.SysUserMapper;
import com.etoak.java.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class LoginServiceImpl
        extends ServiceImpl< SysUserMapper, SysUser >
        implements ILoginService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser login(String username,String password)
    {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        wrapper.eq("password", password);
        wrapper.eq("del_flag", false);
        List<SysUser> sysUsers = sysUserMapper.selectList(wrapper);
        if (!sysUsers.isEmpty()) {
            return sysUsers.get(0);
        }else{
            return null;
        }
    }

    @Override
    public int signup(String username, String password, String role)
    {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        wrapper.eq("del_flag", false);

        List<SysUser> sysUsers = sysUserMapper.selectList(wrapper);
        if (!sysUsers.isEmpty()) {
            return -1;
        }else{
            return sysUserMapper.insert(
                    SysUser
                            .builder()
                            .userName(username)
                            .password(password)
                            .role(role)
                            .delFlag(false)
                            .createdBy("system")
                            .createdTime(new Date())
                            .build()
            );
        }
    }

    @Override
    public int writeoff(String username, String password)
    {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        wrapper.eq("password", password);
        wrapper.eq("del_flag", false);
        List<SysUser> sysUsers = sysUserMapper.selectList(wrapper);
        if (!sysUsers.isEmpty()) {
            return sysUserMapper.update(
                    SysUser.
                            builder()
                            .delFlag(true)
                            .updateBy("system")
                            .updateTime(new Date())
                            .build()
                    , wrapper);
        }else{
            return -1;
        }
    }
}
