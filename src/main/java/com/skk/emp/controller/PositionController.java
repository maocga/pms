package com.skk.emp.controller;

import com.skk.emp.bean.Position;
import com.skk.emp.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @RequestMapping(value = "findAllPosition",method = RequestMethod.GET)
    @ResponseBody
    public List<Position> findAllPosition(){
        return positionService.findPositionAll();
    }

}
