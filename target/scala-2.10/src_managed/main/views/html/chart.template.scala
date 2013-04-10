
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object chart extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(url: String):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.15*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>Welcom to Yoda console</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*8.54*/routes/*8.60*/.Assets.at("stylesheets/main.css"))),format.raw/*8.94*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*9.59*/routes/*9.65*/.Assets.at("images/favicon.png"))),format.raw/*9.97*/("""">
        <script src=""""),_display_(Seq[Any](/*10.23*/routes/*10.29*/.Assets.at("javascripts/jquery-1.9.0.min.js"))),format.raw/*10.74*/("""" type="text/javascript"></script>
    </head>
    <body>
    <h2>Welcome to Yoda Console</h2>
    <div>
        <span>
        <!-- iframe goes here -->
        <iframe id="iframe1" src=""""),_display_(Seq[Any](/*17.36*/url)),format.raw/*17.39*/("""/q?start=2013/03/21-12:00:00&end=2013/03/22-12:00:00&m=sum:xmlstats.transactions.count&o=&ylabel=TransactionCount&yrange=[0:]&wxh=1200x200&smooth=csplines&png" style="width: 100%; height: 200px" scrolling="yes" marginwidth="0" marginheight="0" frameborder="0" vspace="0" hspace="0">
            <p>Your browser does not support iframes.</p>
        </iframe>
        </span>
    </div>
    </body>
</html>
"""))}
    }
    
    def render(url:String): play.api.templates.Html = apply(url)
    
    def f:((String) => play.api.templates.Html) = (url) => apply(url)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Apr 10 14:44:34 PDT 2013
                    SOURCE: /Users/chi.yao/dev/monitoring-pipeline/dw/app/views/chart.scala.html
                    HASH: 3ad042705998200982131359ef6e0e46f2752990
                    MATRIX: 723->1|813->14|984->150|998->156|1053->190|1149->251|1163->257|1216->289|1277->314|1292->320|1359->365|1584->554|1609->557
                    LINES: 26->1|29->1|36->8|36->8|36->8|37->9|37->9|37->9|38->10|38->10|38->10|45->17|45->17
                    -- GENERATED --
                */
            