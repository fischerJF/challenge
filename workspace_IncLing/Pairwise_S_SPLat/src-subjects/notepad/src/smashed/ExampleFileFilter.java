package smashed;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;

import annotationclasses___.Feature___;

/*@(Persistence)*/
 


/**
 * A convenience implementation of FileFilter that filters out
 * all files except for those type extensions that it knows about.
 *
 * Extensions are of the type ".foo", which is typically found on
 * Windows and Unix boxes, but not on Macinthosh. Case is ignored.
 *
 * Example - create a new filter that filerts out all files
 * but gif and jpg image files:
 *
 *     JFileChooser chooser = new JFileChooser();
 *     ExampleFileFilter filter = new ExampleFileFilter(
 *                   new String{"gif", "jpg"}, "JPEG & GIF Images")
 *     chooser.addChoosableFileFilter(filter);
 *     chooser.showOpenDialog(this);
 *
 * @version 1.13 06/13/02
 * @author Jeff Dinkins
 */
@Feature___("Persistence")
public class ExampleFileFilter extends javax.swing.filechooser.FileFilter{
/*@(Persistence)*/
 

    @Feature___("Persistence")
	private static String TYPE_UNKNOWN = "Type Unknown";
/*@(Persistence)*/
 
    @Feature___("Persistence")
	private static String HIDDEN_FILE = "Hidden File";
/*@(Persistence)*/
 

    @Feature___("Persistence")
	private Hashtable filters = null;
/*@(Persistence)*/
 
    @Feature___("Persistence")
	private String description = null;
/*@(Persistence)*/
 
    @Feature___("Persistence")
	private String fullDescription = null;
/*@(Persistence)*/
 
    @Feature___("Persistence")
	private boolean useExtensionsInDescription = true;
/*@(Persistence)*/
 

    /**
     * Creates a file filter. If no filters are added, then all
     * files are accepted.
     *
     * @see #addExtension
     */
    @Feature___("Persistence")
	public ExampleFileFilter(){
	this.filters = new Hashtable();
    }
/*@(Persistence)*/
 

    /**
     * Creates a file filter that accepts files with the given extension.
     * Example: new ExampleFileFilter("jpg");
     *
     * @see #addExtension
     */
    @Feature___("Persistence")
	public ExampleFileFilter(String extension){
	this(extension,null);
    }
/*@(Persistence)*/
 

    /**
     * Creates a file filter that accepts the given file type.
     * Example: new ExampleFileFilter("jpg", "JPEG Image Images");
     *
     * Note that the "." before the extension is not needed. If
     * provided, it will be ignored.
     *
     * @see #addExtension
     */
    @Feature___("Persistence")
	public ExampleFileFilter(String extension, String description){
	this();
	if(extension!=null) addExtension(extension);
	if(description!=null) setDescription(description);
    }
/*@(Persistence)*/
 

    /**
     * Creates a file filter from the given string array.
     * Example: new ExampleFileFilter(String {"gif", "jpg"});
     *
     * Note that the "." before the extension is not needed adn
     * will be ignored.
     *
     * @see #addExtension
     */
    @Feature___("Persistence")
	public ExampleFileFilter(String[] filters){
	this(filters, null);
    }
/*@(Persistence)*/
 

    /**
     * Creates a file filter from the given string array and description.
     * Example: new ExampleFileFilter(String {"gif", "jpg"}, "Gif and JPG Images");
     *
     * Note that the "." before the extension is not needed and will be ignored.
     *
     * @see #addExtension
     */
    @Feature___("Persistence")
	public ExampleFileFilter(String[] filters, String description){
	this();
	for (int i = 0; i < filters.length; i++){
		 // add filters one by one
		 addExtension(filters[i]);
	}
	if(description!=null) setDescription(description);
    }
/*@(Persistence)*/
 

    /**
     * Return true if this file should be shown in the directory pane,
     * false if it shouldn't.
     *
     * Files that begin with "." are ignored.
     *
     * @see #getExtension
     * @see FileFilter#accepts
     */
    @Feature___("Persistence")
	public boolean accept(File f){
	if(f != null){
		 if(f.isDirectory()){
		return true;
		 }
		 String extension = getExtension(f);
		 if(extension != null && filters.get(getExtension(f)) != null){
		return true;
		 };
	}
	return false;
    }
/*@(Persistence)*/
 

    /**
     * Return the extension portion of the file's name .
     *
     * @see #getExtension
     * @see FileFilter#accept
     */
     @Feature___("Persistence")
	public String getExtension(File f){
	if(f != null){
		 String filename = f.getName();
		 int i = filename.lastIndexOf('.');
		 if(i>0 && i<filename.length()-1){
		return filename.substring(i+1).toLowerCase();
		 };
	}
	return null;
    }
/*@(Persistence)*/
 

    /**
     * Adds a filetype "dot" extension to filter against.
     *
     * For example: the following code will create a filter that filters
     * out all files except those that end in ".jpg" and ".tif":
     *
     *   ExampleFileFilter filter = new ExampleFileFilter();
     *   filter.addExtension("jpg");
     *   filter.addExtension("tif");
     *
     * Note that the "." before the extension is not needed and will be ignored.
     */
    @Feature___("Persistence")
	public void addExtension(String extension){
	if(filters == null){
		 filters = new Hashtable(5);
	}
	filters.put(extension.toLowerCase(), this);
	fullDescription = null;
    }
/*@(Persistence)*/
 

    /**
     * Returns the human readable description of this filter. For
     * example: "JPEG and GIF Image Files (*.jpg, *.gif)"
     *
     * @see setDescription
     * @see setExtensionListInDescription
     * @see isExtensionListInDescription
     * @see FileFilter#getDescription
     */
    @Feature___("Persistence")
	public String getDescription(){
	if(fullDescription == null) {
		 if(description == null || isExtensionListInDescription()){
		fullDescription = description==null ? "(" : description + " (";
		// build the description from the extension list
		Enumeration extensions = filters.keys();
		if(extensions != null){
			 fullDescription += "." + (String) extensions.nextElement();
			 while (extensions.hasMoreElements()) {
			fullDescription += ", ." + (String) extensions.nextElement();
			 }
		}
		fullDescription += ")";
		 } else {
		fullDescription = description;
		 }
	}
	return fullDescription;
    }
/*@(Persistence)*/
 

    /**
     * Sets the human readable description of this filter. For
     * example: filter.setDescription("Gif and JPG Images");
     *
     * @see setDescription
     * @see setExtensionListInDescription
     * @see isExtensionListInDescription
     */
    @Feature___("Persistence")
	public void setDescription(String description){
	this.description = description;
	fullDescription = null;
    }
/*@(Persistence)*/
 

    /**
     * Determines whether the extension list (.jpg, .gif, etc) should
     * show up in the human readable description.
     *
     * Only relevent if a description was provided in the constructor
     * or using setDescription();
     *
     * @see getDescription
     * @see setDescription
     * @see isExtensionListInDescription
     */
    @Feature___("Persistence")
	public void setExtensionListInDescription(boolean b){
	useExtensionsInDescription = b;
	fullDescription = null;
    }
/*@(Persistence)*/
 

    /**
     * Returns whether the extension list (.jpg, .gif, etc) should
     * show up in the human readable description.
     *
     * Only relevent if a description was provided in the constructor
     * or using setDescription();
     *
     * @see getDescription
     * @see setDescription
     * @see setExtensionListInDescription
     */
    @Feature___("Persistence")
	public boolean isExtensionListInDescription(){
	return useExtensionsInDescription;
    }
}
