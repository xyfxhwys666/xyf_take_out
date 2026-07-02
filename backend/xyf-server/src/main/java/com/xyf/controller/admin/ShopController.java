package com.xyf.controller.admin;

import com.xyf.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
public class ShopController {

    private static final String KEY = "SHOP_STATUS";
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 修改起售停售状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    public Result setStatus(@PathVariable Integer status) {
        log.info("起售停售状态：{}", status);
        redisTemplate.opsForValue().set(KEY, status);
        return Result.success();
    }
    /**
     * 查询起售停售状态
     * @return
     */
    @GetMapping("/{status}")
    public Result<Integer> getStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("查询起售停售状态：{}", status==1? "营业中":"打烊中");
        return Result.success(status);
    }
}
