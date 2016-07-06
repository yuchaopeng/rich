package com.yu.web.base.service;

import com.yu.base.service.GenericService;
import com.yu.web.base.model.WebSiteConfig;

public interface WebSiteConfigService extends GenericService<WebSiteConfig, Long> {
	
	void initWebSiteConfig();
	
	/**
	 * 根据投注金额获取 佣金
	 * @param bettingAmount
	 * @return
	 */
	Integer getCommAmount(Integer bettingAmount);
}
