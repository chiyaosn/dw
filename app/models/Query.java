package models;

import javax.validation.*;
import play.*;
import play.data.validation.Constraints.*;

public class Query {

    final static String otsdbUrl = Play.application().configuration().getString("openTSDB.host");

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

    public final String getQueryString() {

        return new StringBuilder("http://").append(otsdbUrl)
                .append("/q?start=").append(timeWindow).append("-ago&m=").append(metricAggregation)
                .append(":").append(metric)
                .append(host.isEmpty() ? "" : "{host="+host+"}")
                .toString();
    }
}
