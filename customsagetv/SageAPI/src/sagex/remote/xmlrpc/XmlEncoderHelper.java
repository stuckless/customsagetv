package sagex.remote.xmlrpc;

import java.io.PrintWriter;
import java.io.StringWriter;

import sagex.remote.RemoteObjectRef;
import sagex.remote.RemoteRequest;
import sagex.remote.RemoteResponse;

public class XmlEncoderHelper {

	public static String createXmlResponse(String api, String command, RemoteRequest request, RemoteResponse response) {
		StringWriter sw = new StringWriter();
		PrintWriter writer = new PrintWriter(sw);

		writeHeader(response, writer);

		// --- write body contents

		Object data = response.getData();
		if (data != null) {
			encodeXmlData(data, writer);
		}

		// --- end body contents

		writeFooter(response, writer);

		return sw.getBuffer().toString();
	}

	public static void encodeXmlData(Object data, PrintWriter writer) {
		if (data instanceof RemoteObjectRef) {
			RemoteObjectRef ref = (RemoteObjectRef) data;
			if (ref.isArray()) {
				writer.printf("<arrayRef ref=\"%s\" size=\"%s\"/>\n", ref.getId(), ref.getArraySize());
			} else {
				writer.printf("<objectRef ref=\"%s\"/>\n", ref.getId());
			}
		} else if (data.getClass().isArray()) {
			Object arr[] = (Object[]) data;
			writer.printf("<array size=\"%s\">\n", arr.length);
			for (int i = 0; i < arr.length; i++) {
				writer.printf("<value index=\"%s\">%s</value>\n", i, arr[i]);
			}
			writer.println("</array>");
		} else {
			// you need to handle vectors and collections...

			writer.printf("<value>%s</value>\n", data);
		}
	}

	public static void writeHeader(RemoteResponse response, PrintWriter writer) {
		writer.println("<response>");
		writer.println("<header>");
		if (response.hasError()) {
			writer.printf("<error code=\"%s\">\n", response.getErrorCode());
			writer.printf("<message><![CDATA[%s]]></message>\n", response.getErrorMessage());
			if (response.getException() != null) {
				writer.printf("<exception><![CDATA[%s]]></exception>\n", response.getException());
			}
			writer.println("</error>");
		}
		writer.println("</header>");
		writer.println("<body>");
	}

	public static void writeFooter(RemoteResponse response, PrintWriter writer) {
		writer.println("</body>");
		writer.println("</response>");
	}
}
