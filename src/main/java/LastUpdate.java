import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LastUpdate {

    public static void main2(String[] args) {
        boolean b = false;
        if (b) {
            //all
        } else {
            //lastUpdate
        }

        String path = "C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\lastupdate.html";
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
            printWriter.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
            printWriter.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n");
            printWriter.write("    <meta name=\"keywords\" content=\"汉字，说文解字，甲骨文\">\n");
            printWriter.write("    <meta name=\"description\" content=\"造字法，汉字，简体字，甲骨文，金文，异体字，音韵方言，部首笔画，康熙字典，说文解字，字源字形\" />\n");
            printWriter.write("    <link rel=\"shortcut icon\" href=\"/favicon.ico\" type=\"image/x-icon\" />\n");
            printWriter.write("    <title>象形汉字更新 - 造字法</title>");
            printWriter.write("    <link  type=\"text/css\" rel=\"stylesheet\" href=\"/zaozifa.css?v=13?v=1\"/>\n");
            printWriter.write("    <script src=\"/zaozifa.js\"></script>\n");
            printWriter.write("</head>\n");
            printWriter.write("<body>\n");

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
            printWriter.write("         <h1>象形汉字最近更新</h1>\n");
            printWriter.write("         <div id=\"lastupdatepage\">\n");

            //main
            //所有象形 oder by length(key1),key1
            InputStream inputStream = Resources.getResourceAsStream("config.xml");
            XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream, null, null);
            xmlConfigBuilder.parse();
            SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(xmlConfigBuilder.getConfiguration());
            SqlSession sqlSession = sqlSessionFactory.openSession();
            List<Map> allHanz = sqlSession.selectList("lastupdate");
            System.out.println(allHanz.size());

            Map<String, Map> map = new HashMap<>();
            for (Map t : allHanz) {
                map.put(((String) t.get("zi")), t);
            }
            List<Map> mapList = allHanz;

            printWriter.write("<ul>\n");
            for (Map map1 : allHanz) {
                String zi = ((String) map1.get("zi"));
                String key1 = ((String) map1.get("key1"));
                String yin = ((String) map1.get("yin"));
                Date t = ((Date) map1.get("lastupdate"));
                printWriter.write("<li>");
                printWriter.write("<a href=\"/" + yin + "/" + key1 + ".html\">");
                printWriter.write(zi);
                printWriter.write("(");
                printWriter.write(key1==null?"":key1);
                printWriter.write(")");
                printWriter.write("</a>");

                printWriter.write("<span>");
                printWriter.write(t.toString());
                printWriter.write("</span>");
                printWriter.write("</li>\n");
            }
            printWriter.write("</ul>");

            printWriter.write("</div>");
            printWriter.write("</div>\n");

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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
