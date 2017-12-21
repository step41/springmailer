var SpringMailer = SpringMailer || {};
SpringMailer.Constants = SpringMailer.Constants || {};
SpringMailer.Cookie = SpringMailer.Cookie || {};
SpringMailer.Utilities = SpringMailer.Utilities || {};
SpringMailer.Configs = SpringMailer.Configs || {};
SpringMailer.Plugins = SpringMailer.Plugins || {};
SpringMailer.Controllers = SpringMailer.Controllers || {};
SpringMailer.Application = SpringMailer.Application || {};

SpringMailer.init = function() {

    var scripts = [
        'SpringMailer.Constants.js',
        'SpringMailer.Cookie.js',
        'SpringMailer.Configs.js',
        'SpringMailer.Utilities.js',
        'SpringMailer.Plugins.Vuelidate.js',
        'SpringMailer.Controllers.Roles.js',
        'SpringMailer.Controllers.Messages.js',
        'SpringMailer.Application.js'
    ];
    var src;
    var script;
    var pendingScripts = [];
    var firstScript = document.scripts[0];

    function stateChange() {
        var pendingScript;
        while (pendingScripts[0] && pendingScripts[0].readyState == 'loaded') {
            pendingScript = pendingScripts.shift();
            pendingScript.onreadystatechange = null;
            firstScript.parentNode.insertBefore(pendingScript, firstScript);
        }
    }

    while (src = scripts.shift()) {
        if ('async' in firstScript) {
            script = document.createElement('script');
            script.async = false;
            script.src = "/js/" + src;
            document.head.appendChild(script);
        }
        else if (firstScript.readyState) {
            script = document.createElement('script');
            pendingScripts.push(script);
            script.onreadystatechange = stateChange;
            script.src = "/js/" + src;
        }
        else {
            document.write('<script src="/js/' + src + '" defer></'+'script>');
        }
    }

};

$(document).ready(function(){
  	SpringMailer.init();
});

