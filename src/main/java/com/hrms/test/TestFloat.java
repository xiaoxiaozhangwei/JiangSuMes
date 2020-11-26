package com.hrms.test;

import org.junit.Test;

import java.util.Date;

public class TestFloat {

    @Test
    public void test1(){
        float rightpercent1;
        int rightnum1 = 100;
        int wrongnum1 = 200;
        if ((rightnum1+wrongnum1)==0){
            rightpercent1=0;
            System.out.println(rightpercent1);
        }else{
            System.out.println(new Date());
            rightpercent1=((float)rightnum1/((float)rightnum1+(float)wrongnum1))*100;
            System.out.println(new Date());
            System.out.println(rightpercent1);
        }
    }
}
