package retineurali.tlu;
//SINGOLA UNITA' DI ELABORAZIONE DELLA RETE NEURALE


public class ThresholdLogicUnit {

	private double weightedSum;


	private double activation;

	private double bias;

	public ThresholdLogicUnit(){
		weightedSum = 0.0;
		activation = 0.0;

	}


	//CALCOLO DELLA SOMMA PESATA
	public void calculateWeightedSum(double[] inputs, double[] weights, double bias){

		weightedSum=0.0;

		if(inputs.length != weights.length){
			System.out.println("Error: input size is different from weight size!");
			return;
		}

		for(int i=0; i<inputs.length; i++){

			weightedSum += inputs[i]*weights[i]; 
		}

		//AGGIUNGE IL BIAS
		weightedSum += bias;
	}

	//CALCOLO DELLA FUNZIONE DI ATTIVAZIONE:SIGMOIDE
	public void calculateSigmoidActivation(){
		activation = 1 / Math.exp(-weightedSum);
	}

	//CALCOLO DELLA FUNZIONE DI ATTIVAZIONE:GAUSSIANA
	public void calculateGaussianoActivation(){
		activation = Math.exp(-(Math.pow(weightedSum,2)));
	}

	//CALCOLO DELLA FUNZIONE DI ATTIVAZIONE:SGN
	public void calculateSgnActivation(){

		activation = weightedSum>1 ? 1.0 : 0.0;
	}

	//FIRE DEL NEURONE 
	public double fireNeurons(String type, double[] inputs, double[] weights, double bias){
		
		calculateWeightedSum(inputs, weights, bias);
		
		if(type.equalsIgnoreCase("sigmoid"))
			calculateSigmoidActivation();
		else if(type.equalsIgnoreCase("gaussian"))
			calculateGaussianoActivation();
		else if(type.equalsIgnoreCase("sgn"))
			calculateSgnActivation();
		
		return activation;
	}
	
	//METODI SET-GET
	public double getActivation() {
		return activation;
	}

	public double getWeightedSum() {
		return weightedSum;
	}


}
