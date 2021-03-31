package com.lzy.shiro.config;

import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.extension.api.R;
import com.lzy.shiro.realm.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//用来整合shiro框架相关配置类
@Configuration
public class ShiroConfig {
	// shiroFilter 负责拦截所有请求
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 给filter设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
		// 配置系统受限资源
		// 配置系统公共资源
		Map<String, String> map = new HashMap<String, String>();
		map.put("/assets/**", "anon");
		map.put("/bootstrap-3.3.7/**", "anon");
		map.put("/common/**", "anon");
		map.put("/css/**", "anon");
		map.put("/img/**", "anon");
		map.put("/js/**", "anon");
		map.put("/template/**", "anon");
		map.put("/sys/login/login.page", "anon");// anon 设置为公共资源
		map.put("/sys/login/register.page", "anon");// anon 设置为公共资源
		//   /  受限资源    不受限的资源放在上面
		map.put("/", "authc");// authc 请求这个资源需要认证和授权 /拦截所有资源

//		shiroFilterFactoryBean.setLoginUrl("/sys/login/login.page");
//		shiroFilterFactoryBean.setSuccessUrl("/sys/admin/admin.page");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}

	// 2.创建安全管理器
	@Bean
	public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		// 给安全管理器设置realm
		defaultWebSecurityManager.setRealm(realm);
		return defaultWebSecurityManager;
	}

	// 3.创建自定义realm
	@Bean
	public CustomerRealm getRealm() {
		CustomerRealm customerRealm = new CustomerRealm();
		// 修改凭证校验匹配器
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		// 设置加密算法为md5
		credentialsMatcher.setHashAlgorithmName("MD5");
		// 设置散列次数
		credentialsMatcher.setHashIterations(1024);
		customerRealm.setCredentialsMatcher(credentialsMatcher);

		return customerRealm;
	}
}
