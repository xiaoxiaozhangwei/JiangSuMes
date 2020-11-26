package com.hrms.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Zuzhuang {
    private Integer linkId;//编号
    private String productionSN;//产品SN号
    private String pcbaSN;//PCBA SN号
    private String operation;//操作信息（正确or错误）
    private Integer times;//设备使用次数
    private String operator;//操作员
    private String getTime;//作业时间
    private Integer deviceId;//设备号码
    private String moId;//工单号
    private String devicename;//机种名称
    private Integer monum;//工单量
    private Integer moremain;//工单剩余量
    private String gys;//PCAB供应商
    private  String comments;
    private  String line;

    private String lenovoSN;//联想SN
    private String pn;//联想PN
    private String erp;//erp




    private  String  surfaceMo;
    private  String  surfacePurchase;
    private  String  surfaceErp;
    private  String  surfaceDC;
    //
    private  String  bottomMo;
    private  String  bottomPurchase;
    private  String  bottomErp;
    private  String  bottomDC;
    //


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  Date  bottomTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private   Date  surfaceTime;

    public Date getBottomTime() {
        return bottomTime;
    }

    public void setBottomTime(Date bottomTime) {
        this.bottomTime = bottomTime;
    }

    public Date getSurfaceTime() {
        return surfaceTime;
    }

    public void setSurfaceTime(Date surfaceTime) {
        this.surfaceTime = surfaceTime;
    }

    public String getSurfaceMo() {
        return surfaceMo;
    }

    public void setSurfaceMo(String surfaceMo) {
        this.surfaceMo = surfaceMo;
    }

    public String getSurfacePurchase() {
        return surfacePurchase;
    }

    public void setSurfacePurchase(String surfacePurchase) {
        this.surfacePurchase = surfacePurchase;
    }

    public String getSurfaceErp() {
        return surfaceErp;
    }

    public void setSurfaceErp(String surfaceErp) {
        this.surfaceErp = surfaceErp;
    }

    public String getSurfaceDC() {
        return surfaceDC;
    }

    public void setSurfaceDC(String surfaceDC) {
        this.surfaceDC = surfaceDC;
    }



    public String getBottomMo() {
        return bottomMo;
    }

    public void setBottomMo(String bottomMo) {
        this.bottomMo = bottomMo;
    }

    public String getBottomPurchase() {
        return bottomPurchase;
    }

    public void setBottomPurchase(String bottomPurchase) {
        this.bottomPurchase = bottomPurchase;
    }

    public String getBottomErp() {
        return bottomErp;
    }

    public void setBottomErp(String bottomErp) {
        this.bottomErp = bottomErp;
    }

    public String getBottomDC() {
        return bottomDC;
    }

    public void setBottomDC(String bottomDC) {
        this.bottomDC = bottomDC;
    }


    public Zuzhuang() {
    }

    /*

    public Zuzhuang(Integer linkId, String productionSN, String pcbaSN, String operation, Integer times, String operator, String getTime,  String moId, String devicename, Integer monum, Integer moremain, String gys, String line,Integer deviceId) {
        this.linkId = linkId;
        this.productionSN = productionSN;
        this.pcbaSN = pcbaSN;
        this.operation = operation;
        this.times = times;
        this.operator = operator;
        this.getTime = getTime;
        this.deviceId = deviceId;
        this.moId = moId;
        this.devicename = devicename;
        this.monum = monum;
        this.moremain = moremain;
        this.gys = gys;
        this.line = line;
    }
        * /




    /*

    public Zuzhuang(Integer linkId, String productionSN, String pcbaSN, String operation, Integer times, String operator, String getTime, Integer deviceId, String moId, String devicename, Integer monum, Integer moremain, String gys, String comments) {
        this.linkId = linkId;
        this.productionSN = productionSN;
        this.pcbaSN = pcbaSN;
        this.operation = operation;
        this.times = times;
        this.operator = operator;
        this.getTime = getTime;
        this.deviceId = deviceId;
        this.moId = moId;
        this.devicename = devicename;
        this.monum = monum;
        this.moremain = moremain;
        this.gys = gys;
        this.comments = comments;
    }
 */

    public Zuzhuang(Integer linkId, String productionSN, String pcbaSN, String operation, Integer times, String operator, String getTime, Integer deviceId, String moId, String devicename, Integer monum, Integer moremain, String gys, String comments, String line) {
        this.linkId = linkId;
        this.productionSN = productionSN;
        this.pcbaSN = pcbaSN;
        this.operation = operation;
        this.times = times;
        this.operator = operator;
        this.getTime = getTime;
        this.deviceId = deviceId;
        this.moId = moId;
        this.devicename = devicename;
        this.monum = monum;
        this.moremain = moremain;
        this.gys = gys;
        this.comments = comments;
        this.line = line;
    }


    /*

    public Zuzhuang(Integer linkId, String productionSN, String operation, String operator, String getTime, String moId, String devicename, String comments, String lenovoSN, String pn, String erp) {
        this.linkId = linkId;
        this.productionSN = productionSN;
        this.operation = operation;
        this.operator = operator;
        this.getTime = getTime;
        this.moId = moId;
        this.devicename = devicename;
        this.comments = comments;
        this.lenovoSN = lenovoSN;
        this.pn = pn;
        this.erp = erp;
    }

 * */

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLenovoSN() {
        return lenovoSN;
    }

    public void setLenovoSN(String lenovoSN) {
        this.lenovoSN = lenovoSN;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getErp() {
        return erp;
    }

    public void setErp(String erp) {
        this.erp = erp;
    }
    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getProductionSN() {
        return productionSN;
    }

    public void setProductionSN(String productionSN) {
        this.productionSN = productionSN;
    }

    public String getPcbaSN() {
        return pcbaSN;
    }

    public void setPcbaSN(String pcbaSN) {
        this.pcbaSN = pcbaSN;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getMoId() {
        return moId;
    }

    public void setMoId(String moId) {
        this.moId = moId;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public Integer getMonum() {
        return monum;
    }

    public void setMonum(Integer monum) {
        this.monum = monum;
    }

    public Integer getMoremain() {
        return moremain;
    }

    public void setMoremain(Integer moremain) {
        this.moremain = moremain;
    }

    public String getGys() {
        return gys;
    }

    public void setGys(String gys) {
        this.gys = gys;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
/*

    @Override
    public String toString() {
        return "Zuzhuang{" +
                "linkId=" + linkId +
                ", productionSN='" + productionSN + '\'' +
                ", pcbaSN='" + pcbaSN + '\'' +
                ", operation='" + operation + '\'' +
                ", times=" + times +
                ", operator='" + operator + '\'' +
                ", getTime='" + getTime + '\'' +
                ", deviceId=" + deviceId +
                ", moId='" + moId + '\'' +
                ", devicename='" + devicename + '\'' +
                ", monum=" + monum +
                ", moremain=" + moremain +
                ", gys='" + gys + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
* */

    @Override
    public String toString() {
        return "Zuzhuang{" +
                "linkId=" + linkId +
                ", productionSN='" + productionSN + '\'' +
                ", pcbaSN='" + pcbaSN + '\'' +
                ", operation='" + operation + '\'' +
                ", times=" + times +
                ", operator='" + operator + '\'' +
                ", getTime='" + getTime + '\'' +
                ", deviceId=" + deviceId +
                ", moId='" + moId + '\'' +
                ", devicename='" + devicename + '\'' +
                ", monum=" + monum +
                ", moremain=" + moremain +
                ", gys='" + gys + '\'' +
                ", comments='" + comments + '\'' +
                ", line='" + line + '\'' +
                ", lenovoSN='" + lenovoSN + '\'' +
                ", pn='" + pn + '\'' +
                ", erp='" + erp + '\'' +
                ", surfaceMo='" + surfaceMo + '\'' +
                ", surfacePurchase='" + surfacePurchase + '\'' +
                ", surfaceErp='" + surfaceErp + '\'' +
                ", surfaceDC='" + surfaceDC + '\'' +
                ", surfaceTime='" + surfaceTime + '\'' +
                ", bottomMo='" + bottomMo + '\'' +
                ", bottomPurchase='" + bottomPurchase + '\'' +
                ", bottomErp='" + bottomErp + '\'' +
                ", bottomDC='" + bottomDC + '\'' +
                ", bottomTime='" + bottomTime + '\'' +
                '}';
    }


    public Zuzhuang(String productionSN, String pcbaSN) {
        this.productionSN = productionSN;
        this.pcbaSN = pcbaSN;
    }
}