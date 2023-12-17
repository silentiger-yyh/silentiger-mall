package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends IService<User> {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
