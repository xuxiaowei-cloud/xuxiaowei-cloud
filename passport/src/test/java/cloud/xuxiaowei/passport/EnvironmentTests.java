package cloud.xuxiaowei.passport;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.cloud.nacos.client.NacosPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.bootstrap.config.BootstrapPropertySource;
import org.springframework.core.env.*;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 环境变量 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@SpringBootTest
class EnvironmentTests {

    @Autowired
    private NacosConfigProperties nacosConfigProperties;

    @Autowired
    private ConfigurableEnvironment configurableEnvironment;

    /**
     * 获取环境变量
     */
    @Test
    void getEnvironment() {
        Environment environment = nacosConfigProperties.getEnvironment();

        MutablePropertySources mutablePropertySources = configurableEnvironment.getPropertySources();
        mutablePropertySources.forEach(propertySource -> {
            if (propertySource instanceof OriginTrackedMapPropertySource) {
                OriginTrackedMapPropertySource originTrackedMapPropertySource = (OriginTrackedMapPropertySource) propertySource;
                String name = originTrackedMapPropertySource.getName();
                Map<String, Object> source = originTrackedMapPropertySource.getSource();

                System.out.println("# 配置文件：" + name);

                for (String key : source.keySet()) {
                    System.out.println(key + "= " + source.get(key));
                }

                System.out.println();
            } else if (propertySource instanceof MapPropertySource) {
                MapPropertySource mapPropertySource = (MapPropertySource) propertySource;

                String name = mapPropertySource.getName();
                Object property = mapPropertySource.getProperty(name);

            } else if (propertySource instanceof BootstrapPropertySource) {

                PropertySource<?> delegate = ((BootstrapPropertySource<?>) propertySource).getDelegate();
                if (delegate instanceof NacosPropertySource) {
                    String name = delegate.getName();

                    if (environment instanceof StandardServletEnvironment) {
                        StandardServletEnvironment standardServletEnvironment = (StandardServletEnvironment) environment;

                        MutablePropertySources propertySources = standardServletEnvironment.getPropertySources();
                        PropertySource<?> bootstrapProperties = propertySources.get("bootstrapProperties-" + name);
                        assert bootstrapProperties != null;
                        Object source = bootstrapProperties.getSource();
                        if (source instanceof LinkedHashMap) {

                            LinkedHashMap<?, ?> linkedHashMap = (LinkedHashMap<?, ?>) source;

                            System.out.println("# 配置文件：" + name);

                            for (Object key : linkedHashMap.keySet()) {
                                System.out.println(key + "= " + linkedHashMap.get(key));
                            }

                            System.out.println();
                        }
                    }
                }
            }
        });
    }

}
