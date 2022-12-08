package com.mygdx.maze.entity;

import java.util.ArrayList;
import java.util.Random;
import java.time.temporal.ValueRange;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.maze.main.Driver;
import com.mygdx.maze.maze.Maze;
import com.mygdx.maze.maze.MazeDrawerSquare;
import com.mygdx.maze.maze.Tile;
import com.mygdx.maze.maze.TileType;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.maze.box2dLight.RayHandler;
import com.mygdx.maze.box2dLight.PointLight;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.maze.Settings;
import com.mygdx.maze.ui.FuelBar;
import com.mygdx.maze.interactables.FuelPickup;
import com.mygdx.maze.interactables.EnemyGuard;

public class EntityPlayer extends Entity
{
	Settings global_settings = Settings.getInstance();
	private Stage stage;
	public FuelBar fuelBar;
	RayHandler rayHandler;
	World world;
	float radius;
	int x_position;
	int y_position;
	int playerCurrentXPosition;
	int playerCurrentYPosition;
	ArrayList<FuelPickup> fuelPickups = new ArrayList<FuelPickup>();
	ArrayList<EnemyGuard> enemyGuards = new ArrayList<EnemyGuard>();
	MazeDrawerSquare drawer = (MazeDrawerSquare) this.maze.getDrawer();
	Random random = new Random();
	private BitmapFont font;
  private SpriteBatch batch;

	public EntityPlayer(Maze maze, Tile tile)
	{
		// Create the maze
		super(maze, tile);
		// Create the fuel bar
		stage = new Stage();
		fuelBar = new FuelBar(100, 10);
		fuelBar.setPosition(10, 20);
		stage.addActor(fuelBar);
		// Randomly generate the fuel pickups
		for (int index=0; index < global_settings.getTotalFuelPickups(); index++) {
      int thisRandomXPosition = random.nextInt(global_settings.getMazeWidth() * global_settings.getWallThickness());
			int thisRandomYPosition = random.nextInt(global_settings.getMazeHeight() * global_settings.getWallThickness());
			FuelPickup thisFuelPickup = new FuelPickup(thisRandomXPosition, thisRandomYPosition);
			fuelPickups.add(thisFuelPickup);
		}
		// Randomly generate the enemy guards
		for (int index=0; index < global_settings.getTotalEnemyGuards(); index++) {
      int thisRandomXPosition = random.nextInt(global_settings.getMazeWidth() * global_settings.getWallThickness());
			int thisRandomYPosition = random.nextInt(global_settings.getMazeHeight() * global_settings.getWallThickness());
			EnemyGuard thisEnemyGuard = new EnemyGuard(thisRandomXPosition, thisRandomYPosition);
			enemyGuards.add(thisEnemyGuard);
		}
		// Render the gate close timer text
		batch = new SpriteBatch();
    font = new BitmapFont();
    font.setColor(Color.WHITE);
	}

	@Override
	public void update(float delta)
	{
		this.handlePlayerMovement();
		this.trackPlayerMovement();
	}

	@Override
	public void render(float delta)
	{
		// Draw the player
		this.drawPlayer();
		playerCurrentXPosition = (int) this.maze.getDrawer().getTileCenterX(this.tile.getRow(), this.tile.getColumn()) - drawer.getTileSize() / 6;
		playerCurrentYPosition = (int) this.maze.getDrawer().getTileCenterY(this.tile.getRow(), this.tile.getColumn()) - drawer.getTileSize() / 6;
		// Draw fuel pickups
		for (int index=0; index < fuelPickups.size(); index++) {
			// Check if the fuel pickup was not already used
			if (!fuelPickups.get(index).checkIfUsed()) {
				Driver.shape.begin(ShapeType.Filled);
				Driver.shape.setColor(Color.ORANGE);
				Driver.shape.rect(fuelPickups.get(index).getXPosition(), fuelPickups.get(index).getYPosition(), 10, 10);
				Driver.shape.end();
				// Check if player is near a not used fuel pickup
				ValueRange xRange = ValueRange.of(fuelPickups.get(index).getXPosition() - 30, fuelPickups.get(index).getXPosition() + 30);
				ValueRange yRange = ValueRange.of(fuelPickups.get(index).getYPosition() - 30, fuelPickups.get(index).getYPosition() + 30);
				if (xRange.isValidIntValue(playerCurrentXPosition)) {
					if (yRange.isValidIntValue(playerCurrentYPosition)) {
						fuelPickups.get(index).setAsUsed();
						global_settings.setCurrentTorchFuel(global_settings.getCurrentTorchFuel() + global_settings.getTorchFuelPickupPower());
						if (global_settings.getCurrentTorchFuel() > 1.0f) {
							global_settings.setCurrentTorchFuel(1.0f);
						}
						global_settings.setLightRadius(global_settings.getLightRadius() + global_settings.getTorchFuelPickupPower());
						if (global_settings.getLightRadius() > 1.0f) {
							global_settings.setLightRadius(global_settings.getMaximumLightRadius());
						}
					}
				}
			}
		}
		// Draw enemy guards
		for (int index=0; index < enemyGuards.size(); index++) {
			// Check if the fuel pickup was not already used
			if (!global_settings.checkIfSpottedByGuard()) {
				Driver.shape.begin(ShapeType.Filled);
				Driver.shape.setColor(Color.RED);
				Driver.shape.rect(enemyGuards.get(index).getXPosition(), enemyGuards.get(index).getYPosition(), 10, 10);
				Driver.shape.end();
				// Check if player is near a not used fuel pickup
				ValueRange xRange = ValueRange.of(enemyGuards.get(index).getXPosition() - 30, enemyGuards.get(index).getXPosition() + 30);
				ValueRange yRange = ValueRange.of(enemyGuards.get(index).getYPosition() - 30, enemyGuards.get(index).getYPosition() + 30);
				if (xRange.isValidIntValue(playerCurrentXPosition)) {
					if (yRange.isValidIntValue(playerCurrentYPosition)) {
						global_settings.setAsSpottedByGuard();
						System.out.println("Spotted!");
					}
				}
			}
		}
		// Render the light
		radius = global_settings.getLightRadius();
		world = new World(new Vector2(0,0),false);
		rayHandler = new RayHandler(world);
		new PointLight(rayHandler,500,Color.ORANGE,radius,x_position,y_position);
		rayHandler.updateAndRender();
		//Draw fuel bar (AFTER rendering the light so you can always see it)
		stage.draw();
		stage.act();
		// Draw the gate close timer (AFTER rendering the light so you can always see it)
		if (global_settings.checkIfSpottedByGuard()) {
			batch.begin();
			font.draw(batch, "Escape before the gate closes!", 100, 450);
	    font.draw(batch, String.valueOf(global_settings.getSecondsLeftToEscape()), 350, 450);
			global_settings.setSecondsLeftToEscape(global_settings.getSecondsLeftToEscape() - 0.01f); // Subtract one frame seconds
	    batch.end();
		}
		// Check if the player loses
		if (global_settings.getSecondsLeftToEscape() <= 0) {
			System.out.println("Defeat!");
		}
		if (global_settings.getCurrentTorchFuel() <= 0) {
			System.out.println("Defeat!");
		}
	}

	private void handlePlayerMovement()
	{
		this.handleSquarePlayerMovement();
	}

	private void handleSquarePlayerMovement()
	{
		ArrayList<Tile> neighbors = this.maze.getNeighbors(tile);
		if(Gdx.input.isKeyJustPressed(Keys.W)) // NORTH MOVEMENT
		{
			if(neighbors.get(0) != null && this.maze.isPathClear(tile, neighbors.get(0)))
			{
				this.setTile(neighbors.get(0));
				global_settings.setLightRadius(global_settings.getLightRadius() - global_settings.getLightShrinkSpeed());
				global_settings.setCurrentTorchFuel(global_settings.getCurrentTorchFuel() - global_settings.getLightShrinkSpeed());
				fuelBar.setValue(global_settings.getCurrentTorchFuel());
			}
		}
		else if(Gdx.input.isKeyJustPressed(Keys.A)) // WEST MOVEMENT
		{
			if(neighbors.get(3) != null && this.maze.isPathClear(tile, neighbors.get(3)))
			{
				this.setTile(neighbors.get(3));
				global_settings.setLightRadius(global_settings.getLightRadius() - global_settings.getLightShrinkSpeed());
				global_settings.setCurrentTorchFuel(global_settings.getCurrentTorchFuel() - global_settings.getLightShrinkSpeed());
				fuelBar.setValue(global_settings.getCurrentTorchFuel());
			}
		}
		else if(Gdx.input.isKeyJustPressed(Keys.S)) // SOUTH MOVEMENT
		{
			if(neighbors.get(2) != null && this.maze.isPathClear(tile, neighbors.get(2)))
			{
				this.setTile(neighbors.get(2));
				global_settings.setLightRadius(global_settings.getLightRadius() - global_settings.getLightShrinkSpeed());
				global_settings.setCurrentTorchFuel(global_settings.getCurrentTorchFuel() - global_settings.getLightShrinkSpeed());
				fuelBar.setValue(global_settings.getCurrentTorchFuel());
			}
		}
		else if(Gdx.input.isKeyJustPressed(Keys.D)) // EAST MOVEMENT
		{
			if(neighbors.get(1) != null && this.maze.isPathClear(tile, neighbors.get(1)))
			{
				this.setTile(neighbors.get(1));
				global_settings.setLightRadius(global_settings.getLightRadius() - global_settings.getLightShrinkSpeed());
				global_settings.setCurrentTorchFuel(global_settings.getCurrentTorchFuel() - global_settings.getLightShrinkSpeed());
				fuelBar.setValue(global_settings.getCurrentTorchFuel());
			}
		}
	}

	private void trackPlayerMovement()
	{
		float x = this.maze.getDrawer().getTileCenterX(this.tile.getRow(), this.tile.getColumn());
		float y = this.maze.getDrawer().getTileCenterY(this.tile.getRow(), this.tile.getColumn());

		Driver.camera.position.lerp(new Vector3((int) x, (int) y, 0), 0.15f);
	}

	private void drawPlayer()
	{
		this.drawSquareMazePlayer();
	}

	private void drawSquareMazePlayer()
	{
		Driver.shape.begin(ShapeType.Filled);
		Driver.shape.setColor(this.maze.getDrawer().getPlayerColor());
		Driver.shape.rect(this.maze.getDrawer().getTileCenterX(this.tile.getRow(), this.tile.getColumn()) - drawer.getTileSize() / 6,
				this.maze.getDrawer().getTileCenterY(this.tile.getRow(), this.tile.getColumn()) - drawer.getTileSize() / 6,
				drawer.getTileSize() / 2,
				drawer.getTileSize() / 2);
		Driver.shape.end();
	}

	@Override
	public void dispose()
	{

	}
}
