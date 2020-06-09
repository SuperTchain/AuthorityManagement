package com.lx.authoritymanagement.dao;

import com.lx.authoritymanagement.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    /**
     * 根据id查询角色信息
     * @param id
     * @return
     */
    List<Role> findRoleById(Integer id);
}
