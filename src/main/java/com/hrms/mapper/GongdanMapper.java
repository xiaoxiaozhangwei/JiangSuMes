package com.hrms.mapper;

import com.hrms.bean.Gongdan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GongdanMapper {
  Gongdan selectmo(String moId);

  Gongdan getMoById(String moId);

    Gongdan getMoById0(String moId);
  Integer update_moremain0(String moId);

  int addMo(Gongdan gd);
  String checkMo(String moId);

  List<Gongdan> selectMoAll();
  int  deleteMo(String moId);
  int updateMo(@Param("moId") String moId, @Param("gongdan") Gongdan gongdan);

  List<Gongdan> selectmoid();


  List<Gongdan> selectByLimitAndOffset(@Param("offset") int offset,
                                       @Param("limit") int limit);
  int selectMoNum();
}
