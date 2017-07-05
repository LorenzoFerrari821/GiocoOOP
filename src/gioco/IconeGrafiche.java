package gioco;

import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class IconeGrafiche {


	ImageIcon newiconTick;
	ImageIcon newiconCross;
	ImageIcon newiconCosto;
	ImageIcon newiconOro;
	ImageIcon newiconMat;
	ImageIcon newiconAtk;
	ImageIcon newiconDef;
	ImageIcon newiconVel;
	ImageIcon newiconSentieri;
	ImageIcon newiconAbitazioni;
	ImageIcon newiconCaserma;
	ImageIcon newiconFucina;
	ImageIcon newiconCommercio;
	ImageIcon newiconFanteria;
	ImageIcon newiconOreficeria;
	ImageIcon newiconReligione;
	ImageIcon newiconVille;
	ImageIcon newiconTiroConArco;
	ImageIcon newiconOracolo;
	ImageIcon newiconCitta;
	ImageIcon newiconCavalleria;
	ImageIcon newiconSocMil;
	ImageIcon newiconEtaImp;
	ImageIcon newiconLastr;
	ImageIcon newiconCasaPP;
	ImageIcon newiconCorazze;
	ImageIcon newiconBalestre;
	ImageIcon newiconBiblioteca;
	ImageIcon newiconMercenari;
	ImageIcon newiconClero;
	ImageIcon newiconOspedale;
	ImageIcon newiconCaseASchiera;
	ImageIcon newiconTatCal;
	ImageIcon newiconInquisizione;
	ImageIcon newiconGranai;
	ImageIcon newiconFermentazione;
	ImageIcon newiconPolvereDaSparo;
	ImageIcon newiconBanca;
	ImageIcon newiconStrAsf;
	ImageIcon newiconSisInd;
	ImageIcon newiconScienza;
	ImageIcon newiconCasaMan;
	ImageIcon newiconFucili;
	ImageIcon newiconGerMil;
	ImageIcon newiconSocBor;
	ImageIcon newiconPolitica;
	ImageIcon newiconTatCap;
	ImageIcon newiconMusLir;
	ImageIcon newiconVillette;
	ImageIcon newiconBalistica;
	ImageIcon newiconTeatri;
	ImageIcon newiconCarabinieri;
	ImageIcon newiconGuaRea;
	ImageIcon newiconLegione;
	ImageIcon newiconGK;

	ImageIcon newiconOriz;
	ImageIcon newicon3su;
	ImageIcon newiconVert;
	ImageIcon newicon2dxgiu;
	ImageIcon newicon4;
	ImageIcon newicon2sudx;
	ImageIcon newicon2giusx;
	ImageIcon newicon3giu;
	ImageIcon newicon2sxsu;

	ImageIcon newiconRomani;
	ImageIcon newiconFrancesi;
	ImageIcon newiconInglesi;
	ImageIcon newiconSassoni;

	private Map<String, ImageIcon> iconeScenario;
	private Map<String,ImageIcon>  iconeScenarioNum;
	
	private Map<String, ImageIcon> iconeUMilitari; //Icone delle unità militari

	IconeGrafiche()
	{
		caricaIconeScenario(36, 37); //Questo metodo carica tutte le icone grafiche scenario all'interno del gioco con dimensione di default (36 e 37)
		caricaAltreIcone();
	}

	public void caricaIconeScenario(int width, int height) {
		iconeScenario = new HashMap<>();
		iconeUMilitari = new HashMap<>();

		//Caricamento pavimentazioni
		ImageIcon iconlblPartita = new ImageIcon("media/asset_grafici/scenario/t.png");
		Image scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("t", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/v.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("v", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/d.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("d", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/n.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("n", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/g.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("g", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/gh.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("gh", new ImageIcon(scalelblPartita));

		//Caricamento pavimentazioni num
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/t.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("0", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/v.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/d.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/n.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/gh.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("4", new ImageIcon(scalelblPartita));


		//Caricamento icone scenario
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/x.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("x", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/y.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("y", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/z.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("z", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/at.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("at", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/am.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("am", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/pm.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("pm", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/pg.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("pg", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/pp.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("pp", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/ap.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("ap", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/tr.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("tr", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/c.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("c", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/f.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("f", new ImageIcon(scalelblPartita));



		//Caricamento icone scenario num
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/x.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("5", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/y.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("6", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/z.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("7", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/at.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("8", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/am.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("9", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/pm.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("10", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/pg.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("11", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/pp.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("12", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/ap.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("13", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/tr.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("14", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/c.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("15", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/f.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("16", new ImageIcon(scalelblPartita));



		//caricamento icone alberi tondi
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/at1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("at1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/at2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("at2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/at3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("at3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/at4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("at4", new ImageIcon(scalelblPartita));


		//caricamento icone alberi tondi num
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/at1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("17", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/at2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("18", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/at3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("19", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/at4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("20", new ImageIcon(scalelblPartita));

		//caricamento icone alberi
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/a1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("a1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/a2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("a2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/a3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("a3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/a4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("a4", new ImageIcon(scalelblPartita));

		//caricamento icone alberi num
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/a1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("21", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/a2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("22", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/a3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("23", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/a4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("24", new ImageIcon(scalelblPartita));

		//caricamento icone tronchetti
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/t1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("t1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/t2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("t2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/t3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("t3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/t4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("t4", new ImageIcon(scalelblPartita));


		//caricamento icone tronchetti num
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/t1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("25", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/t2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("26", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/t3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("27", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/t4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("28", new ImageIcon(scalelblPartita));


		//caricamento municipio età classica
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/municipio1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("ecmunicipio1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/municipio2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("ecmunicipio2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/municipio3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("ecmunicipio3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/municipio4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("ecmunicipio4", new ImageIcon(scalelblPartita));

		//Caricamento elementi negozio ETA CLASSICA
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/sentieri/Sentiero.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Sentiero", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/casa.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Casa", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/caserma1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Caserma1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/caserma2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Caserma2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/caserma3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Caserma3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/caserma4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Caserma4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/fucina1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Fucina1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/fucina2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Fucina2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/fucina3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Fucina3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/fucina4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Fucina4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/mercato1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Mercato1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/mercato2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Mercato2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/mercato3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Mercato3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/mercato4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Mercato4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/municipio1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("ecmunicipio1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/municipio2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("ecmunicipio2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/municipio3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("ecmunicipio3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/municipio4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("ecmunicipio4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/orefice1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Orefice1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/orefice2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Orefice2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/orefice3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Orefice3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/orefice4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Orefice4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/palazzo1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Palazzo1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/palazzo2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Palazzo2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/palazzo3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Palazzo3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/palazzo4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Palazzo4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/tempio1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Tempio1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/tempio2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Tempio2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/tempio3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Tempio3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/tempio4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Tempio4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/villa.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Villa", new ImageIcon(scalelblPartita));

		//Caricamento elementi negozio MEDIOEVO
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/lastricato/lastricato.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Lastricato", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/banca1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Banca1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/banca2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Banca2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/banca3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Banca3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/banca4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Banca4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/biblioteca1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Biblioteca1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/biblioteca2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Biblioteca2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/biblioteca3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Biblioteca3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/biblioteca4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Biblioteca4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/campomercenari1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Campo_mercenari1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/campomercenari2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Campo_mercenari2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/campomercenari3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Campo_mercenari3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/campomercenari4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Campo_mercenari4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/casapiupiani.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Casa_a_più_piani", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/casaschiera.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Casa_a_schiera", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/chiesa1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Chiesa1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/chiesa2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Chiesa2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/chiesa3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Chiesa3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/chiesa4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Chiesa4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/granaio1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Granaio1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/granaio2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Granaio2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/granaio3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Granaio3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/granaio4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Granaio4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/mastrobirraio1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Mastro_birraio1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/mastrobirraio2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Mastro_birraio2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/mastrobirraio3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Mastro_birraio3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/mastrobirraio4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Mastro_birraio4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/municipio1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("mmunicipio1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/municipio2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("mmunicipio2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/municipio3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("mmunicipio3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/municipio4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("mmunicipio4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/ospedale1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Ospedale1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/ospedale2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Ospedale2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/ospedale3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Ospedale3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/ospedale4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Ospedale4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/roghi1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Roghi1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/roghi2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Roghi2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/roghi3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Roghi3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/roghi4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Roghi4", new ImageIcon(scalelblPartita));

		//Caricamento elementi negozio ETA VITTORIANA
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/asfalto/asfalto.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Asfalto", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/casamansarda.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Casa_con_mansarda", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/casermaeroi1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Caserma_eroi1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/casermaeroi2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Caserma_eroi2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/casermaeroi3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Caserma_eroi3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/casermaeroi4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Caserma_eroi4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/centrocittadino1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Centro_cittadino1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/centrocittadino2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Centro_cittadino2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/centrocittadino3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Centro_cittadino3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/centrocittadino4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Centro_cittadino4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/fabbrica1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Fabbrica1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/fabbrica2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Fabbrica2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/fabbrica3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Fabbrica3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/fabbrica4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Fabbrica4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/laboratorio1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Laboratorio1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/laboratorio2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Laboratorio2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/laboratorio3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Laboratorio3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/laboratorio4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Laboratorio4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/municipio1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("evmunicipio1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/municipio2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("evmunicipio2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/municipio3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("evmunicipio3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/municipio4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("evmunicipio4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/opera1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Opera1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/opera2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Opera2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/opera3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Opera3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/opera4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Opera4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/parlamento1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Parlamento1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/parlamento2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Parlamento2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/parlamento3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Parlamento3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/parlamento4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Parlamento4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/teatro1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Teatro1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/teatro2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Teatro2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/teatro3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Teatro3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/teatro4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Teatro4", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/villetta.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeScenario.put("Villetta", new ImageIcon(scalelblPartita));

		//Caricamento icone unità militari età classica
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/unitamilitari/arciere.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Arciere", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/unitamilitari/berserk.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Berserk", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/unitamilitari/cavaliere.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Cavaliere", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/unitamilitari/druido.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Druido", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/unitamilitari/miliziano.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Miliziano", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/unitamilitari/pretoriano.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Pretoriano", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/unitamilitari/soldato.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Soldato", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/unitamilitari/spadaccino.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Spadaccino", new ImageIcon(scalelblPartita));

		//Caricamento icone unità militari medioevo
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/unitamilitari/arcieremercenario.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Arciere mercenario", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/unitamilitari/balestriere.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Balestriere", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/unitamilitari/cannone.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Cannone", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/unitamilitari/cavalierecrociato.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Cavaliere crociato", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/unitamilitari/cavalierepesante.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Cavaliere pesante", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/unitamilitari/cavalieretemplare.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Cavaliere templare", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/unitamilitari/medico.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Medico", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/2medioevo/unitamilitari/soldatoarmaturapesante.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Soldato in armatura pesante", new ImageIcon(scalelblPartita));

		//Caricamento icone unità militari età vittoriana
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/unitamilitari/artiglieria.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Artiglieria", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/unitamilitari/carabinieri.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Carabinieri", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/unitamilitari/fuciliacavallo.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Fuciliere a cavallo", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/unitamilitari/fuciliere.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Fuciliere", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/unitamilitari/gardenkorps.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Gardenkorps", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/unitamilitari/granatiere.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Granatiere", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/unitamilitari/guardiareale.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Guardia reale", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/unitamilitari/legionestraniera.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Legione straniera", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/unitamilitari/lorenzo.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Lorenzo", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/unitamilitari/mitragliere.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Mitragliere", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/3eta_vittoriana/unitamilitari/werther.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconeUMilitari.put("Werther", new ImageIcon(scalelblPartita));
	}

	public void caricaAltreIcone()
	{
		ImageIcon iconTick = new ImageIcon("media/asset_grafici/icone/tick.png");
		Image scaleTick = iconTick.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconTick = new ImageIcon(scaleTick);

		ImageIcon iconCross = new ImageIcon("media/asset_grafici/icone/cross.png");
		Image scaleCross = iconCross.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconCross = new ImageIcon(scaleCross);

		//Setting icona costo
		ImageIcon iconCosto = new ImageIcon("media/asset_grafici/icone/ricerca.png");
		Image scaleCosto = iconCosto.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		newiconCosto = new ImageIcon(scaleCosto);


		ImageIcon iconOro = new ImageIcon("media/asset_grafici/icone/oro.png");
		Image scaleOro = iconOro.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		newiconOro = new ImageIcon(scaleOro);
		ImageIcon iconMat = new ImageIcon("media/asset_grafici/icone/materiali.png");
		Image scaleMat = iconMat.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		newiconMat = new ImageIcon(scaleMat);
		ImageIcon iconAtk = new ImageIcon("media/asset_grafici/icone/attacco.png");
		Image scaleAtk = iconAtk.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		newiconAtk = new ImageIcon(scaleAtk);
		ImageIcon iconDef = new ImageIcon("media/asset_grafici/icone/difesa.png");
		Image scaleDef = iconDef.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		newiconDef = new ImageIcon(scaleDef);
		ImageIcon iconVel = new ImageIcon("media/asset_grafici/icone/velocita.png");
		Image scaleVel = iconVel.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		newiconVel = new ImageIcon(scaleVel);

		ImageIcon iconRomani = new ImageIcon("media/asset_grafici/icone/roma.png");
		Image scaleRomani = iconRomani.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconRomani = new ImageIcon(scaleRomani);
		ImageIcon iconFrancesi = new ImageIcon("media/asset_grafici/icone/francia.png");
		Image scaleFrancesi = iconFrancesi.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconFrancesi = new ImageIcon(scaleFrancesi);
		ImageIcon iconInglesi = new ImageIcon("media/asset_grafici/icone/inghilterra.png");
		Image scaleInglesi = iconInglesi.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconInglesi = new ImageIcon(scaleInglesi);
		ImageIcon iconSassoni = new ImageIcon("media/asset_grafici/icone/germania.png");
		Image scaleSassoni = iconSassoni.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconSassoni = new ImageIcon(scaleSassoni);

		ImageIcon iconSentieri = new ImageIcon("media/asset_grafici/icone/1etaclassica/sentieri.png");
		Image scaleSentieri = iconSentieri.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconSentieri = new ImageIcon(scaleSentieri);

		ImageIcon iconAbitazioni = new ImageIcon("media/asset_grafici/icone/1etaclassica/casa.png");
		Image scaleAbitazioni = iconAbitazioni.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconAbitazioni = new ImageIcon(scaleAbitazioni);
		ImageIcon iconCaserma = new ImageIcon("media/asset_grafici/icone/1etaclassica/caserma.png");
		Image scaleCaserma = iconCaserma.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconCaserma = new ImageIcon(scaleCaserma);
		ImageIcon iconFucina = new ImageIcon("media/asset_grafici/icone/1etaclassica/fucina.png");
		Image scaleFucina = iconFucina.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconFucina = new ImageIcon(scaleFucina);

		ImageIcon iconCommercio = new ImageIcon("media/asset_grafici/icone/1etaclassica/mercato.png");
		Image scaleCommercio = iconCommercio.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconCommercio = new ImageIcon(scaleCommercio);
		ImageIcon iconFanteria = new ImageIcon("media/asset_grafici/icone/1etaclassica/fanteria.png");
		Image scaleFanteria = iconFanteria.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconFanteria = new ImageIcon(scaleFanteria);

		ImageIcon iconOreficeria = new ImageIcon("media/asset_grafici/icone/1etaclassica/oreficeria.png");
		Image scaleOreficeria = iconOreficeria.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconOreficeria = new ImageIcon(scaleOreficeria);

		ImageIcon iconReligione = new ImageIcon("media/asset_grafici/icone/1etaclassica/religione.png");
		Image scaleReligione = iconReligione.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconReligione = new ImageIcon(scaleReligione);
		ImageIcon iconVille = new ImageIcon("media/asset_grafici/icone/1etaclassica/villa.png");
		Image scaleVille = iconVille.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconVille = new ImageIcon(scaleVille);
		ImageIcon iconTiroConArco = new ImageIcon("media/asset_grafici/icone/1etaclassica/tiroconarco.png");
		Image scaleTiroConArco = iconTiroConArco.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconTiroConArco = new ImageIcon(scaleTiroConArco);

		ImageIcon iconOracolo = new ImageIcon("media/asset_grafici/icone/1etaclassica/oracolo.png");
		Image scaleOracolo = iconOracolo.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconOracolo = new ImageIcon(scaleOracolo);
		ImageIcon iconCitta = new ImageIcon("media/asset_grafici/icone/1etaclassica/palazzo.png");
		Image scaleCitta = iconCitta.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconCitta = new ImageIcon(scaleCitta);
		ImageIcon iconCavalleria = new ImageIcon("media/asset_grafici/icone/1etaclassica/cavalleria.png");
		Image scaleCavalleria = iconCavalleria.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconCavalleria = new ImageIcon(scaleCavalleria);

		ImageIcon iconSocMil = new ImageIcon("media/asset_grafici/icone/1etaclassica/societamilitare.png");
		Image scaleSocMil = iconSocMil.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconSocMil = new ImageIcon(scaleSocMil);

		ImageIcon iconEtaImp = new ImageIcon("media/asset_grafici/icone/1etaclassica/etaimperiale.png");
		Image scaleEtaImp = iconEtaImp.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconEtaImp = new ImageIcon(scaleEtaImp);

		ImageIcon iconLastr = new ImageIcon("media/asset_grafici/icone/2medioevo/lastricato.png");
		Image scaleLastr = iconLastr.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconLastr = new ImageIcon(scaleLastr);

		ImageIcon iconCasaPP = new ImageIcon("media/asset_grafici/icone/2medioevo/casapiupiani.png");
		Image scaleCasaPP = iconCasaPP.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconCasaPP = new ImageIcon(scaleCasaPP);
		ImageIcon iconCorazze = new ImageIcon("media/asset_grafici/icone/2medioevo/corazze.png");
		Image scaleCorazze = iconCorazze.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconCorazze = new ImageIcon(scaleCorazze);
		ImageIcon iconBalestre = new ImageIcon("media/asset_grafici/icone/2medioevo/balestre.png");
		Image scaleBalestre = iconBalestre.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconBalestre = new ImageIcon(scaleBalestre);

		ImageIcon iconBiblioteca = new ImageIcon("media/asset_grafici/icone/2medioevo/biblioteca.png");
		Image scaleBiblioteca = iconBiblioteca.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconBiblioteca = new ImageIcon(scaleBiblioteca);
		ImageIcon iconMercenari = new ImageIcon("media/asset_grafici/icone/2medioevo/mercenari.png");
		Image scaleMercenari = iconMercenari.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconMercenari = new ImageIcon(scaleMercenari);
		ImageIcon iconClero = new ImageIcon("media/asset_grafici/icone/2medioevo/chiesa.png");
		Image scaleClero = iconClero.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconClero = new ImageIcon(scaleClero);

		ImageIcon iconOspedale = new ImageIcon("media/asset_grafici/icone/2medioevo/ospedale.png");
		Image scaleOspedale = iconOspedale.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconOspedale = new ImageIcon(scaleOspedale);
		ImageIcon iconCaseASchiera = new ImageIcon("media/asset_grafici/icone/2medioevo/casaschiera.png");
		Image scaleCaseASchiera = iconCaseASchiera.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconCaseASchiera = new ImageIcon(scaleCaseASchiera);
		ImageIcon iconTatCal = new ImageIcon("media/asset_grafici/icone/2medioevo/tattichedicavalleria.png");
		Image scaleTatCal = iconTatCal.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconTatCal = new ImageIcon(scaleTatCal);

		ImageIcon iconInquisizione = new ImageIcon("media/asset_grafici/icone/2medioevo/inquisizione.png");
		Image scaleInquisizione = iconInquisizione.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconInquisizione = new ImageIcon(scaleInquisizione);
		ImageIcon iconGranai = new ImageIcon("media/asset_grafici/icone/2medioevo/granai.png");
		Image scaleGranai = iconGranai.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconGranai = new ImageIcon(scaleGranai);

		ImageIcon iconFermentazione = new ImageIcon("media/asset_grafici/icone/2medioevo/fermentazione.png");
		Image scaleFermentazione = iconFermentazione.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconFermentazione = new ImageIcon(scaleFermentazione);
		ImageIcon iconPolvereDaSparo = new ImageIcon("media/asset_grafici/icone/2medioevo/polveredasparo.png");
		Image scalePolvereDaSparo = iconPolvereDaSparo.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconPolvereDaSparo = new ImageIcon(scalePolvereDaSparo);

		ImageIcon iconBanca = new ImageIcon("media/asset_grafici/icone/2medioevo/banca.png");
		Image scaleBanca = iconBanca.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconBanca = new ImageIcon(scaleBanca);

		ImageIcon iconStrAsf = new ImageIcon("media/asset_grafici/icone/3etavittoriana/asfalto.png");
		Image scaleStrAsf = iconStrAsf.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconStrAsf = new ImageIcon(scaleStrAsf);

		ImageIcon iconSisInd = new ImageIcon("media/asset_grafici/icone/3etavittoriana/sistemaindustriale.png");
		Image scaleSisInd = iconSisInd.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconSisInd = new ImageIcon(scaleSisInd);
		ImageIcon iconScienza = new ImageIcon("media/asset_grafici/icone/3etavittoriana/scienza.png");
		Image scaleScienza = iconScienza.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconScienza = new ImageIcon(scaleScienza);

		ImageIcon iconCasaMan = new ImageIcon("media/asset_grafici/icone/3etavittoriana/casamansarda.png");
		Image scaleCasaMan = iconCasaMan.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconCasaMan = new ImageIcon(scaleCasaMan);
		ImageIcon iconFucili = new ImageIcon("media/asset_grafici/icone/3etavittoriana/fucili.png");
		Image scaleFucili = iconFucili.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconFucili = new ImageIcon(scaleFucili);
		ImageIcon iconGerMil = new ImageIcon("media/asset_grafici/icone/3etavittoriana/gerarchiamilitare.png");
		Image scaleGerMil = iconGerMil.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconGerMil = new ImageIcon(scaleGerMil);

		ImageIcon iconSocBor = new ImageIcon("media/asset_grafici/icone/3etavittoriana/centrocittadino.png");
		Image scaleSocBor = iconSocBor.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconSocBor = new ImageIcon(scaleSocBor);
		ImageIcon iconPolitica = new ImageIcon("media/asset_grafici/icone/3etavittoriana/politica.png");
		Image scalePolitica = iconPolitica.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconPolitica = new ImageIcon(scalePolitica);
		ImageIcon iconTatCap = new ImageIcon("media/asset_grafici/icone/3etavittoriana/fuciliacavallo.png");
		Image scaleTatCap = iconTatCap.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconTatCap = new ImageIcon(scaleTatCap);

		ImageIcon iconMusLir = new ImageIcon("media/asset_grafici/icone/3etavittoriana/opera.png");
		Image scaleMusLir = iconMusLir.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconMusLir = new ImageIcon(scaleMusLir);
		ImageIcon iconVillette = new ImageIcon("media/asset_grafici/icone/3etavittoriana/villette.png");
		Image scaleVillette = iconVillette.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconVillette = new ImageIcon(scaleVillette);
		ImageIcon iconBalistica = new ImageIcon("media/asset_grafici/icone/3etavittoriana/artiglieria.png");
		Image scaleBalistica = iconBalistica.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconBalistica = new ImageIcon(scaleBalistica);

		ImageIcon iconTeatri = new ImageIcon("media/asset_grafici/icone/3etavittoriana/teatro.png");
		Image scaleTeatri = iconTeatri.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconTeatri = new ImageIcon(scaleTeatri);

		ImageIcon iconCarabinieri = new ImageIcon("media/asset_grafici/icone/3etavittoriana/carabinieri.png");
		Image scaleCarabinieri = iconCarabinieri.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconCarabinieri = new ImageIcon(scaleCarabinieri);

		ImageIcon iconGuaRea = new ImageIcon("media/asset_grafici/icone/3etavittoriana/guardiareale.png");
		Image scaleGuaRea = iconGuaRea.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconGuaRea = new ImageIcon(scaleGuaRea);

		ImageIcon iconLegione = new ImageIcon("media/asset_grafici/icone/3etavittoriana/legionestraniera.png");
		Image scaleLegione = iconLegione.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconLegione = new ImageIcon(scaleLegione);

		ImageIcon iconGK = new ImageIcon("media/asset_grafici/icone/3etavittoriana/gardenkorps.png");
		Image scaleGK = iconGK.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconGK = new ImageIcon(scaleGK);

		ImageIcon iconOriz = new ImageIcon("media/asset_grafici/icone/frameRicerca/orizzontale.png");
		Image scaleOriz = iconOriz.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconOriz = new ImageIcon(scaleOriz);

		ImageIcon icon3su = new ImageIcon("media/asset_grafici/icone/frameRicerca/3su.png");
		Image scale3su = icon3su.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newicon3su = new ImageIcon(scale3su);
		ImageIcon iconVert = new ImageIcon("media/asset_grafici/icone/frameRicerca/verticale.png");
		Image scaleVert = iconVert.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newiconVert = new ImageIcon(scaleVert);
		ImageIcon icon2dxgiu = new ImageIcon("media/asset_grafici/icone/frameRicerca/2dxgiu.png");
		Image scale2dxgiu = icon2dxgiu.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newicon2dxgiu = new ImageIcon(scale2dxgiu);
		ImageIcon icon4 = new ImageIcon("media/asset_grafici/icone/frameRicerca/4.png");
		Image scale4 = icon4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newicon4 = new ImageIcon(scale4);
		ImageIcon icon2sudx = new ImageIcon("media/asset_grafici/icone/frameRicerca/2sudx.png");
		Image scale2sudx = icon2sudx.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newicon2sudx = new ImageIcon(scale2sudx);
		ImageIcon icon2giusx = new ImageIcon("media/asset_grafici/icone/frameRicerca/2giusx.png");
		Image scale2giusx = icon2giusx.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newicon2giusx = new ImageIcon(scale2giusx);
		ImageIcon icon3giu = new ImageIcon("media/asset_grafici/icone/frameRicerca/3giu.png");
		Image scale3giu = icon3giu.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newicon3giu = new ImageIcon(scale3giu);
		ImageIcon icon2sxsu = new ImageIcon("media/asset_grafici/icone/frameRicerca/2sxsu.png");
		Image scale2sxsu = icon2sxsu.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		newicon2sxsu = new ImageIcon(scale2sxsu);
	}

	public Map<String, ImageIcon> getIconeScenario() {
		return iconeScenario;
	}

	public void setIconeScenario(Map<String, ImageIcon> iconeScenario) {
		this.iconeScenario = iconeScenario;
	}

	public Map<String, ImageIcon> getIconeUMilitari() {
		return iconeUMilitari;
	}

	public void setIconeUMilitari(Map<String, ImageIcon> iconeUMilitari) {
		this.iconeUMilitari = iconeUMilitari;
	}


}
