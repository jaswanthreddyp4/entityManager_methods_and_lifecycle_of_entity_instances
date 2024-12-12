package org.example;

import java.util.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Student;
import org.example.persistence.MyOwnPersistenceInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String,String> propertyMap = new HashMap<>();

        //if you wanna see what queries goes to database
        propertyMap.put("hibernate.show_sql","true");

        EntityManagerFactory emf =new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new MyOwnPersistenceInfo(),propertyMap);

        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            // now student object is in transient state

            /*
            Student student = new Student();

            student.setId(8);
            student.setName("keerthy");
            student.setUsn("is115");
            student.setDepartment("ise");

             */



            // now student object is managed in persistence context by EntityManager
            //em.persist(student);

            //when we use find method

            /*
            System.out.println("student with id 4 is not in context");
            Student student = em.find(Student.class,4);
            System.out.println(student);

            System.out.println("student with id 4 is already in context");
            Student student1 = em.find(Student.class,4);
            System.out.println(student1);



            //using remove() method
            System.out.println("now im going to remove student entity from context");
            em.remove(em.find(Student.class,4));
            System.out.println("student entity is removed from context");

             */

            /*
            Student student = new Student();
            student.setId(9);
            student.setName("niyati");
            student.setUsn("is073");
            student.setDepartment("ise");

            em.persist(student);

            em.detach(student);

            //since student entity is added into context now this should not query database
            Student mergedStudent = em.merge(student);
            mergedStudent.setName("shailaja");

            //now updation is happenend , check database

             */



            // on commiting the transactions changes made to entity object in context is reflected in database
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
}