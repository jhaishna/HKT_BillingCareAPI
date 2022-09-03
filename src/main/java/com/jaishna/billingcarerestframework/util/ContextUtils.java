package com.jaishna.billingcarerestframework.util;

import com.portal.pcm.EBufException;
import com.portal.pcm.PortalContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.net.URL;
import java.net.URLClassLoader;

@Service
public class ContextUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextUtils.class);

    @Value("${loader.path}")
    private String loaderPath;

    public PortalContext createContext() {
        PortalContext ctx = null;
        try {
            //String strPath = System.getProperty("loader.path");
            LOGGER.info("InfranetProperties Path....."+loaderPath);
            ResourceUtils.getFile(loaderPath);

            ClassLoader cl = ClassLoader.getSystemClassLoader();

            LOGGER.info("---------------------------------------------------------------");
            LOGGER.info("Creating PCM context using configuration in Infranet.properties from" + loaderPath);

            ctx = new PortalContext();
            ctx.connect();

            LOGGER.info("Context successfully created.");
            // print out some info about the connection
            LOGGER.info("current DB: " + ctx.getCurrentDB());
            LOGGER.info("user ID:    " + ctx.getUserID());

            // close the connection
           // ctx.close(true);
           // LOGGER.info("PCM connection closed.");
        } catch (EBufException ebufex) {
            LOGGER.info("Your connection to the server failed.");
            LOGGER.info(" * Do you have a correct Infranet.properties file in the classpath?");
            LOGGER.info(" * Is the Infranet server CM up?");
            LOGGER.info("Here's the error:");
            LOGGER.info("Here's the error:" +ebufex);
        } catch(Exception e) {
            LOGGER.info("Here's the error:" + e);
        }
        return ctx;
    }
}
