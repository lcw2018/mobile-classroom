package com.wys.mcr.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wys.mcr.common.dto.req.CheckReq;
import com.wys.mcr.common.dto.resp.CheckResp;
import com.wys.mcr.common.exception.MyException;
import com.wys.mcr.common.validator.ValidatorUtils;
import com.wys.mcr.entity.Dept;
import com.wys.mcr.mapper.ExamCheckMapper;
import com.wys.mcr.mapper.PractiseCheckMapper;
import com.wys.mcr.mapper.StudyCheckMapper;
import com.wys.mcr.service.DeptService;
import com.wys.mcr.service.PointsService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: lcw
 * @Date: 2019/6/8
 */
public class CheckUtils {

    public static Page<CheckResp> firstPage(CheckReq checkReq, String type) {
        ValidatorUtils.validateEntity(checkReq);
        Page<CheckResp> checkRespPage = new Page<>(checkReq.getCurrent(), checkReq.getSize());

        List<CheckResp> checkRespList = new ArrayList<>();
        if ("examCheck".equals(type)) {
            checkRespList = SpringContextUtils.getBean(ExamCheckMapper.class).queryCheckFirstPage(checkRespPage, checkReq.getLikeName(), checkReq.getFromDate(), checkReq.getToDate());
        } else if ("studyCheck".equals(type)) {
            checkRespList = SpringContextUtils.getBean(StudyCheckMapper.class).queryCheckFirstPage(checkRespPage, checkReq.getLikeName(), checkReq.getFromDate(), checkReq.getToDate());
        } else if ("practiseCheck".equals(type)) {
            checkRespList = SpringContextUtils.getBean(PractiseCheckMapper.class).queryCheckFirstPage(checkRespPage, checkReq.getLikeName(), checkReq.getFromDate(), checkReq.getToDate());
        }

        //对返回的记过的名称进行处理，名称应该是第二级加第三级的名称
        if (!CollectionUtils.isEmpty(checkRespList)) {
            DeptService deptService = SpringContextUtils.getBean(DeptService.class);
            for (CheckResp checkResp : checkRespList) {
                Dept dept = deptService.getById(checkResp.getSecondDeptId());
                checkResp.setDeptName(dept.getName() + "-" + checkResp.getDeptName());
            }
        }

        checkRespPage.setRecords(checkRespList);
        return checkRespPage;
    }

    public static Page<CheckResp> secondPage(CheckReq checkReq, String type) {
        if (StringUtils.isEmpty(checkReq.getDeptId())) {
            throw new MyException("部门不能为空");
        }
        Page<CheckResp> checkRespPage = new Page<>(checkReq.getCurrent(), checkReq.getSize());

        List<CheckResp> checkRespList = new ArrayList<>();
        if ("examCheck".equals(type)) {
            checkRespList = SpringContextUtils.getBean(ExamCheckMapper.class).queryCheckSecondPage(checkRespPage, checkReq.getDeptId(), checkReq.getFromDate(), checkReq.getToDate());
            if (!CollectionUtils.isEmpty(checkRespList)) {
                checkRespList.forEach(item -> {
//                // 查询该用户的登录总积分
//                // 参数设置
                    checkReq.setUserId(item.getUserId());
                    Integer loginPoints = SpringContextUtils.getBean(PointsService.class).getTotalPoints(checkReq);
                    item.setPoints(item.getPoints() + loginPoints);
                    item.setUserId(Base64Utils.Base64Decode(item.getUserId()));
                    item.setDeptName(getDeptName(item.getCurrentDeptId()));
                });
            }

        } else if ("studyCheck".equals(type)) {
            checkRespList = SpringContextUtils.getBean(StudyCheckMapper.class).queryCheckSecondPage(checkRespPage, checkReq.getDeptId(), checkReq.getFromDate(), checkReq.getToDate());
            checkRespList.forEach(item -> {
//                // 查询该用户的登录总积分
//                // 参数设置
                item.setUserId(Base64Utils.Base64Decode(item.getUserId()));
                item.setDeptName(getDeptName(item.getCurrentDeptId()));
            });
        } else if ("practiseCheck".equals(type)) {
            checkRespList = SpringContextUtils.getBean(PractiseCheckMapper.class).queryCheckSecondPage(checkRespPage, checkReq.getDeptId(), checkReq.getFromDate(), checkReq.getToDate());
            checkRespList.forEach(item -> {
//                // 查询该用户的登录总积分
//                // 参数设置
                item.setUserId(Base64Utils.Base64Decode(item.getUserId()));
                item.setDeptName(getDeptName(item.getCurrentDeptId()));
            });
        }

        checkRespPage.setRecords(checkRespList);
        return checkRespPage;
    }

    public static String getDeptName(String deptId) {
        /**
         * 查询部门详情
         */
        DeptService deptService = SpringContextUtils.getBean(DeptService.class);
        Dept currentDept = deptService.getById(deptId);
        if (3 == currentDept.getLevel()) {
            //如果当前是第三级的部门，直接返回，不需要拼接
            return currentDept.getName();
        }
        //否则拼接部门
        deptId = currentDept.getParentId();
        //声明一个堆栈用于存放部门名称，先进后出，便于拼接
        Stack<String> deptNames = new Stack<>();
        int i = 0;
        while (true) {
            i = i + 1;
            if (i > 10) {
                throw new MyException("部门查找失败");
            }
            Dept dept = deptService.getById(deptId);
            deptNames.push(dept.getName());
            if (dept.getLevel() == 3) {
                StringBuilder sb = new StringBuilder();
                while (!deptNames.isEmpty()) {
                    sb.append(deptNames.pop()).append("-");
                }
                sb.append(currentDept.getName());
                return sb.toString();
            }
            deptId = dept.getParentId();
        }
    }
}
