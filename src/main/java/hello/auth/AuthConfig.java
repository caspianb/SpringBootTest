package hello.auth;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.ThreadLocalTargetSource;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class AuthConfig {

    @Bean
    @Primary
    public ProxyFactoryBean proxiedThreadLocalTenantStore() {
        ProxyFactoryBean proxy = new ProxyFactoryBean();
        proxy.setTargetSource(threadLocalTenantStore());
        return proxy;
    }

    @Bean
    public ThreadLocalTargetSource threadLocalTenantStore() {
        ThreadLocalTargetSource threadLocalTenantStore = new ThreadLocalTargetSource();
        threadLocalTenantStore.setTargetBeanName("tenantStore");
        return threadLocalTenantStore;
    }

    @Bean("tenantStore")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public TenantStore tenantStore() {
        return new TenantStore();
    }
}
