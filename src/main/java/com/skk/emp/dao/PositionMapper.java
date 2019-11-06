package com.skk.emp.dao;

import com.skk.emp.bean.Position;

import java.util.List;


public interface PositionMapper {

    Position selectById(Integer id);

    List<Position> selectAllPosition();
}
