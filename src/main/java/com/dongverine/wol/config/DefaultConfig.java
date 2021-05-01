package com.dongverine.wol.config;

import com.dongverine.wol.model.MacAddr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DefaultConfig {

    @Value("${config.macaddrs}")
    List<String> list;

    @Bean(name="MacList")
    public List<MacAddr> getMacList(){
        List<MacAddr> comList = new ArrayList<>();
        list.stream().forEach(item -> {
            String[] macInfo = item.split("#");
            comList.add(new MacAddr(macInfo[0],macInfo[1]));
        });
        return comList;
    }
//    @Bean
//    public HttpMessageConverter<String> responseBodyConverter() {
//        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
//    }
}
