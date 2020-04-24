package com.wxc.mall_learning.service;

import com.wxc.mall_learning.common.api.CommonResult;

public interface UmsMemberService {

    /**
     * 生成验证码
     * @param telephone
     * @return
     */
    CommonResult gereateAuthCode(String telephone);


    /**
     * 验证验证码
     * @param telphone
     * @param authCode
     * @return
     */
    CommonResult verifyAuthCode(String telphone,String authCode);
}
