package com.wys.mcr.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wys.mcr.common.dto.req.CheckReq;
import com.wys.mcr.common.dto.resp.CheckResp;
import com.wys.mcr.common.dto.resp.ExamCheckOneResp;
import com.wys.mcr.common.dto.resp.ExamCheckSecondResp;
import com.wys.mcr.common.exception.MyException;
import com.wys.mcr.common.utils.Base64Utils;
import com.wys.mcr.common.utils.CheckUtils;
import com.wys.mcr.common.validator.ValidatorUtils;
import com.wys.mcr.entity.Dept;
import com.wys.mcr.entity.ExamCheck;
import com.wys.mcr.mapper.ExamCheckMapper;
import com.wys.mcr.service.DeptService;
import com.wys.mcr.service.ExamCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lcw
 * @since 2019-06-07
 */
@Service
public class ExamCheckServiceImpl extends ServiceImpl<ExamCheckMapper, ExamCheck> implements ExamCheckService {
    @Autowired
    private DeptService deptService;

    @Override
    public IPage<CheckResp> firstPage(CheckReq checkReq) {
        return CheckUtils.firstPage(checkReq, "examCheck");
    }

    @Override
    public IPage<CheckResp> secondPage(CheckReq checkReq) {
        return CheckUtils.secondPage(checkReq, "examCheck");
    }

    @Override
    public IPage<CheckResp> thirdPage(CheckReq checkReq) {
        if (StringUtils.isEmpty(checkReq.getUserId())) {
            throw new MyException("用户id不能为空");
        }

        Page<CheckResp> checkRespPage = new Page<>(checkReq.getCurrent(), checkReq.getSize());
        List<CheckResp> checkRespList = baseMapper.queryCheckThirdPage(checkRespPage, Base64Utils.Base64Encode(checkReq.getUserId()), checkReq.getCurrentDeptId(), checkReq.getFromDate(), checkReq.getToDate());
        checkRespPage.setRecords(checkRespList);
        return checkRespPage;
    }

    @Override
    public List<ExamCheckOneResp> firstList(CheckReq checkReq) {
        ValidatorUtils.validateEntity(checkReq);

        List<ExamCheckOneResp> examCheckOneRespList = baseMapper.queryCheckFirstList(checkReq.getLikeName(), checkReq.getFromDate(), checkReq.getToDate());
        //对返回的记过的名称进行处理，名称应该是第二级加第三级的名称
        if (!CollectionUtils.isEmpty(examCheckOneRespList)) {
            for (ExamCheckOneResp examCheckOneResp : examCheckOneRespList) {
                //todo: 此处部门可缓存
                Dept dept = deptService.getById(examCheckOneResp.getSecondDeptId());
                examCheckOneResp.setDeptName(dept.getName() + "-" + examCheckOneResp.getDeptName());
            }
        }
        return examCheckOneRespList;
    }

    @Override
    public List<ExamCheckSecondResp> secondList(CheckReq checkReq) {
        if (StringUtils.isEmpty(checkReq.getDeptId())) {
            throw new MyException("部门不能为空");
        }
        List<ExamCheckSecondResp> examCheckSecondRespList = baseMapper.queryCheckSecondList(checkReq.getDeptId(), checkReq.getFromDate(), checkReq.getToDate());
        if (!CollectionUtils.isEmpty(examCheckSecondRespList)) {
            examCheckSecondRespList.forEach(item -> {
                item.setUserId(Base64Utils.Base64Decode(item.getUserId()));
                item.setDeptName(CheckUtils.getDeptName(item.getCurrentDeptId()));
            });
        }
        return examCheckSecondRespList;
    }
}
