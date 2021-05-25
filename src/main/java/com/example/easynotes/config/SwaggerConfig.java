package com.example.easynotes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.easynotes.AppConstants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

/**
 * @author JS
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${api.hostname:}")
    private String apiHost;

    @Bean
    public Docket apiV1() {
        // @formatter:off
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(AppConstants.ApiV1.CONTROLLER_PKG))
                .paths(PathSelectors.regex(AppConstants.ApiV1.BASE_URL + "/.*"))
                .build()
                .apiInfo(metaData(AppConstants.ApiV1.VERSION))
                .host(apiHost)
                .pathProvider(new BasePathAwareRelativePathProvider(AppConstants.ApiV1.BASE_URL))
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(authScheme()))
                ;
        // @formatter:on
    }

    private ApiInfo metaData(String version) {
        // @formatter:off
        return new ApiInfoBuilder()
                .title(AppConstants.TITLE)
                .description(AppConstants.DESCRIPTION)
                .version(version)
                .build();
        // @formatter:on
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(authReference()))
                .forPaths(PathSelectors.regex(AppConstants.ApiV1.BASE_URL + "/.*"))
                .build();
    }

    private SecurityScheme authScheme() {
        return new BasicAuth("basicAuth");
    }

    private SecurityReference authReference() {
        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
    }

    // From https://github.com/springfox/springfox/issues/963#issuecomment-198551416
    class BasePathAwareRelativePathProvider extends AbstractPathProvider {
        private String basePath;

        public BasePathAwareRelativePathProvider(String basePath) {
            this.basePath = basePath;
        }

        @Override
        protected String applicationPath() {
            return basePath;
        }

        @Override
        protected String getDocumentationPath() {
            return "/";
        }

        @Override
        public String getOperationPath(String operationPath) {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
            return Paths.removeAdjacentForwardSlashes(
                    uriComponentsBuilder.path(operationPath.replaceFirst(basePath, "")).build().toString());
        }
    }
}