import com.mysql.cj.util.StringUtils;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UpdateSwCizu {
    public static void main(String[] args) throws IOException {
        String line;
        FileReader file = new FileReader("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\book\\cizu");
        BufferedReader br = new BufferedReader(file);
        while ((line = br.readLine()) != null) {
            String[] strings = line.trim().split("");
            if (strings.length > 10) {
                continue;
            }
            for (String zi : strings) {
                if (StringUtils.isEmptyOrWhitespaceOnly(zi)) {
                    continue;
                }
                HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                objectObjectHashMap.put("zi", zi);
                objectObjectHashMap.put("cizu", line.trim()+"；");
//                int n = sqlSession.update("updatecizu", objectObjectHashMap);
//                sqlSession.commit();
//                if (n == 0) {
//                    stringSet.add(zi);
//                }
                int n = sqlSession.selectOne("selectCZZI", objectObjectHashMap);
                if (n == 0) {
                    stringSet.add(zi);
                }
            }

        }

        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }

        sqlSession.close();
        file.close();
    }

    static SqlSession sqlSession;
    static Set<String> stringSet = new HashSet<>();

    static {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream, null, null);
        xmlConfigBuilder.parse();
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(xmlConfigBuilder.getConfiguration());
        sqlSession = sqlSessionFactory.openSession();
    }
}
