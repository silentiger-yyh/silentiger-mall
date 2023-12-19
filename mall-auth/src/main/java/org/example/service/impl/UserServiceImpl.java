package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.mapper.RoleMapper;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author silentiger@yyh
 * @Date 2023-12-17 15:22:48
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查数据库
        User user = userMapper.loadUserByUsername( username );
        if (null != user) {
            List<Role> roles = roleMapper.getRolesByUserId( user.getId() );
            user.setAuthorities( roles );
        }
        return user;
    }
}
