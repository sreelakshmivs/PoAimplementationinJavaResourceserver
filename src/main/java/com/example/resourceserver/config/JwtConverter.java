package com.example.resourceserver.config;

import com.example.resourceserver.model.AccessTokenMapper;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtConverter extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {

    @Override
    public void configure(JwtAccessTokenConverter converter) {
        converter.setAccessTokenConverter(this);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication auth = super.extractAuthentication(map);
        AccessTokenMapper details = new AccessTokenMapper();

        //if (map.get("clientId") != null)
        //    details.setClient_id((String) map.get("client_id"));

        if (map.get("jti") != null)
            details.setJti((String) map.get("jti"));

        if (map.get("name") != null)
            details.setName((String) map.get("name"));

        auth.setDetails(details);
        return auth;
    }
}
