package org.ua.deth.javamailsender.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.io.IOException;


/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
public class DirectoryConfig {

    private static DirectoryConfig directoryConfig = new DirectoryConfig();

    private String dirToCreate = ".mailApp";
    private String dbUrl;

    public static DirectoryConfig getInstance() {
        return directoryConfig;
    }

    private DirectoryConfig() {
        try {
            createAppDirectory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method created directory for saving DB and other temp files on user directory.
     */
    private void createAppDirectory() throws IOException {
        boolean mkdir = false;
        // for windows
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            dirToCreate = System.getProperty("user.home") + "\\" + dirToCreate + "\\";
            dbUrl = "jdbc:sqlite:" + dirToCreate + "\\" + "person_db.sqlite";
            mkdir = new File(dirToCreate).mkdir();
            // for linux/unix
        } else if (System.getProperty("os.name").toLowerCase().contains("nix") || System.getProperty("os.name").toLowerCase().contains("nux")) {
            dirToCreate = System.getProperty("user.home") + "/" + dirToCreate + "/";
            mkdir = new File(dirToCreate).mkdir();
            dbUrl = "jdbc:sqlite:" + dirToCreate + "/" + "person_db.sqlite";
        }
        System.out.println(mkdir ? "Created dir: " + System.getProperty("user.home") + dirToCreate : "Dir exist or you don't have permissions");
        setProperty(dbUrl);
    }

    private void setProperty(String dbUrl) {

        try {
            PropertiesConfiguration configuration = new PropertiesConfiguration("application.properties");
            configuration.setProperty("spring.datasource.url", dbUrl);
            configuration.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }


}
