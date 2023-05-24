package cloud.xuxiaowei.utils;

import lombok.extern.slf4j.Slf4j;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Variable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GitLab API
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class GitLabAPITests {

	private static final String GITLAB_PRIVATE_TOKEN = "";

	/**
	 * @see <a href="https://docs.gitlab.cn/jh/api/project_level_variables.html">项目级别
	 * CI/CD 变量 API</a>
	 */
	@Test
	void getVariables() throws GitLabApiException {
		GitLabApi gitLabApi = new GitLabApi("https://jihulab.com", GITLAB_PRIVATE_TOKEN);

		List<String> projectIdOrPathList = Arrays.asList("xuxiaowei-com-cn/spring-boot-starter");

		for (String projectIdOrPath : projectIdOrPathList) {
			log.info(projectIdOrPath);
			List<Variable> variables = gitLabApi.getProjectApi().getVariables(projectIdOrPath);
			for (Variable variable : variables) {
				log.info("键：{}", variable.getKey());
				log.info("值：{}", variable.getValue());
				log.info("类型：{}", variable.getVariableType());
				log.info("环境范围：{}", variable.getEnvironmentScope());
				log.info("保护变量：{}", variable.getProtected());
				log.info("隐藏变量：{}", variable.getMasked());
			}

			log.info("");
		}
	}

	/**
	 * @see <a href="https://docs.gitlab.cn/jh/api/project_level_variables.html">项目级别
	 * CI/CD 变量 API</a>
	 * @see <a href="https://docs.gitlab.cn/jh/api/group_level_variables.html">群组级别变量
	 * API</a>
	 */
	@Test
	void createVariable() throws GitLabApiException {
		GitLabApi gitLabApi = new GitLabApi("https://jihulab.com", GITLAB_PRIVATE_TOKEN);

		String groupIdOrPath = "xuxiaowei-cloud";
		String projectIdOrPath = groupIdOrPath + "/xuxiaowei-cloud";

		List<String> keyList = Arrays.asList("GITCODE_PRIVATE_TOKEN", "GITEE_PRIVATE_TOKEN", "GITHUB_PRIVATE_TOKEN",
				"GITLAB_PRIVATE_TOKEN");

		String targetSuper = "xuxiaowei-com-cn";
		List<String> targetList = Arrays.asList("spring-boot-starter", "spring-boot-starter-alipay-miniprogram",
				"spring-boot-starter-alipay-oplatform", "spring-boot-starter-dingtalk",
				"spring-boot-starter-feishu-webpage", "spring-boot-starter-gitee", "spring-boot-starter-github",
				"spring-boot-starter-gitlab", "spring-boot-starter-idempotent", "spring-boot-starter-qq-connect",
				"spring-boot-starter-qq-miniprogram", "spring-boot-starter-redis",
				"spring-boot-starter-wechat-miniprogram", "spring-boot-starter-wechat-offiaccount",
				"spring-boot-starter-wechat-oplatform", "spring-boot-starter-wechat-work", "spring-boot-starter-weibo",
				"spring-security-oauth2-authorization-server-redis");

		log.info(groupIdOrPath);
		log.info(projectIdOrPath);
		List<Variable> projectVariables = gitLabApi.getProjectApi().getVariables(projectIdOrPath);
		List<Variable> groupVariables = gitLabApi.getGroupApi().getVariables(groupIdOrPath);
		List<Variable> variables = new ArrayList<>();
		variables.addAll(projectVariables);
		variables.addAll(groupVariables);
		for (Variable variable : variables) {
			if (!keyList.contains(variable.getKey())) {
				continue;
			}

			String key = variable.getKey();
			String value = variable.getValue();
			Variable.Type variableType = variable.getVariableType();
			String environmentScope = variable.getEnvironmentScope();
			Boolean isProtected = variable.getProtected();
			Boolean isMasked = variable.getMasked();

			log.info("键：{}", key);
			log.info("值：{}", value);
			log.info("类型：{}", variableType);
			log.info("环境范围：{}", environmentScope);
			log.info("保护变量：{}", isProtected);
			log.info("隐藏变量：{}", isMasked);

			for (String target : targetList) {
				String targetPath = targetSuper + "/" + target;
				log.info("新增项目：{} 的变量：{}", targetPath, key);
				gitLabApi.getProjectApi()
					.createVariable(targetPath, key, value, variableType, isProtected, isMasked, environmentScope);
			}

		}

		log.info("");
	}

}
