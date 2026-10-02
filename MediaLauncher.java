interface Playable{
    String play();
    String play(int fromSecond);
    String pause();
}
abstract class MediaFile{
    static int count=1001;
    final String fileId;
    MediaFile(){
        fileId="MF-"+count++;
    }
    abstract String getFormatInfo();
    String getFileId(){
        return fileId;
    }
}
class AudioFile extends MediaFile implements Playable{
    String title;
    AudioFile(String title){
        this.title=title;
    }
    public String play(){
        return "Playing audio: "+title;
    }
    public String play(int fromSecond){
        return "Playing audio: "+title+" from 0:"+String.format("%02d",fromSecond);
    }
    public String pause(){
        return "Audio paused";
    }
    String getFormatInfo(){
        return "Audio file, ID: "+fileId;
    }
}
class Podcast implements Playable{
    String showName;
    int episodeNumber;
    Podcast(String showName,int episodeNumber){
        this.showName=showName;
        this.episodeNumber=episodeNumber;
    }
    public String play(){
        return "Streaming episode "+episodeNumber+" of "+showName;
    }
    public String play(int fromSecond){
        return "Streaming episode "+episodeNumber+" of "+showName+" from "+fromSecond+" seconds";
    }
    public String pause(){
        return "Podcast paused";
    }
}
public class MediaLauncher{
    static void launchAll(Playable[] items){
        for(Playable x:items){
            System.out.println(x.play());
        }
    }
    public static void main(String[] args){
        AudioFile a=new AudioFile("Morning Jazz");
        Podcast p=new Podcast("Tech Talk",12);
        System.out.println(a.play());
        System.out.println(a.play(30));
        System.out.println(a.getFormatInfo());
        System.out.println(p.play());
        Playable ref=a;
        System.out.println(ref.play());
        launchAll(new Playable[]{ref,p});
    }
}