package com.lzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;
import com.lzy.dto.SearchUserDto;
import com.lzy.error.BusinessException;
import com.lzy.mapper.SysPermMapper;
import com.lzy.mapper.SysRoleMapper;
import com.lzy.mapper.SysUserMapper;
import com.lzy.page.PageQuery;
import com.lzy.pojo.SysPerm;
import com.lzy.pojo.SysRole;
import com.lzy.pojo.SysUser;
import com.lzy.service.SysUserService;
import com.lzy.utils.SaltUtils;
import com.lzy.validator.ValidatorApi;
import com.lzy.viewobject.PersonalVo;
import com.lzy.viewobject.SearchUserVo;
import com.lzy.viewobject.SysUserVo;
import com.lzy.viewobject.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private ValidatorApi validator;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysPermMapper sysPermMapper;

    // 根据角色id返回用户列表
    @Override
    public IPage<SysUser> userPageByRoleId(int roleId, SearchUserVo userVo, PageQuery pageQuery) {
        // 注解检验
        validator.check(pageQuery);
        // 将vo赋值给dto
        SearchUserDto dto = new SearchUserDto();
        if (StringUtils.isNotBlank(userVo.getKeyword())){
            dto.setKeyword(userVo.getKeyword());
        }
        if (StringUtils.isNotBlank(userVo.getSearch_status())){
            dto.setSearch_status(Integer.parseInt(userVo.getSearch_status()));
        }
        // mybatis-plus 的查询条件
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(String.valueOf(roleId))){
            queryWrapper.eq("role_id", roleId);
        }
        if (StringUtils.isNotBlank(dto.getKeyword())){
            // 模糊查询 %name%
            queryWrapper.like("username", dto.getKeyword()).or()
                    .like("telephone", dto.getKeyword()).or()
                    .like("mail", dto.getKeyword());
        }
        if (StringUtils.isNotBlank(String.valueOf(dto.getSearch_status()))){
            queryWrapper.eq("user_status", dto.getSearch_status());
        }
        // 统计符合条件的数据
        int count = sysUserMapper.selectCount(queryWrapper);
        IPage<SysUser> page = new Page<>(pageQuery.getPageNo(), pageQuery.getPageSize());
        if (count > 0){
            page = sysUserMapper.selectPage(page,queryWrapper);
            return page;
        }
        return page;
    }

    // 更新用户
    @Override
    public void updateUser(SysUserVo userVo) {
        // 注解检验
        validator.check(userVo);
        // 根据id查询用户是否存在
        SysUser before = sysUserMapper.selectById(userVo.getId());
        Preconditions.checkNotNull(before, "待更新的用户不存在！");
        // 手机号去重
        if (checkTelephoneExist(userVo.getTelephone(), userVo.getId())){
            throw  new BusinessException("该手机号已经存在！");
        }
        // 邮箱去重
        if (checkMailExist(userVo.getMail(), userVo.getId())){
            throw new BusinessException("该邮箱已经存在！");
        }
        // 建造者模式
        SysUser entity = SysUser.builder().id(userVo.getId()).username(userVo.getUsername())
                .roleId(userVo.getRoleId()).telephone(userVo.getTelephone()).mail(userVo.getMail())
                .userStatus(userVo.getUserStatus()).updateTime(new Date()).build();

        sysUserMapper.updateById(entity);
    }

    // 注册用户
    @Override
    public void registerUser(UserVo userVo) {
        validator.check(userVo);
        if (checkTelephoneExist(userVo.getTelephone(), userVo.getId())){
            throw new BusinessException("手机号已经存在！");
        }
        if (checkMailExist(userVo.getMail(), userVo.getId())){
            throw new BusinessException("邮箱已经存在！");
        }
        // 生成随机盐
        String salt = SaltUtils.getSalt(8);
        // 密码加密 md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(userVo.getPassword(), salt, 1024);

        SysUser sysUser = SysUser.builder().username(userVo.getUsername())
                .telephone(userVo.getTelephone()).mail(userVo.getMail())
                .password(md5Hash.toHex()).roleId(6).createTime(new Date())
                .updateTime(new Date()).salt(salt).build();
        // 添加用户
        sysUserMapper.insert(sysUser);
    }

    // 根据身份查找用户
    @Override
    public SysUser findByUserName(String principal) {
        // 查询条件
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(principal)){
            queryWrapper.eq("telephone", principal);
        }
        // 查询
        SysUser user = sysUserMapper.selectOne(queryWrapper);
        return user;
    }

    // 查询角色
    @Override
    public SysRole findRolesByUserName(String primaryPrincipal) {
        // 查询条件
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(primaryPrincipal)){
            queryWrapper.eq("telephone",primaryPrincipal);
        }
        SysUser user = sysUserMapper.selectOne(queryWrapper);

        SysRole role = sysRoleMapper.selectById(user.getRoleId());

        return role;
    }

    // 查询权限
    @Override
    public List<SysPerm> findPermsByRoleId(Integer id) {
        QueryWrapper<SysPerm> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", id);
        List<SysPerm> perms = sysPermMapper.selectList(queryWrapper);
        return perms;
    }

    @Override
    public void updatePersonal(PersonalVo personalVo) {
        validator.check(personalVo);
        if (checkMailExist(personalVo.getMail(),personalVo.getId())){
            throw new BusinessException("邮箱已经存在！");
        }
        if (checkTelephoneExist(personalVo.getTelephone(), personalVo.getId())){
            throw new BusinessException("手机号已经存在！");
        }

        SysUser entity = SysUser.builder().id(personalVo.getId()).username(personalVo.getUsername())
                .telephone(personalVo.getTelephone()).mail(personalVo.getMail())
                .updateTime(new Date()).build();

        if (StringUtils.isNotBlank(personalVo.getPassword())){
            // 生成随机盐
            String salt = SaltUtils.getSalt(8);
            // 密码加密 md5 + salt + hash散列
            Md5Hash md5Hash = new Md5Hash(personalVo.getPassword(), salt, 1024);
            entity.setPassword(md5Hash.toHex());
            entity.setSalt(salt);
        }
        sysUserMapper.updateById(entity);

    }

    // 手机号去重
    private boolean checkTelephoneExist(String tel, Integer id){
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(tel)){
            queryWrapper.eq("telephone", tel);
        }
        if (null != id){
            queryWrapper.ne("id", id);
        }
        return sysUserMapper.selectCount(queryWrapper) > 0;
    }

    // 邮箱去重
    private boolean checkMailExist(String mail, Integer id){
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(mail)){
            queryWrapper.eq("mail", mail);
        }
        if (null != id){
            queryWrapper.ne("id", id);
        }
        return sysUserMapper.selectCount(queryWrapper) > 0;
    }

}
