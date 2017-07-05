import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.util.StringUtil;
import org.junit.Test;

import java.util.Base64;
import java.util.Date;

/**
 * Created by hbowang on 6/8/17.
 */
public class BaseTest {
    @Test
    public void test() {
        System.out.println("-----------------------");
        String str = "category_Lenovo Converged HX 2000 Series_Converged Systems@sungning344";
//        System.out.println(new String(StringUtils.getBytesUtf16(str)));
        System.out.println(Base64.getEncoder().encodeToString(str.getBytes()));
        System.out.println(StringUtils.getBytesUtf16Le(str));
        System.out.println(StringUtils.getBytesUtf16Be(str));
    }

    @Test
    public void test2(){

        System.out.println(new Date(1499057239948L).toLocaleString());
        System.out.println(new Date(1499065200000L).toLocaleString());
        System.out.println(new Date(1499067000000L).toLocaleString());
        System.out.println(new Date(1499068800000L).toLocaleString());
        System.out.println(new Date(1499070600000L).toLocaleString());

    }
}
