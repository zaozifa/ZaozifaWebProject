import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jian {

    public static void main(String[] args) {
        boolean b = false;
        if (b) {
            //all
        } else {
            //lastUpdate
        }

        String path = "C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\xiangxing.html";
        File file = new File(path);
        File dir = file.getParentFile();
        if (dir.exists() == false) {
            dir.mkdirs();
        }
        try {
            file.createNewFile();
        } catch (Exception e) {

        }
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(file))) {
            printWriter.write("<!DOCTYPE html>\n");
            printWriter.write("<html lang=\"zh\">\n");
            printWriter.write("<head>\n");
            printWriter.write("    <meta charset=\"UTF-8\">\n");
            printWriter.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\" />\n");
            printWriter.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n");
            printWriter.write("    <link rel=\"shortcut icon\" href=\"favicon.ico\" type=\"image/x-icon\" />\n");
            printWriter.write("<title>象形汉字 - 造字法</title>");
            printWriter.write("    <link rel=\"stylesheet\" href=\"zaozifa.css\"/>\n");
            printWriter.write("    <script src=\"zaozifa.js\"></script>\n");
            printWriter.write("</head>\n");
            printWriter.write("<body>\n");
            printWriter.write("<h1>全部象形汉字 - 象形码序</h1>");
            printWriter.write("<div>汉字不多，除去无数错体异体简体，正汉字仅9000+，所有正汉字皆始于此400象形，熟此400象形，则汉字不难矣</div>");
            printWriter.write("<div id=\"xxpage\">\n");

            //main
            //所有象形 oder by length(key1),key1
            InputStream inputStream = Resources.getResourceAsStream("config.xml");
            XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream, null, null);
            xmlConfigBuilder.parse();
            SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(xmlConfigBuilder.getConfiguration());
            SqlSession sqlSession = sqlSessionFactory.openSession();
            List<Map> allHanz = sqlSession.selectList("allxx");
            System.out.println(allHanz.size());

            Map<String, Map> map = new HashMap<>();
            for (Map t : allHanz) {
                map.put(((String) t.get("zi")), t);
            }
            List<Map> mapList = allHanz;


            char index = 'A';
            int j = 0;

            printWriter.write("<ul>\n");
            printWriter.write("== <a title=\"点击看全部象形及引申会意字\" href=\""+index+".html\">");
            printWriter.write("象形");
            printWriter.write(index);
            printWriter.write("-</a> ==");
            printWriter.write("\n");
            printWriter.write("\n");

            for (; j < mapList.size(); j++) {

                String zi = ((String) mapList.get(j).get("zi"));
                String key1 = ((String) mapList.get(j).get("key1"));
                String yin = ((String) mapList.get(j).get("yin"));
                if (key1 == null) {
                    continue;
                }

                if (key1.startsWith(String.valueOf(index)) == false) {
                    printWriter.write("</ul>\n");

                    index = key1.charAt(0);
                    printWriter.write("<ul>\n");
                    printWriter.write("== <a title=\"点击看全部象形及引申会意字\" href=\""+index+".html\">");
                    printWriter.write("象形");
                    printWriter.write(index);
                    printWriter.write("-</a> ==");
                    printWriter.write("\n");
                }

                printWriter.write("<li>");
                printWriter.write("<a href=\"/" + yin + "/" + key1 + ".html\">");
                printWriter.write(zi);
                printWriter.write("(");
                printWriter.write(key1);
                printWriter.write(")");
                printWriter.write("</a>");
                printWriter.write("</li>\n");
            }

            printWriter.write("</div>");
            printWriter.write("</body>\n");
            printWriter.write("</html>");
            printWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param printWriter
     * @param mapList
     * @param map
     * @param zi
     * @return
     */
    static void ul_li(PrintWriter printWriter, List<Map> mapList, Map<String, Map> map, String zi) {
        Map ziDetail = map.get(zi);
        String yin = ((String) ziDetail.get("yin"));
        String key1 = ((String) ziDetail.get("key1"));
        printWriter.write("<li>\n");
        printWriter.write("<a href=\"/" + yin + "//" + key1 + ".html\">");
        printWriter.write(zi);
        printWriter.write("(");
        printWriter.write(key1);
        printWriter.write(")");
        printWriter.write("</a>\n");

        boolean have = false;
        for (Map map1 : mapList) {
            String jian = ((String) map1.get("jian"));
            if (jian.endsWith(zi) && jian.equals(zi) == false) {
                have = true;
            }
        }
        if (have) {
            printWriter.write("<ul>\n");
            for (Map map1 : mapList) {
                String subZi = ((String) map1.get("zi"));
                String jian = ((String) map1.get("jian"));
                if (jian.endsWith(zi) && jian.equals(zi) == false && jian.length() == 1) {
                    ul_li(printWriter, mapList, map, subZi);
                }
            }
            printWriter.write("<ul>\n");
        }

        printWriter.write("</li>\n");
    }
}
