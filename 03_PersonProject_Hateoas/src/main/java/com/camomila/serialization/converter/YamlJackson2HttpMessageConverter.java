package com.camomila.serialization.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

// import static com.fasterxml.jackson.annotation.JsonInclude.*;

public final class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {
    public YamlJackson2HttpMessageConverter() {
        super(new YAMLMapper(), MediaType.parseMediaType("application/x-yaml"));

        /**
         * Configuração para não exibir atributos nulos nos Links:
         *     super(new YAMLMapper().setSerializationInclusion(Include.NON_NULL), MediaType.parseMediaType("application/x-yaml"));
         */
    }
}
