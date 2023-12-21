package org.auth.service;

import org.silentiger.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author 喻云虎
 * @since 2023-12-21
 */
public interface ISysUserService extends IService<SysUser> {
    SysUser loadUserByUsername(String username);
}
