package ThinkingInJava.Practise253_300.Practise287;


class WaterSource {
    private String s;
    WaterSource() {
        System.out.println("WaterSource()");
        s = "Constructed";
    }
    public String toString() { return s; }
}

public class t1 {
    private String valve1, valve2, valve3, valve4;
    private WaterSource source = new WaterSource();
    private int i;
    private float f;
    // appears to create only one StringBuilder: (using javap -c)
    public String toString() {
        return
                "valve1 = " + valve1 + " " +
                        "valve2 = " + valve2 + " " +
                        "valve3 = " + valve3 + " " +
                        "valve4 = " + valve4 + " " +
                        "i = " + i + " " + "f = " + f + " " +
                        "source = " + source;
    }
    public static void main(String[] args) {
        t1 sprinklers = new t1();
        System.out.println(sprinklers);
    }
}