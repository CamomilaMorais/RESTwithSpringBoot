package com.camomila.config;

import com.camomila.serialization.converter.YamlJackson2HttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private static final MediaType MEDIA_TYPE_YAML = MediaType.valueOf("application/x-yaml");

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMessageConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        /**
         * DESC: Implementação de Content Negotiation via EXTENSION: Uso de ".json" ou ".xml" para gerar
         * o formato desejado a partir da URL.
         * Examples: http://localhost:8080/api/person/v1.json
         *
         * configurer.favorParameter(false)
         *                 .ignoreAcceptHeader(false)
         *                 .defaultContentType(MediaType.APPLICATION_JSON)
         *                 .mediaType("json", MediaType.APPLICATION_JSON)
         *                 .mediaType("xml", MediaType.APPLICATION_XML);
         */


        /**
         * DESC: Implementação de Content Negotiation via QUERY: Uso de "mediaType=" para gerar
         * o formato desejado a partir da URL.
         * Examples: http://localhost:8080/api/person/v1?mediaType=xml
         *
         * configurer.favorPathExtension(false)
         *                 .favorParameter(true)
         *                 .parameterName("mediaType")
         *                 .ignoreAcceptHeader(true)
         *                 .useRegisteredExtensionsOnly(false)
         *                 .defaultContentType(MediaType.APPLICATION_JSON)
         *                 .mediaType("json", MediaType.APPLICATION_JSON)
         *                 .mediaType("xml", MediaType.APPLICATION_XML);
         */

        /**
         * DESC: Implementação de Content Negotiation via HEADER: Uso de Key "Accept" e Value "application/
         * xml" ou "application/json" com URL padrão http://localhost:8080/api/person/v1
         */
        configurer.favorPathExtension(false)
                .favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("x-yaml", MEDIA_TYPE_YAML);
    }

}
