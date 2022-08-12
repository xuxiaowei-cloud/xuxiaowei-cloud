package cloud.xuxiaowei.system.interceptor;

import cloud.xuxiaowei.system.annotation.EncryptAnnotation;
import cloud.xuxiaowei.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * AES 加密拦截器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Component
public class EncryptHandlerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();

		// 判断是否存在加密注解
		boolean annotationPresent = method.isAnnotationPresent(EncryptAnnotation.class);

		EncryptAnnotation encryptAnnotation = method.getAnnotation(EncryptAnnotation.class);
		if (annotationPresent) {
			// 将加密注解放入响应头中
			response.setHeader(Constant.ENCRYPT, encryptAnnotation.value().version);
		}

		return annotationPresent;
	}

	@Override
	public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull Object handler, Exception ex) throws Exception {

	}

}
