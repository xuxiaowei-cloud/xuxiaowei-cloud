package cloud.xuxiaowei.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositoryPublicKey;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;

/**
 * GitHub API
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class GItHubAPITests {

	private static final String OAUTH_TOKEN = "";

	@Test
	void getPublicKey() throws IOException {
		GitHub github = new GitHubBuilder().withOAuthToken(OAUTH_TOKEN).build();

		GHRepository repo = github.getRepository("xuxiaowei-cloud/xuxiaowei-cloud");

		GHRepositoryPublicKey publicKey = repo.getPublicKey();

		log.info(String.valueOf(publicKey));
	}

}
