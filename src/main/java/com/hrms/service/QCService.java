package com.hrms.service;

import com.hrms.bean.QC;
import com.hrms.mapper.QCMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QCService {

    @Autowired
    QCMapper qcMapper;


     public int  insertQC(QC qc)
        {
            return  qcMapper.insertQC(qc);
        }
    public  int  selectQCpass()
       {
        return  qcMapper.selectQCpass();
       }
   public int  selectQCNG()
       {
       return qcMapper.selectQCNG();
       }

}
