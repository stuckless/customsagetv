package sagex.remote.builder;

import java.io.StringWriter;
import java.util.Stack;

import sagex.remote.json.JSONArray;
import sagex.remote.json.JSONException;
import sagex.remote.json.JSONObject;

public class SimpleJSONBuilder implements BuilderHandler {
    JSONObject        obj  = new JSONObject();
    Object        cur  = obj;
    Stack<Object> objs = new Stack<Object>();
    JSONArray arr = null;
    
    public SimpleJSONBuilder() {
        // push our main object onto the stack
        objs.push(obj);
    }

    public void beginArray(String name, int size) {
        arr = new JSONArray();
        if (cur instanceof JSONObject) {
            try {
                ((JSONObject)cur).put(name, arr);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (cur instanceof JSONArray) {
            ((JSONArray)cur).put(arr);
        }
        objs.push(arr);
        cur=arr;
    }

    public void endArray(String name) {
        objs.pop();
        cur = objs.lastElement();
    }

    public void beginObject(String objectName) {
        JSONObject o = new JSONObject();
        if (cur instanceof JSONObject) {
            try {
                ((JSONObject)cur).put(objectName, o);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (cur instanceof JSONArray) {
            ((JSONArray)cur).put(o);
        }
        objs.push(o);
        cur = o;
    }

    public void endObject(String objectName) {
        objs.pop();
        cur = objs.lastElement();
    }

    public void handleError(String string, Exception e) throws Exception {
        //beginObject("error");
        //handleField("field", string);
        //handleField("message", e.getMessage());
        //endObject("error");
        throw new Exception(string, e);
    }

    public void handleField(String name, Object data) {
        try {
            if (cur instanceof JSONObject) {
                ((JSONObject)cur).put(name, data);
            } else if (cur instanceof JSONArray) {
                ((JSONArray)cur).put(data);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String toString() {
        StringWriter w = new StringWriter();
        try {
            obj.write(w);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        w.flush();
        return w.toString();
    }
}
