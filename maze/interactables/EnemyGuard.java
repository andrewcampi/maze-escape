package com.mygdx.maze.interactables;

import com.badlogic.gdx.math.Rectangle;

public class EnemyGuard {

  int xPosition;
  int yPosition;
  boolean spotted;
  Rectangle bound;

  public EnemyGuard(int givenXPosition, int givenYPosition) {
    this.xPosition = givenXPosition;
    this.yPosition = givenYPosition;
    this.spotted = false;
    bound = new Rectangle(this.xPosition, this.yPosition, 15, 15);
  }

  public void setXPosition(int givenXPosition) { this.xPosition = givenXPosition; }
  public int getXPosition() { return this.xPosition; }

  public void setYPosition(int givenYPosition) { this.yPosition = givenYPosition; }
  public int getYPosition() { return this.yPosition; }

  public void setAsSpotted() { this.spotted = true; }
  public boolean checkIfSpotted() { return this.spotted; }
}
