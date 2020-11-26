package com.hrms.service;

import com.hrms.bean.Zuzhuang;
import com.hrms.mapper.LenovoLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LenovoLinkService {
    @Autowired
    LenovoLinkMapper lenovoLinkMapper;

    public int insertlenovolink(Zuzhuang zuzhuang){
        return lenovoLinkMapper.insertlenovolink(zuzhuang);
    }

    public List<Zuzhuang> selectProductsnExist(String productionSN){
        return lenovoLinkMapper.selectProductsnExist(productionSN);
    }

    public List<Zuzhuang> selectLenovosnExist(String lenovoSN){
        return lenovoLinkMapper.selectLenovosnExist(lenovoSN);
    }
}
