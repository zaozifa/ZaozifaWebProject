import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ABCD {

    public static void main(String[] args) throws IOException {
        boolean b = false;
        if (b) {
            //all
        } else {
            //lastUpdate
        }
        //所有象形 oder by length(key1),key1
        InputStream inputStream = Resources.getResourceAsStream("config.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream, null, null);
        xmlConfigBuilder.parse();
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(xmlConfigBuilder.getConfiguration());
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Map> allxx = sqlSession.selectList("allxx");
        Map haveMap = new HashMap();

        char index = 'A';
        for (int j = 0; j < allxx.size(); j++) {

            String path = "C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\" +(index)+".html";
            File file = new File(path);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(file))){
                printWriter.write("<!DOCTYPE html>\n");
                printWriter.write("<html lang=\"zh\">\n");
                printWriter.write("<head>\n");
                printWriter.write("    <meta charset=\"UTF-8\">\n");
                printWriter.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\" />\n");
                printWriter.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n");
                printWriter.write("    <link rel=\"shortcut icon\" href=\"favicon.ico\" type=\"image/x-icon\" />\n");
                printWriter.write("<title>象形码");
                printWriter.write(index);
                printWriter.write("系列及其属字会意字 - 造字法</title>");
                printWriter.write("    <link rel=\"stylesheet\" href=\"zaozifa.css\"/>\n");
                printWriter.write("    <script src=\"zaozifa.js\"></script>\n");
                printWriter.write("</head>\n");
                printWriter.write("<body>\n");
                printWriter.write("<h1>");
                printWriter.write(index);
                printWriter.write("系列全部象形汉字及其引会意字 - 象形码序</h1>\n");
                printWriter.write("<div>汉字不多，除去无数错体异体简体，正汉字仅9000+，所有正汉字皆始于此400象形，熟此400象形，则汉字不难矣</div>\n");

                for (; j < allxx.size(); j++) {
                    String zi = ((String) allxx.get(j).get("zi"));
                    String key1 = ((String) allxx.get(j).get("key1"));
                    String yin = ((String) allxx.get(j).get("yin"));
                    String jian = ((String) allxx.get(j).get("jian"));
                    String comments = ((String) allxx.get(j).get("comments"));
                    if (key1 == null) {
                        continue;
                    }

                    if (key1.startsWith(String.valueOf(index)) == false) {
                        index = key1.charAt(0);
                        break;
                    }

                    printWriter.write("<ul>\n");
                    ul_li(sqlSession, printWriter, haveMap, zi, yin, key1, jian, comments);
                    printWriter.write("</ul>\n");
                }


                printWriter.write("</body>\n");
                printWriter.write("</html>");
                printWriter.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

    /**
     *
     * @param printWriter
     * @param zi
     * @return
     */
    static void ul_li(SqlSession sqlSession, PrintWriter printWriter, Map haveMap, String zi, String yin, String key1, String jian, String comments) {
        if (haveMap.get(zi) != null) {
            System.out.println("======================================== " + zi);
            return;
        } else {
            haveMap.put(zi, 1);
        }
        printWriter.write("<li>");
        if (comments != null && comments.equals("") == false) {
            printWriter.write("<a href=\"/" + yin + "//" + key1 + ".html\">");
        } else {
            printWriter.write("<a class=\"red\" href=\"/" + yin + "//" + key1 + ".html\">");
        }

        printWriter.write(zi);
        printWriter.write("</a>");

        printWriter.write("(");
        printWriter.write(jian == null?"":jian);
        printWriter.write(",");
        printWriter.write(key1==null?"":key1);
        printWriter.write(")");


        List<Map> allSubByJian = null;
        if (zi.length() > 1) {
            allSubByJian = sqlSession.selectList("allSubByJian", zi.substring(0, 1));
        } else {
            allSubByJian = sqlSession.selectList("allSubByJian", zi);
        }

        if (allSubByJian != null && allSubByJian.size() > 0) {
            printWriter.write("<ul>\n");
            for (Map map : allSubByJian) {
                zi = ((String) map.get("zi"));
                yin = ((String) map.get("yin"));
                key1 = ((String) map.get("key1"));
                jian = ((String) map.get("jian"));
                comments = ((String) map.get("comments"));
                ul_li(sqlSession, printWriter, haveMap, zi, yin, key1, jian, comments);
            }
            printWriter.write("</ul>\n");
        }

        printWriter.write("</li>\n");
    }
}
