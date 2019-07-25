import com.spardon.mybatis.dao.UserDao;
import com.spardon.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class UserDaoTest {
    @Test
    public void findUserById(){
        SqlSession session = getSessionFactory().openSession();
        UserDao userMapper = session.getMapper(UserDao.class);
        User user = userMapper.findUserById(1);
        Assert.assertNotNull("没找到数据", user);
    }

    private static SqlSessionFactory getSessionFactory(){
        SqlSessionFactory sessionFactory = null;
        String resource = "mybatis_conf.xml";

        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sessionFactory;
    }
}
