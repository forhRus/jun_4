package org.example;

import org.example.models.Course;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
  private static final SessionFactory ourSessionFactory;

  static {
    try{
      Configuration conf = new Configuration();
      conf.configure("hibernate.cfg.xml");
      conf.addAnnotatedClass(Course.class);
      ourSessionFactory = conf.buildSessionFactory();
    } catch (Throwable ex){
      throw  new ExceptionInInitializerError(ex);
    }
  }

  static SessionFactory getSessionFactory() throws HibernateException{
    return ourSessionFactory;
  }
}
