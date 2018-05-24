import java.io.IOException;

import acg.architecture.view.glyph.loader.GlyphLoader;
import acg.architecture.view.glyph.loader.LayoutBundle;
import acg.architecture.view.glyph.loader.InvalidLayoutException;


public class PT1_Tester {
    
    public static void main (String[] args) throws IOException, InvalidLayoutException {
        
        String fileName = "testFile.txt";
        
        GlyphLoader loader = new GlyphLoader(fileName);
        LayoutBundle bundle = loader.load();
        
        System.out.println(bundle);
    }
}