import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestREX {

    @Test
    public void testRex() {
        String s = "<img data-original=\"//img.zdic.net/zy/xiaozhuan/27_E424.svg\" class=\"lazy ypic\">" +
                "<img data-original=\"//img.zdic.net/zy/xiaozhuan/27_E425.svg\" class=\"lazy ypic\">";
        String pattern = "<img data-original=\"//(img.zdic.net/zy/xiaozhuan/(.*?).svg)\" class=\"lazy ypic\">";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(s);
        int matcher_start = 0;
        while (matcher.find(matcher_start)){
            System.out.println(matcher.group(1));
            matcher_start = matcher.end();
        }
    }
}
