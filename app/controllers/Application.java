package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        //return ok(index.render("My new application is ready."));
        String url = "http://127.0.0.1:4242";
        /*
        <iframe id="iframe1" src="http://127.0.0.1:4242/q?start=2013/03/21-12:00:00&end=2013/03/22-12:00:00&m=sum:xmlstats.transactions.count&o=&ylabel=TransactionCount&yrange=[0:]&wxh=1200x200&smooth=csplines&png" style="width: 100%; height: 200px" scrolling="yes" marginwidth="0" marginheight="0" frameborder="0" vspace="0" hspace="0">
            <p>Your browser does not support iframes.</p>
        </iframe>
        */
        return ok(chart.render(url));
    }
  
}
