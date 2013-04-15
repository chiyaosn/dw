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
        queryList.add(new Query("host1", "servlet.concurrents", "15m", false));
        queryList.add(new Query("host1", "servlet.transactions", "15m", false));
        queryList.add(new Query("host1", "servlet.responseTime", "15m", false));

        return ok(chart.render(queryList, queryForm));
    }

    public static Result chart() {
        Form<Query> filledForm = queryForm.bindFromRequest();
        Query query = filledForm.get();
        queryList.set(0, query);

        if(query.metric.isEmpty()) {
            return ok("Missing metric");
            // TODO
        }

        return ok(chart.render(queryList, queryForm));
    }

    public static Result ajaxChart(String queryString) {
        return ok(ajax_chart.render(queryString));
    }

    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(Routes.javascriptRouter("jsRoutes",
                // Routes
                controllers.routes.javascript.Application.ajaxChart()
        ));
    }
}
