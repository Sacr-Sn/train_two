package com.sn.demo02.service;

import com.sn.demo02.domain.dto.UserDTO;
import com.sn.demo02.domain.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sn.demo02.domain.vo.Result;
import com.sn.demo02.domain.vo.UserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sn
 * @since 2024-09-06
 */
public interface IUserService extends IService<User> {

    List<User> getUserByName(String username);

    boolean addUser(User user);

    boolean updateUserById(User user);

    boolean eraseUserById(Integer id);

    String register(User user);

    Result login(UserDTO userDTO);

    List<UserVO> getAllUser();

    List<UserVO> getUserByPage(Integer pageNum, Integer pageSize);
}
