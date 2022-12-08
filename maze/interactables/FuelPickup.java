package com.mygdx.maze.interactables;

import com.badlogic.gdx.math.Rectangle;

public class FuelPickup {

  int xPosition;
  int yPosition;
  boolean used;
  Rectangle bound;

  public FuelPickup(int givenXPosition, int givenYPosition) {
    this.xPosition = givenXPosition;
    this.yPosition = givenYPosition;
    this.used = false;
    bound = new Rectangle(this.xPosition, this.yPosition, 15, 15);

  }

  public void setXPosition(int givenXPosition) { this.xPosition = givenXPosition; }
  public int getXPosition() { return this.xPosition; }

  public void setYPosition(int givenYPosition) { this.yPosition = givenYPosition; }
  public int getYPosition() { return this.yPosition; }

  public void setAsUsed() { this.used = true; }
  public boolean checkIfUsed() { return this.used; }
}
