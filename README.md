# Greed-Kata

#Project Description
The Greed-Kata is a program that stimulates a press-your-luck dice rolling game. It utilizes a set of 5 dice and randomly rolls it. The calculated score based off the dice roll is displayed. 
The program is written in Java and utilizes Serialization and wave audio files. Serialization was used to save and load the game and keep track of a maximum score. Wave audio files were used to enhance in game experience.

#Questions that I came up
The only questions that I had were about how the score was calculated, the number of dice rolled and the numbers on the dice. Eventually, I was able to answer those questions myself by re-reading the instructions.

#Assumptions that I made
-The set of dice that are rolled are always 5. This set is used to create the Dice object.
-The dice are always numbered one through six.
-The program needs to run on an IDE environment that runs a Java Development Kit.
-The score calculated is always an integer.
-The program is run on an environment that has the ability to accept keyboard activity as input and output to a screen and speakers.
-The user always makes an input.

#The different paths I contemplated
-I considered writing the code first or writing its tests first. Given the time, I decided to write the tests first and go with a TDD approach to the problem.
-I contemplated sorting the randomly rolled device in ascending order before calculating the score. After considering the fact that I only had to sort five dice, I decided to not put that approach to use.
-I contemplated making use of a 2d array to store the number and its counts. After writing the method that calculates the score, I decided that the 2d array was very unnecessary.
-I contemplated using a constructor injection to supply the Dice class with the set of dice and decided to stick with the injection to make it obvious to whoever reads the code that a set of five dice is used to create the game.
-I also contemplated wether I should make the score a class level variable and decided to localize it to the method that calculates it. I deemed that the score was not used throughout the class and was unnecessary. I also contemplated having the dice set as a class level variable and decided to leave it as one, since the whole class made use of it and the constructor used it to initialize.
-I also decided to localize the dice related methods in one class and the class that handles the printing part in a separate class. This enables me to implement a model-view pattern.