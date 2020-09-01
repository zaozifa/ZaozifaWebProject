import com.mysql.cj.util.StringUtils;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.*;
import java.util.*;

public class Cizu2Page {

    public static void main(String[] args) throws IOException {
        String line;
        FileReader file = new FileReader("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\book\\cizu");
        BufferedReader br = new BufferedReader(file);
        while ((line = br.readLine()) != null) {
            if (StringUtils.isEmptyOrWhitespaceOnly(line)) {
                continue;
            }
            cizuSet.add(line.trim());
//            if (cizuSet.size() > 3) {
//                break;
//            }
        }
        file.close();

        HashMap<String, Object> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("cizu", "");
        for (Iterator<String> it = cizuSet.iterator(); it.hasNext(); ) {
            String next = it.next();
            stringStringHashMap.put("cizu", next);
            List<Map> selectBookWithCizu = sqlSession.selectList("selectBookWithCizu", stringStringHashMap);
            String title = null;
            if (selectBookWithCizu.size() > 0) {
                Map map = selectBookWithCizu.get(0);
                int id = ((int) map.get("id"));
                title = ((String) map.get("juzi"));

                StringBuilder sb = new StringBuilder();

                for (Map map1 : selectBookWithCizu) {
                    int id2 = ((int) map1.get("id"));
                    String juzi = ((String) map1.get("juzi"));
                    String bookName0 = ((String) map1.get("bookName"));
                    int lineIndex = ((int) map1.get("lineIndex"));

                    int lineIndex1 = lineIndex-1;
                    stringStringHashMap.put("lineIndex", lineIndex1+"");
                    stringStringHashMap.put("bookName", bookName0);
                    List<Map> selectBookWithlineIndex = sqlSession.selectList("selectBookWithlineIndex1", stringStringHashMap);
                    if (selectBookWithlineIndex.size() > 0) {
                        Map map2 = selectBookWithlineIndex.get(0);
                        String juzi2 = ((String) map2.get("juzi"));
                        sb.append(juzi2.trim());
                        sb.append("，");
                    }


                    sb.append(juzi.trim());
                    sb.append("，");

                    stringStringHashMap.put("lineIndex", lineIndex+"");
                    selectBookWithlineIndex = sqlSession.selectList("selectBookWithlineIndex2", stringStringHashMap);
                    if (selectBookWithlineIndex.size() > 0) {
                        Map map2 = selectBookWithlineIndex.get(0);
                        String juzi2 = ((String) map2.get("juzi"));
                        sb.append(juzi2.trim());
                        sb.append("，");
                    }

                    sb.append(" 【");
                    sb.append(bookName0);
                    sb.append("】<br><br>");


                }

                //to file
                String prePath = "C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\cizu";
                String path = prePath + File.separator + next + ".html";
                File file2 = new File(path);
                File dir = file2.getParentFile();
                if (dir.exists() == false) {
                    dir.mkdirs();
                }
                try(PrintWriter printWriter = new PrintWriter(new FileOutputStream(file2));) {
                    file2.createNewFile();
                    printWriter.write("<!DOCTYPE html>\n");
                    printWriter.write("<html lang=\"zh\">\n");
                    printWriter.write("<head>\n");
                    printWriter.write("    <meta charset=\"UTF-8\">\n");
                    printWriter.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
                    printWriter.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n");
                    printWriter.write("    <meta name=\"keywords\" content=\"汉语语法，汉语学习\">\n");
                    printWriter.write("    <meta name=\"description\" content=\"汉语语法，汉语学习，汉语演变\" />\n");
                    printWriter.write("    <link rel=\"shortcut icon\" href=\"/favicon.ico\" type=\"image/x-icon\" />\n");
                    printWriter.write("    <title>");
                    printWriter.write(title);
                    printWriter.write("句式，汉语语法 - 造字法</title>\n");
                    printWriter.write("    <link  type=\"text/css\" rel=\"stylesheet\" href=\"/zaozifa.css?v=13\"/>\n");
                    printWriter.write("    <script src=\"/zaozifa.js\"></script>\n");
                    printWriter.write("</head>\n");
                    printWriter.write("<body>\n");
                    printWriter.write("<div id=\"wrap\">\n");
                    printWriter.write("    <div id=\"header\" class=\"\">\n");
                    printWriter.write("        <img alt=\"zaozifa logo\" src=\"/logo.png\" width=\"64px\" height=\"64px\">\n");
                    printWriter.write("        <h2><a href=\"/\">造字法</a></h2>\n");
                    printWriter.write("        <p id=\"subtitle\" class=\"meta\"><strong>造字原理基于说文解字、甲骨文、金文、竹简汉字</strong></p>\n");
                    printWriter.write("        <p id=\"nav\">\n");
                    printWriter.write("            <a href=\"/\">首页</a>\n");
                    printWriter.write("            <span>·</span>\n");
                    printWriter.write("            <a href=\"/ABCD.html\">始于象形</a>\n");
                    printWriter.write("            <span>·</span>\n");
                    printWriter.write("            <a href=\"/lastupdate.html\">更新</a>\n");
                    printWriter.write("            <span>·</span>\n");
                    printWriter.write("            <a href=\"/readme.html\">注意</a>\n");
                    printWriter.write("        </p>\n");
                    printWriter.write("        <div id=\"searchbox\">\n");
                    printWriter.write("            <input type=\"text\" id=\"search\"/>\n");
                    printWriter.write("            <button>Baidu</button>\n");
                    printWriter.write("            <button>Google</button>\n");
                    printWriter.write("        </div>\n");
                    printWriter.write("    </div>\n");
                    printWriter.write("    <div id=\"content\">\n");
                    printWriter.write("        <h1>");
                    printWriter.write(title+"句式");
                    printWriter.write("</h1>\n");
                    printWriter.write("        <p class=\"juzi\">\n");
                    printWriter.write(sb.toString());

                    printWriter.write("</p>\n");
                    printWriter.write("    </div>\n");
                    printWriter.write("    <div style=\"padding: 2em\">\n");
                    printWriter.write("        <a href=\"/dasang.html\">赏</a>\n");
                    printWriter.write("    </div>\n");
                    printWriter.write("    <div id=\"footer\">\n");
                    printWriter.write("        <p>\n");
                    printWriter.write("            <a href=\"https://github.com/zaozifa/ZaozifaWebProject\">历史版本</a>\n");
                    printWriter.write("            <span>·</span>\n");
                    printWriter.write("            <a href=\"/about.html\">关于本站</a>\n");
                    printWriter.write("        </p>\n");
                    printWriter.write("    </div>\n");
                    printWriter.write("</div>\n");
                    printWriter.write("<script>\n");
                    printWriter.write("    loadSuccessThen();\n");
                    printWriter.write("</script>\n");
                    printWriter.write("</body>\n");
                    printWriter.write("</html>");
                    printWriter.flush();
                } catch (Exception e) {

                }

            }
        }
    }

    static SqlSession sqlSession;
    static Map<String, GetSameString.Zi> zimap = new HashMap<>();
    static Map<String, Integer> cizuMap = new HashMap<>();
    static Set<String> cizuSet = new HashSet<>();
    static PrintWriter printWriter;
    static int __n = 10;

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
