package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import static play.data.Form.*;

import views.html.*;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    final static Form<Query> queryForm = form(Query.class);
    static List<Query> queryList = new ArrayList<Query>();

    public static Result index() {
        //return ok(index.render("My new application is ready."));

        // show default chart
        queryList.clear();
        Query query = new Query("host1", "servlet.concurrents", "15m", false);
        queryList.add(query);

        return ok(chart.render(queryList, queryForm));
    }

    public static Result chart() {
        Form<Query> filledForm = queryForm.bindFromRequest();
        Query query = filledForm.get();
        queryList.set(0, query);

        //String timewindow = "start=2013/03/21-12:00:00&end=2013/03/22-12:00:00";

        if(query.metric.isEmpty()) {
            return ok("Missing metric");
            // TODO
        }

        return ok(chart.render(queryList, filledForm));
    }
}
