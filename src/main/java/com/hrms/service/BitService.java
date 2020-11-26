package com.hrms.service;

import com.hrms.bean.Bit;
import com.hrms.mapper.BitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BitService {

    @Autowired
    BitMapper bitMapper;


    public int insertBit(Bit bit)
    {
       return bitMapper.insertBit(bit);
    }


    public int selectBitpass()
    {
        return  bitMapper.selectBitpass();
    }
    public int selectBitNG()
    {
        return  bitMapper.selectBitNG();
    }

    public List<String> selecthismonthmodel(String daytime){
        return bitMapper.selecthismonthmodel(daytime);
    }

    public Integer selectrightcountbymodeldaily(String daytime,String model){
        return bitMapper.selectrightcountbymodeldaily(daytime, model);
    }
    public Integer selectwrongcountbymodeldaily(String daytime,String model){
        return bitMapper.selectwrongcountbymodeldaily(daytime,model);
    }
   public  Integer selectAllNGBymonth(Bit bit)
   {return  bitMapper.selectAllNGBymonth(bit);
   }

    public   Integer selectNGByBitNumberday(String BitNumber, String model, String ErrorCode)
  {
      return  bitMapper.selectNGByBitNumberday(BitNumber,model,ErrorCode);
  }


    public   List<String> selectModelByday(){
        return  bitMapper.selectModelByday();
  }

  public   Integer selectpasscountbymodelandbitnumber(String model, String BitNumber)
    {
        return bitMapper.selectpasscountbymodelandbitnumber(model,BitNumber);
    }
}
