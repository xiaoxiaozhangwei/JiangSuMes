package com.hrms.service;

import com.hrms.bean.Baozhuang;
import com.hrms.bean.Storage;
import com.hrms.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StorageService {
    @Autowired
    private StorageMapper storageMapper;


    public  Integer insertStorage(Storage storage)
    {
        return  storageMapper.insertStorage(storage);
    }

    public  void insertStorage(List<Baozhuang> count, Storage storage) throws Exception {

      insert(count,storage);

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void insert(List<Baozhuang> count,Storage storage) throws Exception
    {
        for (int i=0;i<count.size();i++)
        {
           // storage=new Storage(count.get(i).getProductionSN(),count.get(i).getMoId(),count.get(i).getModel(),
            //        count.get(i).getNboxId(),count.get(i).getWboxId(),count.get(i).getOperator(),count.get(i).getTime());
            //storages.add(storage);
            //System.out.println(storage);
          //  storageMapper.insertStorage(storage);
        }


    }


    public  List<Storage>  selectSNByMo(String mo,String d)
    {
        return storageMapper.selectSNByMo(mo,d);
    }

    //根据入库单查询导出SN号
    public  List<Storage>  selectsnByStorageMo(String mo,String d)
    {
        return storageMapper.selectsnByStorageMo(mo,d);
    }

    //出货查询使用： 根据外箱号去入库数据库查询mo,model num，fw和仓储位置等信息
    public Storage selectWaiExist(String wbox)
    {
        return storageMapper.selectWaiExist(wbox);
    }

    //根据外箱号去入库数据库查询是否重复
    public Storage selectWaiExistByStorage(String wbox)
    {
        return storageMapper.selectWaiExistByStorage(wbox);
    }

    //查询工单是否存在
    public  List<Storage> selectMoExist(String mo,String d)
    {
        return  storageMapper.selectMoExist(mo,d);
    }

    // 根据工单查询外箱信息
    public List<Storage> selectsnByMo(String mo,String d){
        return storageMapper.selectsnByMo(mo, d);
    }

    //出库之后，入库数量更改
    public  int updateStorageByNum(String wbox, int num)
    {
        return  storageMapper.updateStorageByNum(wbox,num);
    }


    //根据入库单，查询出工单
    public   String selectMoByStorageMo(String storageMo)
    {
        return  storageMapper.selectMoByStorageMo(storageMo);
    }

    // 在入库时往成品仓记录表中添加记录
    public Integer insertRecord(String erp, String snRecord, String stock, String operator, String wboxRecord) {
        return storageMapper.insertRecord(erp,snRecord,stock,operator,wboxRecord);
    }

    //拆箱和合箱 删除旧入库外箱
    public  Integer deleteByWbox(String wbox)
    {
        return  storageMapper.deleteByWbox(wbox);
    }

    // 出库时，从入库表中根据外箱号查询该外箱号所在的展板信息
    public String getStock(String wbox) {
        return storageMapper.getStock(wbox);
    }

    // 拆箱和合箱时，先往记录表中插入数据,先查询入库表中是否有该外箱
    public Storage selectByWbox(String wbox) {
        return storageMapper.selectByWbox(wbox);
    }
}
