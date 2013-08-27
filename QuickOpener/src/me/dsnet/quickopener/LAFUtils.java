package me.dsnet.quickopener;

import java.text.MessageFormat;

/**
 *
 * @author markiewb
 */
public class LAFUtils {

    /**
     * @param linkText
     * @return htmlCode which is supported by the dark themes in NB74
     */
    public static String convertToLink(String linkText) {
        return MessageFormat.format("<html><a href=\"#\">{0}</a>", linkText);
    }
}
