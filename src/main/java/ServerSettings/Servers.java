package ServerSettings;

import Modules.Names;
import discordbot.Instance;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sx.blah.discord.Discord4J;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;

public class Servers implements Serializable{
    
    private SeverSettings[] servers= new SeverSettings[0];
    private IDiscordClient api;

    public Servers(IDiscordClient a){
        api=a;
        Load();
    }
    
    public void add(String g){
        if(search(g)==-1){
        SeverSettings[] temp = new SeverSettings[servers.length+1];
        for(int i=0;i<servers.length;i++){
            temp[i]=servers[i];
        }
        temp[servers.length]=new SeverSettings(g);
        servers=temp;
        quickSort(this.servers,0,servers.length-1);
        Save();
    }
    }
    
    public SeverSettings getserver(String s){
        int x=search(s);
        if(x!=-1){
            return servers[x];
        }else{
            add(s);
            x=search(s);
            return servers[x];
        }
    }
    
    public void scrub(List<IGuild> G){
        for(int i=0;i<G.size();i++){
            if(search(G.get(i).getID())==-1){
                add(G.get(i).getID());
            }
        }
    }
    
    public int search(String elt){
        if(servers.length==1&&servers[0].equals(elt)){
            return 0;
        }
        if(servers!=null&&servers.length!=0){
        return recsearch(servers,0,servers.length-1,elt);
        }
        return -1;
    }
    
    private int recsearch(SeverSettings[] ar,int start, int end, String key){
        int mid = (start + end)/2;
        int compare = key.compareTo(ar[mid].getserver());
        if(start > end){
            return -1;
        }
        if(compare == 0){
            return mid;
        }
        else if(compare > 0){
            return recsearch(ar,mid+1,end,key);
        }
        else{
            return recsearch(ar,start,mid-1,key);
        }
    }
    
    public void Load(){
    try {
        FileInputStream fis = new FileInputStream("Serversarray.dat");
        ObjectInputStream in = new ObjectInputStream(fis);
        servers = (SeverSettings[]) in.readObject();
        in.close();
      }
      catch (IOException | ClassNotFoundException e) {
          Logger.getLogger(Instance.class.getName()).log(Level.SEVERE, "No file found, creating new array", e);
      }
}
    
    public void Save(){
        try{
        FileOutputStream fos = new FileOutputStream("Serversarray.dat");
         ObjectOutputStream out = new ObjectOutputStream(fos);
         out.writeObject(servers);
         out.flush();
         out.close();
        }catch (IOException e) {
          Logger.getLogger(Instance.class.getName()).log(Level.SEVERE, "File could not be saved", e);
      }
    }
    
    public static void quickSort(SeverSettings[] arr, int low, int high) {
		if (arr == null || arr.length == 0)
			return;
 
		if (low >= high)
			return;
 
		// pick the pivot
		int middle = low + (high - low) / 2;
		String pivot = arr[middle].getserver();
 
		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (arr[i].getserver().compareTo(pivot) < 0) {
				i++;
			}
 
			while (arr[j].getserver().compareTo(pivot) > 0) {
				j--;
			}
 
			if (i <= j) {
				SeverSettings temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}
 
		// recursively sort two sub parts
		if (low < j){
			quickSort(arr, low, j);
                }
 
		if (high > i){
			quickSort(arr, i, high);
                }
	}
    
}
