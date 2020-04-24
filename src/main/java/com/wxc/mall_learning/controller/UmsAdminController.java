package com.wxc.mall_learning.controller;

import com.wxc.mall_learning.common.api.CommonResult;
import com.wxc.mall_learning.service.UmsAdminService;
import com.wxc.mall_learning.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@Api(tags="UmsAdminController",description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;

    @Value("$(jwt.tokenHeader)")
    private String tokenHeader;
    @Value("$(jwt.tokenHead)")
    private String tokenHead;


    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdmin, BindingResult result){
        if(umsAdmin==null){
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);/**/
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @RequestBody
    public CommonResult login(@ResponseBody UmsAdminLoginParam umsAdminLoginParam,BindingResult result){
        String token = adminService.login(umsAdminLoginParam.getUsername(),UmsAdminLoginParam.getPassword());
        if(token==null){
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return CommonResult.success(tokenMap);
    }


    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId)
}
