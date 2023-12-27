package org.example.models;
import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

  // region Variables
  private static int index = 0;
  private static String[] titles = new String[]{"Введение в программирование",
          "Приветственное обращение к студентам Разработчика",
          "Курс компьютерной грамотности",
          "Умение учиться",
          "Математика и информатика для программистов",
          "Введение в контроль версий",
          "Знакомство с языками программирования",
          "Знакомство с базами данных",
          "Знакомство с языком Python",
          "Java: знакомство и как пользоваться базовым API"};
  @Id
  @Column(name = "id_course")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "title")
  private String title;

  private int duration;
  // endregion

  // region Methods
  @Override
  public String toString() {
    return "Course{" + "id=" + id + ", title='" + title + '\'' + ", duration=" + duration + '}';
  }

  public static Course createCourse() {
    if (index >= titles.length) index = 0;
    return new Course(titles[index++], (int) (Math.random() * 10));
  }
  // endregion

  // region Construstors
  public Course() {

  }

  public Course(int id, String title, int duration) {
    this.id = id;
    this.title = title;
    this.duration = duration;
  }

  public Course(String title, int duration) {
    this.title = title;
    this.duration = duration;
  }
  // endregion

  // region Getters & Setters
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Column(name = "id", nullable = false)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  //  @Basic
//  @Column(name = "title", nullable = false, length = 45)
  public String getTitle() {
    return title;
  }

  //  @Basic
//  @Column(name = "duration", nullable = false)
  public int getDuration() {
    return duration;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }
  // endregion

}
