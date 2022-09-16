package cloud.xuxiaowei.user.service.impl;

import cloud.xuxiaowei.user.entity.Sms;
import cloud.xuxiaowei.user.mapper.SmsMapper;
import cloud.xuxiaowei.user.service.ISmsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 短信表 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-09-16
 */
@Service
public class SmsServiceImpl extends ServiceImpl<SmsMapper, Sms> implements ISmsService {

}
