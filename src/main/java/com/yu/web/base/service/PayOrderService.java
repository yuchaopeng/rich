package com.yu.web.base.service;

import java.util.Map;

import com.yu.base.service.GenericService;
import com.yu.web.base.form.PayOrderCreateForm;
import com.yu.web.base.model.PayOrder;

public interface PayOrderService extends GenericService<PayOrder, String> {
	
	PayOrder createPayOrder(PayOrderCreateForm orderCreateForm);
	
	Map<String,Object> orderPay(PayOrder payOrder);
}
