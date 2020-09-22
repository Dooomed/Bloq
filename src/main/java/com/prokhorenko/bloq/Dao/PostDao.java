package com.prokhorenko.bloq.Dao;

import com.prokhorenko.bloq.HibernateUtil;
import com.prokhorenko.bloq.model.Post;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PostDao {

    public List<Post> getAll(){
        return (List<Post>) HibernateUtil.getSession().createQuery("from Post").list();
    }

    public void save(Post post){
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.save(post);
        tx.commit();
    }

    public void delete(Post post){
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.delete(post);
        tx.commit();
    }

    public void update(Post post){
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        session.saveOrUpdate(post);
        tr.commit();
    }

    public Post findById(int id){
        Session session = HibernateUtil.getSession();
        return (Post) session.createQuery("from Post where id = :id").setParameter("id", id).getSingleResult();
    }

}
