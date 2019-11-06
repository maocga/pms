package com.skk.user.controller;

import com.skk.user.bean.Sources;
import com.skk.user.service.SourcesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/sources")
public class SourcesController {

    @Resource
    private SourcesService sourcesService;

    @RequestMapping(value = "/sourcesList",method = RequestMethod.POST)
    @ResponseBody
    public List<Sources> getSources(){
        List<Sources> sources = sourcesService.findSourcesByPid(0);

        for (Sources source : sources) {
            findChildrenSources(source);
        }
        return sources;
    }

    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    public String saveInfo(Sources sources){
        sourcesService.insert(sources);
        return "redirect:/pm.jsp";
    }

    private void findChildrenSources(Sources source) {

        Integer id = source.getId();

        List<Sources> sources = sourcesService.findSourcesByPid(id);
        source.setChildren(sources);
        for (Sources source1 : sources) {
            findChildrenSources(source1);
        }

    }

}
