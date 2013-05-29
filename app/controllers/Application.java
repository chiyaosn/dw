package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import play.libs.Json;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import static play.data.Form.*;

import views.html.*;
import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.io.IOException;
import java.io.File;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servicenow.bigdata.metadata.MetaDataRepository;

public class Application extends Controller {

    private static final ObjectMapper mapper = new ObjectMapper();

    final static String otsdbUrl = Play.application().configuration().getString("openTSDB.host");
    final static Form<Query> queryForm = form(Query.class);
    final static Form<MetaDataQuery> mdQueryForm = form(MetaDataQuery.class);
    static List<Query> queryList = new ArrayList<Query>();

    public static play.mvc.Result index() {
        //return ok(index.render("My new application is ready."));
        List<String> tabl1 = new ArrayList<String>();
        return ok(landing.render(tabl1, Play.application().configuration().getString("openTSDB.host")));
        /*
        // show default chart
        queryList.clear();
        queryList.add(new Query("host1", "servlet.concurrents", "15m", false));
        queryList.add(new Query("host1", "servlet.transactions", "15m", false));
        queryList.add(new Query("host1", "servlet.responseTime", "15m", false));

        return ok(chart.render(queryList, queryForm, Play.application().configuration().getString("openTSDB.host")));
        */
        //return ok(chart.render(queryList, queryForm, "10.64.8.75:4242"));
    }

    public static play.mvc.Result upload() {
	//File file = request().body().asRaw().asFile();
        //System.out.println(file.getPath()+" "+file.length());
	MultipartFormData body = request().body().asMultipartFormData();
	FilePart filePart = body.getFile("file");
	if (filePart != null) {
	    String fileName = filePart.getFilename();
	    String contentType = filePart.getContentType(); 
	    File file = filePart.getFile();
	    // test
            System.out.println(file.getPath()+" "+file.length());
	    file.renameTo(new File("upload/upload"+System.currentTimeMillis()));
	    return ok("File uploaded");
	} 
	else {
	    return ok("Missing File");
	}
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

    public static play.mvc.Result metadataQueryNameSpaces() {
        org.codehaus.jackson.JsonNode result = null;
        try {
            Collection<String> nameSpaces = MetaDataRepository.getNameSpaces();
            result = Json.toJson(nameSpaces.toArray());
        } catch (IOException e) {
            result = Json.toJson("");  // empty string for no result
            // TODO: use status
        }
        return ok(result);
    }

    public static play.mvc.Result metadataQueryNameSpaceInstances() {
        MetaDataQuery mdQuery = mdQueryForm.bindFromRequest().get();
        org.codehaus.jackson.JsonNode result = null;
        try {
            if (mdQuery==null) {
                result = Json.toJson("");
            }
            else {
                System.out.println("nameSpace = "+mdQuery.nameSpace);
                Collection<String> keys = MetaDataRepository.getInstances(mdQuery.nameSpace);
                result = Json.toJson(keys.toArray());
            }
        } catch (IOException e) {
            result = Json.toJson("");  // empty string for no result
            // TODO: use status
        }
        return ok(result);
    }

    public static play.mvc.Result metadataQueryNameSpaceKeys() {
        MetaDataQuery mdQuery = mdQueryForm.bindFromRequest().get();
        org.codehaus.jackson.JsonNode result = null;
        try {
            if (mdQuery==null) {
                result = Json.toJson("");
            }
            else {
                System.out.println("nameSpace = "+mdQuery.nameSpace);
                Collection<String> keys = MetaDataRepository.getKeys(mdQuery.nameSpace);
                result = Json.toJson(keys.toArray());
            }
        } catch (IOException e) {
            result = Json.toJson("");  // empty string for no result
            // TODO: use status
        }
        return ok(result);
    }

    public static play.mvc.Result metadataQueryNameSpaceKeyValues() {
        MetaDataQuery mdQuery = mdQueryForm.bindFromRequest().get();
        org.codehaus.jackson.JsonNode result = null;
        try {
            if (mdQuery==null) {
                result = Json.toJson("");
            }
            else {
                System.out.println("nameSpace = "+mdQuery.nameSpace);
                System.out.println("key = "+mdQuery.key);
                System.out.println("instanceId = "+mdQuery.instanceId);
                Collection<String> values = MetaDataRepository.getValues(mdQuery.nameSpace,mdQuery.key,mdQuery.instanceId);
                result = Json.toJson(values.toArray());
            }
        } catch (IOException e) {
            result = Json.toJson("");  // empty string for no result
            // TODO: use status
        }
        return ok(result);
    }

}
