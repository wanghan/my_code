package dao.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import actm.data.Author;
import actm.data.Conference;
import actm.data.Paper;


public class HibernateUtil {
        private static final SessionFactory sessionFactory;
        static {
                try {
                        sessionFactory = new AnnotationConfiguration().configure()
                                      .buildSessionFactory();
                } catch (Throwable ex) {
                        System.err.println("Initial SessionFactory creation failed." + ex);
                        throw new ExceptionInInitializerError(ex);
                }
        }
 
        public static SessionFactory getSessionFactory() {
                return sessionFactory;
        }
}