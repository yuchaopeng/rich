package com.yu.web.base.service;

import java.util.List;

import com.yu.base.service.GenericService;
import com.yu.web.base.json.UserAgreementJson;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserAgreement;

/**
 * 用户 业务 接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:53:33
 **/
public interface UserAgreementService extends GenericService<UserAgreement, Long> {
	
	UserAgreement getUsableAgreement(Long userId);
	
	List<UserAgreementJson> buildUserAgreementJson(List<UserAgreement> agreements);
	
	UserAgreement buildUserAgreement(UserAgreement agreement);
	
	/**
	 * 计算中奖金额
	 * @param bettingAmount
	 * @param odds
	 * @return
	 */
	Integer calWinAmount(Integer bettingAmount,Double odds);
	
	/**
	 * 计算用户分成金额
	 * @return
	 */
	Integer calUserAmount(User user,Integer winAmount);
}
