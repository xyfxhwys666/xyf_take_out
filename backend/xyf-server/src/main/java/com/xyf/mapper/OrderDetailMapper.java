package com.xyf.mapper;

import com.xyf.dto.GoodsSalesDTO;
import com.xyf.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDetailMapper {

    /**
     * 批量插入订单明细数据
     * @param detailList
     */
    void insertBatch(List<OrderDetail> detailList);

    /**
     * 根据订单id查询订单明细
     * @param orderId
     * @return
     */
    List<OrderDetail> getByOrderId(Long orderId);

    /**
     * 根据动态条件统计销量排名TOP10
     * @param map
     * @return
     */
    List<GoodsSalesDTO> getSalesTop10(Map map);
}
