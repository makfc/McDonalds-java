package proguard.canary;

public class NewRelicCanary {
    private String sound;

    public NewRelicCanary(String sound) {
        this.sound = sound;
    }

    public static void canaryMethod() {
        NewRelicCanary canary = new NewRelicCanary("tweet!");
    }
}
