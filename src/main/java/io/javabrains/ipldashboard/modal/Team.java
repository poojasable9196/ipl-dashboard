package io.javabrains.ipldashboard.modal;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long totalMatches;
    private Long totalWins;

    @Transient
    List<Match> matches;

    public Team() {
    }

    public Team(String name, Long totalMatches) {
        this.name = name;
        this.totalMatches = totalMatches;
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", name=" + name + ", totalMatches=" + totalMatches + ", totalWins=" + totalWins
                + "]";
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getTotalMatches() {
        return totalMatches;
    }
    public void setTotalMatches(Long totalMatches) {
        this.totalMatches = totalMatches;
    }
    public Long getTotalWins() {
        return totalWins;
    }
    public void setTotalWins(Long totalWins) {
        this.totalWins = totalWins;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
    
}
