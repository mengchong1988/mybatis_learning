package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysRoleExtend;
import tk.mybatis.simple.model.SysUser;

import java.util.Date;
import java.util.List;

public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {


        try(
                SqlSession sqlSession = getSqlSession();
        )
        {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = userMapper.selectById(1L);

            Assert.assertNotNull(user);

            Assert.assertEquals("admin", user.getUserName());
        }
    }

    @Test
    public void testSelectAll() {


        try(
                SqlSession sqlSession = getSqlSession();
        )
        {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<SysUser> userList = userMapper.selectAll();

            Assert.assertNotNull(userList);

            Assert.assertTrue(userList.size()>0);
        }
    }

    @Test
    public void testSelectRolesByUserId() {
        try(
                SqlSession sqlSession = getSqlSession();
        )
        {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
            
            
            for(SysRole sysRole : roleList)
                System.out.println(sysRole);
            
            
        }
    }

    @Test
    public void testSelectRoleExtendsByUserId() {
        try(
                SqlSession sqlSession = getSqlSession();
        )
        {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<SysRoleExtend> roleList = userMapper.selectRoleExtendsByUserId(1L);



            for(SysRoleExtend sysRole : roleList)
                System.out.println(sysRole);


        }
    }

    @Test
    public void testInsert() {
        try(
                SqlSession sqlSession = getSqlSession(true);
        )
        {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test.info");

            //正常情况下应该读如一张图片粗如到byte数组中
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());

            //result是执行SQL影响的行数
            int result = userMapper.insert(user);

            //之插入1条数据
            Assert.assertEquals(1, result);

            //id为null，没有给id赋值，并且没有配置回写id的值
            Assert.assertNull(user.getId());

        }
    }

    @Test
    public void testInsert2() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = new SysUser();
            user.setUserName("test2");
            user.setUserPassword("6789");
            user.setUserEmail("test2@mybatis.tk");
            user.setUserInfo("test2.info");

            //正常情况下应该读如一张图片粗如到byte数组中
            user.setHeadImg(new byte[]{4,5,6});
            user.setCreateTime(new Date());

            //result是执行SQL影响的行数
            int result = userMapper.insert2(user);

            //之插入1条数据
            Assert.assertEquals(1, result);

            //id为null，没有给id赋值，并且没有配置回写id的值
            System.out.println(user.getId());
            Assert.assertNotNull(user.getId());
        }
        finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = userMapper.selectById(1010L);

            Assert.assertEquals("test1", user.getUserName());

            user.setUserName("update_test");

            user.setUserEmail("updatetest@mybatis.tk");

            int result = userMapper.updateById(user);

            Assert.assertEquals(1, result);

            user = userMapper.selectById(1010L);
            Assert.assertEquals("update_test", user.getUserName());
        }
        finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
