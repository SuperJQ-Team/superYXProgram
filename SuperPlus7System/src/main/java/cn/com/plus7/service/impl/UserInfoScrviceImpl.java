package cn.com.plus7.service.impl;

import cn.com.plus7.mapper.SysUserMapper;
import cn.com.plus7.pojo.SysUser;
import cn.com.plus7.pojo.SysUserExample;
import cn.com.plus7.service.IUserInfoService;
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
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andDelFlagEqualTo(false);

        List<SysUser> userInfos = sysUserMapper.selectByExampleWithBLOBs(example);
        if(userInfos.size() > 0) {
            return userInfos.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public int updata(SysUser userInfo) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(userInfo.getId());
        userInfo.setId(null);
        userInfo.setUpdateTime(new Date());

        return sysUserMapper.updateByExampleSelective(userInfo, example);
    }
}
