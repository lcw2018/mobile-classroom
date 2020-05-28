package com.wys.mcr.service.impl;

import com.wys.mcr.common.dto.req.CheckReq;
import com.wys.mcr.common.dto.req.ExcelReq;
import com.wys.mcr.common.dto.resp.ExamCheckOneResp;
import com.wys.mcr.common.dto.resp.ExamCheckSecondResp;
import com.wys.mcr.common.exception.MyException;
import com.wys.mcr.common.utils.ExcelUtil;
import com.wys.mcr.common.validator.ValidatorUtils;
import com.wys.mcr.entity.Judgement;
import com.wys.mcr.entity.MultiChoice;
import com.wys.mcr.entity.SingleChoice;
import com.wys.mcr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author lcw
 * @since 2019-05-28
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private SingleChoiceService singleChoiceService;

    @Autowired
    private MultiChoiceService multiChoiceService;

    @Autowired
    private JudgementService judgementService;

    @Autowired
    private ExamCheckService examCheckService;

    @Override
    public void exportTemplate(ExcelReq excelReq) {
        //数据校验
        ValidatorUtils.validateEntity(excelReq);
        switch (excelReq.getType()) {
            case 1:
                ExcelUtil.genTemplate(SingleChoice.class);
                return;
            case 2:
                ExcelUtil.genTemplate(MultiChoice.class);
                return;
            case 3:
                ExcelUtil.genTemplate(Judgement.class);
                return;
            default:
                throw new MyException("下载类型不存在" + excelReq.getType());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importResource(MultipartFile file, String type, String menuId) {
        //数据校验
        if (null == type) {
            throw new MyException("类型不能为空");
        }
        try {
            switch (type) {
                case "1":
                    List<SingleChoice> singleChoiceList = ExcelUtil.importExcel(SingleChoice.class, file.getInputStream(), type);
                    if (!CollectionUtils.isEmpty(singleChoiceList)) {
                        singleChoiceList.forEach(item -> {
                            item.setMenuId(menuId);
                            item.setType(type);
                        });
                        singleChoiceService.saveBatch(singleChoiceList);
                    }
                    return;
                case "2":
                    List<MultiChoice> multiChoiceList = ExcelUtil.importExcel(MultiChoice.class, file.getInputStream(), type);
                    if (!CollectionUtils.isEmpty(multiChoiceList)) {
                        multiChoiceList.forEach(item -> {
                            item.setMenuId(menuId);
                            item.setType(type);
                        });
                        multiChoiceService.saveBatch(multiChoiceList);
                    }
                    return;
                case "3":
                    List<Judgement> judgementList = ExcelUtil.importExcel(Judgement.class, file.getInputStream(), type);
                    if (!CollectionUtils.isEmpty(judgementList)) {
                        judgementList.forEach(item -> {
                            item.setMenuId(menuId);
                            item.setType(type);
                        });
                        judgementService.saveBatch(judgementList);
                    }
                    return;
                default:
                    throw new MyException("上传类型不存在" + type);
            }
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public void exportData(CheckReq checkReq) {
        if (1 == checkReq.getCurrent()) {
            List<ExamCheckOneResp> examCheckOneRespList = examCheckService.firstList(checkReq);
            ExcelUtil.exportExcel(ExamCheckOneResp.class, examCheckOneRespList);
        } else if (2 == checkReq.getCurrent()) {
            List<ExamCheckSecondResp> examCheckSecondRespList = examCheckService.secondList(checkReq);
            ExcelUtil.exportExcel(ExamCheckSecondResp.class, examCheckSecondRespList);
        }
    }
}
