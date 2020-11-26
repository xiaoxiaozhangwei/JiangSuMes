package com.hrms.bean;

public class Shipment {

    private  String wbox;                   //外箱号
    private  Integer    num;                    //出货数量
    private  String productSpecifications;  //产品规格
    private  String applicant;              //申请人
    private  String customer;                //终端客户
    private  String shipmentNature;         //出货性质
    private  String receivingInformation;   //收件信息（地址+人力+电话）
    private  String erpItemNo;               //ERP料号
    private  String erpOddNumbers;           //ERP单号
    private  String oa;                     //审批单（OA）
    private  String logisticsNumber;         //物流单号
    private  String comments;                //备注
    private  String operator;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Shipment(String wbox, Integer num, String productSpecifications, String applicant, String customer, String shipmentNature, String receivingInformation, String erpItemNo, String erpOddNumbers, String oa, String logisticsNumber, String comments, String operator) {
        this.wbox = wbox;
        this.num = num;
        this.productSpecifications = productSpecifications;
        this.applicant = applicant;
        this.customer = customer;
        this.shipmentNature = shipmentNature;
        this.receivingInformation = receivingInformation;
        this.erpItemNo = erpItemNo;
        this.erpOddNumbers = erpOddNumbers;
        this.oa = oa;
        this.logisticsNumber = logisticsNumber;
        this.comments = comments;
        this.operator = operator;
    }

    public Shipment(String wbox, Integer num, String productSpecifications, String applicant, String customer, String shipmentNature, String receivingInformation, String erpItemNo, String erpOddNumbers, String oa, String logisticsNumber, String comments) {
        this.wbox = wbox;
        this.num = num;
        this.productSpecifications = productSpecifications;
        this.applicant = applicant;
        this.customer = customer;
        this.shipmentNature = shipmentNature;
        this.receivingInformation = receivingInformation;
        this.erpItemNo = erpItemNo;
        this.erpOddNumbers = erpOddNumbers;
        this.oa = oa;
        this.logisticsNumber = logisticsNumber;
        this.comments = comments;
    }

    public Shipment() {
    }

    public String getWbox() {
        return wbox;
    }

    public void setWbox(String wbox) {
        this.wbox = wbox;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getProductSpecifications() {
        return productSpecifications;
    }

    public void setProductSpecifications(String productSpecifications) {
        this.productSpecifications = productSpecifications;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getShipmentNature() {
        return shipmentNature;
    }

    public void setShipmentNature(String shipmentNature) {
        this.shipmentNature = shipmentNature;
    }

    public String getReceivingInformation() {
        return receivingInformation;
    }

    public void setReceivingInformation(String receivingInformation) {
        this.receivingInformation = receivingInformation;
    }

    public String getErpItemNo() {
        return erpItemNo;
    }

    public void setErpItemNo(String erpItemNo) {
        this.erpItemNo = erpItemNo;
    }

    public String getErpOddNumbers() {
        return erpOddNumbers;
    }

    public void setErpOddNumbers(String erpOddNumbers) {
        this.erpOddNumbers = erpOddNumbers;
    }

    public String getOa() {
        return oa;
    }

    public void setOa(String oa) {
        this.oa = oa;
    }

    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "shipment{" +
                "wbox='" + wbox + '\'' +
                ", num=" + num +
                ", productSpecifications='" + productSpecifications + '\'' +
                ", applicant='" + applicant + '\'' +
                ", customer='" + customer + '\'' +
                ", shipmentNature='" + shipmentNature + '\'' +
                ", receivingInformation='" + receivingInformation + '\'' +
                ", erpItemNo='" + erpItemNo + '\'' +
                ", erpOddNumbers='" + erpOddNumbers + '\'' +
                ", oa='" + oa + '\'' +
                ", logisticsNumber='" + logisticsNumber + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
