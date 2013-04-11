package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        //return ok(index.render("My new application is ready."));
        String url = "http://10.64.8.75:4242";

        return ok(chart.render(url));
    }
  
}
