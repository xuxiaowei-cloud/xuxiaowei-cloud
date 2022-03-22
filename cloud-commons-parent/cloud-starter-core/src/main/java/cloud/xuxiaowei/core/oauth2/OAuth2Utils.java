package cloud.xuxiaowei.core.oauth2;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * OAuth2 工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class OAuth2Utils {

    /**
     * 解析参数转 List
     *
     * @param values 参数
     * @return 返回 List
     */
    public static Set<String> parseParameterList(String values) {
        Set<String> result = new TreeSet<>();
        if (values != null && values.trim().length() > 0) {
            String[] tokens = values.split("[\\s+]");
            result.addAll(Arrays.asList(tokens));
        }
        return result;
    }

}
