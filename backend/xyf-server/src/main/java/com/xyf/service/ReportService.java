package com.xyf.service;

import com.xyf.vo.OrderReportVO;
import com.xyf.vo.SalesTop10ReportVO;
import com.xyf.vo.TurnoverReportVO;
import com.xyf.vo.UserReportVO;

import java.time.LocalDateTime;

public interface ReportService {
    /**
     * 营业额统计
     * @param begin
     * @param end
     * @return
     */
    TurnoverReportVO getTurnoverStatistics(LocalDateTime begin, LocalDateTime end);

    /**
     * 用户统计
     * @param begin
     * @param end
     * @return
     */
    UserReportVO getUserStatistics(LocalDateTime begin, LocalDateTime end);

    /**
     * 订单统计
     * @param begin
     * @param end
     * @return
     */
    OrderReportVO getOrderStatistics(LocalDateTime begin, LocalDateTime end);

    /**
     * 销量排名TOP10
     * @param begin
     * @param end
     * @return
     */
    SalesTop10ReportVO getTop10(LocalDateTime begin, LocalDateTime end);
}
