package org.ua.deth.javamailsender.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.MultipartConfigElement;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Configuration
public class UploadConfig {
    @Bean
    public MultipartResolver multipartResolver() {
        MultipartResolver multipartResolver = new StandardServletMultipartResolver();
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(DirectoryConfig.getInstance().getDirToCreate(),
                        10, 10 * 2, 10 / 2);

        return multipartResolver;



    }
}
