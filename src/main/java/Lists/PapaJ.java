package Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PapaJ {

    private ArrayList<String> pj;
    Random rn = new Random();
    int x;

    public PapaJ() {
        pj = new ArrayList<String>(
                Arrays.asList("https://i.ytimg.com/vi/7TKpZS-xtRg/maxresdefault.jpg",
                        "http://i.imgur.com/fIRn64r.jpg",
                        "https://kiwicdn.akamaized.net/fb70/Y3pxFwvpskwgs3gxfL6EJk.jpg",
                        "https://i.redditmedia.com/J7mjKmTdB-Kb_-g03JVYwTq9dJ9NxSQabP2g5pSKWzw.jpg?w=320&s=dbd9251baa13ef49d8f1fa9f9cf16c70",
                        "https://pbs.twimg.com/media/CfHfha1WAAA5MWX.jpg",
                        "https://i.redditmedia.com/jBYuztnMXS5Dy_kC6JMDotzAr9DZMWQYqv0O7mlkEq8.jpg?w=320&s=06908ecbf1700d6d7adeca709271f06a",
                        "https://pbs.twimg.com/media/CXcR4_6WcAANx6Q.png",
                        "https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/12677356_563940157107479_916992307_n.jpg?ig_cache_key=MTIwOTg0OTY0NDEyMTg4Nzk4OQ%3D%3D.2",
                        "https://pbs.twimg.com/profile_images/673498221407109121/9GA3Snmi.jpg"));
    }

    public String pith() {
        return pj.get(rn.nextInt(pj.size()));
    }

}
