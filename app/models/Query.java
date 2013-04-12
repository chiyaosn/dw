package models;

import javax.validation.*;

import play.data.validation.Constraints.*;

public class Query {
    public String host;
    public String metric;
    public String timewindow;

    public Query() {
        host = "";
        metric = "";
        timewindow = "";
    }

    public Query(String h, String m, String tw) {
        host = h;
        metric = m;
        timewindow = tw;
    }

    public String GetQueryString() {
        return "http://" + host + "/q?start=" + timewindow + "-ago&m=" + metric;
    }
}
