package com.quincy.mybatis.service;


import com.quincy.mybatis.dao.StudentMapper;
import com.quincy.mybatis.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * @FileName: StudentService
 * @author: quincy
 * @Date: 2021/6/23 下午2:29
 * @History:
 * 1.select * from student 的执行流程
 * 2.#{}和${}被解析为什么
 */

public class StudentService {

    private SqlSession sqlSession;


    @Before
    public void before(){
        try {
            //解析配置文件并且生成SqlSessionFactoryBuilder
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(inputStream);
             sqlSession = factory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   /* @Test
    public void testFindAllStudent(){
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> allStudent = mapper.findAllStudent();
        System.out.println(allStudent);

    }

    @Test
    public void testFindById(){
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.findById(2);
        System.out.println(student);
    }
*/
    @Test
    public void testFindByName(){
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> student = mapper.findByName("name");
        System.out.println(student);
    }


}
