package dao.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class HibernateUtil {
        private static final SessionFactory sessionFactory;
        static {
                try {
                        sessionFactory = new AnnotationConfiguration().configure()
                                        .addPackage("net.javasight.model") // the fully qualified
                                                                                                        // package name
                                        .addAnnotatedClass(City.class).buildSessionFactory();
                } catch (Throwable ex) {
                        System.err.println("Initial SessionFactory creation failed." + ex);
                        throw new ExceptionInInitializerError(ex);
                }
        }
 
        public static SessionFactory getSessionFactory() {
                return sessionFactory;
        }
}