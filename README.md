# Guess-My-movie
a command prompt game that uses a manual decision tree to guess the movie of the player through learning first.  

# How the game works
The game trys to guess the movie of the player,
initially it will guess wrong because it does not know any movies yet, however it will ask the user a series of question to "Learn" every time it guesses wrong.
After "Teaching" the game a bunch of movies you can save the progress.
Let someone else play the game with the loaded memory, and watch if your "Taught" game guess the movie of the user.

## Once you open
* You can Play (teach the program)
* Save what the program learnt
* Load a memory (works only if you saved)

![image](https://user-images.githubusercontent.com/57009004/137764680-690ac347-cc20-4e46-83c6-c77429726122.png)

## As the program learns
![image](https://user-images.githubusercontent.com/57009004/137765898-d8df387f-9e50-4bf3-a299-518bbea95005.png)

## As the program wins
![image](https://user-images.githubusercontent.com/57009004/137765700-15b562c4-a2b3-438f-9a9e-8fd8fde8b8d9.png)

## Looking at the memory
![image](https://user-images.githubusercontent.com/57009004/137767925-6ae023c5-2a66-4550-a112-81c059bbd707.png)

# How to use
```
javac StartGame.java
java StartGame
```
the load feature will not work at the beginning because no progress was saved.

## Manual Decision tree?
It is a manual desicion tree because it does not follow any "Smart" learning Algorithms
This means that instead of asking the user the most relavent question first, it asks the first thing it learned.
