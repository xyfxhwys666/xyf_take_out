package com.xyf.service.impl;

import com.xyf.dto.GoodsSalesDTO;
import com.xyf.entity.Orders;
import com.xyf.mapper.OrderDetailMapper;
import com.xyf.mapper.OrderMapper;
import com.xyf.mapper.UserMapper;
import com.xyf.service.ReportService;
import com.xyf.vo.OrderReportVO;
import com.xyf.vo.SalesTop10ReportVO;
import com.xyf.vo.TurnoverReportVO;
import com.xyf.vo.UserReportVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;
/**
     * 营业额统计
     * @param begin
     * @param end
     * @return
     */
    @Override
    public TurnoverReportVO getTurnoverStatistics(LocalDateTime begin, LocalDateTime end) {
        log.info("查询营业额数据：{}到{}", begin, end);
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate currentDate = begin.toLocalDate();
        LocalDate endDate = end.toLocalDate();
        
        dateList.add(currentDate);
        while (!currentDate.equals(endDate)) {
            currentDate = currentDate.plusDays(1);
            dateList.add(currentDate);
        }

        List<Double> turnoverList = new ArrayList<>();
        for (LocalDate localDate : dateList) {
            LocalDateTime beginTime = LocalDateTime.of(localDate, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(localDate, LocalTime.MAX);
            Map map=new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            map.put("status", Orders.COMPLETED);
            Double turnover = orderMapper.sumByMap(map);
            turnover = turnover == null ? 0.0 : turnover;
            turnoverList.add(turnover);

        }

        return TurnoverReportVO.builder()
                .dateList(dateList.stream()
                        .map(date -> date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .collect(Collectors.joining(",")))
                .turnoverList(StringUtils.join(turnoverList, ","))
                .build();
    }

    /**
     * 用户统计
     * @param begin
     * @param end
     * @return
     */
    @Override
    public UserReportVO getUserStatistics(LocalDateTime begin, LocalDateTime end) {
        log.info("查询用户数据：{}到{}", begin, end);
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate currentDate = begin.toLocalDate();
        LocalDate endDate = end.toLocalDate();
        
        dateList.add(currentDate);
        while (!currentDate.equals(endDate)) {
            currentDate = currentDate.plusDays(1);
            dateList.add(currentDate);
        }

        List<Integer> totalUserList = new ArrayList<>();
        List<Integer> newUserList = new ArrayList<>();
        
        for (LocalDate localDate : dateList) {
            LocalDateTime beginTime = LocalDateTime.of(localDate, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(localDate, LocalTime.MAX);
            
            // 累计用户数（从查询开始时间到当天的总用户数）
            Integer totalUser = userMapper.countByTime(begin, endTime);
            totalUser = totalUser == null ? 0 : totalUser;
            totalUserList.add(totalUser);
            
            // 新增用户数（当天创建的用户）
            Integer newUser = userMapper.countByTime(beginTime, endTime);
            newUser = newUser == null ? 0 : newUser;
            newUserList.add(newUser);
        }

        return UserReportVO.builder()
                .dateList(dateList.stream()
                        .map(date -> date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .collect(Collectors.joining(",")))
                .totalUserList(StringUtils.join(totalUserList, ","))
                .newUserList(StringUtils.join(newUserList, ","))
                .build();
    }

    /**
     * 订单统计
     * @param begin
     * @param end
     * @return
     */
    @Override
    public OrderReportVO getOrderStatistics(LocalDateTime begin, LocalDateTime end) {
        log.info("查询订单数据：{}到{}", begin, end);
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate currentDate = begin.toLocalDate();
        LocalDate endDate = end.toLocalDate();
        
        dateList.add(currentDate);
        while (!currentDate.equals(endDate)) {
            currentDate = currentDate.plusDays(1);
            dateList.add(currentDate);
        }

        List<Integer> orderCountList = new ArrayList<>();
        List<Integer> validOrderCountList = new ArrayList<>();
        
        int totalOrderCount = 0;
        int validOrderCount = 0;
        
        for (LocalDate localDate : dateList) {
            LocalDateTime beginTime = LocalDateTime.of(localDate, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(localDate, LocalTime.MAX);
            
            // 查询当天订单总数
            Map map = new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            
            // 订单总数（所有状态）
            Integer orderCount = orderMapper.countByMap(map);
            orderCount = orderCount == null ? 0 : orderCount;
            orderCountList.add(orderCount);
            totalOrderCount += orderCount;
            
            // 有效订单数（已完成状态）
            map.put("status", Orders.COMPLETED);
            Integer validOrderCountInt = orderMapper.countByMap(map);
            validOrderCountInt = validOrderCountInt == null ? 0 : validOrderCountInt;
            validOrderCountList.add(validOrderCountInt);
            validOrderCount += validOrderCountInt;
        }

        // 订单完成率
        Double orderCompletionRate = totalOrderCount == 0 ? 0.0 : (double) validOrderCount / totalOrderCount;
        
        log.info("订单统计结果 - 总订单数：{}，有效订单数：{}，完成率：{}", totalOrderCount, validOrderCount, orderCompletionRate);
        log.info("日期列表：{}", dateList.stream()
                .map(date -> date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .collect(Collectors.joining(",")));
        log.info("订单总数列表：{}", StringUtils.join(orderCountList, ","));
        log.info("有效订单列表：{}", StringUtils.join(validOrderCountList, ","));

        return OrderReportVO.builder()
                .dateList(dateList.stream()
                        .map(date -> date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .collect(Collectors.joining(",")))
                .orderCountList(StringUtils.join(orderCountList, ","))
                .validOrderCountList(StringUtils.join(validOrderCountList, ","))
                .totalOrderCount(totalOrderCount)
                .validOrderCount(validOrderCount)
                .orderCompletionRate(orderCompletionRate)
                .build();
    }

    /**
     * 销量排名TOP10
     * @param begin
     * @param end
     * @return
     */
    @Override
    public SalesTop10ReportVO getTop10(LocalDateTime begin, LocalDateTime end) {
        log.info("查询销量排名TOP10：{}到{}", begin, end);
        
        Map map = new HashMap();
        map.put("begin", begin);
        map.put("end", end);
        
        List<GoodsSalesDTO> salesTop10 = orderDetailMapper.getSalesTop10(map);
        
        log.info("查询到销量数据条数：{}", salesTop10.size());
        if (salesTop10.isEmpty()) {
            log.warn("未查询到销量数据，请检查：1.是否有状态为5(已完成)的订单 2.order_detail表的name字段是否有值");
        } else {
            log.info("第一条数据 - 商品名称：{}，销量：{}", salesTop10.get(0).getName(), salesTop10.get(0).getNumber());
        }
        
        // 提取商品名称和销量
        String nameList = salesTop10.stream()
                .map(GoodsSalesDTO::getName)
                .collect(Collectors.joining(","));
        
        String numberList = salesTop10.stream()
                .map(item -> item.getNumber().toString())
                .collect(Collectors.joining(","));
        
        log.info("返回的nameList：{}", nameList);
        log.info("返回的numberList：{}", numberList);

        return SalesTop10ReportVO.builder()
                .nameList(nameList)
                .numberList(numberList)
                .build();
    }
}
