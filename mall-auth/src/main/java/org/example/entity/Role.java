package org.example.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * 角色
 *
 * @Author silentiger@yyh
 * @Date 2023-12-17 15:13:43
 */

@Data
public class Role  implements GrantedAuthority {

    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}