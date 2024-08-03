# RecipeCalorieApp

## about the app
It is an app for creating recipes with basic macro info such as calories, fats, proteins and carbs. You can add your food and type in their macros per 100 gram, and then add those foods
to your recipe, and then you will have a recipe with the macro info. Then you can create a meal from that recipe, where you type in how many grams the portion/meal is and from that you will be able to calculate how many macros there is in one portion of the meal, simply by typing how many grams you have taken. A meal will be in the meal list until it reaches 0 grams or is deleted.

## Future updates and fixes

### Code reduction and formatting
  Making the code look better, remove redundant code, and simplify naming of functions, entities and so on.
1. Remove database helper from several dao since it is not doing anything

### Next tasks
1. Making an activity for meals:
   Here you should be able to see all the ongoing meals and be able to see how much of the meal is left
   
3. Making an entity called portion, which is the portion you take from a meal.
   The portion should contain information such as total macros and date of registration.
   It should be possible to remove the portion from the history if you mistype, so that the amount of the meal always fits what you have left.
  
