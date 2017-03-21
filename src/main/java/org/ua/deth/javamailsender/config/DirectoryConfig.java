package org.ua.deth.javamailsender.config;

import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */

public class DirectoryConfig {

    private String dirToCreate = ".mailApp";
    private String dbUrl;

    public DirectoryConfig() {
        createAppDirectory();
    }

    /**
     * This method created directory for saving DB and other temp files on user directory.
     */
    private void createAppDirectory() {
        boolean mkdir = false;
        // for windows
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            dirToCreate = System.getProperty("user.home") + "\\" + dirToCreate;
            dbUrl = "jdbc:sqlite:" + dirToCreate + "\\" + "person_db.sqlite";
            mkdir = new File(dirToCreate).mkdir();
            // for linux/unix
        } else if (System.getProperty("os.name").toLowerCase().contains("nix") || System.getProperty("os.name").toLowerCase().contains("nux")) {
            dirToCreate = System.getProperty("user.home") + "/" + dirToCreate;
            mkdir = new File(dirToCreate).mkdir();
            dbUrl = "jdbc:sqlite:" + dirToCreate + "/" + "person_db.sqlite";
        }
        System.out.println(mkdir ? "Created dir: " + System.getProperty("user.home") + dirToCreate : "Dir exist or you don't have permissions");
        setProperty();
    }

    private void setProperty() {
        try {
            FileInputStream fileInputStream = new FileInputStream("classpath:application.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);
            properties.setProperty("spring.datasource.url", dbUrl);
            fileInputStream.close();
            FileOutputStream fileOutputStream = new FileOutputStream("classpath:application.properties");
            properties.store(fileOutputStream, null);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
