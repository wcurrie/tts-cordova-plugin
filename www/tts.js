
var exec = require('cordova/exec');

module.exports = {

    speak: function(text) {
        exec(null, null, "TextToSpeech", "speak", [text]);
    }
};
