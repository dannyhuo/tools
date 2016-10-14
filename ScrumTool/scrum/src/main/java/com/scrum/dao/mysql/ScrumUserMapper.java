package com.scrum.dao.mysql;

import java.util.List;

import com.scrum.model.ScrumUser;

public interface ScrumUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(ScrumUser record);

    int insertSelective(ScrumUser record);

    ScrumUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(ScrumUser record);

    int updateByPrimaryKey(ScrumUser record);
    
    List<ScrumUser> selectSelective(ScrumUser user);
}