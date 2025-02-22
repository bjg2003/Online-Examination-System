package com.example.exam.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @Column
    private double score;

    @Column(name = "taken_on", nullable = false)
    private LocalDateTime takenOn;

    // New field to store time taken in seconds
    @Column(name = "time_taken", nullable = false)
    private int timeTaken;

    public Result() {}

    public Result(User user, Exam exam, double score, LocalDateTime takenOn, int timeTaken) {
        this.user = user;
        this.exam = exam;
        this.score = score;
        this.takenOn = takenOn;
        this.timeTaken = timeTaken;
    }

    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Exam getExam() {
        return exam;
    }
    public void setExam(Exam exam) {
        this.exam = exam;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public LocalDateTime getTakenOn() {
        return takenOn;
    }
    public void setTakenOn(LocalDateTime takenOn) {
        this.takenOn = takenOn;
    }
    public int getTimeTaken() {
        return timeTaken;
    }
    public void setTimeTaken(int timeTaken) {
        this.timeTaken = timeTaken;
    }
}
