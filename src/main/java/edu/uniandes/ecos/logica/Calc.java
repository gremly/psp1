package edu.uniandes.ecos.logica;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Clase encargada de llevar a cabo los cálculos matemáticos.
 */
public class Calc {
	
	/**
	 * Atributos
	 */
	private LinkedList<ArrayList> dataSet;
	
	/**
	 * Tamaño del pool de datos.
	 */
	private int dataSize;
	
	/**
	 * Parámetros de la regresión lineal.
	 */
	private double b0;
	private double b1;
	
	/**
	 * Coeficientes de correlación.
	 */
	private double rxy;
	private double r2;
	
	/**
	 * Valor para la predicción mejorada.
	 */
	private double y;
	
	 /**
	 * Define el pool de datos sobre los que operará
	 * @param dataSet Lista enlzazada cuyo elementos deben ser (x, y)
	 */
	public void setData(LinkedList<ArrayList> dataSet){
		this.dataSet = dataSet;
		this.dataSize = this.dataSet.size();
	}
	
	 /**
		 * Obtiene los parámetros B0 y B1 de la regresión lineal.
		 */ 
	public void getRegParams(){
		double[] mean = calculateMean();		
		double xMean = mean[0];
		double yMean = mean[1];
		this.b1 = this.calculateBOne(xMean, yMean);
		this.b0 = this.calculateBZero(this.b1 ,xMean, yMean);
	}
	
	/**
	 * Obtiene los coeficientes de la correlación.
	 */
	public void getCorrelationCoefficients(){
		this.rxy = this.calculateR();
		this.r2 = this.calculateR2(this.rxy);
	}

	/**
	 * Obtiene el valor de la predicción mejorada (Y) dado un valor de estimación.
	 * @param x Estimación dada por el usuario
	 * @return y valor para la predicción mejorada.
	 */
	public double getY(double x){
		return this.b0 + this.b1*x;
	}
	
	/**
	 * Devuelve el parámetro b0 de la regresión.
	 * @return double parámetro b0.
	 */
	public double getB0() {
		return b0;
	}

	/**
	 * Devuelve el parámetro b1 de la regresión.
	 * @return double parámetro b1.
	 */
	public double getB1() {
		return b1;
	}

	/**
	 * Devuelve el coeficiente rxy de la correlación..
	 * @return double coeficiente r de la correlación.
	 */
	public double getR() {
		return rxy;
	}

	/**
	 * Devuelve el coeficiente r^2 de la correlación..
	 * @return double coeficiente r^2 de la correlación.
	 */
	public double getR2() {
		return r2;
	}

	/**
	 * Calcula el coeficiente rXY de la correlación.
	 * @return double coeficiente rXY.
	 */
	private double calculateR() {
		double xySum=  0;
		double xSum = 0;
		double ySum = 0;
		double divisor;
		double r;
		double x2 = 0;
		double y2 = 0;
		
		for (ArrayList<Double> pair : dataSet) {
			xySum += pair.get(0) * pair.get(1);
			xSum += pair.get(0);			
			ySum += pair.get(1);
			x2 += Math.pow(pair.get(0), 2);
			y2 += Math.pow(pair.get(1), 2);
		}
		
		divisor = this.dataSize*xySum - (xSum * ySum);
		r = divisor / Math.sqrt((this.dataSize * x2 -  Math.pow(xSum, 2)) * (this.dataSize * y2 - Math.pow(ySum, 2)));
		
		return r;
	}
	
	/**
	 * Calcula el coeficiente r^2 de la correlación.
	 * @param r coeficiente rXY de la correlacion.
	 * @return coeficiente r^2
	 */
	private double calculateR2(double r) {
		return Math.pow(r, 2);
	}

	/**
	 * Calcula el parámetro b0 de la regresión.
	 * @param bOne parámetro b1 de la regresión.
	 * @param xMean Média aritmética del conjunto de datos x.
	 * @param yMean Média aritmética del conjunto de datos y.
	 * @return double con el parámetro b0 de la regresión.
	 */
	private double calculateBZero(double bOne, double xMean, double yMean) {
		return  yMean - bOne*xMean;
	}

	/**
	 * Calcula el parámetro B1 de la regresión lineal.
	 * @param xMean double resultado de la media de X
	 * @param yMean double resultado de la media de Y
	 * @return double com parámetro B1.
	 */
	private double calculateBOne(double xMean, double yMean) {
		double bOne;
		double pairSum = 0;
		double xSum = 0;
		double divisor;
		
		for (ArrayList<Double> pair :  this.dataSet) {
			pairSum += pair.get(0) * pair.get(1);
			xSum += Math.pow(pair.get(0), 2);
		}
		System.out.println(xSum);
		divisor = pairSum - this.dataSize*yMean*xMean;
		bOne = divisor / (xSum - this.dataSize*Math.pow(xMean, 2));
		
		return bOne;
	}
	
	/**
	 * Calcula la media aritmética tomado como fuente data
	 * @return double con la media aritmética.
	 */
	public double[] calculateMean(){
		double media[] = new double[2];
		
		for (ArrayList<Double> pair:  this.dataSet) {
			media[0] += pair.get(0);
			media[1] += pair.get(1);
		}
		
		media[0] = media[0] / this.dataSize;
		media[1] = media[1] / this.dataSize;
		
		return media;
	}
	
}
