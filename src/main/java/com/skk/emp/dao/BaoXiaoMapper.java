package com.skk.emp.dao;

import java.util.List;

import com.skk.emp.bean.BaoXiao;
import com.skk.emp.bean.BaoXiaoExample;
import org.apache.ibatis.annotations.Param;

public interface BaoXiaoMapper {
    int countByExample(BaoXiaoExample example);

    int deleteByExample(BaoXiaoExample example);

    int deleteByPrimaryKey(String bxid);

    int insert(BaoXiao record);

    int insertSelective(BaoXiao record);

    List<BaoXiao> selectByExample(BaoXiaoExample example);

    BaoXiao selectByPrimaryKey(String bxid);

    int updateByExampleSelective(@Param("record") BaoXiao record, @Param("example") BaoXiaoExample example);

    int updateByExample(@Param("record") BaoXiao record, @Param("example") BaoXiaoExample example);

    int updateByPrimaryKeySelective(BaoXiao record);

    int updateByPrimaryKey(BaoXiao record);
}