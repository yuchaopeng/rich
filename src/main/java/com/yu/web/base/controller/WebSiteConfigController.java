package com.yu.web.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.base.annotation.SystemLog;
import com.yu.base.page.Page;
import com.yu.base.page.PageBean;
import com.yu.web.base.condition.AdminAccountInfoCondition;
import com.yu.web.base.condition.MatchCondition;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.form.AdminAccountInfoForm;
import com.yu.web.base.json.MatchPlayMethodJson;
import com.yu.web.base.model.AdminAccountInfo;
import com.yu.web.base.model.MatchDetail;
import com.yu.web.base.model.MatchPlayMethod;
import com.yu.web.base.model.WebSiteConfig;
import com.yu.web.base.service.AdminAccountInfoService;
import com.yu.web.base.service.MatchDetailService;
import com.yu.web.base.service.MatchPlayMethodService;
import com.yu.web.base.service.WebSiteConfigService;

@Controller
@RequestMapping(value = "/webSite")
public class WebSiteConfigController {
	
	@Resource
	private WebSiteConfigService webSiteConfigService;
	
	@Resource
	private MatchPlayMethodService matchPlayMethodService;
	
	@Resource
	private MatchDetailService matchDetailService;
	
	@Resource
	private AdminAccountInfoService adminAccountInfoService;
	
	@RequestMapping(value="/toWebSiteConfig")
	public String toWebSiteConfig(Model model){
		WebSiteConfig webSiteConfig = webSiteConfigService.selectById(GlobalConstants.WEB_SITE_CONFIG_ID);
		model.addAttribute("webSiteConfig", webSiteConfig);
		return "webSite/webSiteConfig";
	}
	
	@SystemLog(description="编辑站点配置")
	@RequestMapping(value="/saveOrUpdateConfig")
	public @ResponseBody Map<String,Object> saveOrUpdateConfig(Model model,@ModelAttribute("webSiteConfig") WebSiteConfig webSiteConfig){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			webSiteConfigService.update(webSiteConfig);
			//重新加载到内存
			webSiteConfigService.initWebSiteConfig();
			map.put("result", 0);
			map.put("message", "保存成功");
		} catch (Exception e) {
			map.put("result", 1);
			map.put("message", "保存失败："+e.getMessage());
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping(value="/toPlayMethodConfig")
	public String toPlayMethodConfig(Model model){
		return "webSite/playMethodConfig";
	}
	
	@RequestMapping(value="/playMethodConfigListJson")
	public @ResponseBody Map<String, Object> playMethodConfigListJson(@ModelAttribute("pageBean") PageBean pageBean){
		Page<MatchPlayMethod> page = matchPlayMethodService.select4Page(pageBean);
		List<MatchPlayMethod> results = page.getResults();
		
		List<MatchPlayMethodJson> jsons = new ArrayList<MatchPlayMethodJson>();
		
		for(MatchPlayMethod method : results){
			MatchCondition condition = new MatchCondition();
			condition.setPlayMethodId(method.getId());
			List<MatchDetail> details = matchDetailService.selectList(condition);
			boolean flag = true;
			if(details == null || details.size() == 0){
				flag = false;
			}
			MatchPlayMethodJson json = new MatchPlayMethodJson(method,flag);
			jsons.add(json);
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalRecord());
		map.put("rows", jsons);
		
		return map;
	}
	
	@RequestMapping(value="/toSaveOrUpdatePlayMethodConfig")
	public String toSaveOrUpdatePlayMethodConfig(Model model,@RequestParam(value = "playMethodId",required = false) Long playMethodId){
		if(playMethodId != null){
			MatchPlayMethod matchPlayMethod = matchPlayMethodService.selectById(playMethodId);
			model.addAttribute("matchPlayMethod", matchPlayMethod);
		}
		return "webSite/saveOrUpdatePlayMethodConfig";
	}
	
	@SystemLog(description="编辑玩法配置")
	@RequestMapping(value="/saveOrUpdatePlayMethodConfig")
	public @ResponseBody Map<String, Object> saveOrUpdatePlayMethodConfig(@ModelAttribute("matchPlayMethod") MatchPlayMethod matchPlayMethod){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			if(matchPlayMethod.getId() == null){
				matchPlayMethod = matchPlayMethodService.buildMatchPlayMethod(matchPlayMethod);
				matchPlayMethodService.insert(matchPlayMethod);
			}else{
				matchPlayMethodService.update(matchPlayMethod);
			}
			map.put("result", 0);
			map.put("message", "保存成功.");
			map.put("matchPlayMethod", matchPlayMethod);
		} catch (Exception e) {
			map.put("result", 1);
			map.put("message", "保存失败："+e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	@SystemLog(description="删除玩法配置")
	@RequestMapping(value="/deletePlayMethodConfig")
	public @ResponseBody Map<String, Object> deletePlayMethodConfig(@RequestParam("playMethodId") Long playMethodId){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			if(playMethodId == null){
				map.put("result", 1);
				map.put("message", "删除失败，matchPlayMethod.getId()为空");
			}else{
				MatchCondition condition = new MatchCondition();
				condition.setPlayMethodId(playMethodId);
				List<MatchDetail> details = matchDetailService.selectList(condition);
				if(details != null && details.size() > 0){
					map.put("result", 2);
					map.put("message", "当前玩法已被投注计划使用.");
				}else{
					matchPlayMethodService.deleteById(playMethodId);
					map.put("result", 0);
					map.put("message", "删除成功.");
				}
			}
		} catch (Exception e) {
			map.put("result", 3);
			map.put("message", "删除失败："+e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value="/toAdminAccountConfig")
	public String toAdminAccountConfig(Model model){
		List<AdminAccountInfo> accountInfos = adminAccountInfoService.selectList(new AdminAccountInfoCondition());
		for(AdminAccountInfo info : accountInfos){
			if(info.getId().longValue() == 1L){
				model.addAttribute("bank", info);
			}else if(info.getId().longValue() == 2L){
				model.addAttribute("alipay", info);
			}else if(info.getId().longValue() == 3L){
				model.addAttribute("wechat", info);
			}
		}
		return "webSite/adminAccountConfig";
	}
	
	@SystemLog(description="修改收款账户配置")
	@RequestMapping(value="/updateAdminAccountConfig")
	public @ResponseBody Map<String, Object> updateAdminAccountConfig(Model model,@ModelAttribute("adminAccountInfoForm") AdminAccountInfoForm adminAccountInfoForm){
		Map<String,Object> map = new HashMap<>();
		try {
			AdminAccountInfo bank = adminAccountInfoForm.getBank();
			AdminAccountInfo alipay = adminAccountInfoForm.getAlipay();
			AdminAccountInfo wechat = adminAccountInfoForm.getWechat();
			
			if(bank != null){
				adminAccountInfoService.update(bank);
			}
			if(alipay != null){
				adminAccountInfoService.update(alipay);
			}
			if(wechat != null){
				adminAccountInfoService.update(wechat);
			}
			map.put("result", 0);
			map.put("message", "保存成功");
		} catch (Exception e) {
			map.put("result", 1);
			map.put("message", "保存失败："+e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value="/systemInfo")
	public String systemInfo(){
		return "webSite/systemInfo";
	}
}
