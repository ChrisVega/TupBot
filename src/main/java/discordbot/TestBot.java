package discordbot;

import Lists.PapaJ;
import Lists.itslit;
import java.util.Calendar;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//Library Github https://github.com/BtoBastian/Javacord
//Javadoc http://ci.ketrwu.de/job/Javacord/javadoc/
//**to do**
//votekick
//block vote kick abuse
//send message to specific users
//Print all commands
//alert all users (for testing only no one can be trusted with this power)
//Mute all and unmute all
//return what game a user is playing
//Vote ban request (pings a mod if passed)
//Admin and mod only commands
public class TestBot {

    final static PapaJ John = new PapaJ();
    final static itslit af = new itslit();
    final static String authorID = "131066231921836032";
    final static String permissionnumber = "66321471";
    static Instance bot = new Instance();
    static ChatterBotSession bot1session;

    public static void main(String[] args) throws Exception {
        //DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        //Calendar cal = Calendar.getInstance();
        //PrintStream out = new PrintStream(new FileOutputStream("output_"+dateFormat.format(cal.getTime())+".txt"));//name of the output file
         // System.setOut(out);
        bot.login();
        ChatterBotFactory factory = new ChatterBotFactory();

        ChatterBot bot1 = factory.create(ChatterBotType.PANDORABOTS, "efbf0b2b5e34242b");
        bot1session = bot1.createSession();
    }
                
}
