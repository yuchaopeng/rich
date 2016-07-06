package com.yu.web.base.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.base.cache.CommonCache;
import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.mapper.WebSiteConfigMapper;
import com.yu.web.base.model.WebSiteConfig;
import com.yu.web.base.service.WebSiteConfigService;

@Service
public class WebSiteConfigServiceImpl extends GenericServiceImpl<WebSiteConfig, Long> implements WebSiteConfigService {

	@Resource
	private WebSiteConfigMapper webSiteConfigMapper;
	
	public void initWebSiteConfig(){
		WebSiteConfig webSiteConfig = webSiteConfigMapper.selectById(1L);
		CommonCache.put(CommonCache.WEB_SITE_CONFIG_KEY, webSiteConfig);
	}
	
	@Override
	public GenericMapper<WebSiteConfig, Long> getMapper() {
		return webSiteConfigMapper;
	}

	@Override
	public Integer getCommAmount(Integer bettingAmount) {
		WebSiteConfig webSiteConfig = webSiteConfigMapper.selectById(GlobalConstants.WEB_SITE_CONFIG_ID);
		
		Double commRate = webSiteConfig.getCommRate();
		BigDecimal bettingAmountBig = new BigDecimal(bettingAmount);
		BigDecimal commRateBig = new BigDecimal(commRate);
		BigDecimal rate100 = new BigDecimal(100);
		
		BigDecimal newAmount = bettingAmountBig.multiply(commRateBig);
		Integer commissionAmount = 0;
		if(newAmount != null && BigDecimal.ZERO.compareTo(newAmount) != 0){
			BigDecimal resultAmount = newAmount.divide(rate100).setScale(0,BigDecimal.ROUND_HALF_UP);
			String resultAmountStr = resultAmount.toString();
			commissionAmount = Integer.valueOf(resultAmountStr);
		}
		return commissionAmount;
	}
}