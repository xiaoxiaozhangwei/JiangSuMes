package com.hrms.mapper;

import com.hrms.bean.Storage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StorageMapper {


   Integer  insertStorage(Storage storage);

   List<Storage> selectSNByMo(@Param("mo") String mo, @Param("d") String d);


   //根据入库单查询导出SN号
   List<Storage> selectsnByStorageMo(@Param("storageMo") String storageMo, @Param("d") String d);

   //出货查询使用： 根据外箱号去入库数据库查询mo,model num，fw和仓储位置等信息
   Storage selectWaiExist(String wbox);

   //根据外箱号去入库数据库查询是否重复
   Storage selectWaiExistByStorage(String wbox);

   List<Storage> selectMoExist(@Param("mo") String mo, @Param("d") String d);

   List<Storage> selectsnByMo(@Param("mo") String mo, @Param("d") String d);

   //出库之后，入库数量更改
   int updateStorageByNum(@Param("wbox") String wbox, @Param("num") int num);

    //根据入库单，查询出工单-->
   String selectMoByStorageMo(String storageMo);

   // 在入库时往成品仓记录表中添加记录
    Integer insertRecord(@Param("erp")String erp,@Param("snRecord")String snRecord,@Param("stock") String stock,
                         @Param("operator")String operator,@Param("wboxRecord")String wboxRecord);

   //拆箱和合箱 删除旧入库外箱
   Integer deleteByWbox(String wbox);

   // 出库时，从入库表中根据外箱号查询该外箱号所在的展板信息
    String getStock(String wbox);

   // 拆箱和合箱时，先往记录表中插入数据
   Storage selectByWbox(String wbox);
}
