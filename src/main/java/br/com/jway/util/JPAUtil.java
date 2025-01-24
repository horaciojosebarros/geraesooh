package br.com.jway.util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JPAUtil {

    private static EntityManagerFactory emf;

    static {
        try {
            // Carregar propriedades do arquivo application.properties
            Properties properties = new Properties();
            try (InputStream input = JPAUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
                if (input == null) {
                    throw new RuntimeException("Arquivo application.properties não encontrado!");
                }
                properties.load(input);
            }

            // Configurar propriedades programaticamente
            Map<String, Object> settings = new HashMap<>();
            settings.put("jakarta.persistence.jdbc.url", properties.getProperty("spring.datasource.url"));
            settings.put("jakarta.persistence.jdbc.user", properties.getProperty("spring.datasource.username"));
            settings.put("jakarta.persistence.jdbc.password", properties.getProperty("spring.datasource.password"));
            settings.put("jakarta.persistence.jdbc.driver", properties.getProperty("spring.datasource.driver-class-name"));
            settings.put("hibernate.dialect", properties.getProperty("hibernate.dialect"));
            settings.put("hibernate.hbm2ddl.auto", "update");
            settings.put("hibernate.show_sql", "true");
            settings.put("hibernate.format_sql", "true");

            // Criar o EntityManagerFactory
            emf = Persistence.createEntityManagerFactory("geraesooh", settings);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo application.properties", e);
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null) {
            emf.close();
        }
    }
}