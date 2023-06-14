package ac.code.verifier.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

/**
 * The class PathHelper provides methods that handle paths of the project.
 *
 */
public class PathHelper {
	
	private final static String SRC = "src";
	private final static String JAVA_EXTENSION = ".java";
	
	/**
	 * Finds the full path of the source file that contains the element
	 * @param pProcessingEnv The environment
	 * @param prootElem The element
	 * @return
	 */
	public static String getSourceFullPath(ProcessingEnvironment pProcessingEnv, Element prootElem) {
		try {
			String fileName = getSourceFileName(prootElem);
			String targetPath = getTargetPath(pProcessingEnv, fileName);
			String sourceRootPath = ConvertTargetPathToSourceRootPath(targetPath);
			String sourceFileRelativePath = getSourceFileRelativePath(prootElem);
			String sourceFullPath;
			if(!sourceFileRelativePath.isEmpty()) {
				sourceFullPath = findFileAbsolutePath(sourceRootPath, fileName, sourceFileRelativePath, sourceFileRelativePath);
			}else {
				sourceFullPath = findFileWithoutPrefixAbsolutePath(sourceRootPath, fileName);
			}
	
			return sourceFullPath;
		}catch (IOException e) {
			System.out.println("ERROR: " + e);
			return "";
		}
	}
	
	/**
	 * Finds the full path of the configuration file.
	 * @param pProcessingEnv The environment
	 * @param prootElem The element
	 * @return
	 */
	public static File getRefactorabilityConfigurationFullPath(ProcessingEnvironment pProcessingEnv, Element prootElem) {
		try {
			String fileName = getSourceFileName(prootElem);
			String targetPath = getTargetPath(pProcessingEnv, fileName);
			String sourceRootPath = ConvertTargetPathToSourceRootPath(targetPath);
				    
		    Path path = Paths.get(sourceRootPath);
		    path=path.getParent();
		    
		    File file = new File(path.toString(), "refactorability_configuration.json");
		    return file;
			
		}catch (IOException e) {
			System.out.println("ERROR: " + e);
			return null;
		}
	}
	
	/**
	 * Finds the target path of the file.
	 * @param pProcessingEnv The environment
	 * @param pFileName The name of the file
	 * @return
	 * @throws IOException
	 */
	private static String getTargetPath(ProcessingEnvironment pProcessingEnv, String pFileName) throws IOException {
		FileObject fileObject = pProcessingEnv.getFiler().getResource( StandardLocation.CLASS_OUTPUT, "", pFileName);	
		String targetPath = fileObject.toUri().getPath().toString();
		if(targetPath.substring(0, 1).equals("/")) {
			targetPath = targetPath.replaceFirst("/","");
		}
	    targetPath = targetPath.replaceAll("/","\\\\");   	
		return Paths.get(targetPath).toString();
	}
	
	
	/**
	 * Converts target path to path to the root of source files
	 * @param pTargetPath The target path 
	 * @return The root of source files
	 */
	private static String ConvertTargetPathToSourceRootPath(String pTargetPath) {
		try {		    
	    	Path path = Paths.get(pTargetPath);		
	    	while (path !=null)
	    	{   		 		
	    		File[] directories = new File(path.toString()).listFiles(File::isDirectory);
	    		if (directories!=null) {
	    			for (File dir  : directories)
	        		{
	    				if(isSourcePath(dir.toPath())) {
	    					return dir.toString();
	    				}
	        		}	
	    		}
	    		path=path.getParent();
	    	}
			return "";
		}catch(Exception e) {
			System.out.println("ERROR: failed to convert " + pTargetPath + " to source root path");
			return "";
		}	
	}
	
	/**
	 * Converts element the name of the source file
	 * @param pElem The element
	 * @return
	 */
	private static String getSourceFileName(Element pElem) {
		String path = pElem.toString();   
    	return path.substring(path.lastIndexOf(".") + 1) + JAVA_EXTENSION;	
	}
	
	/**
	 * Finds the relative path of the source file
	 * @param pElem The element
	 * @return
	 */
	private static String getSourceFileRelativePath(Element pElem) {
		String path = pElem.toString();
		if(!path.contains(".")) {
			return "";
		}
		path = path.substring(0, path.lastIndexOf("."));
		return path.replaceAll("\\.","\\\\");
	}
	
	/**
	 * Recursive method, finds the full path to the file.
	 * @param pFullPathFromRootUntilCurent The full path from the root to the tested folder.
	 * @param pFileName The name of the file we are interested in.
	 * @param pFullRelativePath The complete known relative path.
	 * @param pRemainingRelativePath The part of the relative path we haven't found yet.
	 * @return
	 */
	private static String findFileAbsolutePath(String pFullPathFromRootUntilCurent, String pFileName, String pFullRelativePath, String pRemainingRelativePath) {		
		if (pRemainingRelativePath.isEmpty() && isFileExistsInCurentFolder(pFullPathFromRootUntilCurent, pFileName)) {
			return Paths.get(pFullPathFromRootUntilCurent, pFileName).toString();		
		}
		
		if(!pRemainingRelativePath.isEmpty()) {
		
			String NextPartOfDir = Paths.get(pRemainingRelativePath).getName(0).toString();	
			File[] directories = new File(pFullPathFromRootUntilCurent.toString()).listFiles(File::isDirectory);
    		if (directories==null) {
    			return "";
    		}
    			
			for (File dir  : directories) //Case we found the next part
    		{
				if(dir.getName().equals(NextPartOfDir)) {
					String newRemainingRelativePath = removeFirstPartFromPath(pRemainingRelativePath);
					String res = findFileAbsolutePath(dir.toString(), pFileName, pFullRelativePath, newRemainingRelativePath);
					if(!res.isEmpty()) {
						return res;
					}
				}
    		}
			
			for (File dir  : directories) //Case we don't found the next part. The relative path start from the beginning.
    		{
				String res = findFileAbsolutePath(dir.toString(), pFileName, pFullRelativePath, pFullRelativePath);
				if(!res.isEmpty()) {
					return res;
				}
    		}    
		}	
		return "";	
	}
	
	/**
	 * finds the full path of the file.
	 * @param pFullPathFromRootUntilCurent The full path from the root to the tested folder.
	 * @param pFileName The name of the file we are interested in.
	 * @return
	 */
	private static String findFileWithoutPrefixAbsolutePath(String pFullPathFromRootUntilCurent, String pFileName) {
		if (isFileExistsInCurentFolder(pFullPathFromRootUntilCurent, pFileName)) {
			return Paths.get(pFullPathFromRootUntilCurent, pFileName).toString();		
		}
		
		File[] directories = new File(pFullPathFromRootUntilCurent.toString()).listFiles(File::isDirectory);
		if (directories==null) {
			return "";
		}
			
		for (File dir  : directories)
		{
			String res = findFileWithoutPrefixAbsolutePath(dir.toString(), pFileName);
			if(!res.isEmpty()) {
				return res;
			}
		}
		return "";
	}

	/**
	 * Checks whether the path is a path to the src folder.
	 * @param pPath The path 
	 * @return
	 */
	private static boolean isSourcePath(Path pPath) {	
		return pPath.getFileName().toString().equals(SRC);
	}
	
	/**
	 * Removes the first part from the path 
	 * @param pPath The path 
	 * @return
	 */
	private static String removeFirstPartFromPath(String pPath) {	
		int indexOfFirstPart = pPath.indexOf("\\");
		if (indexOfFirstPart == -1) {
			return "";
		}		
		return pPath.substring(indexOfFirstPart+1);
	}
	
	/**
	 * Checks if the file is in the current folder.
	 * @param pCurrentFolderPath The path to the current folder
	 * @param pFileName The name of the file.
	 * @return
	 */
	private static boolean isFileExistsInCurentFolder(String pCurrentFolderPath, String pFileName) {	
		File[] files = new File(pCurrentFolderPath).listFiles(File::isFile);
		if (files!=null) {
			for (File file  : files)
    		{
				if(file.getName().equals(pFileName)) {
					return true;
				}
    		}	
		}
		return false;
	}
}


