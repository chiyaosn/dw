package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import static play.data.Form.*;

import views.html.*;
import models.*;

public class Application extends Controller {

    final static Form<Query> queryForm = form(Query.class);

    public static Result index() {
        //return ok(index.render("My new application is ready."));

        // show default chart
        Query query = new Query("10.64.8.75:4242", "sum:servlet.concurrents", "15m");

        return ok(chart.render(query, queryForm));
    }

    public static Result chart() {
        Form<Query> filledForm = queryForm.bindFromRequest();
        Query query = filledForm.get();
        //String timewindow = "start=2013/03/21-12:00:00&end=2013/03/22-12:00:00";

        //String host = filledForm.field("host").valueOr("");         // e.g. 10.64.8.75:4242
        //String metric = filledForm.field("metric").valueOr("");     // e.g. sum:servlet.concurrents

        if(query.host.isEmpty() || query.metric.isEmpty()) {
            return ok("Missing host or metrics");
            // TODO
        }

        //return ok(queryData.host + queryData.metric + queryData.timewindow);
        return ok(chart.render(query, filledForm));
    }
}
