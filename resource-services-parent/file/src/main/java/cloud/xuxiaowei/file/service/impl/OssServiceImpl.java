package cloud.xuxiaowei.file.service.impl;

import cloud.xuxiaowei.file.service.OssService;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.DataRedundancyType;
import com.aliyun.oss.model.StorageClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 阿里云 OSS 对象储存 服务接口 实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Service
public class OssServiceImpl implements OssService {

	/**
	 * Java创建存储空间
	 * @see <a href="https://help.aliyun.com/document_detail/32012.htm">Java创建存储空间</a>
	 * @param endpoint
	 * 填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
	 * @param accessKeyId
	 * 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
	 * @param accessKeySecret AccessKey 秘钥
	 * @param bucketName Bucket名称
	 * @param rsId 资源组ID。如果不填写资源组ID，则创建的Bucket属于默认资源组。
	 * @param cannedAcl 设置存储空间读写权限为公共读，默认为私有。
	 * @param storageClass 此处以设置存储空间的存储类型为标准存储为例介绍。
	 * @param dataRedundancyType
	 * 数据容灾类型默认为本地冗余存储，即DataRedundancyType.LRS。如果需要设置数据容灾类型为同城冗余存储，请设置为DataRedundancyType.ZRS。
	 * @throws OSSException 出现错误，拒绝响应
	 * @throws ClientException 连接异常
	 */
	@Override
	public void createBucket(String endpoint, String accessKeyId, String accessKeySecret, String bucketName,
			String rsId, CannedAccessControlList cannedAcl, StorageClass storageClass,
			DataRedundancyType dataRedundancyType) throws OSSException, ClientException {

		// 创建OSSClient实例。
		OSSClientBuilder ossClientBuilder = new OSSClientBuilder();
		OSS ossClient = ossClientBuilder.build(endpoint, accessKeyId, accessKeySecret);

		try {
			// 创建CreateBucketRequest对象。
			CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);

			// 如果创建存储空间的同时需要指定存储类型、存储空间的读写权限、数据容灾类型, 请参考如下代码。
			// 此处以设置存储空间的存储类型为标准存储为例介绍。
			createBucketRequest.setStorageClass(storageClass);
			// 数据容灾类型默认为本地冗余存储，即DataRedundancyType.LRS。如果需要设置数据容灾类型为同城冗余存储，请设置为DataRedundancyType.ZRS。
			createBucketRequest.setDataRedundancyType(dataRedundancyType);
			// 设置存储空间读写权限为公共读，默认为私有。
			createBucketRequest.setCannedACL(cannedAcl);

			// 在支持资源组的地域创建Bucket时，您可以为Bucket配置资源组。
			createBucketRequest.setResourceGroupId(rsId);

			// 创建存储空间。
			ossClient.createBucket(createBucketRequest);
		}
		catch (OSSException oe) {
			log.error("捕获到一个OSSException，这意味着您的请求发送到OSS，但由于某种原因被错误响应拒绝。");
			log.error("错误信息：{}", oe.getErrorMessage());
			log.error("错误代码：{}", oe.getErrorCode());
			log.error("请求标识：{}", oe.getRequestId());
			log.error("主机标识：{}", oe.getHostId());
			log.error("错误堆栈：", oe);
			throw oe;
		}
		catch (ClientException ce) {
			log.error("捕获到ClientException，这意味着客户端在尝试与OSS通信时遇到了严重的内部问题，例如无法访问网络。");
			log.error("错误信息：{}", ce.getErrorMessage());
			log.error("错误代码：{}", ce.getErrorCode());
			log.error("请求标识：{}", ce.getRequestId());
			log.error("错误堆栈：", ce);
			throw ce;
		}
		finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
		}

	}

}
