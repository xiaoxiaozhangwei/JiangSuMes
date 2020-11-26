package com.hrms.service;

import com.hrms.bean.Zuzhuang;
import com.hrms.mapper.ZuzhuangMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZuzhuangService {
    @Autowired
    ZuzhuangMapper zuzhuangMapper;
    public int addlink(Zuzhuang zuzhuang){
        return zuzhuangMapper.insertOne(zuzhuang);
    }


    public Integer updatemo(String moId){
            return zuzhuangMapper.updatemo(moId);
        }

    public int updatedevice(Zuzhuang zuzhuang){
            return zuzhuangMapper.updatedevice(zuzhuang);
        }

    public Zuzhuang getProByName(String productionSN,String mo) {
        return zuzhuangMapper.selectProByName(productionSN,mo);
    }
    public Zuzhuang getPcba(String pcbaSN,String moid){
        return zuzhuangMapper.selectPcba(pcbaSN,moid);
    }

    public List<Zuzhuang> selectlink(Zuzhuang zuzhuang){
        return zuzhuangMapper.selectlink(zuzhuang);
    }

    public List<Zuzhuang> selectAll(Zuzhuang zuzhuang){
        return zuzhuangMapper.selectAll(zuzhuang);
    }

    public Integer selectcount(){
        return zuzhuangMapper.selectcount();
    }

    public   Integer selectLinkbyline(String line)
    {
        return  zuzhuangMapper.selectLinkbyline(line);
    }
}

