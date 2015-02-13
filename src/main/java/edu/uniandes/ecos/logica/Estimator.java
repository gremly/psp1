package edu.uniandes.ecos.logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.print.attribute.HashAttributeSet;

public class Estimator {

	/**
	 * Atributos
	 */
	
	private String x;
	private String y;
	private double est;

	/**
	 * Métodos
	 */

	/**
	 * Define el conjunto de datos para realizar los cálculos.
	 * @param x String con el pool de datos x separados por comas.
	 * @param y String con el pool de datos y separados por comas.
	 */
	public void defineDataSet(String x, String y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Define el estimado para mejorar la predicción.
	 * @param estimate valor estimado para calcular la predicción.
	 */
	public void defineEstimate(double estimate){
		this.est = estimate;
	}

	/**
	 * Almacena los datos a obtener en una tabla hash
	 * donde la llave es el nombre del parámetro esperado.
	 * @return tabla de parámetros calculados.
	 */
	public HashMap<String, Double> estimate(){
		HashMap<String, Double> result = new HashMap<String, Double>();
		Calc calc = new Calc();
		calc.setData(getData());
		calc.getRegParams();
		calc.getCorrelationCoefficients();

		result.put("b0", calc.getB0());
		result.put("b1", calc.getB1());
		result.put("rxy", calc.getR());
		result.put("r2", calc.getR2());
		result.put("y", calc.getY(this.est));
		
		return result;
	}
	
	private LinkedList<ArrayList> getData(){
		LinkedList<ArrayList> data = new LinkedList<ArrayList>();
		ArrayList d;
		String[] dataX = this.x.split(",");
		String[] dataY = this.y.split(",");
		
		if (dataX.length == dataY.length) {
			for (int i = 0; i < dataX.length; i++) {
				d = new ArrayList();
				d.add(Integer.parseInt(dataX[i].trim()));
				d.add(Integer.parseInt(dataY[i].trim()));
				data.add(d);	
			}
		}
		return data;
	}
	
	public String getX() {
		return x;
	}

	public String getY() {
		return y;
	}
	
}
