import com.mysql.cj.util.StringUtils;
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

public class GetSameString {
    static SqlSession sqlSession;
    static Map<String, Zi> zimap = new HashMap<>();
    static Map<String, Integer > cizuMap = new HashMap<>();
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

    public static void main(String[] args) throws FileNotFoundException {
        List<Map<String, String>> selectBookAll = sqlSession.selectList("selectBookAll");
        File file = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\book\\cizu");
        printWriter = new PrintWriter(new FileOutputStream(file));
//        List<Map<String, String>> selectBookAll = new ArrayList<>();
//
//        HashMap<String, String> selectBookAllMap = new HashMap<>();
//        selectBookAllMap.put("id", "1");
//        selectBookAllMap.put("juzi", "一路顺风顺风");
//
//        HashMap<String, String > selectBookAllMap1 = new HashMap<>();
//        selectBookAllMap1.put("id", "2");
//        selectBookAllMap1.put("juzi", "顺风顺风");
//
//        HashMap<String, String > selectBookAllMap2 = new HashMap<>();
//        selectBookAllMap2.put("id", "2");
//        selectBookAllMap2.put("juzi", "路顺");
//        selectBookAll.add(selectBookAllMap);
//        selectBookAll.add(selectBookAllMap1);
//        selectBookAll.add(selectBookAllMap2);

        for (Map map : selectBookAll) {
//            String zi = ((String) map.get("juzi")).substring(0,1);
            String juzi = ((String) map.get("juzi")).trim();
            String[] zis = ((String) juzi).split("");


            for (int i = 0; i < zis.length; i++) {
                String zi = zis[i];
                if (StringUtils.isEmptyOrWhitespaceOnly(zi)) {
                    continue;
                }
                if (zimap.get(zi) != null) {
                    Zi ziObj = zimap.get(zi);
                    ziObj.n++;
                    if (ziObj.pre == null) {
                        ziObj.append = zi;

                    } else {
                        ziObj.append = ziObj.pre.append + zi;

                    }
                    insertZi(juzi.substring(i).substring(1), ziObj);
                } else {
                    Zi ziObj = new Zi(zi, null, 1);
                    ziObj.append = zi;
                    zimap.put(zi, ziObj);
                    insertZi(juzi.substring(i).substring(1), ziObj);
                }
            }
        }
        sqlSession.close();
        System.out.println("=============");
        showSame();
        System.out.println("------");
        printWriter.flush();
    }

    static void showSame() {
        for (Map.Entry<String, Zi> entry : zimap.entrySet()) {
            Zi zi = entry.getValue();
//            if (zi.zi.equals("不")) {
                printChild2(zi, zi.childs);

//            }
        }

        for (Map.Entry<String, Integer> entry : cizuMap.entrySet()) {
            String k = entry.getKey();
//            printWriter.write(entry.getValue().intValue()+"");
//            printWriter.write(" ");
            printWriter.println(k);

        }
    }

    static void iterChild(Zi zi, List<Zi> childs) {

    }

    static void printChild2(Zi zi, List<Zi> childs) {
        if (zi.n >= __n) {
            if (zi.append.length() > 1) {
                Integer integer = cizuMap.get(zi.append);
                if (integer != null) {
                    cizuMap.put(zi.append, new Integer(integer.intValue() + 1));
                } else {
                    cizuMap.put(zi.append, new Integer(zi.n));
                }
            }

            for (int i = 0; i < childs.size(); i++) {
                Zi t = childs.get(i);
                if (t.n >= __n) {
                    if (t.append.length() > 1) {
                        Integer integer = cizuMap.get(t.append);
                        if (integer != null) {
                            cizuMap.put(t.append, new Integer(integer.intValue() + 1));
                        } else {
                            cizuMap.put(t.append, new Integer(t.n));
                        }
                    }
                    printChild2(t, t.childs);
                }

            }
        }


    }

    static void printChild(Zi zi, List<Zi> childs) {
        for (int i = 0; i < childs.size(); i++) {
            Zi t = childs.get(i);
            if (t.n >= __n) {
                printWriter.write(t.zi);
                printChild(t, t.childs);
                printWriter.println("");
//                printWriter.write(String.format("%020d ", zi.n));
//                printWriter.write(zi.zi);
                if (zi.childs.size() > 0) {
                    boolean h = false;
                    for (int j = i + 1; j < childs.size(); j++) {
                        Zi zi1 = childs.get(j);
                        if (zi1.n >= __n) {
                            h = true;
                            break;
                        }
                    }
                    if (h) {
                        printWriter.write(zi.zi);
                    }
                }
            }
        }

    }


    static void insertZi(String juziChild, Zi ziObj) {
        if (juziChild.length() <= 0 || StringUtils.isEmptyOrWhitespaceOnly(juziChild))
            return;
        boolean have = false;
        Zi tChild = null;
        for (Zi zio : ziObj.childs) {
            if (juziChild.substring(0, 1).equals(zio.zi)) {
                zio.n++;
                have = true;
                tChild = zio;
                break;
            }
        }
        if (have == false) {
            tChild = new Zi(juziChild.substring(0, 1), ziObj, 1);
            tChild.append = tChild.pre.append + juziChild.substring(0, 1);
            ziObj.childs.add(tChild);
        }
        insertZi(juziChild.substring(1), tChild);
    }

    static class Zi {
        public String zi;
        public Zi pre;
        public int n;
        public List<Zi> childs = new ArrayList<>();
        public String append;

        public Zi(String zi, Zi pre, int n) {
            this.zi = zi;
            this.pre = pre;
            this.n = n;
        }
    }
}
