package com.skk.emp.service;

import com.skk.emp.bean.BaoXiao;
import com.skk.emp.bean.BaoXiaoExample;
import com.skk.emp.dao.BaoXiaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaoxiaoService {

    @Autowired
    private BaoXiaoMapper baoXiaoMapper;

    public List<BaoXiao> findBaoxiaoListByEid(Integer eid, Integer sc, String keyword) {
        BaoXiaoExample example = new BaoXiaoExample();
        BaoXiaoExample.Criteria criteria = example.createCriteria();

        if (sc == 1){
            criteria.andPaymodeLike("%"+keyword+"%");
        }else if (sc == 2){
            criteria.andBxremarkLike("%"+keyword+"%");
        }else {
            BaoXiaoExample example1 = new BaoXiaoExample();
            BaoXiaoExample.Criteria criteria1 = example.createCriteria();
            criteria1.andBxremarkLike("%"+keyword+"%");
            criteria.andPaymodeLike("%"+keyword+"%");
            example.or(criteria1);
        }

        criteria.andEmpFkEqualTo(eid);
        List<BaoXiao> list = baoXiaoMapper.selectByExample(example);
        return list;
    }

    public boolean addBaoxiao(BaoXiao baoXiao) {
        int i = baoXiaoMapper.insertSelective(baoXiao);
        if (i > 0){
            return true;
        }
        return false;
    }

    public int countAll() {
        BaoXiaoExample example = new BaoXiaoExample();
        return baoXiaoMapper.countByExample(example);
    }
}
