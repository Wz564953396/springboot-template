package com.wz.example.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wz.example.template.entity.ClientUser;
import com.wz.example.template.entity.TUser;
import com.wz.example.template.mapper.TUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service("UserDetailsService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private TUserMapper userMapper;


    public static void main(String[] args) {
        String password = new BCryptPasswordEncoder().encode("123456");
        System.out.println(password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = new User("zhoudi", new BCryptPasswordEncoder().encode("5250"), grantedAuthorityList);
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TUser::getUsername, username);
        TUser user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        ClientUser clientUser = new ClientUser();
        BeanUtils.copyProperties(user, clientUser);
        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthority() + "," + user.getRole());
        System.out.println(user);
        clientUser.setAuthorities(new HashSet<GrantedAuthority>(grantedAuthorityList));
        return clientUser;
    }
}
