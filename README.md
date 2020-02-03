# Guess-My-movie
a command prompt game that uses a manual decision tree to guess the movie of the player it needs to learn first.
## How to use
* open the project in Eclipse IDE
* run StartGame class 
* the load feature will not work at the beginning because no progress was saved.

## How the game works
The game trys to guess the movie of the player,
initially it will guess wrong because it does not know any movies yet, however it will ask the user a series of question to "Learn" from the wrong guess.
After "Teaching" the game a bunch of movies you can save the progress.
Let someone else play the game with the loaded memory, and watch if your "Taught" game guess the movie of the user.
## Manual Decision tree?
It is a manual desicion tree because it does not follow any "Smart" learning Algorithms
This means that instead of asking the user the most relavent question first, it asks the first thing it learned.

Some of my favorite decision tree learning algorithms that you can intigrate in the game:
* ID3
* CART

It will ask the most relavent question first by constructing the tree based on the most relavent question.
