package com.bankpin.user.ext.mail.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Getter
@RequiredArgsConstructor
public enum MailType {


    HIWORKS(Property.HiworksProperty.getUsername(), Property.HiworksProperty.getPassword()) {
        @Override
        public Properties props() {
            Properties props = new Properties();
            props.put("mail.smtp.host", Property.HiworksProperty.getHost());
            props.put("mail.smtp.port", Property.HiworksProperty.getPort());
            props.put("mail.smtp.auth", Property.HiworksProperty.getSmtpAuth());
            props.put("mail.smtp.ssl.enable", Property.HiworksProperty.getSmtpSslEnable());
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            return props;
        }
    },
    NAVER(Property.NaverProperty.getUsername(), Property.NaverProperty.getPassword()) {
        @Override
        public Properties props() {
            Properties props = new Properties();
            props.put("mail.transport.protocol", Property.NaverProperty.getProtocol());
            props.put("mail.smtp.host", Property.NaverProperty.getHost());
            props.put("mail.smtp.port", Property.NaverProperty.getPort());
            props.put("mail.smtp.auth", Property.NaverProperty.getSmtpAuth());
            props.put("mail.smtp.starttls.enable", Property.NaverProperty.getSmtpStarttlsEnable());
            props.put("mail.smtp.ssl.trust", Property.NaverProperty.getSmtpSslTrust());
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            return props;
        }
    }
    ,
    ;

    private final String email;
    private final String password;

    public abstract Properties props();



    private static class Property {

        @Component
        private static class HiworksProperty {

            @Getter
            private static String host;
            @Value("${spring.mail.hiworks.host}")
            private void setHost(String hosts) {
                host = hosts;
            }

            @Getter
            private static String port;
            @Value("${spring.mail.hiworks.port}")
            private void setPort(String ports) {
                port = ports;
            }

            @Getter
            private static String username;
            @Value("${spring.mail.hiworks.username}")
            private void setUsername(String usernames) {
                username = usernames;
            }

            @Getter
            private static String password;
            @Value("${spring.mail.hiworks.password}")
            private void setPassword(String passwords) {
                password = passwords;
            }

            @Getter
            private static String smtpAuth;
            @Value("${spring.mail.hiworks.properties.mail.smtp.auth}")
            private void setSmtpAuth(String smtpAuths) {
                smtpAuth = smtpAuths;
            }

            @Getter
            private static String smtpSslEnable;
            @Value("${spring.mail.hiworks.properties.mail.smtp.ssl.enable}")
            private void setSmtpSslEnable(String smtpSslEnables) {
                smtpSslEnable = smtpSslEnables;
            }


        }

        @Component
        private static class NaverProperty {

            @Getter
            private static String protocol;
            @Value("${spring.mail.naver.protocol}")
            private void setProtocol(String protocols) {
                protocol = protocols;
            }

            @Getter
            private static String host;
            @Value("${spring.mail.naver.host}")
            private void setHost(String hosts) {
                host = hosts;
            }
            @Getter
            private static String port;
            @Value("${spring.mail.naver.port}")
            private void setPort(String ports) {
                port = ports;
            }

            @Getter
            private static String username;
            @Value("${spring.mail.naver.username}")
            private void setUsername(String usernames) {
                username = usernames;
            }

            @Getter
            private static String password;
            @Value("${spring.mail.naver.password}")
            private void setPassword(String passwords) {
                password = passwords;
            }

            @Getter
            private static String smtpAuth;
            @Value("${spring.mail.naver.properties.mail.smtp.auth}")
            private void setSmtpAuth(String smtpAuths) {
                smtpAuth = smtpAuths;
            }


            @Getter
            private static String smtpStarttlsEnable;
            @Value("${spring.mail.naver.properties.mail.smtp.starttls.enable}")
            private void setSmtpStarttlsEnable(String smtpStarttlsEnables) {
                smtpStarttlsEnable = smtpStarttlsEnables;
            }

            @Getter
            private static String smtpSslTrust;
            @Value("${spring.mail.naver.properties.mail.smtp.ssl.trust}")
            private void setSmtpSslTrust(String smtpSslTrusts) {
                smtpSslTrust = smtpSslTrusts;
            }


        }

    }

}