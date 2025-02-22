package com.example.exam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int id;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    // Constructors
    public Question() {}

    public Question(String questionText, Exam exam) {
        this.questionText = questionText;
        this.exam = exam;
    }

    // Getters and setters...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
