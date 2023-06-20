package com.formacion.bosonit.block6pathvariableheaders.model;

public class Greeting {
    private Integer id;
    private String content;

    public Greeting() {
    }

    public Greeting(Integer id, String name) {
        this.id = id;
        this.content = "Hello, " + name + "!";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
