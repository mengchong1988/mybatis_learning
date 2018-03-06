package tk.mybatis.simple.mapper;

import netscape.security.Privilege;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;

import java.util.List;

public class PrivilegeMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {


        try(
                SqlSession sqlSession = getSqlSession();
        )
        {
            PrivilegeMapper roleMapper = sqlSession.getMapper(PrivilegeMapper.class);

            SysPrivilege privilege = roleMapper.selectById(1L);

            Assert.assertNotNull(privilege);

            Assert.assertEquals("用户管理", privilege.getPrivilegeName());
        }
    }

}
