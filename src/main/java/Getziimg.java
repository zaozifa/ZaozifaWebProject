import org.apache.http.client.fluent.Request;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Getziimg {
    public static void main(String[] args) throws IOException, InterruptedException {
        InputStream inputStream = Resources.getResourceAsStream("config.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream, null, null);
        xmlConfigBuilder.parse();
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(xmlConfigBuilder.getConfiguration());
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Map> queryMap = sqlSession.selectList("queryMap");
        //sqlSession.close();
        for (Map map : queryMap) {
            long id = ((long) map.get("id"));
            String zi = ((String) map.get("zi"));
            String zdic = "https://www.zdic.net/hans/";
            if (zi != null && zi.length() == 1) {
                String[] zis = zi.split("");
                int n = 0;
                for (String s : zis) {
                    try {
                        n = getImgFormURL(zdic + s, zi, id);
                    } catch (Exception e) {
                        System.out.println("null =====" + zi);
                        continue;
                    }
                    if (n > 0) {
                        break;
                    }
                }
            } else if (zi != null){

            }

        }


    }

    static int getImgFormURL(String url, String zi, long id) throws InterruptedException, IOException {
        Thread.sleep(3000);
        ArrayList jiaguwenarrayList = new ArrayList();
        ArrayList jinwenarrayList = new ArrayList();
        ArrayList carrayList = new ArrayList();
        ArrayList xarrayList = new ArrayList();
        int a = 0;
        try {
            String con = Request.Get(url)
                    .connectTimeout(2 * 60 * 1000)
                    .socketTimeout(2 * 60 * 1000)
                    .execute().returnContent().asString();
            if (con != null) {
                String jiaguwen = "<img data-original=\"//(img.zdic.net/zy/jiaguwen/(.*?).svg)\" class=\"lazy ypic\">";
                String jinwen = "<img data-original=\"//(img.zdic.net/zy/jinwen/(.*?).svg)\" class=\"lazy ypic\">";
                String chuwenzi = "<img data-original=\"//(img.zdic.net/zy/chuwenzi/(.*?).svg)\" class=\"lazy ypic\">";
                String xiaozhuan = "<img data-original=\"//(img.zdic.net/zy/xiaozhuan/(.*?).svg)\" class=\"lazy ypic\">";

                //jiaguwen
                {
                    Pattern r = Pattern.compile(jiaguwen);
                    Matcher matcher = r.matcher(con);
                    int matcher_start = 0;
                    int n = 0;
                    while (matcher.find(matcher_start)){
                        if (matcher.group(1) != null) {
                            savejiaguwenImg(url, matcher.group(1), zi, (int)id, n++);
                        }
                        matcher_start = matcher.end();
                    }
                    a += n;
                }

                //jinwen
                {
                    Pattern r = Pattern.compile(jinwen);
                    Matcher matcher = r.matcher(con);
                    int matcher_start = 0;
                    int n = 0;
                    while (matcher.find(matcher_start)){
                        if (matcher.group(1) != null) {
                            System.out.println(zi);
                            savejinwenImg(url, matcher.group(1), zi, (int)id, n++);
                        }
                        matcher_start = matcher.end();
                    }
                    a += n;

                }

                //chuwenzi
                {
                    Pattern r = Pattern.compile(chuwenzi);
                    Matcher matcher = r.matcher(con);
                    int matcher_start = 0;
                    int n = 0;
                    while (matcher.find(matcher_start)){
                        if (matcher.group(1) != null) {
                            savecImg(url, matcher.group(1), zi, (int)id, n++);
                        }
                        matcher_start = matcher.end();
                    }
                    a += n;

                }

                //xiaozhuan
                {
                    Pattern r = Pattern.compile(xiaozhuan);
                    Matcher matcher = r.matcher(con);
                    int matcher_start = 0;
                    int n = 0;
                    while (matcher.find(matcher_start)){
                        if (matcher.group(1) != null) {
                            savexImg(url, matcher.group(1), zi, (int)id, n++);
                        }
                        matcher_start = matcher.end();
                    }
                    a += n;

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(url + " " + id);
        }


        return a;
    }

    static void savejiaguwenImg(String r, String url, String zi, long id, int i) throws IOException, InterruptedException {
        Thread.sleep(300);
        byte[] bytes = Request.Get("https://"+url)
                .addHeader("Referer", r)
                .connectTimeout(2 * 60 * 1000)
                .socketTimeout(2 * 60 * 1000)
                .execute().returnContent().asBytes();
        File file = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\jjw\\"+id+"_"+i+".svg");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
    }

    static void savejinwenImg(String r, String url, String zi, long id, int i) throws IOException, InterruptedException {
        Thread.sleep(300);
        byte[] bytes = Request.Get("https://"+url)
                .addHeader("Referer", r)
                .connectTimeout(2 * 60 * 1000)
                .socketTimeout(2 * 60 * 1000)
                .execute().returnContent().asBytes();
        File file = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\jw\\"+id+"_"+i+".svg");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
    }

    static void savecImg(String r, String url, String zi, long id, int i) throws IOException, InterruptedException {
        Thread.sleep(300);
        byte[] bytes = Request.Get("https://"+url)
                .addHeader("Referer", r)
                .connectTimeout(2 * 60 * 1000)
                .socketTimeout(2 * 60 * 1000)
                .execute().returnContent().asBytes();
        File file = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\zjw\\"+id+"_"+i+".svg");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
    }

    static void savexImg(String r, String url, String zi, long id, int i) throws IOException, InterruptedException {
        Thread.sleep(300);
        byte[] bytes = Request.Get("https://"+url)
                .addHeader("Referer", r)
                .connectTimeout(2 * 60 * 1000)
                .socketTimeout(2 * 60 * 1000)
                .execute().returnContent().asBytes();
        File file = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\sw\\"+id+"_"+i+".svg");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
    }
}
