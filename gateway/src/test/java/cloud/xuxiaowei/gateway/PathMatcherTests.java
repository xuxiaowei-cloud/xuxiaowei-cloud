package cloud.xuxiaowei.gateway;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * 路径匹配 测试类
 *
 * @author xuxiaowei
 * @see AntPathMatcher
 * @see PathMatcher
 * @since 0.0.1
 */
@Slf4j
class PathMatcherTests {

	/**
	 * <ul>
	 * <li>{@code ?} 匹配一个字符</li>
	 * <li>{@code *} 匹配零个或多个字符</li>
	 * <li>{@code **} 匹配零个或多个 <em>目录</em> 在一条路径中</li>
	 * <li>{@code {spring:[a-z]+}} 匹配正则表达式 {@code [a-z]+} 作为名为的路径变量 "spring"</li>
	 * </ul>
	 *
	 * <h3>例子</h3>
	 * <ul>
	 * <li>{@code com/t?st.jsp} &mdash; matches {@code com/test.jsp} but also
	 * {@code com/tast.jsp} or {@code com/txst.jsp}</li>
	 * <li>{@code com/*.jsp} &mdash; matches all {@code .jsp} files in the {@code com}
	 * directory</li>
	 * <li><code>com/&#42;&#42;/test.jsp</code> &mdash; matches all {@code test.jsp} files
	 * underneath the {@code com} path</li>
	 * <li><code>org/springframework/&#42;&#42;/*.jsp</code> &mdash; matches all
	 * {@code .jsp} files underneath the {@code org/springframework} path</li>
	 * <li><code>org/&#42;&#42;/servlet/bla.jsp</code> &mdash; matches
	 * {@code org/springframework/servlet/bla.jsp} but also
	 * {@code org/springframework/testing/servlet/bla.jsp} and
	 * {@code org/servlet/bla.jsp}</li>
	 * <li>{@code com/{filename:\\w+}.jsp} will match {@code com/test.jsp} and assign the
	 * value {@code test} to the {@code filename} variable</li>
	 * </ul>
	 */
	@Test
	void pathMatcher() {
		AntPathMatcher antPathMatcher = new AntPathMatcher();
		boolean match1 = antPathMatcher.match("/passport/passport-assets/*", "/passport/passport-assets/index.css");
		log.info(String.valueOf(match1));
		boolean match2 = antPathMatcher.match("/passport/passport-assets/*", "/passport/passport-assets/index.js");
		log.info(String.valueOf(match2));

		boolean match3 = antPathMatcher.match("/websocket/broadcast/info", "/websocket/broadcast/info");
		log.info(String.valueOf(match3));
		boolean match4 = antPathMatcher.match("/websocket/broadcast/*/*/websocket",
				"/websocket/broadcast/707/vbcoqzff/websocket");
		log.info(String.valueOf(match4));

		boolean match5 = antPathMatcher.match("/*/actuator/**", "/passport/actuator");
		log.info(String.valueOf(match5));
		boolean match6 = antPathMatcher.match("/*/actuator/**", "/passport/actuator/");
		log.info(String.valueOf(match6));
		boolean match7 = antPathMatcher.match("/*/actuator/**", "/passport/actuator/abc");
		log.info(String.valueOf(match7));
		boolean match8 = antPathMatcher.match("/*/actuator/**", "/passport/actuator/abc/def");
		log.info(String.valueOf(match8));
	}

}
