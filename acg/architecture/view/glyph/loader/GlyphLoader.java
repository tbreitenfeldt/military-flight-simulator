/**
 * Team 6
 * CSCD340
 * project part 1
*/

package acg.architecture.view.glyph.loader;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;


public class GlyphLoader {
    
    private String fname;
    private File file;
    
    private EntryMap<EntryColor> colors;
    private EntryMap<EntryVertex> vertices;
    private List edges;
    private List circles;
    
    public GlyphLoader (String filename) throws IOException {
        
        this.fname = filename;
        this.file = new File (filename);
        
        if (!file.exists())
            throw new IOException("The file does not exist.");
        
        
        colors = new EntryMap<EntryColor>();
        vertices = new EntryMap<EntryVertex>();
        edges = new ArrayList<ArrayList<EntryEdge>>();
        circles = new ArrayList<EntryCircle>();
        
        
    }//end constructor

    public LayoutBundle load() throws IOException, InvalidLayoutException {   
        
        // Create file reader
        Scanner fin = new Scanner(file);
        int lineNumber = 0; // First line in file is 1
        int vIndexStart = -1;// First Vertex starts as unknown
        int eIndex = 0; // Edge Index
        
        if (fin == null) {
            throw new IOException("Scanner couldn't be created.");
        }
        
        while (fin.hasNext()) {
            
            // Create a scanner to parse each line read from the file scanner.
            // We do this so we can utilize the comma delimiter
            
            // Get the next non-comment line
            String line = fin.nextLine();
            lineNumber++;
            
            if (line.contains(";")) {
                
                if (line.charAt(0) == ';') {
                    line = "";
                
                } else {
                    line = line.substring(0, line.indexOf(";"));
                }
            }
            
            //remove any white space from the beginning or end, and strip all trailling camas 
            line = line.trim();
            line = line.replaceAll(",*$", "");  //search the end of the line for 0 or more camas and replace with empty string
            
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            char type;
            
            if (lineScanner.hasNext()) {
                
                type = lineScanner.next().charAt(0);
                
                if(type != 'e')
                {
                    vIndexStart = -1; //A blank line or end of file ends this list and starts a new one, if there are more entries
                }
                
                if (type == 'c') {
                    readColor(lineScanner, lineNumber);
                
                } else if (type == 'v') {
                    readVertex(lineScanner, lineNumber);
                
                } else if (type == 'e') {
                    readEdge(lineScanner, vIndexStart, eIndex, lineNumber);
                
                } else if (type == 'o') {
                    readCircle(lineScanner, lineNumber);
                }
            
            } else {
                
                lineScanner.close();
                throw new InvalidLayoutException("Entry not type c, v, e, or o.  Line No.:" + lineNumber, lineNumber);
            }
            
            lineScanner.close();
        }//end while loop
        
        fin.close();
        
        // Create the LayoutBundle by getting the entries from the EntryMaps and return
        //getEntries takes a boolean to sort or not, and cast to correct List type to remove warning
        return new LayoutBundle(this.edges, this.circles);
    }//end method
    
    private void readColor(Scanner lineScanner, int lineNumber) throws IllegalArgumentException, InvalidLayoutException {
        if (lineScanner == null) {
            throw new IllegalArgumentException("Scanner can not be null");
        }//end if
        if (this.colors == null) {
            throw new IllegalArgumentException("Colors can not be null");
        }//end if
        if (lineNumber < 0) {
            throw new IllegalArgumentException("Invalid line number. Line number " + lineNumber + " can not be less than 0");
        }//end if

        int index = 0;
        int rgb = 0;
        String hex = "";
        Color color = null;
        
        if ( !lineScanner.hasNextInt()) {
            throw new InvalidLayoutException("Invalid index for color at line " + lineNumber, lineNumber);
        }// if

        index = lineScanner.nextInt();
        hex = lineScanner.next();
        
        //use a regular expression to insure the hex value is really hexidecimal
        if ( !hex.matches("^#[0-9a-fA-F]+$")) {
            throw new InvalidLayoutException("Invalid hex value for color at line " + lineNumber, lineNumber);
        }//end if
        if (lineScanner.hasNext()) {
            throw new InvalidLayoutException("Invalid sintax for color at line " + lineNumber, lineNumber);
        }//end if
        
        color = Color.decode(hex);

        this.colors.add(new EntryColor(index, color));
    }//end method
    
    private void readVertex(Scanner lineScanner, int lineNumber) throws InvalidLayoutException {
        
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
    }//end method
    
    private void readEdge(Scanner lineScanner, int vIndexStart, int eIndex,
            int lineNumber) throws InvalidLayoutException {
        
        // An edge entry: e, start or end vertex, color index = e, 1, 2
        /*
        EntryEdge edge;
        int vIndexEnd;
        int cIndex;
        
        // Looking for vertex for end of edge
        if (lineScanner.hasNextInt()) {
            
            vIndexEnd = Integer.parseInt(lineScanner.next());
            
            if (lineScanner.hasNextInt()) {
                
                cIndex = Integer.parseInt(lineScanner.next());
                
                if(vIndexStart >= 0) { //Checks if is the first vertex
                     edge = new EntryEdge(eIndex, vertices.getEntry(vIndexStart),vertices.getEntry(vIndexEnd),colors.getEntry(cIndex));
                     this.edges.add(edge);//Adds edge
                     eIndex = eIndex + 1;//Increments the index
                } 
                vIndexStart = vIndexEnd; // Sets the beginning of the next edge from the end of the last.
            }
        }
        */
            
    }//end method
    
    private void readCircle(Scanner lineScanner, int lineNumber) throws InvalidLayoutException {
        
        // A circle entry: o, vertex index, color index, radius = o, 1, 2, 3.4

        
        
    }//end method
    
    @Override
    public String toString() {
        
        String msg = "";
        
        msg += "----- GlyphLoader toString -----\n";
        msg += "Colors:\n";
        msg += this.colors.toString() + "\n";
        msg += "Vertices:\n";
        msg += this.vertices.toString() + "\n";
        msg += "Edges:\n";
        msg += this.edges.toString() + "\n";
        msg += "Circles:\n";
        msg += this.circles.toString() + "\n";
        
        return msg;
    }

}//end class