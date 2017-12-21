SpringMailer.Utilities = {

    // Reserved for any global functions required to ensure they're still namespaced

    formatDate: function(t) {
        console.log(t);
        return moment.unix(t/1000).fromNow();
    },

    getToken: function(v) {
        var token, name, email;
        var c = SpringMailer.Constants;
        var cname = c.CLIENT_APP_TOKEN_NAME;
        var uname = c.CLIENT_APP_USER_NAME;
        if (token = SpringMailer.Cookie.get(cname)) {
            Vue.prototype[cname] = token;
            //console.log("SpringMailer token cookie exists with the value: " + token);
            return token;
        }
        else if (v && v.from.message_from_name && v.from.message_from_email) {
            //console.log("SpringMailer cookies not found. Sending request to token endpoint now...");
            name = v.from.message_from_name;
            email = v.from.message_from_email;
            axios({
                method: "post",
                url: SpringMailer.Constants.URI_TOKEN_ENDPOINT,
                headers: {"Content-type": "application/json; charset=utf-8"},
                data: {
                    username: name,
                    name: email,
                    role: "user"
                }
            }).then(
                function(response) {
                    if (token = response.data) {
                        Vue.prototype[cname] = token;
                        SpringMailer.Cookie.set(cname, token);
                        SpringMailer.Cookie.set(uname, name + "|" + email);
                        return token;
                    }
                    console.log("SpringMailer token is missing and new token generation failed.");
                    return false;

                }
            );
        }
        else {
            console.log("Values for Your Name and Your Email fields are missing or invalid");
        }
    }

};

