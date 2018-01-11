package yj.dzc.love.autoTest;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String s = "123456/8721312";
        System.out.println(s.replaceAll("(" + "123456" + "/)?"+"8721312$", ""));
    }
}
