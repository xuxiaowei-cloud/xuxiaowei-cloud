package cloud.xuxiaowei.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Arrays;

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
class SpelExpressionParserTests {

	@Test
	void m1() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'");
		String message = (String) exp.getValue();
		log.info(message);

		assert "Hello World".equals(message);
	}

	@Test
	void m2() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'.concat('!')");
		String message = (String) exp.getValue();
		log.info(message);

		assert "Hello World!".equals(message);
	}

	@Test
	void m3() {
		ExpressionParser parser = new SpelExpressionParser();
		// invokes 'getBytes()'
		Expression exp = parser.parseExpression("'Hello World'.bytes");
		byte[] bytes = (byte[]) exp.getValue();

		assert bytes != null;

		String s = new String(bytes);

		log.info(s);

		assert Arrays.equals("Hello World".getBytes(), bytes);
		assert "Hello World".equals(s);
	}

	@Test
	void m4() {
		ExpressionParser parser = new SpelExpressionParser();
		// invokes 'getBytes().length'
		Expression exp = parser.parseExpression("'Hello World'.bytes.length");
		int length = (Integer) exp.getValue();
		log.info(String.valueOf(length));

		assert 11 == length;
	}

	@Test
	void m5() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("new String('hello world').toUpperCase()");
		String message = exp.getValue(String.class);
		log.info(message);

		assert "HELLO WORLD".equals(message);
	}

}
