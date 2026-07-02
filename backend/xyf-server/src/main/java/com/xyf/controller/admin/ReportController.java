package com.xyf.controller.admin;

import com.xyf.result.Result;
import com.xyf.service.ReportService;
import com.xyf.vo.OrderReportVO;
import com.xyf.vo.SalesTop10ReportVO;
import com.xyf.vo.TurnoverReportVO;
import com.xyf.vo.UserReportVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin/report")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 营业额统计
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/turnoverStatistics")
    public Result<TurnoverReportVO> turnoverStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate end) {
        log.info("查询营业额数据：{}到{}", begin, end);
        return Result.success(reportService.getTurnoverStatistics(begin.atStartOfDay(), end.atTime(23, 59, 59)));
    }
    /**
     * 用户统计
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/userStatistics")
    public Result<UserReportVO> userStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate end) {
        log.info("查询用户数据：{}到{}", begin, end);
        return Result.success(reportService.getUserStatistics(begin.atStartOfDay(), end.atTime(23, 59, 59)));
    }
    /**
     * 订单统计
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/ordersStatistics")
    public Result<OrderReportVO> ordersStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate end) {
        log.info("查询订单数据：{}到{}", begin, end);
        return Result.success(reportService.getOrderStatistics(begin.atStartOfDay(), end.atTime(23, 59, 59)));
    }

    /**
     * 销量排名TOP10
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/top10")
    public Result<SalesTop10ReportVO> top10(
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate end) {
        log.info("查询销量排名TOP10：{}到{}", begin, end);
        return Result.success(reportService.getTop10(begin.atStartOfDay(), end.atTime(23, 59, 59)));
    }
}
