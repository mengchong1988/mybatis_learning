package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysRoleExtend;
import tk.mybatis.simple.model.SysUser;

import java.util.List;

public interface UserMapper {


    //select////////////////////////////////////////////////////
    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    SysUser selectById(Long id);


    List<SysUser> selectAll();


    List<SysRole> selectRolesByUserId(Long userId);

    /**
     * 不仅返回SysRole对应的sys_role表的属性，还包括sys_user的部分属性
     * 用于测试返回多个表的属性
     * @param userId
     * @return
     */
    List<SysRoleExtend> selectRoleExtendsByUserId(Long userId);



    //insert////////////////////////////////////////////////////

    int insert(SysUser sysUser);

    int insert2(SysUser sysUser);

    int insert3(SysUser sysUser);


    //update////////////////////////////////////////////////////

    int updateById(SysUser sysUser);


    //delete////////////////////////////////////////////////////

    int deleteById(Long id);

    //多参数////////////////////////////////////////////////////

    List<SysRole> selectRolesRolesByUserIdAndRoleEnabled(
            @Param("userId") Long userId,
            @Param("enabled") Integer enabled);

    List<SysRole> selectRolesByUserAndRole(
            @Param("user")SysUser user,
            @Param("role")SysRole role
    );
}
