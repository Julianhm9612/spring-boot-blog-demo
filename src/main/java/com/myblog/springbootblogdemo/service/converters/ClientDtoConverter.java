package com.myblog.springbootblogdemo.service.converters;

import java.util.Map;

import com.google.common.collect.Maps;
import com.myblog.springbootblogdemo.dto.ClientDto;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.util.StringUtils;

public class ClientDtoConverter implements Converter<ClientDto, BaseClientDetails> {
    public static final String ADDITIONAL_INFORMATION_KEY = "additional: ";

    @Override
    public BaseClientDetails convert(final MappingContext<ClientDto, BaseClientDetails> context) {
        final ClientDto client = context.getSource();
        final BaseClientDetails baseClientDetails = new BaseClientDetails(client.getClientId(),
                client.getResourcedIds(), client.getScope(), client.getAuthorizedGrantTypes(), client.getAuthorities(),
                client.getWebServerRedirectUri());

        baseClientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
        baseClientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
        baseClientDetails.setClientSecret(client.getClientSecret());
        baseClientDetails.setAutoApproveScopes(StringUtils.commaDelimitedListToSet(client.getAutoApproveScopes()));
        baseClientDetails.setAdditionalInformation(extractAdditionalInformation(client));
        return baseClientDetails;
    }

    private Map<String, String> extractAdditionalInformation(final ClientDto client) {
        final Map<String, String> additionalInfo = Maps.newHashMap();
        additionalInfo.put(ADDITIONAL_INFORMATION_KEY, client.getAdditionalInformation());
        return additionalInfo;
    }
}
