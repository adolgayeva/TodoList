package com.quantum.todolist;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Bean for the items of the list
 */
@Document
public final class Todo implements Serializable {

    /**
     * Id of an item. Auto-generated value.
     */
    @Id
    private String id;

    /**
     * Title of an item
     */
    private String title;

    /**
     * Text of an item
     */
    private String text;

    /**
     * Status of an item
     */
    private Boolean done;

    /**
     * Date and time of the event
     */
    private Date dateTime;

    public Todo() {
    }

    public Todo(String id, String title, String text, Boolean done, Date dateTime) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.done = done;
        this.dateTime = dateTime;
    }

    public Todo(String title, String text) {
        this.title = title;
        this.text = text;
        this.done = false;
        this.dateTime = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", done=" + done +
                ", dateTime=" + dateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id) &&
                Objects.equals(title, todo.title) &&
                Objects.equals(text, todo.text) &&
                Objects.equals(done, todo.done) &&
                Objects.equals(dateTime, todo.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, done, dateTime);
    }
}
