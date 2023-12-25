package org.silentiger.service.impl;

import org.silentiger.entity.SysUser;
import org.silentiger.mapper.SysUserMapper;
import org.silentiger.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author silentiger@yyh
 * @since 2023-12-21
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
