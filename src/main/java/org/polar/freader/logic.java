package org.polar.freader;

public class logic {

    public void numberGeneration(String difficulty) {
        System.out.println("OVER HERE: "+difficulty);
        if (difficulty != null) {
            switch (difficulty) {
                case "Easy" -> System.out.println("Easy lol");
                case "Medium" -> System.out.println("Medium lol");
                case "Hard" -> System.out.println("Hard lol");
                default -> System.out.println("Unknown lol");
            }
        } else {
            System.out.println("ddddifficulty = null");
        }
    }
    public void test1(){
        System.out.println("test1");

    }
}
