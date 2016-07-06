package com.yu.web.base.mapper;

import com.yu.base.mapper.GenericMapper;
import com.yu.web.base.model.AdminAccountInfo;
import com.yu.web.base.model.PaymentMethodType;

public interface AdminAccountInfoMapper  extends GenericMapper<AdminAccountInfo, Long>{
	
	PaymentMethodType selectPaymentMethodTypeById(Long id);
}
