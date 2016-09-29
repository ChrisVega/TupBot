package Modules;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IPrivateChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class SendPM {
    public static void Send(IDiscordClient client, String userID, String message) throws DiscordException, MissingPermissionsException, RateLimitException {
        IPrivateChannel channel = client.getOrCreatePMChannel(client.getUserByID(userID));
        channel.sendMessage(message);
    }
}
