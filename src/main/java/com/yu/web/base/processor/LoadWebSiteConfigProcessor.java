package com.yu.web.base.processor;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.yu.base.cache.CommonCache;
import com.yu.web.base.model.WebSiteConfig;
import com.yu.web.base.service.WebSiteConfigService;

public class LoadWebSiteConfigProcessor implements BeanPostProcessor {
	
	private Logger log = Logger.getLogger(LoadWebSiteConfigProcessor.class);
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof WebSiteConfigService) {  
			log.info("开始加载网站设置信息============start============");
            ((WebSiteConfigService)bean).initWebSiteConfig(); //调用方法加载数据  
            WebSiteConfig config = (WebSiteConfig) CommonCache.get(CommonCache.WEB_SITE_CONFIG_KEY);
            if(config == null){
            	log.info("网站配置信息为空...");
            }else{
            	log.info("网站名称："+config.getWebSiteName()+"，佣金比率："+config.getCommRate());
            }
            log.info("开始加载网站设置信息============finish============");
        }  
		return bean;
	}

}
