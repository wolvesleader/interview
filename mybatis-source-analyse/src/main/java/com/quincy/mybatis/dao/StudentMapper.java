package com.quincy.mybatis.dao;

import com.quincy.mybatis.pojo.Student;

import java.util.List;

/**
 * Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * FileName: StudentMapper
 *
 * @author: quincy
 * Date: 2021/6/23 下午2:25
 * History:
 */
public interface StudentMapper {

    public List<Student> findByName(String name);

  /*  public List<Student> findAllStudent();

    public Student findById(int id);*/


}
