package cloud.xuxiaowei.utils;

import cloud.xuxiaowei.utils.exception.CloudRuntimeException;

import java.util.List;

/**
 * 断言
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class AssertUtils {

    /**
     * 最小长度断言
     *
     * @param list    数据
     * @param min     最小长度
     * @param message 消息
     */
    public static void min(List<?> list, int min, String message) {
        if (list != null) {
            if (list.size() < min) {
                throw new CloudRuntimeException(message);
            }
        }
    }

    /**
     * 最小长度断言
     *
     * @param list    数据
     * @param min     最小长度
     * @param message 消息
     */
    public static void minNonNull(List<?> list, int min, String message) {
        if (list == null) {
            throw new CloudRuntimeException(message);
        }

        if (list.size() < min) {
            throw new CloudRuntimeException(message);
        }
    }

}
