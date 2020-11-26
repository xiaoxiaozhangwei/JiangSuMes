package com.hrms.service;

import com.hrms.bean.Email;
import com.hrms.mapper.EmailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    EmailMapper emailMapper;


    public  int insertEmail(Email email)
    {

        return  emailMapper.insertEmail(email);
    }

    public List<Email>  selectEmail(String groupEmail)
    {
        return  emailMapper.selectEmail(groupEmail);
    }


   public int deleteEmail(String account)
   {

       return  emailMapper.deleteEmail(account);
   }
    public int deleteEmail2(String name)
    {

        return  emailMapper.deleteEmail2(name);
    }
}
