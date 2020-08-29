package com.prokhorenko.bloq;

import com.prokhorenko.bloq.Dao.PostDao;
import com.prokhorenko.bloq.model.Post;
import com.prokhorenko.bloq.service.PostService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloqApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloqApplication.class, args);
    }

}
