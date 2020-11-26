package com.hrms.mapper;

import com.hrms.bean.Erp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ErpMapper {

    int insertErp(Erp erp);
    List<Erp> selectErp(String erp);
    int deleteErp(String erp);

    Erp selectErpByName(String erp);

    int updateErp(Erp erp);

    List<Erp> selectErpByLimitAndOffset(@Param("offset") int offset,
                                        @Param("limit") int limit);
    int selectErpNum();

    List<Erp> selectErpByPass(@Param("erp") String erp);

}
