package vn.edu.fu.veazy.core.common.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HtmlUtils {
    
    public static boolean isEmptyDocument(String document) {
        if (Utils.isNullOrEmpty(document)) return true;
        Document doc = Jsoup.parse(document);
        if (doc == null) return false;
        Elements el = doc.select(":containsOwn(\u00a0)");
        if (el != null) {
            el.remove();
        }
        return Utils.isNullOrEmpty(doc.text());
    }
    
}
