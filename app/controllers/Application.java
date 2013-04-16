package controllers;

import org.apache.hadoop.hbase.KeyValue;
import play.*;
import play.mvc.*;
import play.data.*;

import static play.data.Form.*;

import views.html.*;
import models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

public class Application extends Controller {

    final static String otsdbUrl = Play.application().configuration().getString("openTSDB.host");
    final static Form<Query> queryForm = form(Query.class);
    static List<Query> queryList = new ArrayList<Query>();
    static Configuration hbaseConf = HBaseConfiguration.create();

    public static play.mvc.Result index() {
        //return ok(index.render("My new application is ready."));

        // show default chart
        queryList.clear();
        queryList.add(new Query("host1", "servlet.concurrents", "15m", false));
        queryList.add(new Query("host1", "servlet.transactions", "15m", false));
        queryList.add(new Query("host1", "servlet.responseTime", "15m", false));

        return ok(chart.render(queryList, queryForm, Play.application().configuration().getString("openTSDB.host")));
        //return ok(chart.render(queryList, queryForm, "10.64.8.75:4242"));
    }

    public static play.mvc.Result chart() {
        Form<Query> filledForm = queryForm.bindFromRequest();
        Query query = filledForm.get();
        queryList.set(0, query);

        if(query.metric.isEmpty()) {
            return ok("Missing metric");
            // TODO
        }

        return ok(chart.render(queryList, queryForm, otsdbUrl));
    }

    public static play.mvc.Result ajaxChart(String queryString) {
        return ok(ajax_chart.render(queryString));
    }

    public static play.mvc.Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(Routes.javascriptRouter("jsRoutes",
                // Routes
                controllers.routes.javascript.Application.ajaxChart()
        ));
    }
}
