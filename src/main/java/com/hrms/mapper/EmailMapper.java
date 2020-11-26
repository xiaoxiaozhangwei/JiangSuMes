package com.hrms.mapper;

import com.hrms.bean.Email;

import java.util.List;

public interface EmailMapper {

   int insertEmail(Email email);
   List<Email>  selectEmail(String groupEmail);
   int deleteEmail(String account);

   int deleteEmail2(String name);
}
