import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class UpdateImg {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("config.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream, null, null);
        xmlConfigBuilder.parse();
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(xmlConfigBuilder.getConfiguration());
        SqlSession sqlSession = sqlSessionFactory.openSession();


        String path = "C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\sw\\";
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            String n = f.getName();
            Map<String, String> map = new HashMap<>();
            map.put("img", n);
            int index = n.indexOf("_");
            int lastIndex = n.indexOf(".");
            map.put("zi", n.substring(index + 1, lastIndex));
            sqlSession.update("updateimg", map);
            sqlSession.commit();
        }

        sqlSession.close();
    }
}
