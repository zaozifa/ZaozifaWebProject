import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.executor.result.DefaultResultHandler;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.*;
import java.util.*;

/**
 * @Date 2020/7/18 9:47
 * @Description 生成单个字的页面
 * @Created by wei
 */

public class ZI {

    public static void main(String[] args) {
        try {
            InputStream inputStream = Resources.getResourceAsStream("config.xml");
            XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream, null, null);
            xmlConfigBuilder.parse();
            SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(xmlConfigBuilder.getConfiguration());
            SqlSession sqlSession = sqlSessionFactory.openSession();
            ResultHandler resultHandler = new DefaultResultHandler();
            List<Map> allHanz = sqlSession.selectList("allHanz");
            System.out.println(allHanz.size());


            boolean b = false;
            if (b) {
                //all
            } else {
                //lastUpdate
            }
            //所有汉字
            List<Map> mapList = allHanz;
            for (Map map : mapList) {
                String jian = ((String) map.get("jian"));
                String zi = ((String) map.get("zi"));
                String yin = ((String) map.get("yin"));
                String js = ((String) map.get("js"));
                String comments = ((String) map.get("comments"));
                String kouyu = ((String) map.get("kouyu"));
                String kx = ((String) map.get("kx"));
                String key1 = ((String) map.get("key1"));


                String prePath = "C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage" + File.separator;
                String path = prePath + yin + File.separator + key1 + ".html";
                File file = new File(path);
                File dir = file.getParentFile();
                if (dir.exists() == false) {
                    dir.mkdirs();
                }
                try {
                    file.createNewFile();
                } catch (Exception e) {

                }
                try {
                    PrintWriter printWriter = new PrintWriter(new FileOutputStream(file));
                    printWriter.write("<!DOCTYPE html>\n");
                    printWriter.write("<html lang=\"zh\">\n");
                    printWriter.write("<head>\n");
                    printWriter.write("    <meta charset=\"UTF-8\">\n");
                    printWriter.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\" />\n");
                    printWriter.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n");
                    printWriter.write("    <link rel=\"shortcut icon\" href=\"favicon.ico\" type=\"image/x-icon\" />\n");
                    printWriter.write("<title>");
                    printWriter.write(zi);
                    printWriter.write(" - 造字法</title>\n");
                    printWriter.write("        <link rel=\"stylesheet\" href=\"../../zaozifa.css\">\n");
                    printWriter.write("    <script src=\"zaozifa.js\"></script>\n");
                    printWriter.write("</head>\n");
                    printWriter.write("<body>\n");
                    printWriter.write("<div id=\"wrap\">\n");
                    printWriter.write("    <div id=\"header\" class=\"\">\n");
                    printWriter.write("        <img alt=\"zaozifa logo\" src=\"logo.png\" width=\"64px\" height=\"64px\">\n");
                    printWriter.write("        <h2><a href=\"/\">造字法</a></h2>\n");
                    printWriter.write("        <p id=\"subtitle\" class=\"meta\"><strong>造字原理基于说文解字、甲骨文、金文、竹简汉字</strong></p>\n");
                    printWriter.write("        <p id=\"nav\">");
                    printWriter.write("            <a href=\"#\" class=\"logo\">首页</a>\n");
                    printWriter.write("            <a href=\"#\" class=\"button\">汉字按部索引</a>\n");
                    printWriter.write("            <a href=\"#\" class=\"button\">相关古书</a>\n");
                    printWriter.write("        </p>\n");
                    printWriter.write("        <div>\n");
                    printWriter.write("            <input type=\"search\" name=\"search-bar\" id=\"search-bar\" placeholder=\"Search Homebrew\"/>\n");
                    printWriter.write("            <button>submit</button>\n");
                    printWriter.write("        </div>\n");
                    printWriter.write("    </div>\n");
                    printWriter.write("    <div id=\"content\">\n");
                    printWriter.write("        <h1>");
                    printWriter.write(zi);
                    printWriter.write("</h1>\n");
                    printWriter.write("        <p>\n");
                    printWriter.write("            <img src=\"ziimg/whan.png\"/>\n");
                    printWriter.write("        </p>\n");
                    printWriter.write("        <p class=\"green\">发声：");
                    printWriter.write(yin==null?"":yin);
                    printWriter.write("</p>\n");
                    printWriter.write("        <p class=\"green\">部件：");
                    printWriter.write(jian);
                    printWriter.write("</p>\n");
                    printWriter.write("        <p><strong class=\"red\">造字法");
                    printWriter.write(zi);
                    printWriter.write("：</strong>");
                    printWriter.write(comments);
                    printWriter.write("        </p>\n");
                    printWriter.write("        <p><strong class=\"meta\">说文解字");
                    printWriter.write(zi);
                    printWriter.write("：</strong>");
                    printWriter.write(js);
                    printWriter.write("        <div id=\"kxzd\"><strong class=\"meta\">康熙字典");
                    printWriter.write(zi);
                    printWriter.write("：</strong><br>\n");
                    printWriter.write("            <p>\n");
                    printWriter.write(kx == null ?"":kx);
                    printWriter.write("            </p>\n");
                    printWriter.write("        </div>\n");
                    printWriter.write("        <div id=\"gswy\"><strong class=\"meta\">古书物语");
                    printWriter.write(zi);
                    printWriter.write("：</strong><br>\n");
                    printWriter.write("            <p>\n");
                    printWriter.write(kouyu == null?"":kouyu);
                    printWriter.write("            </p>\n");
                    printWriter.write("        </div>\n");
                    printWriter.write("    </div>\n");
                    printWriter.write("    <div style=\"padding: 2em\">\n");
                    printWriter.write("        <a href=\"dasang.html\">赏</a>\n");
                    printWriter.write("    </div>\n");
                    printWriter.write("    <div id=\"footer\">\n");
                    printWriter.write("        <p>\n");
                    printWriter.write("            <a>历史版本</a>\n");
                    printWriter.write("            <a href=\"about.html\">关于本站</a>\n");
                    printWriter.write("<select>\n" +
                            "                <option value=\"\">字体</option>\n" +
                            "                <option value=\"\">宋体</option>\n" +
                            "                <option value=\"\">微软雅黑</option>\n" +
                            "                <option value=\"\">黑体</option>\n" +
                            "                <option value=\"\">隶体</option>\n" +
                            "                <option value=\"\">隶体</option>\n" +
                            "            </select>\n" +
                            "            <select>\n" +
                            "                <option value=\"\">字大小</option>\n" +
                            "                <option value=\"\">14px</option>\n" +
                            "                <option value=\"\">16px</option>\n" +
                            "                <option value=\"\">18px</option>\n" +
                            "                <option value=\"\">20px</option>\n" +
                            "                <option value=\"\">22px</option>\n" +
                            "                <option value=\"\">24px</option>\n" +
                            "                <option value=\"\">26px</option>\n" +
                            "                <option value=\"\">28px</option>\n" +
                            "                <option value=\"\">30px</option>\n" +
                            "            </select>\n" +
                            "        </p>\n" +
                            "    </div>\n" +
                            "</div>\n" +
                            "<script>\n" +
                            "    loadSuccessThen();\n" +
                            "</script>\n" +
                            "</body>\n" +
                            "</html>");
                    printWriter.flush();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            sqlSession.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}