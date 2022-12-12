package com.wz.example.template.service.impl;

import com.wz.example.template.entity.TUser;
import com.wz.example.template.mapper.TUserMapper;
import com.wz.example.template.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zz_Wang
 * @since 2022-12-12
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}
