SpringMailer.Application = new Vue({
    el: SpringMailer.Constants.CLIENT_APP_ROOT,
    data: {
        from: {
            message_from_name: (function() {
                var uname = SpringMailer.Constants.CLIENT_APP_USER_NAME;
                var d = Vue.prototype[uname];
                return (d && d['fromName']) ? d['fromName'] : '';
            })(),
            message_from_email: (function() {
                var uname = SpringMailer.Constants.CLIENT_APP_USER_NAME;
                var d = Vue.prototype[uname];
                return (d && d['fromEmail']) ? d['fromEmail'] : '';
            })(),
        },
        to: {
            message_to_name: '',
            message_to_email: '',
            message_subject: '',
            message_body: '',
        },
        list: {
            messages: [],
        }
    },
    validations: {
        from: {
            message_from_name: {
                required: validators.required,
                maxLength: validators.maxLength(30),
            },
            message_from_email: {
                required: validators.required,
                email: validators.email,
                maxLength: validators.maxLength(50),
            },
        },
        to: {
            message_to_name: {
                required: validators.required,
                maxLength: validators.maxLength(30),
            },
            message_to_email: {
                required: validators.required,
                email: validators.email,
                maxLength: validators.maxLength(50),
            },
            message_subject: {
                required: validators.required,
                maxLength: validators.maxLength(50),
            },
            message_body: {
                required: validators.required,
                maxLength: validators.maxLength(1000),
            }
        },
        list: {
            messages: {}
        }
    },
    methods: {
        checkToken: function() {
            return SpringMailer.Utilities.getToken(this);
        },
        listMessages: function() {
            if (this.checkToken()) {
                SpringMailer.Controllers.Messages.listMessages();
            }
        },
        newMessage: function(dir) {
            if (this.checkToken()) {
                SpringMailer.Controllers.Messages.newMessage(dir);
            }
        },
        newSender: function(dir) {
            if (this.checkToken()) {
                SpringMailer.Controllers.Messages.newSender(dir);
            }
        },
        saveSender: function(dir) {
            if (this.checkToken()) {
                this.newMessage(dir);
            }
        },
        sendMessage: function(dir) {
            if (this.checkToken()) {
                SpringMailer.Controllers.Messages.sendMessage(dir);
            }
        },
        toggleMessage: function(id) {
            SpringMailer.Controllers.Messages.toggleMessage(id);
        },
        viewMessages: function(dir) {
            if (this.checkToken()) {
                SpringMailer.Controllers.Messages.viewMessages(dir);
            }
        },

    }
});

