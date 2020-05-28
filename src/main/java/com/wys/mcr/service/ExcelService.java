package com.wys.mcr.service;

import com.wys.mcr.common.dto.req.CheckReq;
import com.wys.mcr.common.dto.req.ExcelReq;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * excel
 * </p>
 *
 * @author lcw
 * @since 2019-05-28
 */
public interface ExcelService {

    void exportTemplate(ExcelReq excelReq);

    void importResource(MultipartFile file, String type, String menuId);

    void exportData(CheckReq checkReq);
}
