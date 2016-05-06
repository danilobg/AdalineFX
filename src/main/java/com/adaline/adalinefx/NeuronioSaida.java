package com.adaline.adalinefx;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author danilo
 */
public class NeuronioSaida {
    
    private double[][] peso;
    
    private double[][] delta;
    
    private double pesoBias;
    
    private double deltaBias;
    
    private String letra;

    public double[][] getPeso() {
        return peso;
    }
    
    public double getPeso(int linha, int coluna) {
        return peso[linha][coluna];
    }

    public void setPeso(double[][] peso) {
        this.peso = peso;
    }
    
    public void setPeso(int linha, int coluna, double peso) {
        this.peso[linha][coluna] = peso;
    }

    public double[][] getDelta() {
        return delta;
    }
    
    public double getDelta(int linha, int coluna) {
        return delta[linha][coluna];
    }

    public void setDelta(double[][] delta) {
        this.delta = delta;
    }
    
    public void setDelta(int linha, int coluna, double delta) {
        this.delta[linha][coluna] = delta;
    }
    

    public double getPesoBias() {
        return pesoBias;
    }

    public void setPesoBias(double pesoBias) {
        this.pesoBias = pesoBias;
    }

    public double getDeltaBias() {
        return deltaBias;
    }

    public void setDeltaBias(double deltaBias) {
        this.deltaBias = deltaBias;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
        
    public NeuronioSaida(String letra)
    {
        
        peso = new double[9][7];
        delta = new double[9][7];

            for (int linha = 0 ; linha < 9 ; linha++)
            {
                for (int coluna = 0 ; coluna < 7 ; coluna++)
                {
                    peso[linha][coluna] = 0;
                    delta[linha][coluna] = 0;
                }
            }

            this.letra = letra;        
    }        
    
}
