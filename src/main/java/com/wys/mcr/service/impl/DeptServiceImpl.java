package com.wys.mcr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wys.mcr.common.ContextHolder;
import com.wys.mcr.common.dto.req.DeptReq;
import com.wys.mcr.common.exception.MyException;
import com.wys.mcr.common.utils.DeptUtils;
import com.wys.mcr.common.validator.ValidatorUtils;
import com.wys.mcr.entity.Dept;
import com.wys.mcr.entity.User;
import com.wys.mcr.mapper.DeptMapper;
import com.wys.mcr.service.DeptService;
import com.wys.mcr.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author lcw
 * @since 2019-05-28
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Override
    public List<Dept> list(DeptReq deptReq) {
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dept::getDeleted, 0);

        if (StringUtils.isNotEmpty(deptReq.getParentId())) {
            queryWrapper.eq(Dept::getParentId, deptReq.getParentId());
        }

        return list(queryWrapper);
    }

    @Override
    public List<Dept> queryListByParentId(String parentId) {
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dept::getParentId, parentId);
        queryWrapper.eq(Dept::getDeleted, 0);
        return list(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(DeptReq deptReq) {
        ValidatorUtils.validateEntity(deptReq, DeptReq.Save.class);
        Dept dept = new Dept();
        if (null != deptReq) {
            BeanUtils.copyProperties(deptReq, dept);
        }
        dept.setCreateDate(LocalDateTime.now());
        dept.setCreateId(ContextHolder.getUserId());
        dept.setModifyDate(LocalDateTime.now());
        dept.setModifyId(ContextHolder.getUserId());
        save(dept);

//        // 生成部门id
//        DeptReq deptReqTemp = new DeptReq();
//        deptReqTemp.setParentId(dept.getParentId());
//        List<Dept> deptList = list(deptReqTemp);
//        //如果是父节点下的第一个子节点
//        if (CollectionUtils.isEmpty(deptList)) {
//            dept.setDeptId(getFirstNodeId(dept));
//        } else {
//            String[] arrTemp = deptList.get(deptList.size() - 1).getDeptId().split("-");
//            dept.setDeptId(genDeptId(arrTemp));
//        }
//        save(dept);
//        return dept.getDeptId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DeptReq deptReq) {
        //数据校验
        ValidatorUtils.validateEntity(deptReq, DeptReq.Update.class);

        Dept deptTemp = getById(deptReq.getDeptId());
        BeanUtils.copyProperties(deptReq, deptTemp);
        updateById(deptTemp);
        //如果层级变了，则对应的子部门的层级都需要变
        if (!deptReq.getLevel().equals(deptTemp.getLevel())) {
            //级别变化的数量
            int levelChangeNum = deptReq.getLevel() - deptTemp.getLevel();
            //如果有子部门，则需要修改对应子部门的level，广度遍历所有子节点的部门id
            List<String> deptIdList = DeptUtils.getAllChild(deptTemp.getDeptId());
            //对查询出来的这些部门id的level值进行修改
            if (!CollectionUtils.isEmpty(deptIdList)) {
                baseMapper.updateLevel(deptIdList, levelChangeNum);
            }
        }

//        if (!deptTemp.getParentId().equals(dept.getParentId())) {
//            deptTemp.setDeleted(1);
//            updateById(deptTemp);
//            //新增一条部门信息
//            DeptReq deptReq = new DeptReq();
//            deptReq.setName(deptTemp.getName());
//            deptReq.setParentId(deptTemp.getName());
//            String newDeptId = save(deptReq);
//            // 将用户的部门信息修改
//            userService.updateDeptId(deptTemp.getDeptId(), newDeptId);
//        } else {
//            deptTemp.setName(dept.getName());
//            deptTemp.setSortOrder(dept.getSortOrder());
//            updateById(deptTemp);
//        }
    }

//    private List<String> getAllChild(String deptId) {
//        List<String> deptIdList = new ArrayList<>();
//
//        Stack<String> stack = new Stack<>();
//        //入栈
//        stack.push(deptId);
//        while (!stack.isEmpty()) {
//            String currentDeptId = stack.pop();
//            //查询当前节点下的部门列表
//            DeptReq deptReqTemp = new DeptReq();
//            deptReqTemp.setParentId(currentDeptId);
//            List<Dept> deptList = list(deptReqTemp);
//            //如果当前节点列表不为空，则依次入栈
//            if (!CollectionUtils.isEmpty(deptList)) {
//                for (Dept d : deptList) {
//                    deptIdList.add(d.getDeptId());
//                    stack.push(d.getDeptId());
//                }
//            }
//        }
//        return deptIdList;
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(DeptReq deptReq) {
        ValidatorUtils.validateEntity(deptReq, DeptReq.Deleted.class);
        //判断是否有子菜单或按钮
        List<Dept> deptList = queryListByParentId(deptReq.getDeptId());
        if (deptList.size() > 0) {
            throw new MyException("请先删除子级部门");
        }
        //查询该部门是否存在用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getDeptId, deptReq.getDeptId());
        if (userService.count(queryWrapper) > 0) {
            throw new MyException("该部门下存在用户，无法删除");
        }
        Dept dept = getById(deptReq.getDeptId());
        dept.setDeleted(1);
        updateById(dept);
    }

    @Override
    public List<Dept> orderList() {
        List<Dept> deptList = new ArrayList<>();
        // 部门级数不可能多余10级
        for (int i = 1; i < 10; i++) {
            LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Dept::getLevel, i);
            queryWrapper.eq(Dept::getDeleted, 0);
            queryWrapper.orderByAsc(Dept::getSortOrder);
            List<Dept> deptListTemp = list(queryWrapper);
            if (CollectionUtils.isEmpty(deptListTemp)) {
                break;
            }
            deptList.addAll(deptListTemp);
        }
        return deptList;
    }

    @Override
    public List<Dept> nameList() {
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Dept::getDeptId,Dept::getParentId, Dept::getName, Dept::getLevel);
        List<Dept> deptList = list(queryWrapper);
        // 先查出所有的部门id和name
        LambdaQueryWrapper<Dept> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(Dept::getDeptId, Dept::getName);
        List<Dept> deptListAll = list(lambdaQueryWrapper);
        Map<String, String> map = deptListAll.stream().collect(Collectors.toMap(Dept::getDeptId, Dept::getName));

        if (!CollectionUtils.isEmpty(deptList)) {
            deptList.forEach(item -> {
                if (!"-1".equals(item.getParentId())) {
                    item.setName(map.get(item.getParentId()) + "-" + item.getName());
                }
            });
        }

        return deptList;
    }

//    private String genDeptId(String[] arrTemp) {
//        StringBuffer sb = new StringBuffer();
//
//        if (arrTemp.length == 1) {
//            Integer i = Integer.valueOf(arrTemp[0]) + 1;
//            Integer length = (i / 10) + 1;
//            for (int j = 2; j > 2 - length; j--) {
//                sb.append("0");
//            }
//            sb.append(String.valueOf(i));
//        }
//
//        if (arrTemp.length > 1) {
//            for (int k = 0; k < arrTemp.length - 1; k++) {
//                sb.append(arrTemp[k] + "-");
//            }
//            Integer i = Integer.valueOf(arrTemp[arrTemp.length - 1]) + 1;
//            Integer length = (i / 10) + 1;
//            for (int j = 4; j > length; j--) {
//                sb.append("0");
//            }
//            sb.append(String.valueOf(i));
//        }
//        return sb.toString();
//    }
//
//    private String getFirstNodeId(Dept dept) {
//        StringBuffer sb = new StringBuffer();
//        if ("-1".equals(dept.getParentId())) {
//            sb.append("01");
//        } else {
//            String[] arrTemp = dept.getParentId().split("-");
//            if (arrTemp.length > 0) {
//                for (int k = 0; k < arrTemp.length; k++) {
//                    sb.append(arrTemp[k] + "-");
//                }
//                sb.append("0001");
//            }
//        }
//        return sb.toString();
//    }
}
