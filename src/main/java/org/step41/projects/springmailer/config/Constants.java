package org.step41.projects.springmailer.config;

public class Constants {

    private Constants() {}

    /** Routing Related Constants */
    public static final class Routing {

        private Routing() {}

        public static final String URI_API_ENDPOINT_FILTER = "/api/**";
        public static final String URI_API_ENDPOINT = "/api";
        public static final String URI_TOKEN_ENDPOINT = "/token";
    }

    /** Strings and Message Related Constants */
    public static final class Strings {

        private Strings() {}

        public static final String MSG_AUTHENTICATION_SUCCESS = "Successfully Authenticated";
        public static final String MSG_NOT_AUTHORIZED = "Not Authorized";
        public static final String MSG_JWT_NOT_VALID = "The JSON web token is invalid";
        public static final String MSG_JWT_NOT_FOUND = "The JSON web token was not found";
        public static final String MSG_MAIL_SEND_START = "Sending mail message now...";
        public static final String MSG_MAIL_SEND_FAIL = "An error occurred while attempting to send your message via ";
        public static final String MSG_MAIL_SEND_PASS = "The email was sent successfully!";
        public static final String API_TOKEN_HEADER_NAME = "Authorization";
        public static final String API_TOKEN_HEADER_VALUE_PREFIX = "Token ";
        public static final String APPLICATION_DEFAULT_CHARSET = "UTF-8";

    }

}
