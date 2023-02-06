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

}
