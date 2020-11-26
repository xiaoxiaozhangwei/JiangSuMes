package com.hrms.mapper;

import com.hrms.bean.Baozhuang;

public interface ScanSNPrintMapper {
    Integer getmoremain(String mo_id);

    Integer add_chandesn(Baozhuang baozhuang);
}
