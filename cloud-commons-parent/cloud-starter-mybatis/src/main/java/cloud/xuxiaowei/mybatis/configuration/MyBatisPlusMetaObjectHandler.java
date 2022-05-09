package cloud.xuxiaowei.mybatis.configuration;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * Mybatis Plus 自动填充
 *
 * @author 徐晓伟
 * @since 0.0.1
 */
@Slf4j
@Configuration
public class MyBatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        strictInsertFill(metaObject, "createDate", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        strictUpdateFill(metaObject, "updateDate", LocalDateTime.class, LocalDateTime.now());
    }

}
