package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysRoleExtend;
import tk.mybatis.simple.model.SysUser;

import javax.management.relation.Role;
import java.util.Date;
import java.util.List;

public class RoleMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {


        try(
                SqlSession sqlSession = getSqlSession();
        )
        {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            SysRole role = roleMapper.selectById(1L);

            Assert.assertNotNull(role);

            Assert.assertEquals("管理员", role.getRoleName());
        }
    }

    @Test
    public void testSelectAll() {


        try(
                SqlSession sqlSession = getSqlSession();
        )
        {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            List<SysRole> roleList = roleMapper.selectAll2();

            Assert.assertNotNull(roleList);

            Assert.assertTrue(roleList.size()>0);
        }
    }

}
