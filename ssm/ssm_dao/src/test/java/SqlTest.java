
import com.mmm.dao.ProductDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;



public class SqlTest {

    private SqlSession session;

    @Before
    public void loadFile(){

        try {
            InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            SqlSessionFactory factory = builder.build(in);

            session = factory.openSession(true);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    @Test
    public void testFindAll(){
        ProductDao mapper = session.getMapper(ProductDao.class);
        mapper.findAll().forEach(System.out::println);
    }


    @After
    public void close(){
        session.close();
    }
}
