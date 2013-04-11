package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.*;
import models.*;

public class Application extends Controller {
  
    final static Form<Query> queryForm = new Form(Query.class);

    public static Result index() {
        //return ok(index.render("My new application is ready."));
        
	// show default chart
	String query = "http://10.64.8.75:4242/q?start=15m-ago&ignore=48&m=sum:servlet.concurrents";

        return ok(chart.render(query,queryForm));
    }

    public static Result chart() {

	Form<Query> filledForm = queryForm.bindFromRequest();
        String host = filledForm.field("host").valueOr("");  // e.g. 10.64.8.75:4242
	String metrics = filledForm.field("metrics").valueOr(""); // e.g. sum:servlet.concurrents
        if (host.isEmpty() || metrics.isEmpty()) {
	    return ok("Missing host or metrics");
            // TODO: to handle better
	}
	//String window = "start=2013/03/21-12:00:00&end=2013/03/22-12:00:00";
	String window = "start=15m-ago&ignore=48";

        String query = new StringBuilder("http://").
	    append(host).append("/q?").append(window).append("&m=").
	    append(metrics).toString();
	
	return ok(chart.render(query,filledForm));
    }

}
