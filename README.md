# RandomNumberTestApp
Recruitment project for Mode Mobile

# Architecture:
Within this task I used MVVM + Clean Architecture
![image](https://user-images.githubusercontent.com/11060323/207816866-d1a128c3-9d85-4f64-af3e-2cfa7c2f0f7b.png)


# TechStack:
- Hilt
- Compose
- Retrofit
- SharedPrefs

# API
https://www.randomnumberapi.com/

# How to use the app
In order to change range of random numbers please change `Constants.MIN_RANDOM_NUMBER` and `Constants.MAX_RANDOM_NUMBER` values.

1. In order to draw number at random, hit the Draw number button first (TextField for your guess will be disabled until the number will be fetched and ready to be guesseed)
2. Then you'll see that the number is downloaded and ready to check.
3. Type your guess number. (TextField is already enabled)
4. Check button to verify if your guess matched the random number.

https://user-images.githubusercontent.com/11060323/207820533-134e08c1-1756-48b3-8580-afead1bb2e17.mp4

# Room for improvement:
- I might use SharedPreferences as a local repository and use it like this instead of tangling it into VM logic
- Since in total I spent 3hrs as suggested in assessment description I didn't write Unit and Instrumented Tests. Normally those would be implemented as usual.

