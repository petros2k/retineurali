package retineurali.patterns;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PatternMatrix {

	private double[][] inputPatterns;
	private double[][] outputPatterns;
 	private int inputPatternLength;
 	private int outputPatternLength;
 	private int numberOfPatterns;
 	
 	public PatternMatrix(File patternFile, int inputPatternLength, int outputPatternLength, int numberOfPatterns) throws FileNotFoundException{
 		this.inputPatternLength = inputPatternLength;
 		this.outputPatternLength = outputPatternLength;
 		this.numberOfPatterns = numberOfPatterns;
 		inputPatterns = new double[this.numberOfPatterns][this.inputPatternLength];
 		outputPatterns = new double[this.numberOfPatterns][this.outputPatternLength];
 		
 	}
 	
 	public PatternMatrix(int inputPattarenLength, int outputPatternLength, int numberOfPatterns) throws FileNotFoundException{
 		
 		
 	}
 	
 	public void readFile(File patternFile) throws FileNotFoundException{
 		Scanner fileReader = new Scanner(patternFile);
 		String tmpInput;
 		String tmpOutput;
 		fileReader.nextLine();
 		fileReader.nextLine();
 		
 		for(int i=0; i<numberOfPatterns; i++){
 			tmpInput = fileReader.nextLine();
 			tmpOutput = fileReader.nextLine();
 			
 			fileReader.nextLine();
 			
 			StringTokenizer inputTokenizer = new StringTokenizer(tmpInput, ",");
 			StringTokenizer outputTokenizer = new StringTokenizer(tmpOutput, ",");
 			
 			for(int countInput=0; countInput<inputPatternLength; countInput++)
 				inputPatterns[i][countInput] = Double.parseDouble(inputTokenizer.nextToken());
 			for(int countOutput=0; countOutput<outputPatternLength; countOutput++)
 				outputPatterns[i][countOutput] = Double.parseDouble(inputTokenizer.nextToken());
 			
 		}
 	}
 	
 	public double[] getInputPatterns(int patternIndex) {
		return inputPatterns[patternIndex];
	}

	public double[] getOutputPatterns(int patternIndex) {
		return outputPatterns[patternIndex];
	}

	public int getInputPatternLength() {
		return inputPatternLength;
	}

	public int getOutputPatternLength() {
		return outputPatternLength;
	}

	public int getNumberOfPatterns() {
		return numberOfPatterns;
	}

	
}
