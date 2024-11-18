package mg.kodoro.affichage;

public abstract class HtmlTableObject implements HtmlObject {
    @Override
    public String getHtmlPattern() {
        String html = "    <table class=\"table table-striped mt-3\">\r\n";
        html += getThead();
        html += getTbody();
        html += getTfooter();
        return html += "    </table>\r\n";
    }

    abstract public String getThead();
    abstract public String getTbody();
    abstract public String getTfooter();
}
