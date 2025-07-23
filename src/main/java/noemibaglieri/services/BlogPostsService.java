package noemibaglieri.services;

import lombok.extern.slf4j.Slf4j;
import noemibaglieri.entities.BlogPost;
import noemibaglieri.exceptions.NotFoundException;
import noemibaglieri.payloads.NewBlogPostPayload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BlogPostsService {
    private List<BlogPost> postsDB = new ArrayList<>();

    public List<BlogPost> findAll() {
        return this.postsDB;
    }

    public BlogPost save(NewBlogPostPayload payload) {
        BlogPost newBlogPost = new BlogPost(payload.getCategory(), payload.getTitle(), payload.getContent(), payload.getReadingTime(), "https://picsum.photos/200/300");
        this.postsDB.add(newBlogPost);
        log.info("Your blog post * " + newBlogPost.getTitle() + " * was successfully published");
        return newBlogPost;
    }

    public BlogPost findById(long blogId) {
        BlogPost found = null;
        for (BlogPost post : this.postsDB) {
            if(post.getId() == blogId) found = post;
        }

        if (found == null) throw new NotFoundException("Post with id * " + blogId + " * was not found");

        return found;
    }

    public BlogPost findByIdAndUpdate(long blogId, NewBlogPostPayload payload) {
        BlogPost found = null;
        for (BlogPost post : this.postsDB) {
            if (post.getId() == blogId) {
                found = post;
                found.setTitle(payload.getTitle());
                found.setContent(payload.getContent());
                found.setCategory(payload.getCategory());
                found.setReadingTime(payload.getReadingTime());
            }
        }

        if(found == null) throw new NotFoundException("Post with id * " + blogId + " * was not found");
        return found;
    }

    public void findByIdAndDelete(long blogId) {
        BlogPost found = null;
        for (BlogPost post : this.postsDB) {
            if(post.getId() == blogId) found = post;
        }
        if(found == null) throw new NotFoundException("Post with id * " + blogId + " * was not found");
        this.postsDB.remove(found);
    }
}
