package models;

import play.Play;

public class MetaDataQuery {

    public String nameSpace;
    public String key;
    public String instanceId;

    public MetaDataQuery() {
        nameSpace = "";
        key = "";
        instanceId="";
    }

    public MetaDataQuery(String nameSpace, String key, String instanceId) {
        this.nameSpace = nameSpace;
        this.key = key;
        this.instanceId = instanceId;
    }

}
