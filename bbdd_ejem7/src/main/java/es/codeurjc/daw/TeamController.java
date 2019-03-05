package es.codeurjc.daw;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.daw.model.Player;
import es.codeurjc.daw.model.Team;

@RestController
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private PlayerRepository playerRepository;

	@PostConstruct
	public void init() {

		Player p1 = new Player("Torres", 10);
		Player p2 = new Player("Iniesta", 10);
		
		playerRepository.save(p1);
		playerRepository.save(p2);
		
		Team team = new Team("Selección", 1);
		
		team.getPlayers().add(p1);
		team.getPlayers().add(p2);

		teamRepository.save(team);
	}

	@RequestMapping("/teams/")
	public List<Team> getTeams() throws Exception {
		return teamRepository.findAll();
	}
	
	@RequestMapping("/players/")
	public List<Player> getPlayers() throws Exception {
		return playerRepository.findAll();
	}
	
	//Deleting a team doesn't delete its associated players
	@DeleteMapping("/teams/{id}")	
	public Team deleteTeam(@PathVariable Long id) {
		Team team = teamRepository.findById(id).get();		
		team.getPlayers().size(); //Force loading players from database before deleting team
		teamRepository.deleteById(id);
		return team;
	}
	
	//A player only can be deleted if it has no associated team
	@DeleteMapping("/players/{id}")	
	public Player deleteProject(@PathVariable Long id) {
		Player player = playerRepository.findById(id).get();		
		playerRepository.deleteById(id);
		return player;
	}
}
