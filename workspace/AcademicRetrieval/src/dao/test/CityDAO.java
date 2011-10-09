package dao.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
 
public class CityDAO {
        public Long saveCity(String cityName) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transaction = null;
                Long cityId = null;
                try {
                        transaction = session.beginTransaction();
                        City city = new City();
                        city.setName(cityName);
                        cityId = (Long) session.save(city);
                        transaction.commit();
                } catch (Exception e) {
                        transaction.rollback();
                        e.printStackTrace();
                } finally {
                        session.close();
                }
                return cityId;
        }
 
        @SuppressWarnings("unchecked")
        public void listCities() {
        	Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transaction = null;
                try {
                        transaction = session.beginTransaction();
                        List<City> cities = session.createQuery("from City").list();
                        for (City city : cities) {
                                System.out.println(city.getName());
                        }
                        transaction.commit();
                } catch (Exception e) {
                        transaction.rollback();
                        e.printStackTrace();
                } finally {
                        session.close();
                }
        }
 
        public void updateCity(Long cityId, String cityName) {
        	Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transaction = null;
                try {
                        transaction = session.beginTransaction();
                        City city = (City) session.get(City.class, cityId);
                        city.setName(cityName);
                        transaction.commit();
                } catch (Exception e) {
                        transaction.rollback();
                        e.printStackTrace();
                } finally {
                        session.close();
                }
        }
 
        public void deleteCity(Long cityId) {
        	Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transaction = null;
                try {
                        transaction = session.beginTransaction();
                        City city = (City) session.get(City.class, cityId);
                        session.delete(city);
                        transaction.commit();
                } catch (Exception e) {
                        transaction.rollback();
                        e.printStackTrace();
                } finally {
                        session.close();
                }
        }
}