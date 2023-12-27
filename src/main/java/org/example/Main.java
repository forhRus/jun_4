package org.example;

import org.example.models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
  public static void main(String[] args) {

    try (SessionFactory sf = HibernateUtil.getSessionFactory()) {

//      Добавление данных в БД
      insert(sf.openSession(), Course.createCourse());
      insert(sf.openSession(), Course.createCourse());
      insert(sf.openSession(), Course.createCourse());
      System.out.println("save successfully\n");

      // чтение данных
      int count = 1;
      int index1 = 1;
      while (count <= 3) {

        Course courseFromDB = readData(sf.openSession(), index1++);

        if (courseFromDB != null) {
          System.out.printf("%d: %s", count++, courseFromDB);
        }

      }
      System.out.println("read successfully\n");

// Обновление данных
      int index2 = 1;
      Course courseUpdated = null;
      while (true) {

        courseUpdated = readData(sf.openSession(), index2++);

        if(courseUpdated != null) {

          int idUpdated = courseUpdated.getId();
          courseUpdated.setTitle("Курс не доступен");
          courseUpdated.setDuration(0);

          updateData(sf.openSession(), courseUpdated);
          System.out.println(readData(sf.openSession(), idUpdated));

          break;
        }
      }
      System.out.printf("update successfully\n");


      // Удаление данных
      int index3 = 1;
      Course courseDeleted = null;
      while (true) {
        courseDeleted = readData(sf.openSession(), index3++);

        if(courseDeleted != null) {
          System.out.println(courseDeleted + " - Удалён");
          deleteData(sf.openSession(), courseDeleted);

          break;
        }
      }
      System.out.printf("delete successfully\n");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void deleteData(Session session, Course course) {
    session.beginTransaction();
    session.delete(course);
    session.getTransaction().commit();
  }

  private static void updateData(Session session, Course course) {
    session.beginTransaction();
    session.update(course);
    session.getTransaction().commit();
  }

  private static Course readData(Session session, int id) {
    Course course = session.get(Course.class, id);
    session.close();
    return course;
  }

  public static void insert(Session session, Course course) {
    session.beginTransaction();
    session.save(course);
    session.getTransaction().commit();
  }

}


