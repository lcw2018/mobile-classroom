package com.wys.mcr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wys.mcr.common.dto.req.MenuReq;
import com.wys.mcr.entity.Menu;
import com.wys.mcr.entity.MenuType;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author lcw
 * @since 2019-05-30
 */
public interface MenuService extends IService<Menu> {

    List<Menu> list(MenuReq menuReq);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<Menu> queryListParentId(String parentId);

    void save(MenuReq menuReq);

    void update(MenuReq menuReq);

    List<MenuType> getType();

    List<Menu> orderList();
}
