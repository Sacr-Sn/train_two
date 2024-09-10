package com.sn.demo02.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sn.demo02.domain.dto.UserDTO;
import com.sn.demo02.domain.po.User;
import com.sn.demo02.domain.vo.Result;
import com.sn.demo02.domain.vo.UserVO;
import com.sn.demo02.mapper.UserMapper;
import com.sn.demo02.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sn.demo02.utils.JwtUtil;
import com.sn.demo02.utils.Md5Util;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.tools.BasicCommandLineArguments;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sn
 * @since 2024-09-06
 */
@Service
@Log4j2
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    
    @Override
    public List<User> getUserByName(String username) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername,username);
        log.info("查询username对应的数据:{}",username);
        return userMapper.selectList(userLambdaQueryWrapper);
    }

    @Override
    public boolean addUser(User user) {
        return user != null && userMapper.insert(user) == 1;
    }

    @Override
    public boolean updateUserById(User user) {
        if (user == null || user.getId() == null) {
            return false;
        }
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.eq(User::getId,user.getId());
        return userMapper.update(user,userLambdaUpdateWrapper) == 1;
    }

    @Override
    public boolean eraseUserById(Integer id) {
        return id != null && userMapper.deleteById(id) == 1;
    }

    @Override
    public String register(User user) {
        if (user == null) {
            return "注册失败，用户信息为空！";
        }
        if (user.getUsername() == null || "".equals(user.getUsername()) ||
                "".equals(user.getPassword()) || user.getPassword() == null ||
                "".equals(user.getEmail()) || user.getEmail() == null) {
            return "注册失败，请完整填写信息！";
        }
        if (getUserByUsername(user.getUsername()) != null) {
            return "注册失败，用户名已存在！";
        }
        log.info("参数校验成功：user:{}",user);
        // 对password进行MD5加密
        String md5Pwd = Md5Util.getMD5String(user.getPassword());
        user.setPassword(md5Pwd);
        return userMapper.insert(user) == 1 ? "注册成功！" : "注册失败！";
    }

    @Override
    public Result login(UserDTO userDTO) {
        if (userDTO == null || "".equals(userDTO.getUsername()) || userDTO.getPassword() == null || "".equals(userDTO.getPassword())) {
            return Result.err(Result.CODE_ERR_BUSINESS,"请输入用户名和密码！");
        }
        User user = getUserByUsername(userDTO.getUsername());
        if (user != null && user.getPassword().equals(Md5Util.getMD5String(userDTO.getPassword()))) {
            // 登录成功，生成token并返回
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            return Result.ok("登录成功！",JwtUtil.genToken(claims));
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"登录失败，请检查用户名和密码");
    }

    @Override
    public List<UserVO> getAllUser() {
        // 1. 查询所有用户
        List<User> userList = userMapper.selectList(null);
        // 2. 将User转换成UserVO
        List<UserVO> userVOList = new ArrayList<>(userList.size());
        // 3. 遍历
        userList.forEach(user -> {
            UserVO userVO = new UserVO();
            // 属性拷贝
            BeanUtils.copyProperties(user,userVO);
            userVOList.add(userVO);
        });
        return userVOList;
    }

    @Override
    public List<UserVO> getUserByPage(Integer pageNum, Integer pageSize) {
        // 添加分页参数
        Page<User> pageInfo = new Page<>(pageNum, pageSize);
        pageInfo = userMapper.selectPage(pageInfo,null);
        ArrayList<UserVO> userVOS = new ArrayList<>();
        // 解析
        pageInfo.getRecords().forEach(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user,userVO);
            userVOS.add(userVO);
        });
        return userVOS;
    }

    /**
     * 根据username查询user
     * @param username
     * @return
     */
    private User getUserByUsername(String username) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,username));
    }
}
