package com.wys.mcr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wys.mcr.common.dto.req.DeptReq;
import com.wys.mcr.entity.Dept;

import java.util.List;

/**
 * <p>
 * 部门管理 服务类
 * </p>
 *
 * @author lcw
 * @since 2019-05-28
 */
public interface DeptService extends IService<Dept> {
    List<Dept> list(DeptReq deptReq);

    List<Dept> queryListByParentId(String parentId);

    void save(DeptReq deptReq);

    void update(DeptReq deptReq);

    void delete(DeptReq deptReq);

    List<Dept> orderList();

    List<Dept> nameList();
}
