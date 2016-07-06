package com.yu.web.base.service.impl;

import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.web.base.mapper.RoleMapper;
import com.yu.web.base.model.Role;
import com.yu.web.base.service.RoleService;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {

    //@Resource
    private RoleMapper roleMapper;

    @Override
    public GenericMapper<Role, Long> getMapper() {
        return roleMapper;
    }

}
