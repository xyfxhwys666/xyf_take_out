package com.xyf.service;

import com.xyf.dto.UserLoginDTO;
import com.xyf.vo.UserLoginVO;

public interface UserService {

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    UserLoginVO wxLogin(UserLoginDTO userLoginDTO);
}
