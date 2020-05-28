package com.wys.mcr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wys.mcr.common.ContextHolder;
import com.wys.mcr.common.dto.req.MenuReq;
import com.wys.mcr.common.validator.ValidatorUtils;
import com.wys.mcr.entity.Menu;
import com.wys.mcr.entity.MenuType;
import com.wys.mcr.mapper.MenuMapper;
import com.wys.mcr.service.MenuService;
import com.wys.mcr.service.MenuTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author lcw
 * @since 2019-05-30
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuTypeService menuTypeService;

    @Override
    public List<Menu> list(MenuReq menuReq) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<Menu>();
        queryWrapper.eq(Menu::getDeleted, 0);

        if (StringUtils.isNotEmpty(menuReq.getType())) {
            queryWrapper.eq(Menu::getType, menuReq.getType());
        }
        if (StringUtils.isNotEmpty(menuReq.getParentId())) {
            queryWrapper.eq(Menu::getParentId, menuReq.getParentId());
        }
        if (null != menuReq.getLevel()) {
            queryWrapper.eq(Menu::getLevel, menuReq.getLevel());
        }

        return list(queryWrapper);
    }

    @Override
    public List<Menu> queryListParentId(String parentId) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId, parentId);
        return list(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(MenuReq menuReq) {
        //数据校验
        ValidatorUtils.validateEntity(menuReq, MenuReq.Save.class);
        Menu menu = new Menu();
        if (null != menuReq) {
            BeanUtils.copyProperties(menuReq, menu);
        }
        menu.setCreateDate(LocalDateTime.now());
        menu.setCreateId(ContextHolder.getUserId());
        menu.setModifyDate(LocalDateTime.now());
        menu.setModifyId(ContextHolder.getUserId());
        save(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MenuReq menuReq) {
        //数据校验
        ValidatorUtils.validateEntity(menuReq, MenuReq.Update.class);
        Menu menuTemp = getById(menuReq.getId());

        BeanUtils.copyProperties(menuReq, menuTemp);

        updateById(menuTemp);
    }

    @Override
    public List<MenuType> getType() {
        LambdaQueryWrapper<MenuType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(MenuType::getTypeId, MenuType::getTypeName);
        return menuTypeService.list(queryWrapper);
    }

    @Override
    public List<Menu> orderList() {
        List<Menu> menuList = new ArrayList<>();
        // 菜单级数不可能多余10级，这里可以写死
        for (int i = 1; i < 10; i++) {
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Menu::getLevel, i);
            queryWrapper.orderByAsc(Menu::getSortOrder);
            List<Menu> menuListTemp = list(queryWrapper);
            if (CollectionUtils.isEmpty(menuListTemp)) {
                break;
            }
            menuList.addAll(menuListTemp);
        }
        return menuList;
    }
}
