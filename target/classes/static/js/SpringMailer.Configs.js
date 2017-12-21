SpringMailer.Configs = {

    uname: SpringMailer.Constants.CLIENT_APP_USER_NAME,

    init: function() {
        // load user data from cookies if present
        var u, c;
        var d = {};
        if (c = SpringMailer.Cookie.get(this.uname)) {
            if (u = c.split("|")) {
                if (u.length === 2) {
                    Vue.prototype[this.uname] = {"fromName": u[0], "fromEmail": u[1]};
                }
            }
        }
    }

};

$(document).ready(function(){
  	SpringMailer.Configs.init();
});

