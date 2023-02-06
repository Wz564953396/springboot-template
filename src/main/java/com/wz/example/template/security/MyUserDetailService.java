package com.wz.example.template.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wz.example.template.entity.ClientUser;
import com.wz.example.template.entity.Role;
import com.wz.example.template.entity.User;
import com.wz.example.template.entity.UserRole;
import com.wz.example.template.mapper.RoleMapper;
import com.wz.example.template.mapper.UserMapper;
import com.wz.example.template.mapper.UserRoleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("UserDetailsService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    public static void main(String[] args) {
        String password = new BCryptPasswordEncoder().encode("123456");
        System.out.println(password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = new User("zhoudi", new BCryptPasswordEncoder().encode("5250"), grantedAuthorityList);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<UserRole> userRoleList = userRoleMapper.selectList(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, user.getId()));
        List<Role> roleList = new ArrayList<>();
        if (Objects.nonNull(userRoleList)) {
            roleList = roleMapper.selectBatchIds(userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList()));
        }
        user.setRoles(roleList);
        return user;
//        ClientUser clientUser = new ClientUser();
//        BeanUtils.copyProperties(user, clientUser);
//        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthority() + "," + user.getRole());
//        System.out.println(user);
//        clientUser.setAuthorities(new HashSet<GrantedAuthority>(grantedAuthorityList));
//        return clientUser;
    }
}
