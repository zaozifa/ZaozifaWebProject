import com.mysql.cj.util.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Request;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetBook {

    static SqlSession sqlSession;
    static int lineIndex = 0;


    public static void main2(String[] args) {
        String s = "<ul><li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%88%9D%E8%A6%8B%E7%A7%A6\" title=\"韓非子/初見秦\">初見秦第一</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%AD%98%E9%9F%93\" title=\"韓非子/存韓\">存韓第二</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E9%9B%A3%E8%A8%80\" title=\"韓非子/難言\">難言第三</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E6%84%9B%E8%87%A3\" title=\"韓非子/愛臣\">愛臣第四</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E4%B8%BB%E9%81%93\" title=\"韓非子/主道\">主道第五</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E6%9C%89%E5%BA%A6\" title=\"韓非子/有度\">有度第六</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E4%BA%8C%E6%9F%84\" title=\"韓非子/二柄\">二柄第七</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E6%8F%9A%E6%AC%8A\" title=\"韓非子/揚權\">揚權第八</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%85%AB%E5%A7%A6\" title=\"韓非子/八姦\">八姦第九</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%8D%81%E9%81%8E\" title=\"韓非子/十過\">十過第十</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%AD%A4%E6%86%A4\" title=\"韓非子/孤憤\">孤憤第十一</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E8%AA%AA%E9%9B%A3\" title=\"韓非子/說難\">說難第十二</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%92%8C%E6%B0%8F\" title=\"韓非子/和氏\">和氏第十三</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%A5%B8%E5%8A%AB%E5%BC%91%E8%87%A3\" title=\"韓非子/奸劫弑臣\">奸劫弑臣第十四</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E4%BA%A1%E5%BE%B5\" title=\"韓非子/亡徵\">亡徵第十五</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E4%B8%89%E5%AE%88\" title=\"韓非子/三守\">三守第十六</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%82%99%E5%85%A7\" title=\"韓非子/備內\">備內第十七</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%8D%97%E9%9D%A2\" title=\"韓非子/南面\">南面第十八</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E9%A3%BE%E9%82%AA\" title=\"韓非子/飾邪\">飾邪第十九</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E8%A7%A3%E8%80%81\" title=\"韓非子/解老\">解老第二十</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%96%BB%E8%80%81\" title=\"韓非子/喻老\">喻老第二十一</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E8%AA%AA%E6%9E%97%E4%B8%8A\" title=\"韓非子/說林上\">說林上第二十二</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E8%AA%AA%E6%9E%97%E4%B8%8B\" title=\"韓非子/說林下\">說林下第二十三</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E8%A7%80%E8%A1%8C\" title=\"韓非子/觀行\">觀行第二十四</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%AE%89%E5%8D%B1\" title=\"韓非子/安危\">安危第二十五</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%AE%88%E9%81%93\" title=\"韓非子/守道\">守道第二十六</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E7%94%A8%E4%BA%BA\" title=\"韓非子/用人\">用人第二十七</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%8A%9F%E5%90%8D\" title=\"韓非子/功名\">功名第二十八</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%A4%A7%E9%AB%94\" title=\"韓非子/大體\">大體第二十九</a></li></ul><ul><li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%85%A7%E5%84%B2%E8%AA%AA%E4%B8%8A%E4%B8%83%E8%A1%93\" title=\"韓非子/內儲說上七術\">內儲說上七術第三十</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%85%A7%E5%84%B2%E8%AA%AA%E4%B8%8B%E5%85%AD%E5%BE%AE\" title=\"韓非子/內儲說下六微\">內儲說下六微第三十一</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%A4%96%E5%84%B2%E8%AA%AA%E5%B7%A6%E4%B8%8A\" title=\"韓非子/外儲說左上\">外儲說左上第三十二</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%A4%96%E5%84%B2%E8%AA%AA%E5%B7%A6%E4%B8%8B\" title=\"韓非子/外儲說左下\">外儲說左下第三十三</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%A4%96%E5%84%B2%E8%AA%AA%E5%8F%B3%E4%B8%8A\" title=\"韓非子/外儲說右上\">外儲說右上第三十四</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%A4%96%E5%84%B2%E8%AA%AA%E5%8F%B3%E4%B8%8B\" title=\"韓非子/外儲說右下\">外儲說右下第三十五</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E9%9B%A3%E4%B8%80\" title=\"韓非子/難一\">難一第三十六</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E9%9B%A3%E4%BA%8C\" title=\"韓非子/難二\">難二第三十七</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E9%9B%A3%E4%B8%89\" title=\"韓非子/難三\">難三第三十八</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E9%9B%A3%E5%9B%9B\" title=\"韓非子/難四\">難四第三十九</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E9%9B%A3%E5%8B%A2\" title=\"韓非子/難勢\">難勢第四十</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%95%8F%E8%BE%AF\" title=\"韓非子/問辯\">問辯第四十一</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%95%8F%E7%94%B0\" title=\"韓非子/問田\">問田第四十二</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%AE%9A%E6%B3%95\" title=\"韓非子/定法\">定法第四十三</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E8%AA%AA%E7%96%91\" title=\"韓非子/說疑\">說疑第四十四</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E8%A9%AD%E4%BD%BF\" title=\"韓非子/詭使\">詭使第四十五</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%85%AD%E5%8F%8D\" title=\"韓非子/六反\">六反第四十六</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%85%AB%E8%AA%AA\" title=\"韓非子/八說\">八說第四十七</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%85%AB%E7%B6%93\" title=\"韓非子/八經\">八經第四十八</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E4%BA%94%E8%A0%B9\" title=\"韓非子/五蠹\">五蠹第四十九</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E9%A1%AF%E5%AD%B8\" title=\"韓非子/顯學\">顯學第五十</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%BF%A0%E8%80%83\" title=\"韓非子/忠考\">忠孝第五十一</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E4%BA%BA%E4%B8%BB\" title=\"韓非子/人主\">人主第五十二</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E9%A3%AD%E4%BB%A4\" title=\"韓非子/飭令\">飭令第五十三</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%BF%83%E5%BA%A6\" title=\"韓非子/心度\">心度第五十四</a></li>\n" +
                "<li><a href=\"/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E5%88%B6%E5%88%86\" title=\"韓非子/制分\">制分第五十五</a></li></ul>";
        s = s.replaceAll("第(.*?)</a>", "");
        String s2 = s.replaceAll("<[^>]*>","|");

        s2 = s2.replaceAll("\\|\\|\\|", "");
        s2 = s2.replaceAll("\\n", "");
        String[] split = s2.split("\\|\\|\\|");
        for (int i = 0; i < split.length; i++) {
            System.out.println("\"韓非子/" + split[i] + "\",");
        }
//        System.out.println(s2);
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        String[] books = new String[]{
//                "鶡冠子",
//                "鬼谷子",
//                "本经阴符七术",
//                "范子計然",
//                "公孫龍子/1",
//                "公孫龍子/2",
//                "公孫龍子/3",
//                "公孫龍子/4",
//                "公孫龍子/5",
//                "公孫龍子/6",
//                "列子/天瑞篇",
//                "列子/黃帝篇",
//                "列子/周穆王篇",
//                "列子/仲尼篇",
//                "列子/湯問篇",
//                "列子/力命篇",
//                "列子/楊朱篇",
//                "列子/說符篇",
//                "子夏易傳/卷一",
//                "子夏易傳/卷二",
//                "子夏易傳/卷三",
//                "子夏易傳/卷四",
//                "子夏易傳/卷五",
//                "子夏易傳/卷六",
//                "子夏易傳/卷七",
//                "子夏易傳/卷八",
//                "子夏易傳/卷九",
//                "子夏易傳/卷十",
//                "子夏易傳/卷十一",
//                "孔子家語/卷一",
//                "孔子家語/卷二",
//                "孔子家語/卷三",
//                "孔子家語/卷四",
//                "孔子家語/卷五",
//                "孔子家語/卷六",
//                "孔子家語/卷七",
//                "孔子家語/卷八",
//                "孔子家語/卷九",
//                "孔子家語/卷十",
//                "管子/第01篇牧民",
//                "管子/第02篇形勢",
//                "管子/第03篇權修",
//                "管子/第04篇立政",
//                "管子/第05篇乘馬",
//                "管子/第06篇七法",
//                "管子/第07篇版法",
//                "管子/第08篇幼官",
//                "管子/第09篇幼官圖",
//                "管子/第10篇五輔",
//                "管子/第11篇宙合",
//                "管子/第12篇樞言",
//                "管子/第13篇八觀",
//                "管子/第14篇法禁",
//                "管子/第15篇重令",
//                "管子/第16篇法法",
//                "管子/第17篇兵法",
//                "管子/第18篇匡君大匡",
//                "管子/第19篇匡君中匡",
//                "管子/第20篇匡君小匡",
//                "管子/第22篇霸形",
//                "管子/第23篇霸言",
//                "管子/第24篇問",
//                "管子/第26篇戒",
//                "管子/第27篇地圖",
//                "管子/第28篇參患",
//                "管子/第29篇制分",
//                "管子/第30篇君臣上",
//                "管子/第31篇君臣下",
//                "管子/第32篇小稱",
//                "管子/第33篇四稱",
//                "管子/第35篇侈靡",
//                "管子/第36篇心術上",
//                "管子/第37篇心術下",
//                "管子/第38篇白心",
//                "管子/第39篇水地",
//                "管子/第40篇四時",
//                "管子/第41篇五行",
//                "管子/第42篇勢",
//                "管子/第43篇正",
//                "管子/第44篇九變",
//                "管子/第45篇任法",
//                "管子/第46篇明法",
//                "管子/第47篇正世",
//                "管子/第48篇治國",
//                "管子/第49篇內業",
//                "管子/第50篇封禪",
//                "管子/第51篇小問",
//                "管子/第52篇七主七臣",
//                "管子/第53篇禁藏",
//                "管子/第54篇入國",
//                "管子/第55篇九守",
//                "管子/第56篇桓公問",
//                "管子/第57篇度地",
//                "管子/第58篇地員",
//                "管子/第59篇弟子職",
//                "管子/第64篇形勢解",
//                "管子/第65篇立政九敗解",
//                "管子/第66篇版法解",
//                "管子/第67篇明法解",
//                "管子/第68篇臣乘馬",
//                "管子/第69篇乘馬數",
//                "管子/第71篇事語",
//                "管子/第72篇海王",
//                "管子/第73篇國蓄",
//                "管子/第74篇山國軌",
//                "管子/第75篇山權數",
//                "管子/第76篇山至數",
//                "管子/第77篇地數",
//                "管子/第78篇揆度",
//                "管子/第79篇國准",
//                "管子/第80篇輕重甲",
//                "管子/第81篇輕重乙",
//                "管子/第83篇輕重丁",
//                "管子/第84篇輕重戊",
//                "管子/第85篇輕重己",
//                "韓非子/初見秦",
//                "韓非子/存韓",
//                "韓非子/難言",
//                "韓非子/愛臣",
//                "韓非子/主道",
//                "韓非子/有度",
//                "韓非子/二柄",
//                "韓非子/揚權",
//                "韓非子/八姦",
//                "韓非子/十過",
//                "韓非子/孤憤",
//                "韓非子/說難",
//                "韓非子/和氏",
//                "韓非子/奸劫弑臣",
//                "韓非子/亡徵",
//                "韓非子/三守",
//                "韓非子/備內",
//                "韓非子/南面",
//                "韓非子/飾邪",
//                "韓非子/解老",
//                "韓非子/喻老",
//                "韓非子/說林上",
//                "韓非子/說林下",
//                "韓非子/觀行",
//                "韓非子/安危",
//                "韓非子/守道",
//                "韓非子/用人",
//                "韓非子/功名",
//                "韓非子/大體",
//                "韓非子/內儲說上七術",
//                "韓非子/內儲說下六微",
//                "韓非子/外儲說左上",
//                "韓非子/外儲說左下",
//                "韓非子/外儲說右上",
//                "韓非子/外儲說右下",
//                "韓非子/難一",
//                "韓非子/難二",
//                "韓非子/難三",
//                "韓非子/難四",
//                "韓非子/難勢",
//                "韓非子/問辯",
//                "韓非子/問田",
//                "韓非子/定法",
//                "韓非子/說疑",
//                "韓非子/詭使",
//                "韓非子/六反",
//                "韓非子/八說",
//                "韓非子/八經",
//                "韓非子/五蠹",
//                "韓非子/顯學",
//                "韓非子/忠孝",
//                "韓非子/人主",
//                "韓非子/飭令",
//                "韓非子/心度",
//                "韓非子/制分",
//                "素書",
//                "三略",
//                "諫逐客書",
//                "繹山刻石",
//                "商君書/卷一",
//                "商君書/卷二",
//                "商君書/卷三",
//                "商君書/卷四",
//                "商君書/卷五",
//                "法言/01",
//                "法言/02",
//                "法言/03",
//                "法言/04",
//                "法言/05",
//                "法言/06",
//                "法言/07",
//                "法言/08",
//                "法言/09",
//                "法言/10",
//                "法言/11",
//                "法言/12",
//                "法言/13",
//                "自序_(揚雄)",
//                "方言/方言序",
//                "方言/刻方言序",
//                "方言/跋李刻方言",
//                "方言/卷一",
//                "方言/卷二",
//                "方言/卷三",
//                "方言/卷四",
//                "方言/卷五",
//                "方言/卷六",
//                "方言/卷七",
//                "方言/卷八",
//                "方言/卷九",
//                "方言/卷十",
//                "方言/卷十一",
//                "方言/卷十二",
//                "方言/卷十三",
//                "氾勝之書",
//                "白虎通/卷01",
//                "白虎通/卷02",
//                "白虎通/卷03",
//                "白虎通/卷04",
//                "白虎通/卷05",
//                "白虎通/卷07",
//                "白虎通/卷08",
//                "白虎通/卷09",
//                "白虎通/卷10",
//                "呂氏春秋/卷一",
//                "呂氏春秋/卷二",
//                "呂氏春秋/卷三",
//                "呂氏春秋/卷四",
//                "呂氏春秋/卷五",
//                "呂氏春秋/卷六",
//                "呂氏春秋/卷七",
//                "呂氏春秋/卷八",
//                "呂氏春秋/卷九",
//                "呂氏春秋/卷十",
//                "呂氏春秋/卷十一",
//                "呂氏春秋/卷十二",
//                "呂氏春秋/卷十三",
//                "呂氏春秋/卷十四",
//                "呂氏春秋/卷十五",
//                "呂氏春秋/卷十六",
//                "呂氏春秋/卷十七",
//                "呂氏春秋/卷十八",
//                "呂氏春秋/卷十九",
//                "呂氏春秋/卷二十",
//                "呂氏春秋/卷二十一",
//                "呂氏春秋/卷二十二",
//                "呂氏春秋/卷二十三",
//                "呂氏春秋/卷二十四",
//                "呂氏春秋/卷二十五",
//                "呂氏春秋/卷二十六",
//                "海島算經",
//                "山海經/南山經",
//                "山海經/西山經",
//                "山海經/北山經",
//                "山海經/東山經",
//                "山海經/中山經",
//                "山海經/海外南經",
//                "山海經/海外西經",
//                "山海經/海外北經",
//                "山海經/海外東經",
//                "山海經/海內南經",
//                "山海經/海內西經",
//                "山海經/海內北經",
//                "山海經/海內東經",
//                "山海經/大荒東經",
//                "山海經/大荒南經",
//                "山海經/大荒西經",
//                "山海經/大荒北經",
//                "山海經/海內經",
//                "難經",
//                "傷寒論",
//                "金匱要略",
//                "穆天子傳/卷一",
//                "穆天子傳/卷二",
//                "穆天子傳/卷三",
//                "穆天子傳/卷四",
//                "穆天子傳/卷五",
//                "穆天子傳/卷六",
//                "吳越春秋/第001卷",
//                "吳越春秋/第002卷",
//                "吳越春秋/第003卷",
//                "吳越春秋/第004卷",
//                "吳越春秋/第005卷",
//                "吳越春秋/第006卷",
//                "吳越春秋/第007卷",
//                "吳越春秋/第008卷",
//                "吳越春秋/第009卷",
//                "吳越春秋/第010卷",
//                "吳越春秋/佚文",
//                "風俗通義/風俗通義序",
//                "風俗通義/1",
//                "風俗通義/2",
//                "風俗通義/3",
//                "風俗通義/4",
//                "風俗通義/5",
//                "風俗通義/6",
//                "風俗通義/7",
//                "風俗通義/8",
//                "風俗通義/9",
//                "風俗通義/10",
//                "春秋繁露/卷01",
//                "春秋繁露/卷02",
//                "春秋繁露/卷03",
//                "春秋繁露/卷04",
//                "春秋繁露/卷05",
//                "春秋繁露/卷06",
//                "春秋繁露/卷07",
//                "春秋繁露/卷08",
//                "春秋繁露/卷09",
//                "春秋繁露/卷10",
//                "春秋繁露/卷11",
//                "春秋繁露/卷12",
//                "春秋繁露/卷13",
//                "春秋繁露/卷14",
//                "春秋繁露/卷15",
//                "春秋繁露/卷16",
//                "春秋繁露/卷17",
                "水經注/原序",
                "水經注/01",
                "水經注/02",
                "水經注/03",
                "水經注/04",
                "水經注/05",
                "水經注/06",
                "水經注/07",
                "水經注/08",
                "水經注/09",
                "水經注/10",
                "水經注/12",
                "水經注/13",
                "水經注/14",
                "水經注/15",
                "水經注/16",
                "水經注/17",
                "水經注/18",
                "水經注/19",
                "水經注/20",
                "水經注/21",
                "水經注/22",
                "水經注/23",
                "水經注/24",
                "水經注/25",
                "水經注/26",
                "水經注/27",
                "水經注/28",
                "水經注/29",
                "水經注/30",
                "水經注/31",
                "水經注/32",
                "水經注/33",
                "水經注/34",
                "水經注/35",
                "水經注/36",
                "水經注/37",
                "水經注/38",
                "水經注/39",
                "水經注/40",

        };

        for (int i = 0; i < books.length; i++) {
//            getBookFormURL("https://zh.wikisource.org/wiki/" + books[i], books[i]);
            getBookFormURL("https://zh.wikisource.org/wiki/" + books[i], "水經注");
        }

        sqlSession.close();
    }

    static int getBookFormURL(String url, String bookName) throws InterruptedException, IOException {
        Thread.sleep(3000);
        try {
            HttpHost httpHost = new HttpHost("127.0.0.1", 1080);
            String con = Request.Get(url)
                    .viaProxy(httpHost)
                    .connectTimeout(2 * 60 * 1000)
                    .socketTimeout(2 * 60 * 1000)
                    .execute().returnContent().asString();
            if (con != null) {
//                String pattern = "((<h2>(.*?)</h2>)*)\n?((<p>(.*?)\n?</p>)*)";
                String pattern = "(<p>(.*)\n?</p>)";

                {
                    Pattern r = Pattern.compile(pattern);
                    Matcher matcher = r.matcher(con);
                    int matcher_start = 0;
                    int n = 0;
                    while (matcher.find(matcher_start)) {
                        if (matcher.group(1) != null) {
                            int count = matcher.groupCount();
//                            System.out.println(count);
                            String m = matcher.group(count).replaceAll("(<small (.*?)</small>)","");
//                            System.out.println(m);
//                            String p = matcher.group(count).replaceAll("<[^>]*>","");
                            String p = m.replaceAll("<[^>]*>","");
//                            System.out.println(p);
                            if (StringUtils.isEmptyOrWhitespaceOnly(p) == false) {
                                insertBook(sqlSession, bookName, p);
                            }
                        }
                        matcher_start = matcher.end();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0;
    }

    static void insertBook(SqlSession sqlSession, String bookName, String p) {
        //Reads each line
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("bookName", bookName);
        objectObjectHashMap.put("lineIndex", 0);
        objectObjectHashMap.put("like", 0f);
        if (p != null) {
            String string[] = p.split("([，；：。「」！？])");
            //Adding all words generated in previous step into words
            for (String s : string) {
                if (StringUtils.isEmptyOrWhitespaceOnly(s)
                        || s.trim().equals("＝＝＝＝")
                )
                    continue;
                objectObjectHashMap.put("juzi", s.trim());
                objectObjectHashMap.put("lineIndex", lineIndex);
                sqlSession.insert("addBook", objectObjectHashMap);
                sqlSession.commit();
                lineIndex++;
            }
        }
    }

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
