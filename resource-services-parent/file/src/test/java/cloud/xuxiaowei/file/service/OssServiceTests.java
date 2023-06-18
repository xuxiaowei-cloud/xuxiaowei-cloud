package cloud.xuxiaowei.file.service;

import cloud.xuxiaowei.core.properties.CloudFileProperties;
import cloud.xuxiaowei.utils.Constants;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.DataRedundancyType;
import com.aliyun.oss.model.StorageClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 阿里云 OSS 对象储存 服务接口 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@SpringBootTest
class OssServiceTests {

	@Autowired
	private OssService ossService;

	@Autowired
	private CloudFileProperties cloudFileProperties;

	@Test
	void createBucket() {

		List<CloudFileProperties.OssConfig> ossConfigs = cloudFileProperties.getOssConfigs();

		int size = ossConfigs.size();
		Assert.isTrue(size > 0, "未配置 阿里云OSS 对象储存");

		CloudFileProperties.OssConfig ossConfig = ossConfigs.get(0);

		// yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
		String endpoint = ossConfig.getEndpoint();
		// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
		String accessKeyId = ossConfig.getAccessKeyId();
		String accessKeySecret = ossConfig.getAccessKeySecret();
		// 填写Bucket名称。
		String bucketName = "examplebucket" + RandomStringUtils.random(4, Constants.LOWER_CASE_LETTERS);
		// 填写资源组ID。如果不填写资源组ID，则创建的Bucket属于默认资源组。
		// String rsId = "rg-aek27tc****";
		String rsId = null;
		// 设置存储空间读写权限为公共读，默认为私有。
		CannedAccessControlList cannedAcl = null;
		// 此处以设置存储空间的存储类型为标准存储为例介绍。
		StorageClass storageClass = null;
		// 数据容灾类型默认为本地冗余存储，即DataRedundancyType.LRS。如果需要设置数据容灾类型为同城冗余存储，请设置为DataRedundancyType.ZRS。
		DataRedundancyType dataRedundancyType = null;

		ossService.createBucket(endpoint, accessKeyId, accessKeySecret, bucketName, rsId, cannedAcl, storageClass,
				dataRedundancyType);
	}

}
