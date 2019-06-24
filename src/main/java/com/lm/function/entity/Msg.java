package com.lm.function.entity;

import lombok.Data;

@Data
public class Msg {

    private String content;
    private String name;

    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setName("lalaname");
        msg.setContent("lalacontent");
        System.out.println(msg.content = "hihi");
        System.out.println(msg);
    }

    @Override
    public String toString() {
        return name + " " + content;
    }
}
