package cloud.xuxiaowei.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositoryPublicKey;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * GitHub API
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class GItHubAPITests {

	private static final String OAUTH_TOKEN = "";

	private static final String JIHULAB_PRIVATE_TOKEN = "";

	@Test
	void getPublicKey() throws IOException {
		GitHub github = new GitHubBuilder().withOAuthToken(OAUTH_TOKEN).build();

		GHRepository repo = github.getRepository("xuxiaowei-cloud/xuxiaowei-cloud");

		GHRepositoryPublicKey publicKey = repo.getPublicKey();

		log.info(String.valueOf(publicKey));
	}

	@Test
	void createSecret() throws IOException {
		GitHub github = new GitHubBuilder().withOAuthToken(OAUTH_TOKEN).build();

		String targetSuper = "xuxiaowei-com-cn";
		List<String> targetList = Arrays.asList("spring-boot-starter", "spring-boot-starter-alipay-miniprogram",
				"spring-boot-starter-alipay-oplatform", "spring-boot-starter-dingtalk",
				"spring-boot-starter-feishu-webpage", "spring-boot-starter-gitee", "spring-boot-starter-github",
				"spring-boot-starter-gitlab", "spring-boot-starter-idempotent", "spring-boot-starter-qq-connect",
				"spring-boot-starter-qq-miniprogram", "spring-boot-starter-redis",
				"spring-boot-starter-wechat-miniprogram", "spring-boot-starter-wechat-offiaccount",
				"spring-boot-starter-wechat-oplatform", "spring-boot-starter-wechat-work", "spring-boot-starter-weibo",
				"spring-security-oauth2-authorization-server-redis");

		String secretName = "JIHULAB_PRIVATE_TOKEN";

		for (String target : targetList) {
			GHRepository repo = github.getRepository(targetSuper + "/" + target);
			GHRepositoryPublicKey publicKey = repo.getPublicKey();
			String keyId = publicKey.getKeyId();
			repo.createSecret(secretName, JIHULAB_PRIVATE_TOKEN, keyId);
		}

	}

}
