package com.adaline.adalinefx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FXMLController implements Initializable {
    
    @FXML
    private GridPane grid;
    
    @FXML
    private RadioButton radioTipo1;
    
    @FXML
    private RadioButton radioTipo2;
    
    @FXML
    private RadioButton radioTipo3;
    
    @FXML
    private RadioButton radioA;
    
    @FXML
    private RadioButton radioB;
    
    @FXML
    private RadioButton radioC;
    
    @FXML
    private RadioButton radioD;
    
    @FXML
    private RadioButton radioE;
    
    @FXML
    private RadioButton radioJ;
    
    @FXML
    private RadioButton radioK;
    
    @FXML
    private Label labelCiclo;
    
    @FXML
    private TextField textTeste;
    
    Adaline adaline = new Adaline();

    
    private TextField[][] textMatrix = new TextField[7][9];

    public TextField[][] getTextMatrix() {
        return textMatrix;
    }
    
    public TextField getTextMatrix(int linha, int coluna) {
        return textMatrix[linha][coluna];
    }

    public void setTextMatrix(TextField[][] textMatrix) {
        this.textMatrix = textMatrix;
    }
    
    public void setTextMatrix(int linha, int coluna, TextField textMatrix) {
        this.textMatrix[linha][coluna] = textMatrix;
    }
    
    @FXML
    private void handleButtonTest(ActionEvent event) {
        
        double teste[][] = new double[9][7];
         
        int i = 1;
        
        for (int linha = 0 ; linha < 7 ; linha++){
            
            for (int coluna = 0 ; coluna < 9 ; coluna++){
                
                TextField getNode = (TextField)grid.getChildren().get(i);
                
                if ("0".equals(getNode.getText()))
                   teste[coluna][linha] = 0;
                
                if (".".equals(getNode.getText()))
                   teste[coluna][linha] = -1;
                
                if ("#".equals(getNode.getText()))
                   teste[coluna][linha] = 1;

                i++;
                
            }
            
        }        
        
        textTeste.setText(adaline.testar(teste));

    }
    
    @FXML
    private void handleButtonTrain(ActionEvent event) {
        
        labelCiclo.setText("Ciclos: " + adaline.treinarRede());
    
    }
    
    @FXML
    private void handleButtonLoad(ActionEvent event) {

        int i = 1, linha, coluna;
        
        double teste[][] = null;
        
        if (radioTipo1.isSelected()){
            if (radioA.isSelected())
                teste = neuronioA1;
            
            if (radioB.isSelected())
                teste = neuronioB1;
            
            if (radioC.isSelected())
                teste = neuronioC1;
            
            if (radioD.isSelected())
                teste = neuronioD1;
            
            if (radioE.isSelected())
                teste = neuronioE1;
            
            if (radioJ.isSelected())
                teste = neuronioJ1;
            
            if (radioK.isSelected())
                teste = neuronioK1;
        }
        
        if (radioTipo2.isSelected()){
            if (radioA.isSelected())
                teste = neuronioA2;
            
            if (radioB.isSelected())
                teste = neuronioB2;
            
            if (radioC.isSelected())
                teste = neuronioC2;
            
            if (radioD.isSelected())
                teste = neuronioD2;
            
            if (radioE.isSelected())
                teste = neuronioE2;
            
            if (radioJ.isSelected())
                teste = neuronioJ2;
            
            if (radioK.isSelected())
                teste = neuronioK2;
        }
        
        if (radioTipo3.isSelected()){
            if (radioA.isSelected())
                teste = neuronioA3;
            
            if (radioB.isSelected())
                teste = neuronioB3;
            
            if (radioC.isSelected())
                teste = neuronioC3;
            
            if (radioD.isSelected())
                teste = neuronioD3;
            
            if (radioE.isSelected())
                teste = neuronioE3;
            
            if (radioJ.isSelected())
                teste = neuronioJ3;
            
            if (radioK.isSelected())
                teste = neuronioK3;
        }
            
        for (linha = 0 ; linha < 7 ; linha++){
            
            for (coluna = 0 ; coluna < 9 ; coluna++){
                
                TextField getNode = (TextField)grid.getChildren().get(i);
                
                if (teste[coluna][linha] == -1)
                    getNode.setText(".");
                
                if (teste[coluna][linha] == 1)
                    getNode.setText("#");
                
                i++;
                
            }
            
        }
        
        for (linha = 0 ; linha < 9 ; linha++)
        {
            System.out.println();
            for (coluna = 0 ; coluna < 7 ; coluna++)
            {
                System.out.print("  " + teste[linha][coluna]);
            }
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        int linha, coluna;
        
        for (linha = 0 ; linha < 7 ; linha++)
        {
            for (coluna = 0; coluna < 9;coluna++)
            {
                grid.add(new TextField(), linha, coluna);
            }
        }
        
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
