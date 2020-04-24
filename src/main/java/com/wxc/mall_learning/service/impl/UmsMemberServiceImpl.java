package com.wxc.mall_learning.service.impl;

import com.wxc.mall_learning.common.api.CommonResult;
import com.wxc.mall_learning.service.RedisService;
import com.wxc.mall_learning.service.UmsMemberService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private RedisService redisService;

    @Value("$(redis.key.prefix.authCode)")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;



    @Override
    public CommonResult gereateAuthCode(String telephone) {

        StringBuilder sb = new StringBuilder();
        Random random =  new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE+telephone,sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+telephone,AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(),"获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String telphone, String authCode) {
        if(StringUtils.isEmpty(authCode)){
            return CommonResult.failed("请输入验证码");
        }

        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE);
        boolean result = authCode.equals(realAuthCode);
        if(result){
            return CommonResult.success(null,"验证码校验成功");
        }else{
            return CommonResult.failed("验证码不正确");
        }
    }
}
