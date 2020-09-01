import com.mysql.cj.util.StringUtils;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.*;

/**
 * 句子相似度
 */
public class MostRepeatedJUZI {

    public static void main(String[] args) throws Exception {
        String line, word = "";
        int count = 0, maxCount = 0;
        ArrayList<String> juzi = new ArrayList<String>();

        //Opens file in read mode  
        FileReader file = new FileReader("C:\\Users\\zhangxinwei\\Documents\\汉字\\世說新語");
        BufferedReader br = new BufferedReader(file);

        InputStream inputStream = Resources.getResourceAsStream("config.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream, null, null);
        xmlConfigBuilder.parse();
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(xmlConfigBuilder.getConfiguration());
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //Reads each line
        HashMap<String , Object > objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("bookName", "世說新語");
        objectObjectHashMap.put("lineIndex", 0);
        objectObjectHashMap.put("like", 0f);
        int lineIndex = 0;
        while ((line = br.readLine()) != null) {
            String string[] = line.split("([，；：。「」！？])");
            //Adding all words generated in previous step into words
            for (String s : string) {
                if (StringUtils.isEmptyOrWhitespaceOnly(s)
//                        ||s.trim().equals("子墨子曰")
//                        || s.trim().equals("岐伯對曰")
//                        || s.trim().equals("岐伯曰")
//                        || s.trim().equals("帝曰")
                        || s.trim().equals("＝＝＝＝")
                )
                    continue;
//                juzi.add(s.trim());
                objectObjectHashMap.put("juzi", s.trim());
                objectObjectHashMap.put("lineIndex", lineIndex);
                sqlSession.insert("addBook", objectObjectHashMap);
                sqlSession.commit();
                lineIndex++;
            }
        }
        sqlSession.close();

        //比较句子相似度
//        TreeMap<Float, ArrayList<JUZI>> floatArrayListTreeMap = new TreeMap<>();
//        int k = 0;
//        for (String jz : juzi) {
//            int j = 0;
//            label:
//            for (String tjz : juzi) {
//                if (tjz.equals("") || jz.equals("") || k == j) {
//                    j++;
//                    continue;
//                }
//                float similarityRatio = getSimilarityRatio(jz, tjz, true);
//                if (similarityRatio < 0.5) {
//                    j++;
//                    continue;
//                }
//
//                ArrayList<JUZI> juziArrayList = floatArrayListTreeMap.get(similarityRatio);
//                if (juziArrayList != null) {
//                    if (juziArrayList.size() > 10000) {
//                        j++;
//                        continue;
//                    }
//                    for (JUZI juzi1 : juziArrayList) {
//                        if ((k == juzi1.si && j == juzi1.di) || (k == juzi1.di && j == juzi1.si)){
//                            j++;
//                            continue label;
//                        }
//                    }
//                    JUZI juzi1 = new JUZI();
//                    juzi1.src = jz;
//                    juzi1.si = k;
//                    juzi1.dis = tjz;
//                    juzi1.di = j;
//                    juziArrayList.add(juzi1);
//                }else {
//                    ArrayList<JUZI> objects = new ArrayList<>();
//                    JUZI juzi1 = new JUZI();
//                    juzi1.src = jz;
//                    juzi1.si = k;
//                    juzi1.dis = tjz;
//                    juzi1.di = j;
//                    objects.add(juzi1);
//                    floatArrayListTreeMap.put(similarityRatio, objects);
//                }
//                j++;
//            }
//            k++;
//        }
//
//        System.out.println(System.currentTimeMillis());
//        sortMapByKey(floatArrayListTreeMap);
//        Set<Map.Entry<Float, ArrayList<JUZI>>> entries = floatArrayListTreeMap.entrySet();
//        for (Map.Entry<Float, ArrayList<JUZI>> entry : entries) {
//            System.out.println(entry.getKey());
//            for (JUZI juzi1 : entry.getValue()) {
//                System.out.println("\t\t\t\t" + juzi1.src + " " +juzi1.si);
//                System.out.println("\t\t\t\t" + juzi1.dis + " " +juzi1.di);
//            }
//
//        }
//
//
//        System.out.println("Most repeated word: " + word);
        br.close();
    }

    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static Map<Float, ArrayList<JUZI>> sortMapByKey(Map<Float, ArrayList<JUZI>> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<Float, ArrayList<JUZI>> sortMap = new TreeMap<Float, ArrayList<JUZI>>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }

    private static int compare(String str, String target, boolean isIgnore) {
        int d[][]; // 矩阵
        int n = str.length();
        int m = target.length();
        int i; // 遍历str的
        int j; // 遍历target的
        char ch1; // str的
        char ch2; // target的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) { // 初始化第一列
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) { // 初始化第一行
            d[0][j] = j;
        }

        for (i = 1; i <= n; i++) { // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (isIgnore) {
                    if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                        temp = 0;
                    } else {
                        temp = 1;
                    }
                } else {
                    if (ch1 == ch2) {
                        temp = 0;
                    } else {
                        temp = 1;
                    }
                }

                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }

    private static int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }

    public static float getSimilarityRatio(String str, String target, boolean isIgnore) {
        float ret = 0;
        if (Math.max(str.length(), target.length()) == 0) {
            ret = 1;
        } else {
            ret = 1 - (float) compare(str, target, isIgnore) / Math.max(str.length(), target.length());
        }
        return ret;
    }

    static class JUZI {
        public String src;
        public int si;
        public String dis;
        public int di;
    }

    static class MapKeyComparator implements Comparator<Float>{

        @Override
        public int compare(Float str1, Float str2) {

            return str2.compareTo(str1);
        }
    }
}  
