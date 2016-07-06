package com.yu.web.base.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.base.util.DateUtils;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.json.UserAgreementJson;
import com.yu.web.base.mapper.UserAgreementMapper;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserAgreement;
import com.yu.web.base.service.SequenceService;
import com.yu.web.base.service.UserAgreementService;

/**
 * 用户Service实现类
 *
 * @author StarZou
 * @since 2014年7月5日 上午11:54:24
 */
@Service
public class UserAgreementServiceImpl extends GenericServiceImpl<UserAgreement, Long> implements UserAgreementService {

    @Resource
    private UserAgreementMapper userAgreementMapper;
    
    @Resource
    private SequenceService sequenceService;
    
    public UserAgreement getUsableAgreement(Long userId){
		return userAgreementMapper.getUsableAgreement(userId);
	}
    
    public List<UserAgreementJson> buildUserAgreementJson(List<UserAgreement> agreements){
		List<UserAgreementJson> agreementJsons = new ArrayList<UserAgreementJson>();
		for(UserAgreement agreement : agreements){
			UserAgreementJson json = new UserAgreementJson(agreement);
			agreementJsons.add(json);
		}
		return agreementJsons;
	}
    
    public UserAgreement buildUserAgreement(UserAgreement agreement){
		int id = sequenceService.get("agreement");
		agreement.setId(Long.valueOf(id));
		agreement.setCreateDate(DateUtils.getNowTime());
		return agreement;
	}
    
	public Integer calWinAmount(Integer bettingAmount,Double odds){
		Double amount = bettingAmount * odds;
		Integer winAmount = roundHalfUp(amount);
		return winAmount;
	}
	
	@Override
	public Integer calUserAmount(User user, Integer winAmount) {
		UserAgreement agreement = userAgreementMapper.getUsableAgreement(user.getId());
		Integer userAmount = 0;
		if(agreement != null){
			Integer agreementType = agreement.getAgreementType();
			Double agreementValue = agreement.getAgreementValue();
			
			if(agreementType == GlobalConstants.USER_AGREEMENT_NOTHING){
				userAmount = 0;
			}else if(agreementType == GlobalConstants.USER_AGREEMENT_NUM){
				userAmount = roundHalfUp(agreementValue*100);
			}else if(agreementType == GlobalConstants.USER_AGREEMENT_PROP){
				Double amount = winAmount * agreementValue;
				BigDecimal amountBigDecimal = new BigDecimal(amount);
				BigDecimal result = amountBigDecimal.divide(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
				userAmount = Integer.valueOf(result.toString());
			}
		}
		return userAmount;
	}
	
	/**
	 * 四舍五入
	 * @param i
	 * @return
	 */
	private Integer roundHalfUp(Double i){
		BigDecimal decimal =  new BigDecimal(i).setScale(0, BigDecimal.ROUND_HALF_UP); 
		return Integer.valueOf(decimal.toString());
	}

    @Override
    public GenericMapper<UserAgreement, Long> getMapper() {
        return userAgreementMapper;
    }


}
