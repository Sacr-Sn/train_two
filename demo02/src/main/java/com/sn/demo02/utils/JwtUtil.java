package com.sn.demo02.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    //密钥
    private static final String KEY = "klf";
	
	//接收业务数据,生成token并返回
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)//添加载荷，也就是我们平常的数据
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2 )) //2小时过期时间
                .sign(Algorithm.HMAC256(KEY)); //设置签名指定算法，以及密钥
    }

	//接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))//申请验证器，按照加密的方式验证token 一定要保证加解密一样
                .build()
                .verify(token)//解析token生成解析后对象
                .getClaim("claims")//获取载荷
                .asMap();
    }

}
