package com.mygdx.maze;

public class Settings {

  public float settings_maximum_lightRadius = 1.35f; // Default = 1.35f
  public float settings_lightRadius = 1.35f; // Default = 1.35f
  public float settings_lightShrinkSpeed = 0.005f; // Default = 0.005f
  public int settings_cameraZoomWidth = 500; // Default = 500
  public int settings_cameraZoomHeight = 450; // Default = 450
  public int settings_mazeWidth = 20; // Default = 20
  public int settings_mazeHeight = 20; // Default = 20
  public int settings_playerSpeed = 1; // Default = 1
  public int settings_playerWidth = 8; // Default = 10
  public int settings_playerHeight = 8; // Default = 10
  public int settings_wallThickness = 40; // Default = 20
  public int settings_playerCurrentXPosition = 0; // Default = 0
  public int settings_playerCurrentYPosition = 0; // Default = 0
  public float settings_current_torch_fuel = 1.0f; // Default = 1.0f
  public int settings_total_fuel_pickups = 7; // Default = 8
  public float settings_torch_fuel_pickup_power = 0.5f; // Default = 0.5f
  public int settings_total_enemy_guards = 7; // Default = 7
  public float settings_gate_timer_total_seconds = 30.f; // Default = 30.0f
  public float settings_seconds_left_to_escape = this.settings_gate_timer_total_seconds;
  public boolean settings_was_spotted_by_guard = false; // Default = false

  private static Settings instance = null;

  public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

  public void setMaximumLightRadius(float givenMaximumLightRadius) { this.settings_maximum_lightRadius = givenMaximumLightRadius; }
  public float getMaximumLightRadius() { return this.settings_maximum_lightRadius; }

  public void setLightRadius(float givenLightRadius) { this.settings_lightRadius = givenLightRadius; }
  public float getLightRadius() { return this.settings_lightRadius; }

  public void setLightShrikSpeed(float givenLightShrinkSpeed) { this.settings_lightShrinkSpeed = givenLightShrinkSpeed; }
  public float getLightShrinkSpeed() { return this.settings_lightShrinkSpeed; }

  public void setCameraZoomWidth(int givenCameraZoomWidth) { this.settings_cameraZoomWidth = givenCameraZoomWidth; }
  public int getCameraZoomWidth() { return this.settings_cameraZoomWidth; }

  public void setCameraZoomHeight(int givenCameraZoomHeight) { this.settings_cameraZoomHeight = givenCameraZoomHeight; }
  public int getCameraZoomHeight() { return this.settings_cameraZoomHeight; }

  public void setMazeWidth(int givenMazeWidth) { this.settings_mazeWidth = givenMazeWidth; }
  public int getMazeWidth() { return this.settings_mazeWidth; }

  public void setMazeHeight(int givenMazeHeight) { this.settings_mazeHeight = givenMazeHeight; }
  public int getMazeHeight() { return this.settings_mazeHeight; }

  public void setPlayerSpeed(int givenPlayerSpeed) { this.settings_playerSpeed = givenPlayerSpeed; }
  public int getPlayerSpeed() { return this.settings_playerSpeed; }

  public void setPlayerWidth(int givenPlayerWidth) { this.settings_playerSpeed = givenPlayerWidth; }
  public int getPlayerWidth() { return this.settings_playerWidth; }

  public void setPlayerHeight(int givenPlayerHeight) { this.settings_playerSpeed = givenPlayerHeight; }
  public int getPlayerHeight() { return this.settings_playerHeight; }

  public void setWallThickness(int givenWallThickness) { this.settings_wallThickness = givenWallThickness; }
  public int getWallThickness() { return this.settings_wallThickness; }

  public void setPlayerCurrentXPosition(int givenPlayerCurrentXPosition) { this.settings_playerCurrentXPosition = givenPlayerCurrentXPosition; }
  public int getPlayerCurrentXPosition() { return this.settings_playerCurrentXPosition; }

  public void setPlayerCurrentYPosition(int givenPlayerCurrentYPosition) { this.settings_playerCurrentYPosition = givenPlayerCurrentYPosition; }
  public int getPlayerCurrentYPosition() { return this.settings_playerCurrentYPosition; }

  public void setCurrentTorchFuel(float givenCurrentTorchFuel) { this.settings_current_torch_fuel = givenCurrentTorchFuel; }
  public float getCurrentTorchFuel() { return this.settings_current_torch_fuel; }

  public void setTotalFuelPickups(int givenTotalFuelPickups) { this.settings_total_fuel_pickups = givenTotalFuelPickups; }
  public float getTotalFuelPickups() { return this.settings_total_fuel_pickups; }

  public void setTorchFuelPickupPower(int givenTorchFuelPickupPower) { this.settings_torch_fuel_pickup_power = givenTorchFuelPickupPower; }
  public float getTorchFuelPickupPower() { return this.settings_torch_fuel_pickup_power; }

  public void setTotalEnemyGuards(int givenTotalEnemyGuards) { this.settings_total_enemy_guards = givenTotalEnemyGuards; }
  public int getTotalEnemyGuards() { return this.settings_total_enemy_guards; }

  public void setGateTimerTotalSeconds(float givenGateTimerTotalSeconds) { this.settings_gate_timer_total_seconds = givenGateTimerTotalSeconds; }
  public float getGateTimerTotalSeconds() { return this.settings_gate_timer_total_seconds; }

  public void setSecondsLeftToEscape(float givenSecondsLeftToEscape) { this.settings_seconds_left_to_escape = givenSecondsLeftToEscape; }
  public float getSecondsLeftToEscape() { return this.settings_seconds_left_to_escape; }

  public void setAsSpottedByGuard() { this.settings_was_spotted_by_guard = true; }
  public void setAsNotSpottedByGuard() { this.settings_was_spotted_by_guard = false; }
  public boolean checkIfSpottedByGuard() { return this.settings_was_spotted_by_guard; }

}
