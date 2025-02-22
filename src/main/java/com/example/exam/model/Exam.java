package com.example.exam.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int duration; // in minutes

    @Column(name = "scheduled_at", nullable = false)
    private LocalDateTime scheduledAt;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Question> questions;

    // Constructors
    public Exam() {}

    public Exam(String title, int duration, LocalDateTime scheduledAt) {
        this.title = title;
        this.duration = duration;
        this.scheduledAt = scheduledAt;
    }

    // Getters and setters...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
