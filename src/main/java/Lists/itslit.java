package Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class itslit {

    private ArrayList<String> lit;
    Random rn = new Random();
    int x;

    public itslit() {
        lit = new ArrayList<String>(
                Arrays.asList("https://www.youtube.com/watch?v=sjwYDqwZchY&nohtml5=False",
                        "https://www.youtube.com/watch?v=g-sgw9bPV4A&nohtml5=False",
                        "https://www.youtube.com/watch?v=YrKMiE9WII0",
                        "https://www.youtube.com/watch?v=ThvD1n-bY_s",
                        "https://www.youtube.com/watch?v=cU8HrO7XuiE",
                        "https://www.youtube.com/watch?v=5Y7YQU_JUyI",
                        "https://www.youtube.com/watch?v=JYVfk6AZnnE",
                        "https://www.youtube.com/watch?v=PWmfNeLs7fA",
                        "https://www.youtube.com/watch?v=xYc4DT18EJg",
                        "https://www.youtube.com/watch?v=5-2nByd2cr4",
                        "https://www.youtube.com/watch?v=Dexsy4E0y1I",
                        "https://www.youtube.com/watch?v=Fz9cZoE45QI&nohtml5=False",
                        "https://www.youtube.com/watch?v=lKQnV1g-K-w&nohtml5=False",
                        "https://www.youtube.com/watch?v=yQzNZR-LXZc&nohtml5=False",
                        "https://www.youtube.com/watch?v=PYrTCbI18pE&nohtml5=False",
                        "https://www.youtube.com/watch?v=kZ6F9m25KA4",
                        "https://www.youtube.com/watch?v=AeqRj-bYIzc",
                        "https://youtu.be/hpvhftQ-hLE",
                        "https://www.youtube.com/watch?v=sc04xiqgeCw&nohtml5=False",
                        "https://www.youtube.com/watch?v=LgwCVQdW1HM&nohtml5=False",
                        "https://www.youtube.com/watch?v=vTIIMJ9tUc8&nohtml5=False",
                        "https://www.youtube.com/watch?v=7AB3GNAMteM&nohtml5=False",
                        "https://www.youtube.com/watch?v=1tpQskhhs9c",
                        "https://www.youtube.com/watch?v=G1Ccj_Fq1sw",
                        "https://youtu.be/wphbL1YdS_M",
                        "https://www.youtube.com/watch?v=fat65cSEGWg",
                        "https://youtu.be/pKiTv4BeyWQ",
                        "https://youtu.be/-uaqpSOxqXs",
                        "https://www.youtube.com/watch?v=YQ2ESrAmlOI",
                        "https://www.youtube.com/watch?v=aOeummzCjpE",
                        "https://www.youtube.com/watch?v=3SUAVhVlsd0",
                        "https://youtu.be/fCe2bTtKCJg",
                        "https://www.youtube.com/watch?v=JMMe52YthFI&nohtml5=False",
                        "https://youtu.be/qFa4s-edu20",
                        "https://youtu.be/d2s_hg_AG2w",
                        "https://youtu.be/JKKbxZvj0lQ",
                        "https://youtu.be/K6Y5RVDfgbw",
                        "https://youtu.be/MGeKrPYOmuU",
                        "https://youtu.be/4d0Q8BfI4Cw",
                        "https://www.youtube.com/watch?v=_fhFfpnI-R4",
                        "https://youtu.be/oVzj_g4lyjs",
                        "https://youtu.be/D8Sl8hkBXB0",
                        "https://youtu.be/H3ZnzqaPbD4",
                        "https://youtu.be/zv_3dChqk_Q",
                        "https://www.youtube.com/watch?v=QVtQBhVEM0A&nohtml5=False",
                        "https://www.youtube.com/watch?v=rTfa-9aCTYg",
                        "https://www.youtube.com/watch?v=-GHQJC0onTY",
                        "https://www.youtube.com/watch?v=wH45HnMy6r0",
                        "https://www.youtube.com/watch?v=TzhpzdslThs",
                        "https://www.youtube.com/watch?v=JHJRNmjqhT0",
                        "https://soundcloud.com/frankjavcee/zelda-shop-trap-prod-by-frankjavcee",
                        "https://www.youtube.com/watch?v=mn-TNMXbBcg&feature=youtu.be",
                        "https://youtu.be/VlmDPC6ai4Y",
                        "https://youtu.be/3Yqm8_IuEns",
                        "https://youtu.be/3fyuZd2I0mM",
                        "https://www.youtube.com/watch?v=jykCqN8c0vU",
                        "https://www.youtube.com/watch?v=cIRDIhGfX2o",
                        "https://www.youtube.com/watch?v=WFf4es36hNE",
                        "https://www.youtube.com/watch?v=faXQGgGzxUs",
                        "https://youtu.be/Op4oEuJoVbw",
                        "https://youtu.be/ct3-f8If-PE",
                        "https://youtu.be/QPf8cd6jI_k",
                        "https://youtu.be/7lguPbuP2h8",
                        "https://youtu.be/U8GqA47EF4I",
                        "https://www.youtube.com/watch?v=6kgpbHdbZA0",
                        "https://www.youtube.com/watch?v=5dbG4wqN0rQ",
                        "https://www.youtube.com/watch?v=v71c6NDs5VI&feature=youtu.be",
                        "https://www.youtube.com/watch?v=WQfsH_LL8oI",
                        "https://www.youtube.com/watch?v=L9HjWLKMoiM&feature=youtu.be",
                        "https://www.youtube.com/watch?v=5QjZdfJ2Nc8",
                        "https://www.youtube.com/watch?v=Pbuzi4quz0k",
                        "https://www.youtube.com/watch?v=jG7IGiBJU4c&annotation_id=annotation_1096535805&src_vid=5KPxdXMUCws&feature=iv",
                        "https://youtu.be/PpdeiaYZ4pw",
                        "https://www.youtube.com/watch?v=TccScBS9obk",
                        "https://www.youtube.com/watch?v=fTlw3T44ynk",
                        "https://www.youtube.com/watch?v=K8ZDlDF2s_4",
                        "https://youtu.be/ZXVhOPiM4mk"));
    }

    public String litaf() {
        return lit.get(rn.nextInt(lit.size()));
    }
}
