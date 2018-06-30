package com.site.webapp.service;

/**
 *
 * @author Arturs
 */
import com.site.webapp.bean.Car;
import com.site.webapp.util.HibernateUtils;
import java.util.List;
import org.hibernate.Session;

public class CarService {
    
    private CarService() {};
    
    public static Car getCarById(Long id) {
        
        Car car;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            car = session.get(Car.class, id);
        }
        
        return car;
    }    
    
    public static List<Car> getCars() {
        
        List<Car> cars;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            cars = session.createQuery("from Car").list();
        }
        return cars;
    }
    
    public static void save(Car car) {
        
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            
            session.save(car);
            
            session.getTransaction().commit();
        }
    }
    
    public static void update(Car car) {
        
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            
            session.update(car);
            session.getTransaction().commit();
        }
    }
    
    public static void delete(Car car) {
        
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            
            session.delete(car);
            session.getTransaction().commit();
        }
    }
}