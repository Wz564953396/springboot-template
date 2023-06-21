package com.wz.example.template.service.impl;

import com.wz.example.template.entity.User;
import com.wz.example.template.mapper.UserMapper;
import com.wz.example.template.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zz_Wang
 * @since 2023-01-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    public void testJvm() {
        byte i = 15;
        int j = 8;
        int k = i + j;
    }

//  javap -v UserServiceImpl.class
    /*
    public void testJvm();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=4, args_size=1
         0: bipush        15
         2: istore_1
         3: bipush        8
         5: istore_2
         6: iload_1
         7: iload_2
         8: iadd
        Start  Length  Slot  Name   Signature
            0      11     0  this   Lcom/wz/example/template/service/impl/UserServiceImpl;
            3       8     1     i   B
            6       5     2     j   I
           10       1     3     k   I

    */

}
