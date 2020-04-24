package com.wxc.mall_learning.service;

public interface UmsAdminService {

    UmsAdmin getAdminByUsername(String Username);

    UmsAdmin register(UmsAdmin umsAdminParams);

    String login(String username,String password);

    List<UserPermission> getPermissionList(Long adminId);

}
