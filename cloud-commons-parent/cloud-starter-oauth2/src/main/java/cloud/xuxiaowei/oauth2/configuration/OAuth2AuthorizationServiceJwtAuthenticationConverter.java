// package cloud.xuxiaowei.oauth2.configuration;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.convert.converter.Converter;
// import org.springframework.lang.NonNull;
// import org.springframework.security.authentication.AbstractAuthenticationToken;
// import org.springframework.security.oauth2.jwt.Jwt;
// import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
// import
// org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
// import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
// import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
// import
// org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//
/// **
// * JWT {@link OAuth2AuthorizationService}
// *
// * @author xuxiaowei
// * @since 6.0.0
// * @see JwtAuthenticationConverter
// * @see OAuth2AuthorizationService
// * @see JwtAuthenticationConverterConfiguration 与此类配合使用
// */
// @Configuration
// public class OAuth2AuthorizationServiceJwtAuthenticationConverter
// implements Converter<Jwt, AbstractAuthenticationToken> {
//
// private OAuth2AuthorizationService authorizationService;
//
// private JwtAuthenticationConverter jwtAuthenticationConverter;
//
// @Autowired
// public void setAuthorizationService(OAuth2AuthorizationService authorizationService) {
// this.authorizationService = authorizationService;
// }
//
// @Autowired
// public void setJwtAuthenticationConverter(JwtAuthenticationConverter
// jwtAuthenticationConverter) {
// this.jwtAuthenticationConverter = jwtAuthenticationConverter;
// }
//
// @Override
// public final AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
// String tokenValue = jwt.getTokenValue();
// OAuth2Authorization authorization = authorizationService.findByToken(tokenValue,
// OAuth2TokenType.ACCESS_TOKEN);
// if (authorization == null) {
// throw new InvalidBearerTokenException(tokenValue + " not found in
// OAuth2AuthorizationService");
// }
// return jwtAuthenticationConverter.convert(jwt);
// }
//
// }
