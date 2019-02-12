package es.codeurjc.daw;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.daw.model.Blog;
import es.codeurjc.daw.model.Comment;

@RestController
@RequestMapping("/blogs")
public class BlogController {

	@Autowired
	private BlogRepository repository;

	@PostConstruct
	public void init() {

		Blog blog = new Blog("New", "My new product");
		blog.getComments().add(new Comment("Cool", "Pepe"));
		blog.getComments().add(new Comment("Very cool", "Juan"));

		repository.save(blog);		
	}

	@RequestMapping("/")
	public List<Blog> getBlogs() throws Exception {
		return repository.findAll();
	}
}
