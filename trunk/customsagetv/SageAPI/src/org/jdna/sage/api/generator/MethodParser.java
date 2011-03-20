package org.jdna.sage.api.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdna.url.URLSaxParser;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class MethodParser extends URLSaxParser {
	public MethodParser(String url) {
		super(url);
	}

	public static class MethodParam {
		String dataType;
		String varName;
        public String getDataType() {
            return dataType;
        }
        public void setDataType(String dataType) {
            this.dataType = dataType;
        }
        public String getVarName() {
            return varName;
        }
        public void setVarName(String varName) {
            this.varName = varName;
        }
	}

	public static class SageMethod {
		public String comment;
		public String returnType;
		public String name;
		public List<MethodParam> args = new ArrayList<MethodParam>();
        public String getComment() {
            return comment;
        }
        public void setComment(String comment) {
            this.comment = comment;
        }
        public String getReturnType() {
            return returnType;
        }
        public void setReturnType(String returnType) {
            this.returnType = returnType;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public List<MethodParam> getArgs() {
            return args;
        }
        public void setArgs(List<MethodParam> args) {
            this.args = args;
        }
	}

	private static final int READING = -1;
	private static final int READ_METHODS = 0;
	private static final int READ_PREFIX = 1;
	private static final int READ_NAME = 2;
	private static final int READ_ARGS = 3;

	// TODO: start with the DL tag and end with the HR tag... preserve all
	// inline HTML in between.
	private static final int LOOK_JAVADOC = 4;
	private static final int READ_JAVADOC = 5;
	private static final int DONE = 99;

	private int state = READING;
	private SageMethod method = null;
	private String charbuf = null;

	private List<SageMethod> methods = new ArrayList<SageMethod>();

	private APIRules rules = new APIRules();
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (state == DONE || state == READING)
			return;

		charbuf = getCharacters(ch, start, length);
		if (charbuf == null || charbuf.length() == 0)
			return;

		if (state == READ_PREFIX) {
			method.returnType = parseReturnType(charbuf);
			state = READ_NAME;
		} else if (state == READ_NAME) {
			method.name = charbuf;
			state = READ_ARGS;
		} else if (state == READ_ARGS) {
			charbuf = charbuf.replaceAll("[()]+", "").trim();

			// Pattern p =
			// Pattern.compile("([a-zA-Z0-9\\._]+)\\s+([a-zA-Z0-9\\._]+)[\\s,]*");
			Pattern p = Pattern.compile("([^\\s]+)\\s+([^\\s,$]+)[\\s,]*");
			Matcher m = p.matcher(charbuf);
			while (m.find()) {
				MethodParam mp = new MethodParam();
				mp.dataType = m.group(1);
				mp.varName = m.group(2);
				method.args.add(mp);
			}

			// state = READ_METHODS;

			methods.add(method);
			
			// let's fix up the API if necessary...
			APIRule rule = rules.getRule(method.name);
			if (rule!=null) {
				if (rule.getRenameTo()!=null) {
					String oldName = method.name;
					System.out.printf("Renaming API from %s to %s\n", oldName, rule.getRenameTo());
					method.name=rule.getRenameTo();
					method.comment = String.format("<i>NOTE: API Was Renamed from <b>%s</b> to <b>%s</b></i><br/></br>\n", oldName, rule.getRenameTo()) + (method.comment==null?"":method.comment);
					// renaming API
				}
			}
			
			// method = null;
			state = LOOK_JAVADOC;
		} else if (state == READ_JAVADOC) {
			method.comment += charbuf;
		}
	}

	private String parseReturnType(String retString) {
		return retString.replaceAll("public|private|static|final|\\s+", "");
	}

	@Override
	public void startElement(String uri, String localName, String name, Attributes atts) throws SAXException {
		if ((state == READ_JAVADOC || state == LOOK_JAVADOC) && isTag("hr", localName)) {
			state = READ_METHODS;
		} else if (state == READ_JAVADOC) {
		} else if (state == LOOK_JAVADOC) {
			if (isTag("dl", localName)) {
				if (method.comment==null) {
					method.comment = "";
				}
				state = READ_JAVADOC;
			}
		} else if (isTag("A", localName)) {
			if ("method_detail".equals(atts.getValue("NAME"))) {
				state = READ_METHODS;
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {
		if (state == READ_JAVADOC) {
			method.comment += "\n";
		}
		if (state == READ_METHODS && isTag("h3", localName)) {
			method = new SageMethod();
			state = READ_PREFIX;
		}
	}

	public List<SageMethod> getMethods() {
		return methods;
	}
}
