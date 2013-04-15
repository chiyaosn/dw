package models;

import javax.validation.*;
import play.*;
import play.data.validation.Constraints.*;

public class Query {
    public String metric;
    public enum Aggregation {sum, avg, max, min, dev};
    public Aggregation metricAggregation;
    public String host;
    public String timeWindow;
    public Boolean autoRefresh;

    public Query() {
        metric = "";
        metricAggregation = Aggregation.avg;
        timeWindow = "";
        autoRefresh = false;
        host = "";
    }

    public Query(String h, String m, String tw, Boolean ar) {
        metric = m;
        metricAggregation = Aggregation.avg;
        timeWindow = tw;
        autoRefresh = ar;
        host = h;
    }

    public String getQueryString() {
        String qs = "http://" + Play.application().configuration().getString("openTSDB.host") +
                "/q?start=" + timeWindow + "-ago&m=" + metricAggregation + ":" + metric;

        if(host.isEmpty() == false)
            qs += "{host=" + host + "}";

        return qs;
    }
}
