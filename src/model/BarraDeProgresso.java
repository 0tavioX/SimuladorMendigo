package model;

import application.TrabalhoController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;

public class BarraDeProgresso extends Task<Void>{
	
	private int qt;
	private int tempo;
	
	private ProgressBar barra;
	private Button botao;
	private Label lb$;
	private Label lbXP;
	
	private double ganho$;
	private double dinheiro;
	private int XP;
	private int ganhoX;
	private boolean fracasso;
		
	
	public BarraDeProgresso(int qt, int tempo, ProgressBar barra, double dinheiro, double ganho$, int XP, int ganhoX, Button botao, Label lb$, Label lbXP, Boolean fracasso) {
		this.qt = qt;
		this.tempo = tempo;
		this.barra = barra;
		
		this.dinheiro = dinheiro;
		this.ganho$ = ganho$;
		
		this.XP = XP;
		this.ganhoX = ganhoX;
		
		this.botao = botao;
		this.lb$ = lb$;
		this.lbXP = lbXP;
		
		this.fracasso = fracasso;
		
		barra.setProgress(0);
	}
	
	@Override
	protected Void call() throws Exception {	
		botao.setDisable(true);
		double incremento = 1.0/qt;
		for(int i=0; i<qt; i++){
			try {
				Thread.sleep(tempo);
				Platform.runLater(()-> {
					barra.setProgress(barra.getProgress()+incremento);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Platform.runLater(()-> {
		if(fracasso == false){	
			dinheiro = dinheiro + ganho$;
			TrabalhoController.dinheiro = dinheiro;		
			lb$.setText(String.valueOf(dinheiro));
			
			XP = XP + ganhoX;
			TrabalhoController.XP = XP;
			lbXP.setText(String.valueOf(XP));
			
			barra.setProgress(0);
			
		}else{
			mostraMensagem("Deu Pau", AlertType.ERROR);
		}
			botao.setDisable(false);
		});
		return null;
	}
	
	public void mostraMensagem(String msg, AlertType tipo) {
		Alert a = new Alert(tipo);
		a.setHeaderText(null);
		a.setContentText(msg);
		a.show();
	}
	
	public int getQt() {
		return qt;
	}
	public void setQt(int qt) {
		this.qt = qt;
	}
	
    public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	
	public ProgressBar getBarra() {
		return barra;
	}
	public void setBarra(ProgressBar barra) {
		this.barra = barra;
	}

	public Button getBotao() {
		return botao;
	}
	public void setBotao(Button botao) {
		this.botao = botao;
	}

	public Label getLb$() {
		return lb$;
	}
	public void setLb$(Label lb$) {
		this.lb$ = lb$;
	}

	public Label getLbXP() {
		return lbXP;
	}
	public void setLbXP(Label lbXP) {
		this.lbXP = lbXP;
	}

	public double getGanho$() {
		return ganho$;
	}
	public void setGanho$(double ganho$) {
		this.ganho$ = ganho$;
	}
	
	public double getDinheiro() {
		return dinheiro;
	}
	public void setDinheiro(double dinheiro) {
		this.dinheiro = dinheiro;
	}

	public int getXP() {
		return XP;
	}
	public void setXP(int xP) {
		XP = xP;
	}

    public int getGanhoX() {
		return ganhoX;
	}
	public void setGanhoX(int ganhoX) {
		this.ganhoX = ganhoX;
	}

	
	public boolean isFracasso() {
		return fracasso;
	}

	public void setFracasso(boolean fracasso) {
		this.fracasso = fracasso;
	}
	
}