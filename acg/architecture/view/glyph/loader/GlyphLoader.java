package acg.architecture.view.glyph.loader;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GlyphLoader {
    
    String fname;
    File file;
    
    public GlyphLoader (String filename) throws IOException {
        
        fname = filename;
        file = new File (filename);
        
        if (!file.exists())
            throw new IOException("The file does not exist.");
    }

    public LayoutBundle load() throws IOException, InvalidLayoutException {   
        
        // Create color, vertex, edge, and circle lists
        EntryMap colors = new EntryMap<EntryColor>();
        EntryMap vertices = new EntryMap<EntryVertex>();
        EntryMap edges = new EntryMap<EntryEdge>();
        EntryMap circles = new EntryMap<EntryCircle>();
        
        // Create file reader
        Scanner fin = new Scanner(file);
        int lineNumber = 0; // First line in file is 1
        
        if (fin == null)
            throw new IOException("Scanner couldn't be created.");
        
        while (fin.hasNext()) {
            
            // Create a scanner to parse each line read from the file scanner.
            // We do this so we can utilize the comma delimiter
            
            // Get the next non-comment line
            String line = fin.nextLine();
            lineNumber++;
            
            if (line.contains(";")) {
                
                if (line.charAt(0) == ';')
                    line = "";
                
                else {
                    line = line.substring(0, line.indexOf(";"));
                }
            }
            
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            char type;
            
            if (lineScanner.hasNext()) {
                
                type = lineScanner.next().charAt(0);
                
                if (type == 'c')
                    readColor(lineScanner, colors, lineNumber);
                
                else if (type == 'v')
                    readVertex(lineScanner, vertices, lineNumber);
                
                else if (type == 'e')
                    readEdge(lineScanner, edges, lineNumber);
                
                else if (type == 'o')
                    readCircle(lineScanner, circles, lineNumber);
                
            }

            else {
                
                lineScanner.close();
                throw new InvalidLayoutException("Entry not type c, v, e, or o.  Line No.:" + lineNumber, lineNumber);
            }
               
            
            lineScanner.close();
        }
        
        fin.close();
        
        // Create the LayoutBundle and return
        return new LayoutBundle(edges, circles);
    }
    
    private void readColor(Scanner lineScanner, EntryMap<EntryColor> colors, int lineNumber) throws InvalidLayoutException {
        
        // A color entry: c, index, hex value = c, 1, #abcdef

    }
    
    private void readVertex(Scanner lineScanner, EntryMap<EntryVertex> vertices, int lineNumber) throws InvalidLayoutException {
        
        // A vertex entry: v, index, x, y, z = v, 1, 2.3, 1.1, -2
        int index;
        double x, y, z;
        boolean success = false;
        
        // Looking for vertex index
        if (lineScanner.hasNextInt()) {
            
            index = Integer.parseInt(lineScanner.next());
            
            // Looking for x value
            if (lineScanner.hasNextDouble()) {
                
                x = Double.parseDouble(lineScanner.next());
                
                // Looking for y value
                if (lineScanner.hasNextDouble()) {
                    
                    y = Double.parseDouble(lineScanner.next());
                    
                    // Looking for z value
                    if (lineScanner.hasNextDouble()) {
                        
                        z = Double.parseDouble(lineScanner.next());
                        
                        success = true; // We have this because we are not sure if Tappan keeps the program running if an exception occurs
                    }
                    else
                        throw new InvalidLayoutException("Expected a double for z. Line No.: " + lineNumber, lineNumber);
                }
                else
                    throw new InvalidLayoutException("Expected a double for y. Line No.: " + lineNumber, lineNumber);
            }
            else
                throw new InvalidLayoutException("Expected a double for x. Line No.: " + lineNumber, lineNumber);
        }
        else
            throw new InvalidLayoutException("Expected an int. Line No.: " + lineNumber, lineNumber);
        
        if (success)
            vertices.addEntry(new EntryVertex(index, x, y, z));
    }
    
    private void readEdge(Scanner lineScanner, EntryMap<EntryEdge> edges, int lineNumber) throws InvalidLayoutException {
        
        // An edge entry: e, start or end vertex, color index = e, 1, 2
        
    }
    
    private void readCircle(Scanner lineScanner, EntryMap<EntryCircle> circles, int lineNumber) throws InvalidLayoutException {
        
        // A circle entry: o, vertex index, color index, radius = o, 1, 2, 3.4

    }
}