package com.sn.demo02.controller;


import com.sn.demo02.domain.dto.UserDTO;
import com.sn.demo02.domain.po.User;
import com.sn.demo02.domain.vo.Result;
import com.sn.demo02.service.IUserService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sn
 * @since 2024-09-06
 */
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation("根据username查询user")
    @GetMapping("/findUserByName")
    public Result findUserByName(String username){
        return Result.ok(userService.getUserByName(username));
    }

    @ApiOperation("创建用户")
    @PostMapping("/createUser")
    public Result createUser(@RequestBody User user){
        return Result.ok(userService.addUser(user) ? "创建用户成功！" : "创建用户失败！");
    }

    @ApiOperation("根据id修改用户")
    @PutMapping("/modifyUser")
    public Result modifyUser(@RequestBody User user){
        return Result.ok(userService.updateUserById(user) ? "修改用户成功！" : "修改用户失败！");
    }

    @ApiOperation("根据id删除用户")
    @DeleteMapping("/eraseUser")
    public Result eraseUser(Integer id){
        return Result.ok(userService.eraseUserById(id) ? "删除成功！" : "删除失败！");
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody @NotNull User user){
        return Result.ok(userService.register(user));
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    @ApiOperation(value = "查询所有用户信息")
    @GetMapping("getAllUser")
    public Result getAllUser(){
        return Result.ok("查询成功",userService.getAllUser());
    }

    @ApiOperation(value = "分页查询用户信息")
    @GetMapping("/getUserByPage")
    public Result getUserByPage(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                @RequestParam(required = false,defaultValue = "5") Integer pageSize){
        return Result.ok("分页查询成功",userService.getUserByPage(pageNum,pageSize));
    }



}
