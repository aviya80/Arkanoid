# Arkanoid (Brick Breaker) Game

## Introduction
Arkanoid is a classic Brick Breaker game implemented in Java. It was created as a project in the Introduction to Object-Oriented Programming course during my 1st year, second semester at Bar-Ilan University. The game utilizes various object-oriented programming concepts such as design patterns (Factory, Observer/Listener), polymorphism & interfaces, inheritance, and more.

## About
The game consists of 4 different levels, each with incrementing difficulty. It uses our university's GUI to display the animations.

## Requirements
- GUI .jar file (included)
- Apache Ant tool (for convenient build and run tasks) - installation instructions available online

## Installation and Usage
1. Enter the directory folder in the command-line.
2. Run `ant compile` to compile the files.
3. Run `ant run` followed by a sequence of numbers between 1-4 to select the game level, e.g., `ant run 3 1 1 4`. 
   - Alternatively, just run `ant run` for the default level sequence.

## Game Levels
- **Direct Hit**: One ball, one block. Hit the block with a clean shot.
- **Final Four**: Classic level with three balls.
- **Green3**: Classic level with two balls.
- **WideEazy**: Ten balls, one line of blocks. Each block hit gives 100 points. Each round grants additional bonus points.

## How to Run
1. Navigate to the game directory in the command line.
2. Run `ant compile`.
3. Run `ant run` followed by the desired level numbers or just `ant run` for default levels.

Enjoy the game!
