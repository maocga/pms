package com.skk.user.service;

import com.skk.user.bean.Sources;
import com.skk.user.bean.SourcesExample;
import com.skk.user.mapper.SourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SourcesService {

    @Resource
    private SourcesMapper sourcesMapper;

    public List<Sources> findSourcesByPid(int id) {
        SourcesExample example = new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(id);
        List<Sources> sources = sourcesMapper.selectByExample(example);
        return sources;

    }

    public void insert(Sources sources) {
        sourcesMapper.insert(sources);
    }
}
