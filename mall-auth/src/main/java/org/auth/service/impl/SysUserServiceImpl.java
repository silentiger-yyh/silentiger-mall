package org.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.auth.mapper.SysUserMapper;
import org.auth.service.ISysUserService;
import org.silentiger.entity.SysUser;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author 喻云虎
 * @since 2023-12-21
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser loadUserByUsername(String username) {
        return baseMapper.getUserByUsername(username);
    }
}
