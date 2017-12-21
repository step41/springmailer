SpringMailer.Controllers.Messages = {

    api: SpringMailer.Constants.URI_API_ENDPOINT,

	init: function() {

        // initialize this object

	},

	listMessages: function() {
        var v = SpringMailer.Application;
        var t = SpringMailer.Constants.CLIENT_APP_TOKEN_NAME;
        if (token = Vue.prototype[t]) {
            axios({
                method: "get",
                url: SpringMailer.Constants.URI_MESSAGES_ENDPOINT,
                headers: {"Authorization": "Token " + token},
                data: {}
            }).then(
                function(response) {
                    //console.log(response.data);
                    SpringMailer.Application.messages = response.data;
                }
            );
        }

	},

	newMessage: function(dir) {
        var v = SpringMailer.Application;
        var t = SpringMailer.Constants.CLIENT_APP_TOKEN_NAME;
        var fn, fe, tn, te, ms, mb;
        if (token = Vue.prototype[t]) {
            fn = v.from.message_from_name;
            fe = v.from.message_from_email;
            if (fn && fe) {
                $(".springmailer-panel-1, .springmailer-panel-3").hide();
                $(".springmailer-panel-2").toggle("slide", {direction: dir}, 500);
            }
        }

	},

    newSender: function(dir) {
        $(".springmailer-panel-2, .springmailer-panel-3").hide();
        $(".springmailer-panel-1").toggle("slide", {direction: dir}, 500);
    },

    sendMessage: function(dir) {
        var v = SpringMailer.Application;
        var t = SpringMailer.Constants.CLIENT_APP_TOKEN_NAME;
        var fn, fe, tn, te, ms, mb;
        if (token = Vue.prototype[t]) {
            fn = v.from.message_from_name;
            fe = v.from.message_from_email;
            tn = v.to.message_to_name;
            te = v.to.message_to_email;
            ms = v.to.message_subject;
            mb = v.to.message_body;
            // ensure no values are blank
            if (fn && fe && tn && te && ms && mb) {
                // create json data
                var data = {
                    "fromName": fn,
                    "fromEmail": fe,
                    "toName": tn,
                    "toEmail": te,
                    "subject": ms,
                    "body": mb
                };
                //console.log({"data":data});
                axios({
                    method: "post",
                    url: SpringMailer.Constants.URI_MESSAGES_ENDPOINT,
                    headers: {"Authorization": "Token " + token},
                    data: data
                }).then(
                    function(response) {
                        console.log(response.data);
                        SpringMailer.Application.list.messages = response.data;
                        SpringMailer.Application.viewMessages(dir);
                    }
                );
            }
        }

    },

    toggleMessage(id) {
        var tr = $("#message-" + id);
        if ($(tr).is(":visible")) {
            $(tr).animate({ height: "show", opacity: 1 });
        }
        else {
            $(tr).animate({ height: "hide", opacity: 0 });
        }
    },

    viewMessages: function(dir) {
        $(".springmailer-panel-1, .springmailer-panel-2").hide();
        $(".springmailer-panel-3").toggle("slide", {direction: dir}, 500);
    },


}

$(document).ready(function(){
  	SpringMailer.Controllers.Messages.init();
});

