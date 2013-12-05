package retineurali.utility;

import java.util.Random;

public class ConnectionMatrix {

	private double[] weights;
	private int size;

	public ConnectionMatrix(int size){
		weights = new double[size];
		this.size = size;
	}

	public void initialiteRandomWeights(){
		Random generator = new Random();

		for(int count=0; count < size; count++){
			weights[count] = (generator.nextDouble()*2)-1;
		}
	}

	public void initialiteWeights(double[] weights){
		this.weights = weights;

	}

	//AGGIORNAMENTO DEI PESI
	public void updateWeights(int element, double deltaWeight){
		weights[element] += deltaWeight;
	}

	public void changeMatrixSize(int newSize){
		double[] tmpWeights = new double[newSize];
		//ESTENDERE LA MATRICE DEI PESI
		if(newSize > size){
			tmpWeights = weights;
			weights = new double[newSize];

			for(int count=0; count<size; count++){
				weights[count] = tmpWeights[count];
			}
			size = newSize;
		//RIMPICCIOLIRE LA MATRICE DEI PESI
		}else if(newSize < size){
			for(int count=0; count < newSize; count++){
				tmpWeights[count] = weights[count];
			}
			weights = tmpWeights;

		}
	}

	public double[] getWeightMatrix() {
		return weights;
	}

	public int getMatrixSize() {
		return size;
	}
	
	public double getWeightAt(int element){
		return weights[element];
	}

}
