package com.hrms.service;

import com.hrms.bean.Gongdan;
import com.hrms.mapper.GongdanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GongdanService {
    @Autowired
    GongdanMapper gongdanMapper;

    public Gongdan selectmo(String moId) {
        return  gongdanMapper.selectmo(moId);

    }

    public Gongdan getMoById(String moId) {
        return gongdanMapper.getMoById(moId);
    }

    public Gongdan getMoById0(String moId) {
        return gongdanMapper.getMoById0(moId);
    }

    public Integer update_moremain0(String moId){
        return gongdanMapper.update_moremain0(moId);
    }


    public  int  addMo(Gongdan gongdan)
    {
        return gongdanMapper.addMo(gongdan);
    }

    public  String checkMo(String moId)
    {
        return gongdanMapper.checkMo(moId);
    }
    public List<Gongdan> selectMoAll()
    {
        return gongdanMapper.selectMoAll();
    }

    public  int deleteMo(String moId)
    {
        return gongdanMapper.deleteMo(moId);
    }

    public int  updateMo(String moId,Gongdan gongdan){
        return  gongdanMapper.updateMo(moId,gongdan);
    }

    public List <Gongdan> selectmoid(){
        return gongdanMapper.selectmoid();
    }


    public  List<Gongdan> selectByLimitAndOffset(int offset,int limit)
    {return gongdanMapper.selectByLimitAndOffset(offset,limit);}
    public int selectMoNum()
    {
        return  gongdanMapper.selectMoNum();
    }
}
