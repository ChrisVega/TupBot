package Modules;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkException;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.paginators.Sorting;
import net.dean.jraw.paginators.SubmissionSearchPaginator;
import net.dean.jraw.paginators.SubredditPaginator;
import net.dean.jraw.paginators.TimePeriod;
import sx.blah.discord.Discord4J;

public class RedditSearch {

    UserAgent myUserAgent = UserAgent.of("", "", "", "");
    RedditClient redditClient = new RedditClient(myUserAgent);
    Credentials credentials = Credentials.script("Reddit account", "Reddit pass word", "", "");

    public RedditSearch(){
        OAuthData authData = null;
        try {
            authData = redditClient.getOAuthHelper().easyAuth(credentials);
        } catch (NetworkException | OAuthException ex) {
            Logger.getLogger(RedditSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        redditClient.authenticate(authData);
    }

    public String top(String qry) {
        int x=0;
        SubredditPaginator sp = new SubredditPaginator(redditClient);
        String search=qry;
        sp.setLimit(100);
        sp.setSorting(Sorting.HOT);
        sp.setSubreddit(search);
        sp.next(true);
        Listing<Submission> list = sp.getCurrentListing();
        if(list.size()==0){
            return"Subreddit not found";
        }
        if(list.get(x).isStickied()){
            while(list.get(x).isStickied()){
                x=x+1;
            }
        }
        
        return (list.get(x).getUrl());
    }

}
