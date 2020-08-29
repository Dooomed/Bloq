package com.prokhorenko.bloq;

import com.prokhorenko.bloq.model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static{
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Post.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }
}
