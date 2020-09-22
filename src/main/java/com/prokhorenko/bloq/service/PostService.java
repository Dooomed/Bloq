package com.prokhorenko.bloq.service;

import com.prokhorenko.bloq.Dao.PostDao;
import com.prokhorenko.bloq.model.Post;

import java.util.List;

public class PostService {

    private static final PostDao postDao = new PostDao();
    public PostService(){

    }

    public static List<Post> findAllPosts(){
        return postDao.getAll();
    }

    public static void savePost(Post post){
        postDao.save(post);
    }

    public static void updatePost(Post post){
        postDao.update(post);
    }

    public static void deletePost(Post post){
        postDao.delete(post);
    }

    public static Post findPostById(int id){
        return postDao.findById(id);
    }

}
