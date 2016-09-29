package discordbot;

import java.util.Optional;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;

import sx.blah.discord.Discord4J;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.DiscordDisconnectedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.Status;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RateLimitException;

public class Instance {

    private volatile IDiscordClient client;
    private String token = "";//login token
    private AtomicBoolean reconnect = new AtomicBoolean(false);
    private static boolean helptrue = false;
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(0);

    public Instance() {

    }

    public void login() throws DiscordException {//logs the bot into Discord and registers the listeners here and in AnnotationListener
        client = new ClientBuilder().withToken(token).login();
        client.getDispatcher().registerListener(this);
        client.getDispatcher().registerListener(new AnnotationListener());
    }

    public void setgame() {//changes the status every 10 min to prevent the bot from going idle which prevents it from responding to commands
        executor.scheduleAtFixedRate(setgamerun, 0, 10, TimeUnit.MINUTES);
    }

    public IDiscordClient getclient() {//returns the IDiscordClient
        return client;
    }

    @EventSubscriber
    public void onReady(ReadyEvent event) {//event fired when logged in
        Discord4J.LOGGER.info("Bot starting");
        setgame();//starts changing the status
        reconnect.set(false);
        Discord4J.LOGGER.info("Bot ready");
    }

    @EventSubscriber
    public void onDisconnect(DiscordDisconnectedEvent event) {
        //currently not working, a bug in D4J prevents this from firing, working on it so that the bot can login
        //after a disconnect
    }

    Runnable setgamerun = new Runnable() {

        @Override
        public void run() {
            Status gameStatus;
                if (!helptrue) {
                    gameStatus = Status.game("#help for commands");
                    client.changeStatus(gameStatus);
                    client.changePresence(false);
                    helptrue = true;
                    Discord4J.LOGGER.debug("Game Chnaged");
                } else {
                    gameStatus = Status.game("#Pomf Pomf Kimochi ;)");
                    client.changeStatus(gameStatus);
                    client.changePresence(false);
                    helptrue = false;
                }
            }
    };

}
