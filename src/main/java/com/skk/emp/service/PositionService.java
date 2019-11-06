package com.skk.emp.service;

import com.skk.emp.bean.Position;
import com.skk.emp.dao.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionMapper positionMapper;

    public Position findPosById(Integer id) {
        Position position = positionMapper.selectById(id);
        return position;
    }

    public List<Position> findPositionAll() {
        return positionMapper.selectAllPosition();
    }
}
