package com.hzht.mlxc.dao;

import com.hzht.mlxc.dao.entity.AppHome;
import com.hzht.mlxc.dao.entity.AppHomeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
public interface AppHomeMapper {
    long countByExample(AppHomeExample example);

    int deleteByExample(AppHomeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppHome record);

    int insertSelective(AppHome record);

    List<AppHome> selectByExample(AppHomeExample example);

    List<AppHome> selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppHome record, @Param("example") AppHomeExample example);

    int updateByExample(@Param("record") AppHome record, @Param("example") AppHomeExample example);

    int updateByPrimaryKeySelective(AppHome record);

    int updateByPrimaryKey(AppHome record);
}