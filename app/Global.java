/**
 * Created with IntelliJ IDEA.
 * User: SERVICE-NOW\chi.yao
 * Date: 6/12/13
 * Time: 1:15 PM
 * To change this template use File | Settings | File Templates.
 */

import com.servicenow.bigdata.metadata.MetaDataRepository;
import play.*;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Logger.info("Application has started");
        String hbaseHost = Play.application().configuration().getString("metaDataRepository.hbaseHost");
        if (hbaseHost != null) {
            System.setProperty("metaDataRepository.hbaseHost",hbaseHost);
            Logger.info("metaDataRepository.hbaseHost = " + hbaseHost);
        }
        String warehouse = Play.application().configuration().getString("warehouse.name");
        if (warehouse != null) {
            try {
                MetaDataRepository.useWarehouse(warehouse);
                Logger.info("use warehouse " + warehouse);
            } catch (Exception e) {
                Logger.error("Can not use warehouse " + warehouse);  // use default warehouse instead
            }
        }
        String warehouseCreate = Play.application().configuration().getString("warehouse.create");
        if (warehouseCreate != null && warehouseCreate.equalsIgnoreCase("true")) {
            System.setProperty("warehouse.create","true");
            Logger.info("warehouse.create = true");
        }
    }

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }

}