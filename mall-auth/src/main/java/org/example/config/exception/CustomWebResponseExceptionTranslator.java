package org.example.config.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * 鉴权失败时返回的异常类
 *
 * @Author silentiger@yyh
 * @Date 2023-12-17 15:40:00
 */

@Component
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity translate(Exception e) {

//        InternalAuthenticationServiceException oAuth2Exception = (InternalAuthenticationServiceException) e;
        return ResponseEntity
                .status(200)
                .body(e.getMessage());
//                .body(new InternalAuthenticationServiceException(oAuth2Exception.getMessage()));
    }
}
