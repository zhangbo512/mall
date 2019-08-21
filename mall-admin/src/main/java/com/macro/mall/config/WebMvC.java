package com.macro.mall.config;

import com.macro.mall.component.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * description
 *
 * @author zb 2019/08/20 17:46
 */
@Configuration
public class WebMvC implements WebMvcConfigurer {

    private final PermissionInterceptor permissionInterceptor;

    public WebMvC(PermissionInterceptor permissionInterceptor) {
        this.permissionInterceptor = permissionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(permissionInterceptor);
    }
}
