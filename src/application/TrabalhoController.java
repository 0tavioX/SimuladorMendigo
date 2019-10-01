package application;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import model.BarraDeProgresso;
import util.Conexao;

public class TrabalhoController {
	
	@FXML Label lb$;
	@FXML Label lbxp;
	
	public static double dinheiro = 6;
	
	public static int XP = 0;

	
    //mendigagem
	
	@FXML Button butEsmola;
	@FXML ProgressBar barraEsmola;
	
	@FXML Button butAssalto;
	@FXML ProgressBar barraAssalto;
	@FXML Label lbRiscoAssalto;
	
	@FXML Button butPS2;
	@FXML ProgressBar barraPS2Pirata;
	
	@FXML Button butTrote;
	@FXML ProgressBar barraTrote;
	@FXML Label lbRiscoTrote;
	
	@FXML Button butInscrito;
	@FXML ProgressBar barraInscrito;
		
	
	@FXML
	public void pedirEsmola() {
		double ganho$ = 1 * multiDinheiro;
		int tempo = 50 - adiantamento;
		BarraDeProgresso b = new BarraDeProgresso(100, tempo, barraEsmola, dinheiro, ganho$, XP, 0, butEsmola, lb$, lbxp, false);
		new Thread(b).start();
	
		
	}
	
	@FXML
	public void assaltarPessoas() {
		boolean fracasso = false;
		Random r = new Random();
	    int chance = r.nextInt(100);
		
		if(chance<risco){
			fracasso = true;			
		}
		
		double ganho$ = 5 * multiDinheiro;
		int tempo = 100 - (adiantamento * 2);
		BarraDeProgresso b = new BarraDeProgresso(100, tempo, barraAssalto, dinheiro, ganho$, XP, 5, butAssalto, lb$, lbxp, fracasso);
		new Thread(b).start();
		
	}
	
	@FXML
	public void vendePs2Pirata() {
		double ganho$ = 10 * multiDinheiro;
		int tempo = 150 - (adiantamento * 3);
		BarraDeProgresso b = new BarraDeProgresso(100, tempo, barraPS2Pirata, dinheiro, ganho$, XP, 15, butPS2, lb$, lbxp, false);
		new Thread(b).start();
	}
	
	@FXML
	public void passarTrote() {
		boolean fracasso = false;
		Random r = new Random();
	    int chance = r.nextInt(100);
		
		if(chance<(risco*3)){
			fracasso = true;			
		}
		
		double ganho$ = 50 * multiDinheiro;
		int tempo = 200 - (adiantamento * 4);
		BarraDeProgresso b = new BarraDeProgresso(100, tempo, barraTrote, dinheiro, ganho$, XP, 60, butTrote, lb$, lbxp, fracasso);
		new Thread(b).start();
	}
	
	@FXML
	public void mendigarInscritos() {
		double ganho$ = 100 * multiDinheiro;
		int tempo = 250 - (adiantamento * 5);
		BarraDeProgresso b = new BarraDeProgresso(100, tempo, barraInscrito, dinheiro, ganho$, XP, 350, butInscrito, lb$, lbxp, false);
		new Thread(b).start();
	}
	
	//loja
	
	@FXML Button butGarrafa;
	@FXML Button butTapetao;
	@FXML Button butNokia;
	@FXML Button butNotebook;
	@FXML Button butCasa;
	@FXML HBox hbCasa;
	
	@FXML HBox hbAssalto;
	@FXML HBox hbPS2Pirata;	
	@FXML HBox hbTrote;
	@FXML HBox hbInscritos;
	
	@FXML ImageView imgEnfeite;
    @FXML Label fileSelected;
    
    boolean GarrafaC = false;
    boolean TapetaoC = false;
    boolean NokiaC = false;
    boolean NotebookC = false;
    boolean CasaC = false;
    
    
	
	@FXML
    public void compraEnfeite(ActionEvent actionEvent) throws MalformedURLException {
	try{
		FileChooser chooser = new FileChooser();
	    chooser.setTitle("Selecione uma imagem (pode ser gif)");
	    chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif"));
	    File file = chooser.showOpenDialog(new Stage());
	    if(file != null) {
	        String imagepath = file.toURI().toURL().toString();;
	        System.out.println("file:"+imagepath);
	        Image image = new Image(imagepath);
	        imgEnfeite.setImage(image);
        }
	    }catch(NullPointerException e) {
	    	mostraMensagem("Imagem não selecionada", AlertType.ERROR);	    	
        }catch(Exception e) {
        	e.printStackTrace();
        }
	}
	
	@FXML
	public void compraGarrafa(){
        if(dinheiro>=6){
		    mostraMensagem("Você bebe a cerveja, e quebra a garrafa vazia no chão\n"
					     + "usando ela como uma arma para assaltar as pessoas.", AlertType.INFORMATION);
			dinheiro = dinheiro - 6;
			lb$.setText(String.valueOf(dinheiro));
			hbAssalto.setDisable(false);
		    hbAssalto.setVisible(true);
		    butTapetao.setDisable(false);
		    butGarrafa.setDisable(true);
		    GarrafaC = true;
	   }else{
		    mostraMensagem("Mendiga mais pra conseguir mais dinheiro!!!", AlertType.ERROR);
		    }	
	}

	@FXML
	public void compraTapetao() {
		if(dinheiro>=25){
			mostraMensagem("Você joga o tapete no meio da calçada e abre um camelô.", AlertType.INFORMATION);
			dinheiro = dinheiro - 25;
			lb$.setText(String.valueOf(dinheiro));
			hbPS2Pirata.setDisable(false);
			hbPS2Pirata.setVisible(true);
			butNokia.setDisable(false);
			butTapetao.setDisable(true);
			TapetaoC = true;
		}else{
			mostraMensagem("Mendiga mais pra conseguir mais dinheiro!!!", AlertType.ERROR);
		}
	}
	
	@FXML
	public void compraNokia() {
		if(dinheiro>=80){
			mostraMensagem("Você começa a fazer ligações à cobrar com seu nokia pedindo esmola.", AlertType.INFORMATION);
			dinheiro = dinheiro - 80;
			lb$.setText(String.valueOf(dinheiro));
			hbTrote.setDisable(false);
			hbTrote.setVisible(true);
			butNotebook.setDisable(false);
			butNokia.setDisable(true); 
			NokiaC = true;
		}else{
			mostraMensagem("Mendiga mais pra conseguir mais dinheiro!!!", AlertType.ERROR);
		}
	}
	
	public void compraNotebook() {
		if(dinheiro>=300){
			mostraMensagem("Com o seu novo Notebook da negativo e a internet da padaria ao lado do seu lixão, você cria um canal no youtube.", AlertType.INFORMATION);
			dinheiro = dinheiro - 300;
			lb$.setText(String.valueOf(dinheiro));
			hbInscritos.setDisable(false);
			hbInscritos.setVisible(true);
			hbCasa.setDisable(false);
			hbCasa.setVisible(true);
			butNotebook.setDisable(true); 
			NotebookC = true;
		}else{
			mostraMensagem("Mendiga mais pra conseguir mais dinheiro!!!", AlertType.ERROR);
		}
	}
		
	public void compraCasa() {
		if(dinheiro>=300){
			mostraMensagem("Você compra uma casa e deixa de ser mendigo, fim de jogo.", AlertType.INFORMATION);
			dinheiro = dinheiro - 300;
			butCasa.setDisable(true);
			CasaC = true;
		}else{
			mostraMensagem("Mendiga mais pra conseguir mais dinheiro!!!", AlertType.ERROR);
		}	
	}
	
	
	//habilidades
	
	int hbl = 0;
	double multiDinheiro = 1;
	int adiantamento = 0;
	double risco = 10;
		
	@FXML Button butCompra;
	@FXML Label lbDescricao;
	@FXML Label lbpreco;
	
	@FXML Button butHCDGN;
	@FXML Button butHCDGA;
	@FXML Button butHCDGV;
	@FXML Button butHCDGM;
	
	@FXML Button butHVelN;
	@FXML Button butHVelA;
	@FXML Button butHVelV;
	@FXML Button butHVelM;
	
	@FXML Button butHRobN;
	@FXML Button butHRobA;
	@FXML Button butHRobV;
	@FXML Button butHRobM;
	
	boolean CDGNovatoC = false;
	boolean CDGAdeptoC = false;
	boolean CDGVeteranoC = false;
	boolean CDGMestreC = false;
	
	boolean VelNovatoC = false;
	boolean VelAdeptoC = false;
	boolean VelVeteranoC = false;
	boolean VelMestreC = false;
	
	boolean RobNovatoC = false;
	boolean RobAdeptoC = false;
	boolean RobVeteranoC = false;
	boolean RobMestreC = false;
	
	
	
	@FXML
	public void CDGNovato() {
		butCompra.setDisable(false);
		hbl = 1;
		lbDescricao.setText("Você magicamente começa a catar mais dinheiro do que o normal, "
				          + "você ganha 1.5x mais dinheiro por cada serviço completo.");
		lbpreco.setText("10");
	}
	
	@FXML
	public void CDGAdepto() {
		butCompra.setDisable(false);
		hbl = 2;
		lbDescricao.setText("Você começa a catar ainda mais dinheiro do que conseguia antes, "
				          + "você ganha o dobro do dinheiro por cada serviço completo.");
		lbpreco.setText("100");
	}
	
	@FXML
	public void CDGVeterano() {
		butCompra.setDisable(false);
		hbl = 3;
		lbDescricao.setText("Você manja de multiplicação de dinheiro e cata muito mais dinheiro, "
				          + "você ganha 2.5x mais dinheiro por cada serviço completo.");
		lbpreco.setText("1.000");
	}
	
	@FXML
	public void CDGMestre() {
		butCompra.setDisable(false);
		hbl = 4;
		lbDescricao.setText("Você é o mestre da mendigagem e vira um completo imã de dinheiro, "
				          + "você ganha o triplo do dinheiro por cada serviço completo.");
		lbpreco.setText("10.000");
	}
	
	
	@FXML
	public void VelNovato() {
		butCompra.setDisable(false);
		hbl = 5;
		lbDescricao.setText("Uma latinha de Red Bull que te dá mais energia e faz você ficar mais rápido, "
		                  + "serviços se completam um pouco mais rápido.");
        lbpreco.setText("10");
	}

	@FXML
	public void VelAdepto() {
		butCompra.setDisable(false);
		hbl = 6;
		lbDescricao.setText("Você toma um litro de café puro que achou no lixo, te deixando mais agitado, "
		                  + "serviços se completam moderadamente mais rápido.");
        lbpreco.setText("100");
	}
	
	@FXML
	public void VelVeterano() {
		butCompra.setDisable(false);
		hbl = 7;
		lbDescricao.setText("Um traficante te dá um tubo de cocaína estragada, fazendo você ficar a 1000, "
		                  + "serviços se completam considerávelmente mais rápido.");
        lbpreco.setText("1.000");
	}
	
	@FXML
	public void VelMestre() {
		butCompra.setDisable(false);
		hbl = 8;
		lbDescricao.setText("Você acha uma injeção de adrenalina no lixo hospitalar, que faz você ficar mais rápido que o Relâmpago Marquinhos, "
		                  + "serviços se completam drásticamente mais rápido.");
        lbpreco.setText("10.000");
	}
	
	
	@FXML
	public void RobNovato() {
		butCompra.setDisable(false);
		hbl = 9;
		lbDescricao.setText("Você aprende as habilidades de roubação e intimidação, "
                          + "o risco de chance de fracasso em assaltos é diminuído em 25%.");
        lbpreco.setText("10");
	}
	
	@FXML
	public void RobAdepto() {
		butCompra.setDisable(false);
		hbl = 10;
		lbDescricao.setText("Suas habilidades de roubação e intimidação são melhoradas, "
                          + "o risco de chance de fracasso em assaltos é diminuído pela metade.");
        lbpreco.setText("100");
	}
	
	@FXML
	public void RobVeterano() {
		butCompra.setDisable(false);
		hbl = 11;
		lbDescricao.setText("Suas habilidades em roubação e intimidação são excepcionais, "
                          + "o risco de chance de fracasso em assaltos é diminuído em 75%.");
        lbpreco.setText("1.000");
	}
	
	@FXML
	public void RobMestre() {
		butCompra.setDisable(false);
		hbl = 12;
		lbDescricao.setText("Você se torna o mestre roubador, ninguém é páreo para você, "
                          + "o risco de chance de fracasso em assaltos é anulado.");
        lbpreco.setText("10.000");
	}
	
	
	@FXML
	public void Compra() {
		switch (hbl) {
		case 1:
			if(XP>=10){
			  XP = XP - 10;	
			  lbxp.setText(String.valueOf(XP));
			  multiDinheiro = 1.5;
			  butHCDGA.setVisible(true);
			  butHCDGA.setDisable(false);
			  butHCDGN.setDisable(true);
			  butCompra.setDisable(true);
			  CDGNovatoC = true;
		   }else{
			  mostraMensagem("Você é Fraco, lhe falta XP", AlertType.ERROR);
			    }
			break;
		case 2:
			if(XP>=100){
			  XP = XP - 100;	
			  lbxp.setText(String.valueOf(XP));
			  multiDinheiro = 2;
			  butHCDGV.setVisible(true);
			  butHCDGV.setDisable(false);
			  butHCDGA.setDisable(true);
			  butCompra.setDisable(true);
			  CDGAdeptoC = true;
		   }else{
			  mostraMensagem("Você é Fraco, lhe falta XP", AlertType.ERROR);
			    }
			break;	
		case 3:
			if(XP>=1000){
			  XP = XP - 1000;	
			  lbxp.setText(String.valueOf(XP));
			  multiDinheiro = 2.5;
			  butHCDGM.setVisible(true);
			  butHCDGM.setDisable(false);
			  butHCDGV.setDisable(true);
			  butCompra.setDisable(true);
			  CDGVeteranoC = true;
		   }else{
			  mostraMensagem("Você é Fraco, lhe falta XP", AlertType.ERROR);
			    }
			break;
		case 4:
			if(XP>=10000){
			  XP = XP - 10000;
			  lbxp.setText(String.valueOf(XP));
			  multiDinheiro = 3;
			  butHCDGM.setDisable(true);
			  butCompra.setDisable(true);
			  CDGMestreC = true;
		   }else{
			  mostraMensagem("Você é Fraco, lhe falta XP", AlertType.ERROR);
			    }
			break;	
		case 5:
			if(XP>=10){
				XP = XP - 10;
				lbxp.setText(String.valueOf(XP));
				adiantamento = 12;
				butHVelA.setVisible(true);
				butHVelA.setDisable(false);
				butHVelN.setDisable(true);
				butCompra.setDisable(true);
				VelNovatoC = true;
		   }else{
				mostraMensagem("Você é Fraco, lhe falta XP", AlertType.ERROR);
				}
			break;	
		case 6:
			if(XP>=100){
				XP = XP - 100;
				lbxp.setText(String.valueOf(XP));
				adiantamento = 23;
				butHVelV.setVisible(true);
				butHVelV.setDisable(false);
				butHVelA.setDisable(true);
				butCompra.setDisable(true);
				VelAdeptoC = true;
		   }else{
				mostraMensagem("Você é Fraco, lhe falta XP", AlertType.ERROR);
	  	        }
	        break;
		case 7:
			if(XP>=1000){
				XP=XP - 1000;
				lbxp.setText(String.valueOf(XP));
				adiantamento = 34;
				butHVelM.setVisible(true);
				butHVelM.setDisable(false);
				butHVelV.setDisable(true);
				butCompra.setDisable(true);
				VelVeteranoC = true;
		   }else{
				mostraMensagem("Você é Fraco, lhe falta XP", AlertType.ERROR);
	  	        }
	        break;
		case 8:
			if(XP>=10000){
				XP = XP - 10000;
				lbxp.setText(String.valueOf(XP));
				adiantamento = 45;
				butHVelM.setDisable(true);
				butCompra.setDisable(true);
				VelMestreC = true;
		   }else{
				mostraMensagem("Você é Fraco, lhe falta XP", AlertType.ERROR);
	  	        }
	        break;
		case 9:
			if(XP>=10){
				XP = XP - 10;
				risco = 7.5;
                lbRiscoAssalto.setText("Risco: 7,5%");
				lbRiscoTrote.setText("Risco: 22,5%");
                lbxp.setText(String.valueOf(XP));
				butHRobA.setVisible(true);
				butHRobA.setDisable(false);				
				butHRobN.setDisable(true);
				butCompra.setDisable(true);
				RobNovatoC = true;
		   }else{
				mostraMensagem("Você é Fraco, lhe falta XP", AlertType.ERROR);
	  	        }
	        break;
		case 10:
			if(XP>=100){
				XP = XP - 100;
				risco = 5;
				lbRiscoAssalto.setText("Risco: 5%");
				lbRiscoTrote.setText("Risco: 15%");
				lbxp.setText(String.valueOf(XP));
				butHRobV.setVisible(true);
				butHRobV.setDisable(false);				
				butHRobA.setDisable(true);
				butCompra.setDisable(true);
				RobAdeptoC = true;
		   }else{
				mostraMensagem("Você é Fraco, lhe falta XP", AlertType.ERROR);
	  	        }
	        break;
		case 11:
			if(XP>=1000){
				XP = XP - 1000;
				risco = 2.5;
				lbRiscoAssalto.setText("Risco: 2,5%");
				lbRiscoTrote.setText("Risco: 7,5%");
				lbxp.setText(String.valueOf(XP));
				butHRobM.setVisible(true);
				butHRobM.setDisable(false);				
				butHRobV.setDisable(true);
				butCompra.setDisable(true);
				RobVeteranoC = true;
		   }else{
				mostraMensagem("Você é Fraco, lhe falta XP", AlertType.ERROR);
	  	        }
	        break;
		case 12:
			if(XP>=10000){
				XP = XP - 10000;	
				risco = 0;
				lbRiscoAssalto.setVisible(false);
				lbRiscoTrote.setVisible(false);
				lbxp.setText(String.valueOf(XP));
				butHRobM.setDisable(true);
				butCompra.setDisable(true);
				RobMestreC = true;
		   }else{
				mostraMensagem("Você é Fraco, lhe falta XP", AlertType.ERROR);
	  	        }
	        break;
		}
	}
		
	
	//aux	
	public void salvar() {
      try {
       Connection conn = Conexao.getConexao();
	   String sql = "insert into save ("
	   		                         + "dinheiro, xp, "
	   		                         + "Garrafa, Tapete, Nokia, Notebook, Casa, "
	   		                         + "CDGNovato, CDGAdepto, CDGVeterano, CDGMestre, "
	   		                         + "VelNovato, VelAdepto, VelVeterano, VelMestre, "
	   		                         + "RobNovato, RobAdepto, RobVeterano, RobMestre"
	   		                         + ") values ("
	   		                         + "?, ?"
	   		                         + "?, ?, ?, ?, ?, "
	   		                         + "?, ?, ?, ?, "
	   		                         + "?, ?, ?, ?, "
	   		                         + "?, ?, ?, ?)";
	   		                        
				    
	   PreparedStatement ps = conn.prepareStatement(sql);
	   ps.setDouble(1, dinheiro);
	   ps.setInt(2, XP);
	   ps.setBoolean(3, GarrafaC);
	   ps.setBoolean(4, TapetaoC);
	   ps.setBoolean(5, NokiaC);
	   ps.setBoolean(6, NotebookC);
	   ps.setBoolean(7, CasaC);
	   ps.setBoolean(8, CDGNovatoC);
	   ps.setBoolean(9, CDGAdeptoC);
	   ps.setBoolean(10, CDGVeteranoC);
	   ps.setBoolean(11, CDGMestreC);
	   ps.setBoolean(12, VelNovatoC);
	   ps.setBoolean(13, VelAdeptoC);
	   ps.setBoolean(14, VelVeteranoC);
	   ps.setBoolean(15, VelMestreC);
	   ps.setBoolean(16, RobNovatoC);
	   ps.setBoolean(17, RobAdeptoC);
	   ps.setBoolean(18, RobVeteranoC);
	   ps.setBoolean(19, RobMestreC);
	   
	   ps.executeUpdate();
	   conn.close();
	   mostraMensagem("Salvo", AlertType.INFORMATION);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   
	  
	}
	
	public void carregar() {
		try {
			Connection conn = Conexao.getConexao(); 
			String sql = "Select * from save where codigo=?"; 
			PreparedStatement ps = conn.prepareStatement(sql); 
			ResultSet rs = ps.executeQuery(); 
			if (rs.next()) { 
				dinheiro = rs.getDouble("codigo"); 
				XP = rs.getInt("xp"); 
				lb$.setText(String.valueOf(dinheiro));
				lbxp.setText(String.valueOf(XP));
				
				GarrafaC = rs.getBoolean("Garrafa"); 
				TapetaoC = rs.getBoolean("Tapete");
				NokiaC = rs.getBoolean("Nokia");
				NotebookC = rs.getBoolean("Notebook");
				CasaC = rs.getBoolean("Casa");
				
				if(GarrafaC = true){
					hbAssalto.setDisable(false);
				    hbAssalto.setVisible(true);
				    butTapetao.setDisable(false);
				    butGarrafa.setDisable(true);
				    if(TapetaoC = true) {
				    	hbPS2Pirata.setDisable(false);
						hbPS2Pirata.setVisible(true);
						butNokia.setDisable(false);
						butTapetao.setDisable(true);
						if(NokiaC = true) {
							hbTrote.setDisable(false);
							hbTrote.setVisible(true);
							butNotebook.setDisable(false);
							butNokia.setDisable(true); 
							if(NotebookC = true) {
								hbInscritos.setDisable(false);
								hbInscritos.setVisible(true);
								hbCasa.setDisable(false);
								hbCasa.setVisible(true);
								butNotebook.setDisable(true); 
								if(CasaC = true) {
									dinheiro = dinheiro - 300;
									butCasa.setDisable(true);
								}
							   }
						      }
				             }
             		        }
				
				butCompra.setDisable(true);
				
				CDGNovatoC = rs.getBoolean("CDGNovato");
				CDGAdeptoC = rs.getBoolean("CDGAdepto");
				CDGVeteranoC = rs.getBoolean("CDGVeterano");
				CDGMestreC = rs.getBoolean("CDGMestre");
				
				if(CDGNovatoC = true) {
					  multiDinheiro = 1.5;
					  butHCDGA.setVisible(true);
					  butHCDGA.setDisable(false);
					  butHCDGN.setDisable(true);
					  if(CDGAdeptoC = true) {
						  multiDinheiro = 2;
						  butHCDGV.setVisible(true);
						  butHCDGV.setDisable(false);
						  butHCDGA.setDisable(true);
						  if(CDGVeteranoC = true) {
							  multiDinheiro = 2.5;
							  butHCDGM.setVisible(true);
							  butHCDGM.setDisable(false);
							  butHCDGV.setDisable(true);
							  if(CDGMestreC = true) {
								  multiDinheiro = 3;
								  butHCDGM.setDisable(true);
							  }
						     }				  
					        }
				           }
				
				VelNovatoC = rs.getBoolean("VelNovato");
				VelAdeptoC = rs.getBoolean("VelAdepto");
				VelVeteranoC = rs.getBoolean("VelVeterano");
				VelMestreC = rs.getBoolean("VelMestre");
				
				if(VelNovatoC = true) {
					adiantamento = 12;
					butHVelA.setVisible(true);
					butHVelA.setDisable(false);
					butHVelN.setDisable(true);					
					if(VelAdeptoC = true) {
						adiantamento = 23;
						butHVelV.setVisible(true);
						butHVelV.setDisable(false);
						butHVelA.setDisable(true);
						if(VelVeteranoC = true) {
							adiantamento = 34;
							butHVelM.setVisible(true);
							butHVelM.setDisable(false);
							butHVelV.setDisable(true);
							if(VelMestreC = true) {
								adiantamento = 45;
								butHVelM.setDisable(true);
							}
						   }
					      }
				         }
				
				RobNovatoC = rs.getBoolean("RobNovato"); 
				RobAdeptoC = rs.getBoolean("RobAdepto"); 
				RobVeteranoC = rs.getBoolean("RobVeterano");
				RobMestreC = rs.getBoolean("RobMestre");
				
				if(RobNovatoC = true) {
					risco = 7.5;
	                lbRiscoAssalto.setText("Risco: 7,5%");
					lbRiscoTrote.setText("Risco: 22,5%");
	                lbxp.setText(String.valueOf(XP));
					butHRobA.setVisible(true);
					butHRobA.setDisable(false);				
					butHRobN.setDisable(true);
					if(RobAdeptoC = true) {
						risco = 5;
						lbRiscoAssalto.setText("Risco: 5%");
						lbRiscoTrote.setText("Risco: 15%");
						lbxp.setText(String.valueOf(XP));
						butHRobV.setVisible(true);
						butHRobV.setDisable(false);				
						butHRobA.setDisable(true);
						if(RobAdeptoC = true) {
							risco = 2.5;
							lbRiscoAssalto.setText("Risco: 2,5%");
							lbRiscoTrote.setText("Risco: 7,5%");
							lbxp.setText(String.valueOf(XP));
							butHRobM.setVisible(true);
							butHRobM.setDisable(false);				
							butHRobV.setDisable(true);
							if(RobMestreC = true) {
								risco = 0;
								lbRiscoAssalto.setVisible(false);
								lbRiscoTrote.setVisible(false);
								butHRobM.setDisable(true);
							}
						   }					
					      }
				         }
				
				 			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sair() {
		System.exit(0);
	}
		
	public void mostraMensagem(String msg, AlertType tipo) {
		Alert a = new Alert(tipo);
		a.setHeaderText(null);
		a.setContentText(msg);
		a.show();
	}
	

}
