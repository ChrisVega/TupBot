package discordbot;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import sx.blah.discord.Discord4J;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.DiscordDisconnectedEvent;
import sx.blah.discord.handle.impl.events.GuildUpdateEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.RoleUpdateEvent;
import sx.blah.discord.handle.impl.events.UserRoleUpdateEvent;
import sx.blah.discord.handle.obj.Status;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;

public class Instance {

    private volatile IDiscordClient client;
    private String email = "";
    private String password = "";
    private String token ="";
    private final AtomicBoolean reconnect = new AtomicBoolean(true);
    private Timer timer = null;
    Timer timert = new Timer();
    private static boolean helptrue = false;

    public Instance(String token) {
        this.token = token;
    }

    public Instance() {

    }

    public void login() throws DiscordException {
        client = new ClientBuilder().withToken(token).login();
        client.getDispatcher().registerListener(this);
        client.getDispatcher().registerListener(new AnnotationListener());

    }

        public void setgame() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(client!=null){
        if(!helptrue){
        client.changeStatus(Status.game("#help for commands"));
        client.changePresence(false);
        helptrue=true;
        }else{
            client.changeStatus(Status.game("#help for commands"));
            client.changePresence(false);
            helptrue=false;
        }
         }
            }
        }, 0, 600*1000);
    }

    public IDiscordClient getclient() {
        return client;
    }

    @EventSubscriber
    public void onReady(ReadyEvent event) {
        Discord4J.LOGGER.info("Bot ready");
        if (timer != null) {
            timer.cancel();
        }
    }

    @EventSubscriber
    public void onDisconnect(DiscordDisconnectedEvent event) {
        timer = new Timer();
        Discord4J.LOGGER.info(event.getReason().name());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!client.isReady()) {
                    try {
                        client.login();
                    } catch (DiscordException ex) {
                        Discord4J.LOGGER.info(event.getReason().name());
                    }
                } else {
                    timer.cancel();
                }
            }
        }, 15, 15);
    }

}
