package com.hrms.service;

import com.hrms.bean.Erp;
import com.hrms.mapper.ErpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ErpService {
    @Autowired
    ErpMapper erpMapper;

    public  int insertErp(Erp erp)
    {
        return  erpMapper.insertErp(erp);
    }

    public List<Erp> selectErp(String erp)
    {
        return  erpMapper.selectErp(erp);
    }
    public  int deleteErp(String erp)
    {
        return  erpMapper.deleteErp(erp);
    }

    public   Erp selectErpByName(String erp)
    {
        return  erpMapper.selectErpByName(erp);
    }

    public   int updateErp(Erp erp)
    {
        return  erpMapper.updateErp(erp);
    }

    public  List<Erp> selectByLimitAndOffset(int offset, int limit)
    {return erpMapper.selectErpByLimitAndOffset(offset,limit);}
    public int selectErpNum()
    {
        return  erpMapper.selectErpNum();
    }

    //查询审核通过的
    public   List<Erp> selectErpByPass(String erp)
    {
        return  erpMapper.selectErpByPass(erp);
    }
}
