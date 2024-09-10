package com.sn.demo02;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sn.demo02.domain.po.SStu;
import com.sn.demo02.mapper.SStuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SStuTest {

    @Autowired
    private SStuMapper sStuMapper;

    @Test
    void selectBitchByCons() {
        LambdaQueryWrapper<SStu> sStuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sStuLambdaQueryWrapper.like(SStu::getEmail,"@163")
                .lt(SStu::getBalance,1000)
                .ge(SStu::getAge,20)
                .le(SStu::getAge,25);
        sStuMapper.selectList(sStuLambdaQueryWrapper).forEach(System.out::println);
    }

    @Test
    void updateBitchByCons() {
        LambdaUpdateWrapper<SStu> sStuLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        sStuLambdaUpdateWrapper.notLike(SStu::getEmail,"@qq")
                .notLike(SStu::getStuName,"小")
                .ge(SStu::getAge,24)
                .setSql("balance = balance + 6000");
        sStuMapper.update(null,sStuLambdaUpdateWrapper);
    }
}
