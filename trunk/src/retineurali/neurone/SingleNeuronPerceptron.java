package retineurali.neurone;

import java.io.File;
import java.io.FileNotFoundException;

import retineurali.patterns.PatternMatrix;
import retineurali.tlu.ThresholdLogicUnit;
import retineurali.utility.ConnectionMatrix;

/*
 * CLASSE MODELLO NEURONE
 */

public class SingleNeuronPerceptron {

	private ConnectionMatrix weightMatrix;
	private ThresholdLogicUnit outputNeuron;
	private PatternMatrix patterns;
	double bias;
	int epochLimit;
	double errorLimit;
	int numberOfInputs;
	int numberOfPatterns;
	double learningConstant;
	double[] perceptronOutputs;
	int epochCount;
	double error;



	public SingleNeuronPerceptron(String patternFile, int numberOfInputs, int numberOfPatterns, int epochLimit, double errorLimit, double learningConstant)throws FileNotFoundException
	{	
		weightMatrix = new ConnectionMatrix(numberOfInputs);
		//INIZIALIZZA LA MATRICE DEI PESI
		weightMatrix.initialiteRandomWeights();	
		initialize(patternFile,numberOfInputs, numberOfPatterns, epochLimit, errorLimit, learningConstant);

	}


	private void initialize(String patternFile, int numberOfInputs,int numberOfPatterns, int epochLimit, double errorLimit,double learningConstant) throws FileNotFoundException {
		
		outputNeuron = new ThresholdLogicUnit();
		patterns = new PatternMatrix(new File(patternFile), numberOfInputs, 1, numberOfPatterns);
		bias = Math.random() *2 -1;
		this.epochLimit = epochLimit;
		this.errorLimit = errorLimit;
		this.numberOfInputs = numberOfInputs;
		this.numberOfPatterns = numberOfPatterns;
		this.learningConstant = learningConstant;
		perceptronOutputs = new double [numberOfPatterns];
		epochCount = 0;
		computeAllOutputs();
		computeError();		
	}


	private void computeAllOutputs() {

		for(int count=0; count<numberOfPatterns; count++){
			perceptronOutputs[count] = outputNeuron.fireNeurons("sgn", patterns.getInputPatterns(count), weightMatrix.getWeightMatrix(), bias);
		}

	}


	private void computeError() {
		error = 0.0;
		for(int patternCount =0; patternCount<numberOfPatterns; patternCount++){
			double[] tmp = patterns.getOutputPatterns(patternCount);
			error += Math.pow((tmp[0]-computeOutput(patternCount)), 2.0);
		}

	}


	private double computeOutput(int patternIndex) {

		return outputNeuron.fireNeurons("sgn", patterns.getInputPatterns(patternIndex), weightMatrix.getWeightMatrix(), bias);
	}

	private void presentOnePattern(int patternCount){

		double[] tmpInputs = patterns.getInputPatterns(patternCount);
		double[] tmpOutputs = patterns.getOutputPatterns(patternCount);
		double tmpPerceptronOutput = computeOutput(patternCount);

		bias += learningConstant * (tmpOutputs[0] - tmpPerceptronOutput);

		for(int weightCount=0; weightCount<numberOfInputs; weightCount++){
			weightMatrix.updateWeights(weightCount, (learningConstant*(tmpOutputs[0] - tmpPerceptronOutput)*tmpInputs[weightCount]));
		}


	}

	public void performLearningEpoch(){
		for(int patternCount = 0; patternCount < numberOfPatterns; patternCount++){
			presentOnePattern(patternCount);
		}
		computeError(); 
		computeAllOutputs();
		epochCount++;
	}

	public void learn(){
		while(!isDoneLearning())
			performLearningEpoch();
	}


	public boolean isDoneLearning() {
		if(error >errorLimit && epochCount< epochLimit)
			return false;

		return true;

	}
	
	public double recall(double[] inputs){
		
		return outputNeuron.fireNeurons("sgn", inputs, weightMatrix.getWeightMatrix(), bias);
	}
	
	//METODI SET-GET
	public double getBias() {
		return bias;
	}


	public void setBias(double bias) {
		this.bias = bias;
	}


	public int getEpochLimit() {
		return epochLimit;
	}


	public void setEpochLimit(int epochLimit) {
		this.epochLimit = epochLimit;
	}


	public double getErrorLimit() {
		return errorLimit;
	}


	public void setErrorLimit(double errorLimit) {
		this.errorLimit = errorLimit;
	}


	public double getLearningConstant() {
		return learningConstant;
	}


	public void setLearningConstant(double learningConstant) {
		this.learningConstant = learningConstant;
	}


	public ConnectionMatrix getWeightMatrix() {
		return weightMatrix;
	}


	public int getEpochCount() {
		return epochCount;
	}


	public double getError() {
		return error;
	}



}
