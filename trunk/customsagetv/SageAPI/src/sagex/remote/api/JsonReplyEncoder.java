package sagex.remote.api;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import sagex.remote.builder.SageAPIBuilder;
import sagex.remote.builder.SimpleJSONBuilder;
import sagex.remote.json.JSONObject;

public class JsonReplyEncoder implements ReplyEncoder {
    public String encodeError(Exception e) {
        try {
            JSONObject jo = new JSONObject();
            jo.put("sagexVersion", sagex.api.Version.GetVersion());
            jo.put("error", e.getMessage());
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            jo.put("exception", sw.toString());

            StringWriter sw2 = new StringWriter();
            jo.write(sw2);
            return sw2.toString();
        } catch (Exception ex) {
            return "{error: " + e.getMessage() + "}";
        }
    }

    public String encodeReply(Object o, HttpServletRequest req) throws Exception {
        SimpleJSONBuilder builder = new SimpleJSONBuilder();
        SageAPIBuilder.INSTANCE.build("Result", o, builder, false);

        String jsonCallback = req.getParameter("jsoncallback");
        if (jsonCallback!=null && jsonCallback.trim().length()>0) {
            StringBuilder sb = new StringBuilder(jsonCallback);
            sb.append("(");
            sb.append(builder.toString());
            sb.append(")");
            return sb.toString();
        } else {
            return builder.toString();
        }
    }

    public String getContentType() {
        return "application/json";
    }
}