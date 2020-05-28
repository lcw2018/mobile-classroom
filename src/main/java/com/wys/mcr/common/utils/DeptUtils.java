package com.wys.mcr.common.utils;

import com.google.common.base.Strings;
import com.wys.mcr.common.dto.req.DeptReq;
import com.wys.mcr.common.exception.MyException;
import com.wys.mcr.entity.Dept;
import com.wys.mcr.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: lcw
 * @Date: 2019/6/8
 */
@Component
public class DeptUtils {
    @Autowired
    private DeptService deptService;

    public static DeptUtils deptUtils;

    @PostConstruct  //关键四：通过@PostConstruct注解实现注入
    public void init() {
        deptUtils = this;
        //tokenUtils.rService =  this.rService;
    }

    public static List<String> getAllChild(String deptId) {
        List<String> deptIdList = new ArrayList<>();

        Stack<String> stack = new Stack<>();
        //入栈
        stack.push(deptId);
        while (!stack.isEmpty()) {
            String currentDeptId = stack.pop();
            //查询当前节点下的部门列表
            DeptReq deptReqTemp = new DeptReq();
            deptReqTemp.setParentId(currentDeptId);
            List<Dept> deptList = deptUtils.deptService.list(deptReqTemp);
            //如果当前节点列表不为空，则依次入栈
            if (!CollectionUtils.isEmpty(deptList)) {
                for (Dept d : deptList) {
                    deptIdList.add(d.getDeptId());
                    stack.push(d.getDeptId());
                }
            }
        }
        return deptIdList;
    }

    public static String getFullDeptName(String deptId) {
        String name = null;
        int i = 0;
        while (true) {
            i = i + 1;
            if (i > 10) {
                throw new MyException("部门查找失败");
            }
            Dept dept = deptUtils.deptService.getById(deptId);

            if (dept.getParentId().equals("-1")) {
                name = Strings.isNullOrEmpty(name) ? dept.getName() : dept.getName() + "-" + name;
                return name;
            }
            name = Strings.isNullOrEmpty(name) ? dept.getName() : dept.getName() + "-" + name;
            deptId = dept.getParentId();
        }
    }
}
