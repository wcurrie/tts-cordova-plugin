package au.id.currie.tts;

import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Locale;

public class TextToSpeechPlugin extends CordovaPlugin {

    private static final String TAG = "TextToSpeech";

    private TextToSpeech textToSpeech;

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if ("speak".equals(action)) {
            final String text = args.getString(0);
            final long start = System.currentTimeMillis();
            Log.d(TAG, "Speaking [" + text + "]");
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "1");
            textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {
                }

                @Override
                public void onDone(String utteranceId) {
                    long t = System.currentTimeMillis() - start;
                    Log.d(TAG, "Took " + t + "ms");
                }

                @Override
                public void onError(String utteranceId) {
                }
            });
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, map);
            return true;
        }
        return false;
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        Log.d(TAG, "Initialising");
        textToSpeech = new TextToSpeech(cordova.getActivity().getBaseContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                textToSpeech.setLanguage(Locale.UK);
                Log.d(TAG, "Ready to speak");
            }
        });
    }

    @Override
    public void onPause(boolean multitasking) {
        textToSpeech.stop();
    }

    @Override
    public void onDestroy() {
        textToSpeech.stop();
        textToSpeech.shutdown();
    }
}
