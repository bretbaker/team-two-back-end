package com.agora.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static Session session;
    private static SessionFactory sf = new Configuration()
            .configure("hibernate.cfg.xml")
            .setProperty("hibernate.connection.username", System.getenv("AGORA_DB_USERNAME"))
            .setProperty("hibernate.connection.password", System.getenv("AGORA_DB_PASSWORD"))
            .setProperty("hibernate.connection.url", System.getenv("AGORA_DB_URL"))
            .buildSessionFactory();

    private HibernateUtil() {
        super();
//        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
//        cfg.setProperty("hibernate.connection.username", System.getenv("AGORA_DB_USERNAME"));
//        cfg.setProperty("hibernate.connection.password", System.getenv("AGORA_DB_PASSWORD"));
//        cfg.setProperty("hibernate.connection.url", System.getenv("AGORA_DB_URL"));
//        sf = cfg.buildSessionFactory();
    }

    public static Session getSession() {
        if(session == null || !session.isOpen()) {
            session = sf.openSession();
        }

        return session;
    }

    public static void closeSession() {
        session.close();
    }
}
