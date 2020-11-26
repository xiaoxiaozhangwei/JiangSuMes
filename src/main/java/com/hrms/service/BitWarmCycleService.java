package com.hrms.service;

import com.hrms.bean.Bit;
import com.hrms.mapper.BitMapper;
import com.hrms.mapper.BitWarmCycleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BitWarmCycleService {

    @Autowired
    BitWarmCycleMapper bitWarmCycleMapper;


    public int insertBitWarmCycle(Bit bit)
    {
       return bitWarmCycleMapper.insertBitWarmCycle(bit);
    }




}
