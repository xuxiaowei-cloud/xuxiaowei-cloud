package cloud.xuxiaowei.oauth2.matcher;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.test.context.support.WithMockUser;

/**
 * SpEL 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 * @see <a href=
 * "https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions-evaluation">SpEL
 * 表达式-求值</a>
 * @see <a href=
 * "https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions-bean-references">SpEL
 * 表达式-bean-引用</a>
 */
@Slf4j
@SpringBootTest(classes = AuthorizeAntPathMatcher.class)
class AuthorizeAntPathMatcherTests {

	@Autowired
	private BeanFactory beanFactory;

	@Test
	@WithMockUser(username = "xuxiaowei", roles = { "USER" }, authorities = { "audit_authorization_consent:*" })
	void hasAuthority_true() {
		String expressionString = "@ant.hasAuthority('audit_authorization_consent:read')";
		ExpressionParser expressionParser = new SpelExpressionParser();
		Expression expression = expressionParser.parseExpression(expressionString);
		StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
		standardEvaluationContext.setBeanResolver(new BeanFactoryResolver(beanFactory));
		Boolean value = expression.getValue(standardEvaluationContext, Boolean.class);
		log.info(String.valueOf(value));

		assert value != null;
		assert Boolean.TRUE.equals(value);
	}

	@Test
	@WithMockUser(username = "xuxiaowei", roles = { "USER" }, authorities = { "audit_authorization_consent:delete" })
	void hasAuthority_false() {
		String expressionString = "@ant.hasAuthority('audit_authorization_consent:read')";
		ExpressionParser expressionParser = new SpelExpressionParser();
		Expression expression = expressionParser.parseExpression(expressionString);
		StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
		standardEvaluationContext.setBeanResolver(new BeanFactoryResolver(beanFactory));
		Boolean value = expression.getValue(standardEvaluationContext, Boolean.class);
		log.info(String.valueOf(value));

		assert value != null;
		assert Boolean.FALSE.equals(value);
	}

}
