package cloud.xuxiaowei.file.service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.DataRedundancyType;
import com.aliyun.oss.model.StorageClass;

/**
 * 阿里云 OSS 对象储存 服务接口
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface OssService {

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
	void createBucket(String endpoint, String accessKeyId, String accessKeySecret, String bucketName, String rsId,
			CannedAccessControlList cannedAcl, StorageClass storageClass, DataRedundancyType dataRedundancyType)
			throws OSSException, ClientException;

}
