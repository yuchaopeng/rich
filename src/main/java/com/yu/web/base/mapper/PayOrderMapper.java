package com.yu.web.base.mapper;

import com.yu.base.mapper.GenericMapper;
import com.yu.web.base.model.PayOrder;
import com.yu.web.base.model.PayOrderItem;

public interface PayOrderMapper extends GenericMapper<PayOrder, String> {
	void insertOrderItem(PayOrderItem payOrderItem);
}