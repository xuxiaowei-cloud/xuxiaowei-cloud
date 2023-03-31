package cloud.xuxiaowei.oauth2client.http;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.client.RestTemplate;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.nimbusds.jose.JWSAlgorithm.RS256;

/**
 * client_secret_jwt {@link RestTemplate} 示例
 * <p>
 * 需要使用依賴：com.nimbusds:nimbus-jose-jwt
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class RestTemplateClientSecretJwtTests {

	/**
	 * 客户端凭证模式：client_credentials
	 * <p>
	 * 需要使用依賴：com.nimbusds:nimbus-jose-jwt
	 */
	@Test
	void clientCredentials_with_nimbus_jose_jwt() throws JOSEException {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		String clientId = "xuxiaowei_client_client_secret_jwt_id";
		String clientSecret = "6c63d616146a4c3db7d0d2f3ec6d93ce";
		String scope = "snsapi_base snsapi_info";

		// 创建JWT Token声明集合对象
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
			// 设置JWT Token的主题，即客户端的唯一标识符
			.subject(clientId)
			// 设置JWT Token的签发者，即客户端的唯一标识符
			.issuer(clientId)
			// 设置JWT Token的受众，即接收JWT Token的服务器的地址
			.audience("http://localhost:1401")
			// 设置JWT Token的过期时间
			.expirationTime(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
			// 构建JWT Token声明集合对象
			.build();

		// String jwt = signWithHmacSHA256(claimsSet, clientSecret);
		String jwt = signWithRS256(claimsSet, clientSecret);

		Map<String, String> map = new HashMap<>(8);
		map.put("client_id", clientId);
		map.put("scope", scope);
		map.put("client_assertion_type", "urn:ietf:params:oauth:client-assertion-type:jwt-bearer");
		map.put("client_assertion", jwt);

		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

		String accessTokenUri = "http://localhost:1401/oauth2/token"
				+ "?client_id={client_id}&client_assertion={client_assertion}&grant_type=client_credentials&scope={scope}&client_assertion_type={client_assertion_type}";

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(accessTokenUri, httpEntity, String.class,
				map);

		HttpStatus statusCode = responseEntity.getStatusCode();
		log.info(String.valueOf(statusCode));
		log.info(responseEntity.getBody());
	}

	/**
	 * 客户端凭证模式：client_credentials
	 * <p>
	 * 需要使用依賴：spring.security.oauth2.jose:spring-security-oauth2-jose
	 */
	@Test
	void clientCredentials_with_spring_security_oauth2_jose() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		String clientId = "xuxiaowei_client_client_secret_jwt_id";
		String clientSecret = "6c63d616146a4c3db7d0d2f3ec6d93ce";
		String scope = "snsapi_base snsapi_info";

		// 创建JWT Token声明集合对象
		JwtClaimsSet claimsSet = JwtClaimsSet.builder()
			// 设置JWT Token的主题，即客户端的唯一标识符
			.subject(clientId)
			// 设置JWT Token的签发者，即客户端的唯一标识符
			.issuer(clientId)
			// 设置JWT Token的受众，即接收JWT Token的服务器的地址
			.audience(Collections.singletonList("http://localhost:1401"))
			// 设置JWT Token的过期时间
			.expiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
			// 构建JWT Token声明集合对象
			.build();

		String jwt = sign(claimsSet, clientSecret);

		Map<String, String> map = new HashMap<>(8);
		map.put("client_id", clientId);
		map.put("scope", scope);
		map.put("client_assertion_type", "urn:ietf:params:oauth:client-assertion-type:jwt-bearer");
		map.put("client_assertion", jwt);

		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

		String accessTokenUri = "http://localhost:1401/oauth2/token"
				+ "?client_id={client_id}&client_assertion={client_assertion}&grant_type=client_credentials&scope={scope}&client_assertion_type={client_assertion_type}";

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(accessTokenUri, httpEntity, String.class,
				map);

		HttpStatus statusCode = responseEntity.getStatusCode();
		log.info(String.valueOf(statusCode));
		log.info(responseEntity.getBody());
	}

	/**
	 * 需要使用依賴：com.nimbusds:nimbus-jose-jwt
	 */
	public static String signWithHmacSHA256(JWTClaimsSet claimsSet, String clientSecret) throws JOSEException {
		// 使用客户端密钥创建一个加密键
		SecretKeySpec secretKeySpec = new SecretKeySpec(clientSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
		// 使用加密键创建JWT签名者
		JWSSigner signer = new MACSigner(secretKeySpec);
		// 创建具有指定算法的新JWT
		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
		// 对JWT进行签名
		signedJWT.sign(signer);
		// 获取JWT的字符串表示形式
		return signedJWT.serialize();
	}

	/**
	 * 需要使用依賴：com.nimbusds:nimbus-jose-jwt
	 */
	public static String signWithRS256(JWTClaimsSet claimsSet, String clientSecret) throws JOSEException {
		// 使用客户端密钥创建一个加密键
		SecretKeySpec secretKeySpec = new SecretKeySpec(clientSecret.getBytes(StandardCharsets.UTF_8), RS256.getName());
		// 创建JWT Header对象
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
		// 创建JWT Payload对象，并设置声明集合对象
		Payload payload = new Payload(claimsSet.toJSONObject());
		// 创建JWT Object对象，并使用Header和Payload设置JWT
		JWSObject jwsObject = new JWSObject(header, payload);
		// 使用签名者创建JWT签名
		JWSSigner signer = new MACSigner(secretKeySpec);
		jwsObject.sign(signer);
		// 获取JWT的字符串表示形式
		return jwsObject.serialize();
	}

	/**
	 * 需要使用依賴：spring.security.oauth2.jose:spring-security-oauth2-jose
	 */
	public static String sign(JwtClaimsSet claimsSet, String secret) {
		JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
		JWKSource<SecurityContext> jwkSource = new ImmutableSecret<>(secret.getBytes(StandardCharsets.UTF_8));
		NimbusJwtEncoder nimbusJwtEncoder = new NimbusJwtEncoder(jwkSource);
		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(jwsHeader, claimsSet);
		Jwt encode = nimbusJwtEncoder.encode(jwtEncoderParameters);
		return encode.getTokenValue();
	}

}
