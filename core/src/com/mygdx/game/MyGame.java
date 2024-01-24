package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyGame extends GameBeta{

	private int countdown = 240;

	private static final String WIN_TEXTURE = "you-win.png";

	private static final String LOOSE_TEXTURE = "game-over.png";
	private Button buttonRight;
	private Button buttonLeft;
	private Button buttonTop;
	private Button buttonBottom;
	private Button buttonFire;

	private Omino omino;

	private Cinema cinema;

	private Sedie sedie;

	private Vite cuore;
	private Vite cuore2;
	private Vite cuore3;

	private Message winMessage;
	private Message looseMessage;



	private boolean win;
	private boolean loose;

	private boolean gameover = false;


	@Override
	public void initialize(){
		//mainStage = new Stage();
		Gdx.input.setInputProcessor(mainStage);

		cinema = new Cinema(540,0,mainStage);
		//cinema = new Cinema(0,0,mainStage);

		//BaseActor.setWorldBounds(cinema);
		BaseActor.setWorldBounds(540, 0, 1000, 860);

		sedie = new Sedie(817,204 ,mainStage);

		omino = new Omino(640,130, mainStage);

		Spettatore spettatore = new Spettatore(820,220,mainStage,"spettatore1_seduto.png", "spettatore1.png");
		Spettatore spettatore2 = new Spettatore(910,360,mainStage,"spettatore2_seduto.png","spettatore2.png");
		Spettatore spettatore3 = new Spettatore(910,565,mainStage,"spettatore3_seduto.png", "spettatore3.png");
		Spettatore spettatore4 = new Spettatore(910,430,mainStage,"spettatore4_seduto.png", "spettatore4.png");
		Spettatore spettatore5 = new Spettatore(1010,360,mainStage,"spettatore5_seduto.png", "spettatore5.png");
		Spettatore spettatore6 = new Spettatore(910,220,mainStage,"spettatore6_seduto.png", "spettatore6.png");
		Spettatore spettatore7 = new Spettatore(1280,360,mainStage,"spettatore7_seduto.png", "spettatore7.png");
		Spettatore spettatore8 = new Spettatore(1190,500,mainStage,"spettatore8_seduto.png", "spettatore8.png");
		Spettatore bomberman = new Spettatore(1280,430,mainStage,"bomberman_seduto.png", "bomberman.png");
		Spettatore goku = new Spettatore(1015,500,mainStage,"goku_seduto.png","goku.png");
		Spettatore gandalf = new Spettatore(820,630,mainStage,"gandalf_seduto.png", "gandalf.png");
		Spettatore halo = new Spettatore(1105,430,mainStage,"halo_seduto.png", "halo.png");
		Spettatore mario = new Spettatore(1190,220,mainStage,"mario_seduto.png", "mario.png");
		Spettatore pokemon = new Spettatore(1105,360,mainStage,"pokemon_seduto.png", "pokemon.png");
		Spettatore spiderman = new Spettatore(823,500,mainStage,"spiderman_seduto.png", "spiderman.png");
		Spettatore superman = new Spettatore(1280,565,mainStage,"superman_seduto.png", "superman.png");
		Spettatore vader = new Spettatore(1195,630,mainStage,"vader_seduto.png", "vader.png");
		Spettatore wolverine = new Spettatore(1010,295,mainStage,"wolverine_seduto.png", "wolverine.png");
		Spettatore yoda = new Spettatore(1005,565,mainStage,"yoda_seduto.png", "yoda.png");


		winMessage = new Message(820,840,mainStage,WIN_TEXTURE);
		winMessage.setVisible(false);

		looseMessage = new Message(810,840,mainStage,LOOSE_TEXTURE);
		looseMessage.setVisible(false);


		cuore = new Vite(760,920,mainStage);
		cuore2 = new Vite(790,920,mainStage);
		cuore3 = new Vite(820,920,mainStage);
		List<Vite> cuori = new ArrayList<>(3);
		cuori.add(cuore);
		cuori.add(cuore2);
		cuori.add(cuore3);

		omino.setCuori(cuori);

		win = false;
		loose = false;

		//Begin 2.1.
		Texture myTextureBottom = new Texture(Gdx.files.internal("freccia_giu.png"));
		TextureRegion myTextureRegionBottom = new TextureRegion(myTextureBottom);
		TextureRegionDrawable myTexRegionDrawableBottom = new TextureRegionDrawable(myTextureRegionBottom);

		Texture myTextureTop = new Texture(Gdx.files.internal("freccia_su.png"));
		TextureRegion myTextureRegionTop = new TextureRegion(myTextureTop);
		TextureRegionDrawable myTexRegionDrawableTop = new TextureRegionDrawable(myTextureRegionTop);

		Texture myTextureLeft = new Texture(Gdx.files.internal("freccia_sinistra.png"));
		TextureRegion myTextureRegionLeft = new TextureRegion(myTextureLeft);
		TextureRegionDrawable myTexRegionDrawableLeft = new TextureRegionDrawable(myTextureRegionLeft);


		Texture myTextureRight = new Texture(Gdx.files.internal("freccia_destra.png"));
		TextureRegion myTextureRegionRight = new TextureRegion(myTextureRight);
		TextureRegionDrawable myTexRegionDrawableRight = new TextureRegionDrawable(myTextureRegionRight);

		Texture myTextureFire = new Texture(Gdx.files.internal("tasto_ascia.jpg"));
		TextureRegion myTextureRegionFire = new TextureRegion(myTextureFire);
		TextureRegionDrawable myTexRegionDrawableFire = new TextureRegionDrawable(myTextureRegionFire);

		buttonRight =  new ImageButton(myTexRegionDrawableRight);
		buttonRight.setSize(180,150);
		buttonRight.setPosition(290,Gdx.graphics.getHeight()-280*3);
		buttonRight.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Press a Button");
				omino.stopMoving();
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Pressed Text Button");
				omino.startMoving(Direction.RIGHT);
				return true;
			}
		});

		buttonLeft =  new ImageButton( myTexRegionDrawableLeft );
		buttonLeft.setSize(180,150);
		buttonLeft.setPosition(30,Gdx.graphics.getHeight()-280*3);
		buttonLeft.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Press a Button");
				omino.stopMoving();
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Pressed Text Button");
				omino.startMoving(Direction.LEFT);
				return true;
			}
		});

		buttonTop =  new ImageButton( myTexRegionDrawableTop );
		buttonTop.setSize(150,180);
		buttonTop.setPosition(175,Gdx.graphics.getHeight()-235*3);
		buttonTop.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Press a Button");
				omino.stopMoving();
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Pressed Text Button");
				omino.startMoving(Direction.UP);
				return true;
			}
		});

		buttonBottom =  new ImageButton( myTexRegionDrawableBottom );
		buttonBottom.setSize(150,180);
		buttonBottom.setPosition(175,Gdx.graphics.getHeight()-335*3);
		buttonBottom.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Press a Button");
				omino.stopMoving();
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Pressed Text Button");
				omino.startMoving(Direction.DOWN);
				return true;
			}
		});

		buttonFire =  new ImageButton(myTexRegionDrawableFire);
		buttonFire.setSize(180,150);
		buttonFire.setPosition(1740,Gdx.graphics.getHeight()-280*3);
		buttonFire.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				omino.lanciaArma();
				return true;
			}
		});

		mainStage.addActor(buttonRight);
		mainStage.addActor(buttonLeft);
		mainStage.addActor(buttonTop);
		mainStage.addActor(buttonBottom);
		mainStage.addActor(buttonFire);

	}

	@Override
	public void processStage(float dt) {
		if(gameover)
			return;

		if(win || loose){
			gameover = true;

		}
		super.processStage(dt);
	}

	@Override
	public void update (float dt) {
		List<Spettatore> spettatori = BaseActor.getList(mainStage,Spettatore.class);
		List<Ascia> ascie = BaseActor.getList(mainStage,Ascia.class);
		List<Tridente> tridenti = BaseActor.getList(mainStage,Tridente.class);

		if(win || loose){
			for(Tridente tridente : tridenti){
				tridente.kill();
			}

			for(Ascia ascia : ascie){
				ascia.kill();
			}
			return;
		}

		countdown--;

		if(countdown == 0){
			List<Spettatore> spettatoriRandom = new ArrayList<>(spettatori);
			Collections.shuffle(spettatoriRandom);
			for(Spettatore spettatore : spettatoriRandom){
				if (spettatore.getState() == Spettatore.State.SITTING){
					spettatore.standUp();
					spettatore.setMovementDirection(omino.getMovementDirection());
					break;
				}

			}
			countdown = 300;
		}

		for(Spettatore spettatore : spettatori){
		if(spettatore.getState() == Spettatore.State.STANDING){
			for(Ascia ascia : ascie){
				if(ascia.overlaps(spettatore)){
					ascia.kill();
					spettatore.kill();
					break;
				}
			}
		}
		}

		for(Tridente tridente : tridenti){
			if(omino.colpitoDa(tridente)){
				tridente.kill();
				break;
					}
				}

		omino.preventOverlap(sedie);


		if(!omino.isAlive()){
			loose = true;
			looseMessage.setVisible(true);
		}

		if(spettatori.isEmpty()){
			win = true;
			winMessage.setVisible(true);
		}

	}

	@Override
	public void dispose () {


	}
}

