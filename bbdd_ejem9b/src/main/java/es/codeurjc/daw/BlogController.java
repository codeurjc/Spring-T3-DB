package es.codeurjc.daw;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.daw.model.Blog;
import es.codeurjc.daw.model.Comment;

@RestController
@RequestMapping("/")
public class BlogController {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private CommentRepository commentRepository;

	@PostConstruct
	public void init() {

		Blog blog = new Blog("New", "My new product");
		blog.addComment(new Comment("Cool", "Pepe"));
		blog.addComment(new Comment("Very cool", "Juan"));

		blogRepository.save(blog);
	}

	@RequestMapping("/blogs")
	public List<Blog> getBlogs() throws Exception {
		return blogRepository.findAll();
	}

	// Deleting a blog delete its associated comments
	@DeleteMapping("/blogs/{id}")
	public Blog deleteBlog(@PathVariable Long id) {
		Blog blog = blogRepository.findById(id).get();
		blogRepository.deleteById(id);
		return blog;
	}

	// Deleting a comment removes it from associated blog
	@DeleteMapping("/comments/{id}")
	public Comment deleteComment(@PathVariable Long id) {
		Comment comment = commentRepository.findById(id).get();
		commentRepository.deleteById(id);
		return comment;
	}

}