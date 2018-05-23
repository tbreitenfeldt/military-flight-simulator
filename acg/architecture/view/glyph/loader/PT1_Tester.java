
public class PT1_Tester {
    
    public static void main (String[] args) {
        
        String fileName = "testFile.txt";
        
        GlyphLoader loader = new GlyphLoader(fileName);
        LayoutBundle bundle = loader.load();
        
        System.out.println(bundle);
    }
}