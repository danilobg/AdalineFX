package com.adaline.adalinefx;


import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author danilo
 */
public class Adaline {
    
    private List<NeuronioEntrada> listLetra;
    
    private List<NeuronioSaida> listCamadaSaida;
    
    private double erroMinimo = 0.001;
    
    private double taxaAprendizagem = 0.01;
    
    private double maiorErroQuadratico = 1000;
    
    private int tamCamada = 7;

    public Adaline() {
        
        listLetra = new ArrayList<NeuronioEntrada>();
        listCamadaSaida = new ArrayList<NeuronioSaida>();
        
        listLetra.add(new NeuronioEntrada(neuronioA1, "A"));
        listLetra.add(new NeuronioEntrada(neuronioA2, "A"));
        listLetra.add(new NeuronioEntrada(neuronioA3, "A"));
        
        listLetra.add(new NeuronioEntrada(neuronioB1, "B"));
        listLetra.add(new NeuronioEntrada(neuronioB2, "B"));
        listLetra.add(new NeuronioEntrada(neuronioB3, "B"));
        
        listLetra.add(new NeuronioEntrada(neuronioC1, "C"));
        listLetra.add(new NeuronioEntrada(neuronioC2, "C"));
        listLetra.add(new NeuronioEntrada(neuronioC3, "C"));        
        
        listLetra.add(new NeuronioEntrada(neuronioD1, "D"));
        listLetra.add(new NeuronioEntrada(neuronioD2, "D"));
        listLetra.add(new NeuronioEntrada(neuronioD3, "D"));
        
        listLetra.add(new NeuronioEntrada(neuronioE1, "E"));
        listLetra.add(new NeuronioEntrada(neuronioE2, "E"));
        listLetra.add(new NeuronioEntrada(neuronioE3, "E"));
        
        listLetra.add(new NeuronioEntrada(neuronioJ1, "J"));
        listLetra.add(new NeuronioEntrada(neuronioJ2, "J"));
        listLetra.add(new NeuronioEntrada(neuronioJ3, "J"));
        
        listLetra.add(new NeuronioEntrada(neuronioK1, "K"));
        listLetra.add(new NeuronioEntrada(neuronioK2, "K"));
        listLetra.add(new NeuronioEntrada(neuronioK3, "K"));
        
        listCamadaSaida.add(new NeuronioSaida("A"));
        listCamadaSaida.add(new NeuronioSaida("B"));
        listCamadaSaida.add(new NeuronioSaida("C"));
        listCamadaSaida.add(new NeuronioSaida("D"));
        listCamadaSaida.add(new NeuronioSaida("E"));
        listCamadaSaida.add(new NeuronioSaida("J"));
        listCamadaSaida.add(new NeuronioSaida("K"));
      
    }
    
    public double funcaoBipolar(double x){
        if (x >= 0) return 1;
        else return -1;
    }
    
    public int treinarRede()
    {
        double erro, erroQuadratico, saidaDesejada;
        int ciclos = 0;      

        while (maiorErroQuadratico > erroMinimo)
        {
            maiorErroQuadratico = -10000;

            for (int padrao = 0; padrao < listLetra.size(); padrao++)
            {
                //System.out.println("padrao: " + padrao + "  | listLetra.size(): " + listLetra.size() + "\n----");
                
                for (int camadaSaida = 0; camadaSaida < tamCamada; camadaSaida++)
                {
                    //System.out.println("camadaSaida: " + camadaSaida + "  | tamCamada: " + tamCamada);
                    
                    if (listLetra.get(padrao).getLetra().equals(listCamadaSaida.get(camadaSaida).getLetra()))
                    {
                        saidaDesejada = 1;
                    }

                    else
                    {
                        saidaDesejada = -1;
                    }

                    erro = saidaDesejada - somatorioRede(padrao, camadaSaida);
                    
                    for (int linha = 0; linha < 9; linha++)
                    {
                        for (int coluna = 0; coluna < 7; coluna++)
                        {
                                                        
                            //double teste = erro * listLetra.get(padrao).getNeuronioTreino(linha, coluna) * taxaAprendizagem;
                            
                            listCamadaSaida.get(camadaSaida).setDelta(linha, coluna, (erro * listLetra.get(padrao).getNeuronioTreino(linha, coluna) * taxaAprendizagem));
                            
                            //teste = listCamadaSaida.get(camadaSaida).getPeso(linha, coluna) + listCamadaSaida.get(camadaSaida).getDelta(linha, coluna);
                            
                            listCamadaSaida.get(camadaSaida).setPeso(linha, coluna, (listCamadaSaida.get(camadaSaida).getPeso(linha, coluna) + listCamadaSaida.get(camadaSaida).getDelta(linha, coluna)));
                            
                        }
                          
                    }

                    listCamadaSaida.get(camadaSaida).setDeltaBias(erro * listLetra.get(padrao).getBias() * taxaAprendizagem);
                    listCamadaSaida.get(camadaSaida).setPesoBias(listCamadaSaida.get(camadaSaida).getDeltaBias() + listCamadaSaida.get(camadaSaida).getPesoBias());

                    erroQuadratico = (Math.pow(erro,2)) * 0.5;
                    
                    if (erroQuadratico > maiorErroQuadratico)
                    {
                        maiorErroQuadratico = erroQuadratico;
                    }
                }
            }

            ciclos++;
            
            System.out.println("Ciclos: " + ciclos);
            
        }

        return ciclos;
    }
    
    private double somatorioRede(int idLetra, int idPeso)
    {
        double soma = 0;

        for (int linha = 0; linha < 9; linha++)
        {
            for (int coluna = 0; coluna < 7; coluna++)
            {
                soma += listLetra.get(idLetra).getNeuronioTreino(linha, coluna) * listCamadaSaida.get(idPeso).getPeso(linha, coluna);
            }
        }

        soma += listCamadaSaida.get(idPeso).getPesoBias() * listLetra.get(idLetra).getBias();

        return soma;
    }
    
    public String testar(double[][] neuronio)
    {
        double result = 0;
        String caracetere = "";

        for (int i = 0; i < 7; i++)
        {
            result = 0;

            for (int linha = 0; linha < 9; linha++)
            {
                for (int coluna = 0; coluna < 7; coluna++)
                {
                    result += neuronio[linha][coluna] * listCamadaSaida.get(i).getPeso(linha, coluna);
                }
            }

            result += listCamadaSaida.get(i).getPesoBias();

            if (result >= 0)
            {
                caracetere += listCamadaSaida.get(i).getLetra() + " ";
            }
        }

        return caracetere;

    }
        
    public static void main(String[] args) {
        
        /*double neuronioTeste[][] = {{0, 0, 1, 1, 0, 0, 0},
                             {0, 0, 0, 1, 0, 0, 0},
                             {0, 0, 0, 1, 0, 0, 0},
                             {0, 0, 1, 0, 1, 0, 0},
                             {0, 0, 1, 0, 1, 0, 0},
                             {0, 1, 1, 1, 1, 1, 0},
                             {0, 1, 0, 0, 0, 1, 0},
                             {0, 1, 0, 0, 0, 1, 0},
                             {1, 1, 1, 0, 1, 1, 1},
                            };
        
        Adaline p = new Adaline();
        
        System.out.println("CICLOS: " + p.treinarRede());
        
        System.out.println("\n\n-------\n TESTE: " + p.testar(neuronioTeste));
        */
    }
    
    // === CAMADA DE ENTRADA ===
        
    double neuronioA1[][] = {{-1, -1, 1, 1, -1, -1, -1},
                             {-1, -1, -1, 1, -1, -1, -1},
                             {-1, -1, -1, 1, -1, -1, -1},
                             {-1, -1, 1, -1, 1, -1, -1},
                             {-1, -1, 1, -1, 1, -1, -1},
                             {-1, 1, 1, 1, 1, 1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {1, 1, 1, -1, 1, 1, 1},
                            };

    double neuronioB1[][] = {{1, 1, 1, 1, 1, 1, -1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, 1, 1, 1, 1, -1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {1, 1, 1, 1, 1, 1, -1},
                            };

    double neuronioC1[][] = {{-1, -1, 1, 1, 1, 1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, -1, 1, 1, 1, 1, -1},
                            };

    double neuronioD1[][] = {{1, 1, 1, 1, 1, -1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {1, 1, 1, 1, 1, -1, -1},
                            };

    double neuronioE1[][] = {{1, 1, 1, 1, 1, 1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, -1},
                             {-1, 1, -1, 1, -1, -1, -1},
                             {-1, 1, 1, 1, -1, -1, -1},
                             {-1, 1, -1, 1, -1, -1, -1},
                             {-1, 1, -1, -1, -1, -1, -1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {1, 1, 1, 1, 1, 1, 1},
                            };

    double neuronioJ1[][] = {{-1, -1, -1, 1, 1, 1, 1},
                             {-1, -1, -1, -1, -1, 1, -1},
                             {-1, -1, -1, -1, -1, 1, -1},
                             {-1, -1, -1, -1, -1, 1, -1},
                             {-1, -1, -1, -1, -1, 1, -1},
                             {-1, -1, -1, -1, -1, 1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {-1, -1, 1, 1, 1, -1, -1},
                            };

    double neuronioK1[][] = {{1, 1, 1, -1, -1, 1, 1},
                             {-1, 1, -1, -1, 1, -1, -1},
                             {-1, 1, -1, 1, -1, -1, -1},
                             {-1, 1, 1, -1, -1, -1, -1},
                             {-1, 1, 1, -1, -1, -1, -1},
                             {-1, 1, -1, 1, -1, -1, -1},
                             {-1, 1, -1, -1, 1, -1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {1, 1, 1, -1, -1, 1, 1},
                            }; 

    //-----------------------------------------------------------------

    double neuronioA2[][] = {{-1, -1, -1, 1, -1, -1, -1},
                             {-1, -1, -1, 1, -1, -1, -1},
                             {-1, -1, -1, 1, -1, -1, -1},
                             {-1, -1, 1, -1, 1, -1, -1},
                             {-1, -1, 1, -1, 1, -1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {-1, 1, 1, 1, 1, 1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                            };

    double neuronioB2[][] = {{1, 1, 1, 1, 1, 1, -1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {1, 1, 1, 1, 1, 1, -1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {1, 1, 1, 1, 1, 1, -1},
                            };

    double neuronioC2[][] = {{-1, -1, 1, 1, 1, -1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {-1, -1, 1, 1, 1, -1, -1},
                            };

    double neuronioD2[][] = {{1, 1, 1, 1, 1, -1, -1},
                             {1, -1, -1, -1, -1, 1, -1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {1, -1, -1, -1, -1, 1, -1},
                             {1, 1, 1, 1, 1, -1, -1},
                            };

    double neuronioE2[][] = {{1, 1, 1, 1, 1, 1, 1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, 1, 1, 1, 1, -1, -1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, 1, 1, 1, 1, 1, 1},
                            };

    double neuronioJ2[][] = {{-1, -1, -1, -1, -1, 1, -1},
                             {-1, -1, -1, -1, -1, 1, -1},
                             {-1, -1, -1, -1, -1, 1, -1},
                             {-1, -1, -1, -1, -1, 1, -1},
                             {-1, -1, -1, -1, -1, 1, -1},
                             {-1, -1, -1, -1, -1, 1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {-1, -1, 1, 1, 1, -1, -1},
                            };

    double neuronioK2[][] = {{1, -1, -1, -1, -1, 1, -1},
                             {1, -1, -1, -1, 1, -1, -1},
                             {1, -1, -1, 1, -1, -1, -1},
                             {1, -1, 1, -1, -1, -1, -1},
                             {1, 1, -1, -1, -1, -1, -1},
                             {1, -1, 1, -1, -1, -1, -1},
                             {1, -1, -1, 1, -1, -1, -1},
                             {1, -1, -1, -1, 1, -1, -1},
                             {1, -1, -1, -1, -1, 1, -1},
                            }; 

    //-----------------------------------------------------------------

    double neuronioA3[][] = {{-1, -1, -1, 1, -1, -1, -1},
                             {-1, -1, -1, 1, -1, -1, -1},
                             {-1, -1, 1, -1, 1, -1, -1},
                             {-1, -1, 1, -1, 1, -1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {-1, 1, 1, 1, 1, 1, -1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {1, 1, -1, -1, -1, 1, 1},
                            };

    double neuronioB3[][] = {{1, 1, 1, 1, 1, 1, -1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, 1, 1, 1, 1, 1},
                             {-1, 1, -1, -1, -1, -1, -1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {1, 1, 1, 1, 1, 1, -1},
                            };

    double neuronioC3[][] = {{-1, -1, 1, 1, 1, -1, 1},
                             {-1, 1, -1, -1, -1, 1, 1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, -1, -1, -1, -1, -1, -1},
                             {1, -1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {-1, -1, 1, 1, 1, -1, -1},
                            };

    double neuronioD3[][] = {{1, 1, 1, 1, 1, -1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {1, 1, 1, 1, 1, -1, -1},
                            };

    double neuronioE3[][] = {{1, 1, 1, 1, 1, 1, 1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {-1, 1, -1, -1, -1, -1, -1},
                             {-1, 1, -1, 1, -1, -1, -1},
                             {-1, 1, 1, 1, -1, -1, -1},
                             {-1, 1, -1, 1, -1, -1, -1},
                             {-1, 1, -1, -1, -1, -1, -1},
                             {-1, 1, -1, -1, -1, -1, 1},
                             {1, 1, 1, 1, 1, 1, 1},
                            };

    double neuronioJ3[][] = {{-1, -1, -1, 1, 1, 1, 1},
                             {-1, -1, -1, -1, -1, 1, -1},
                             {-1, -1, -1, -1, -1, 1, -1},
                             {-1, -1, -1, -1, -1, 1, -1},
                             {-1, -1, -1, -1, -1, 1, -1},
                             {-1, -1, -1, -1, -1, 1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {-1, -1, 1, 1, 1, -1, -1},
                            };

    double neuronioK3[][] = {{1, 1, 1, -1, -1, 1, 1},
                             {-1, 1, -1, -1, 1, -1, -1},
                             {-1, 1, -1, 1, -1, -1, -1},
                             {-1, 1, 1, -1, -1, -1, -1},
                             {-1, 1, 1, -1, -1, -1, -1},
                             {-1, 1, -1, 1, -1, -1, -1},
                             {-1, 1, -1, -1, 1, -1, -1},
                             {-1, 1, -1, -1, -1, 1, -1},
                             {1, 1, 1, -1, -1, 1, 1},
                            }; 
    
}
