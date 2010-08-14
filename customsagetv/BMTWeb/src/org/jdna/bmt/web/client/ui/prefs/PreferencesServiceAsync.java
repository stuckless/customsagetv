package org.jdna.bmt.web.client.ui.prefs;


import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PreferencesServiceAsync {
    void searchPreferences(String search, AsyncCallback<PrefItem> callback);
    void getPreferences(PrefItem parent, AsyncCallback<PrefItem[]> callback);
    void savePreferences(PrefItem[] preferences, AsyncCallback<Boolean> callback);
    void getLog4jPreferences(AsyncCallback<Log4jPrefs> callback);
    void saveLog4jPreferences(Log4jPrefs prefs, AsyncCallback<String> status);
    void getVideoSources(AsyncCallback<List<VideoSource>> sources);
    void saveVideoSources(List<VideoSource> sources, AsyncCallback<List<VideoSource>> result);
    void getSageProperties(AsyncCallback<List<PrefItem>> callback);
    void getSagePropertiesAsString(AsyncCallback<String> callback);
    void getSagePropertiesAsList(AsyncCallback<ArrayList<String>> callback);
    void validateRegex(RegexValidation val, AsyncCallback<RegexValidation> callback);
    void getLog4jLoggers(AsyncCallback<String[]> callback);
    void getLog4jProperties(String log, AsyncCallback<ArrayList<PrefItem>> callback);
    void saveLog4jProperties(String log, ArrayList<PrefItem> items, AsyncCallback<Void> callback);
	void refreshConfiguration(String id, AsyncCallback<Void> callback);
}
