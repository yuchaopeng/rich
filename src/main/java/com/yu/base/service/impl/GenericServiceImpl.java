package com.yu.base.service.impl;

import java.util.List;

import com.yu.base.condition.BaseCondition;
import com.yu.base.mapper.GenericMapper;
import com.yu.base.page.Page;
import com.yu.base.page.PageBean;
import com.yu.base.service.GenericService;

/**
 * GenericService的实现类, 其他的自定义 ServiceImpl, 继承自它,可以获得常用的增删查改操作,
 * 未实现的方法有 子类各自实现
 * <p/>
 * Model : 代表数据库中的表 映射的Java对象类型
 * PK :代表对象的主键类型
 *
 * @author StarZou
 * @since 2014年6月9日 下午6:14:06
 */
public abstract class GenericServiceImpl<Model, PK> implements GenericService<Model, PK> {

    /**
     * 定义成抽象方法,由子类实现,完成dao的注入
     *
     * @return GenericDao实现类
     */
    public abstract GenericMapper<Model, PK> getMapper();

	@Override
	public int insert(Model model) {
		return getMapper().insert(model);
	}

	@Override
	public int update(Model model) {
		return getMapper().update(model);
	}

	@Override
	public int deleteById(PK id) {
		return getMapper().deleteById(id);
	}

	@Override
	public Model selectById(PK id) {
		return getMapper().selectById(id);
	}

	@Override
	public List<Model> selectList(BaseCondition condition) {
		return getMapper().selectList(condition);
	}

	@Override
	public Page<Model> select4Page(PageBean pageBean) {
		Page<Model> page = new Page<Model>(pageBean);
		List<Model> results = getMapper().select4Page(page);
		page.setResults(results);
		return page;
	}
}
