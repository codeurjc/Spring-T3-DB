package es.codeurjc.daw;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.daw.model.Project;
import es.codeurjc.daw.model.Student;

@RestController
public class SchoolController {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@PostConstruct
	public void init() {

		Student s1 = new Student("Pepe", 2010);
		s1.setProject(new Project("TFG1", 8));
		studentRepository.save(s1);
		
		Student s2 = new Student("Juan", 2011);
		studentRepository.save(s2);		
	}

	@RequestMapping("/students/")
	public List<Student> getStudents() throws Exception {
		return studentRepository.findAll();
	}
	
	@RequestMapping("/projects/")
	public List<Project> getProjects() {
		return projectRepository.findAll();
	}
	
	//Deleting a student delete her associated project
	@DeleteMapping("/students/{id}")
	public Student deleteStudent(@PathVariable Long id) {
		Student student = studentRepository.findById(id).get();		
		studentRepository.deleteById(id);
		return student;
	}
	
	//A project only can be deleted if it has no associated student.
	@DeleteMapping("/projects/{id}")
	public Project deleteProject(@PathVariable Long id) {
		Project project = projectRepository.findById(id).get();		
		projectRepository.deleteById(id);
		return project;
	}
}
