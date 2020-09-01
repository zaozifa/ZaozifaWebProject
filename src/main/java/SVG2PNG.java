//import org.apache.batik.transcoder.Transcoder;
//import org.apache.batik.transcoder.TranscoderException;
//import org.apache.batik.transcoder.TranscoderInput;
//import org.apache.batik.transcoder.TranscoderOutput;
//import org.apache.batik.transcoder.image.PNGTranscoder;
//import org.apache.ibatis.builder.xml.XMLConfigBuilder;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
//
//import java.io.*;
//import java.util.List;
//import java.util.Map;
//
//public class SVG2PNG {
//    //svg转为png
//    public static void convertSvg2Png(File svg, File png) throws IOException, TranscoderException {
//
//        InputStream in = new FileInputStream(svg);
//        OutputStream out = new FileOutputStream(png);
//        out = new BufferedOutputStream(out);
//
//        Transcoder transcoder = new PNGTranscoder();
//        try {
//            TranscoderInput input = new TranscoderInput(in);
//            try {
//                TranscoderOutput output = new TranscoderOutput(out);
//                transcoder.transcode(input, output);
//            } finally {
//                out.close();
//            }
//        } finally {
//            in.close();
//        }
//    }
//
//
//    public static void main(String args[]) throws Exception {
//        toPNG();
//
////        InputStream inputStream = Resources.getResourceAsStream("config.xml");
////        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream, null, null);
////        xmlConfigBuilder.parse();
////        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(xmlConfigBuilder.getConfiguration());
////        SqlSession sqlSession = sqlSessionFactory.openSession();
////        List<Map> queryMap = sqlSession.selectList("queryMap");
//        //sqlSession.close();
//    }
////        for (Map map : queryMap) {
////            String id = ((String) map.get("id"));
////            String zi = ((String) map.get("zi"));
////            String zdic = "https://www.zdic.net/hans/";
////            if (zi.length() > 1) {
////                String[] zis = zi.split("");
////                int n = 0;
////                for (String s : zis) {
////                    n = getImgFormURL(zdic + s);
////                    if (n > 0) {
////                        break;
////                    }
////                }
////            } else {
////
////            }
////
////        }
////
////
////    }
////
////    static int getImgFormURL(String url) throws InterruptedException, IOException {
////        Thread.sleep(5000);
////        try {
////            String con = Request.Get("http://news.163.com")
////                    .connectTimeout(2 * 60 * 1000)
////                    .socketTimeout(2 * 60 * 1000)
////                    .execute().returnContent().asString();
////            if (con != null) {
////                String jiaguwen = "jiaguwen";
////                String jinwen = "jinwen";
////                String chuwenzi = "chuwenzi";
////                String xiaozhuan = "xiaozhuan";
////            }
////        } catch (IOException e) {
////            System.out.println(url);
////        }
////
////
////        return 0;
////    }
////
//    static void toPNG() throws Exception {
//        {
//            File f = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\zjw");
//            File[] files = f.listFiles();
//
//            for (File t : files) {
//                File destFile = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\zjw\\"+t.getName().replaceAll("[.][^.]+$", "")+".png");
//                try {
//                    convertSvg2Png(t, destFile);
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (TranscoderException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        {
//            File f = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\jjw");
//            File[] files = f.listFiles();
//
//            for (File t : files) {
//                File destFile = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\jjw\\"+t.getName().replaceAll("[.][^.]+$", "")+".png");
//                try {
//                    convertSvg2Png(t, destFile);
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (TranscoderException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }
//
//
//        {
//            File f = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\sw");
//            File[] files = f.listFiles();
//
//            for (File t : files) {
//                File destFile = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\sw\\"+t.getName().replaceAll("[.][^.]+$", "")+".png");
//                try {
//                    convertSvg2Png(t, destFile);
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (TranscoderException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        {
//            File f = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\jw");
//            File[] files = f.listFiles();
//
//            for (File t : files) {
//                File destFile = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\jw\\"+t.getName().replaceAll("[.][^.]+$", "")+".png");
//                try {
//                    convertSvg2Png(t, destFile);
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (TranscoderException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        ChangeSize.main2(null);
//
//    }
//}
