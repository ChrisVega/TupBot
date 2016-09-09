package Modules;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IPrivateChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MissingPermissionsException;

public class SendPM {
    public static void Send(IDiscordClient client, String userID, String message) throws DiscordException, HTTP429Exception, MissingPermissionsException {
        IPrivateChannel channel = client.getOrCreatePMChannel(client.getUserByID(userID));
        channel.sendMessage(message);
    }
}
