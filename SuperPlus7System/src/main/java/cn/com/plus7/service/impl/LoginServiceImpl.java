package cn.com.plus7.service.impl;

import cn.com.plus7.mapper.SysUserMapper;
import cn.com.plus7.pojo.SysUser;
import cn.com.plus7.pojo.SysUserExample;
import cn.com.plus7.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser login(String username,String password)
    {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        criteria.andDelFlagEqualTo(false);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if (sysUsers.size() > 0) {
            return sysUsers.get(0);
        }else{
            return null;
        }
    }

    @Override
    public int signup(String username, String password, String role)
    {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        criteria.andDelFlagEqualTo(false);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if (sysUsers.size() > 0) {
            return -1;
        }else{
            return sysUserMapper.insertSelective(
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
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if (sysUsers.size() > 0) {
            return sysUserMapper.updateByExampleSelective(
                    SysUser.
                            builder()
                            .delFlag(true)
                            .updateBy("system")
                            .updateTime(new Date())
                            .build()
                    ,example);
        }else{
            return -1;
        }
    }
}
