package org.jdna.media.metadata.impl.xbmc;

import org.apache.commons.lang.StringUtils;


public class Expression {
    private String expression, noClean;
    private boolean clear=true, repeat=false;
    private String noCleanArray[] = null;

    public Expression() {
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * used to specify the replacement bufferst that you do not want to clean of special characters.
     * 
     * @return
     */
    public String getNoClean() {
        return noClean;
    }

    public void setNoClean(String noClean) {
        this.noClean = noClean;
    }

    public boolean isClear() {
        return clear;
    }

    /**
     * use to specify whether of not the dest should be cleared if the expression fails.
     * @param clear
     */
    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public String[] getNoCleanArray() {
        if (!StringUtils.isEmpty(getNoClean()) && noCleanArray==null) {
            noCleanArray = new String[21];
            String vals[] = getNoClean().split(",");
            for (String s: vals) {
               noCleanArray[Integer.parseInt(s)] = s; 
            }
        }
        return noCleanArray;
    }
}
