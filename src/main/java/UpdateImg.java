import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.*;
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
        int i = 0;
        for (File f : files) {
            File file1 = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\sw2\\"+i+".png");
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            FileInputStream fileInputStream = new FileInputStream(f);
            byte[] bytes = new byte[(int) f.length()];
            fileInputStream.read(bytes);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();

            String n = f.getName();
            Map<String, String> map = new HashMap<>();
            map.put("img", "sw/"+i+".png");
            int index = n.indexOf("_");
            int lastIndex = n.indexOf(".");
            map.put("zi", n.substring(index + 1, lastIndex));
            sqlSession.update("updateimg", map);
            sqlSession.commit();
            i++;
            System.out.println(n.substring(index + 1, lastIndex));
        }

        sqlSession.close();
    }

    public static void main2(String[] args) throws IOException {
        String path = "C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\sw\\";
        File file = new File(path);
        File[] files = file.listFiles();
        int i = 0;
        for (File f : files) {
            String n = f.getName();
            File file1 = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\sw2\\"+i+".png");
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            FileInputStream fileInputStream = new FileInputStream(f);
            byte[] bytes = new byte[(int) f.length()];
            fileInputStream.read(bytes);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            break;
        }

    }
}
