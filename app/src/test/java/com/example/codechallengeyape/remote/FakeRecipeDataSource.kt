package com.example.codechallengeyape.remote

import com.example.codechallengeyape.data.network.ResultData
import com.example.codechallengeyape.domain.interfaces.RecipeDataSource
import com.example.codechallengeyape.domain.models.Location
import com.example.codechallengeyape.domain.models.Recipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FakeRecipeDataSource: RecipeDataSource {

    override suspend fun getRecipes(): ResultData<List<Recipe>> {
        val jsonString = "[{\"id\":1,\"name\":\"Mexican Street Corn (Elote)\",\"ingredients\":[\"Corn on the cob\",\"Mayonnaise\",\"Cotija cheese, crumbled\",\"Chili powder\",\"Lime wedges\"],\"instructions\":[\"Grill or roast corn on the cob until kernels are charred.\",\"Brush each cob with mayonnaise, then sprinkle with crumbled Cotija cheese and chili powder.\",\"Serve with lime wedges for squeezing over the top.\",\"Enjoy this delicious and flavorful Mexican Street Corn!\"],\"prepTimeMinutes\":15,\"cookTimeMinutes\":15,\"difficulty\":\"Easy\",\"cuisine\":\"Mexican\",\"tags\":[\"Elote\",\"Mexican\",\"Street food\"],\"image\":\"https://cdn.dummyjson.com/recipe-images/26.webp\",\"rating\":4.6,\"location\":{\"latitude\":19.42712,\"longitude\":-99.16765},\"mealType\":[\"Snack\",\"Side Dish\"]},{\"id\":2,\"name\":\"Blueberry Banana Smoothie\",\"ingredients\":[\"Blueberries, fresh or frozen\",\"Banana, peeled and sliced\",\"Greek yogurt\",\"Almond milk\",\"Honey\",\"Chia seeds (optional)\"],\"instructions\":[\"In a blender, combine blueberries, banana, Greek yogurt, almond milk, and honey.\",\"Blend until smooth and creamy.\",\"Add chia seeds for extra nutrition and blend briefly.\",\"Pour into a glass and enjoy this nutritious Blueberry Banana Smoothie!\"],\"prepTimeMinutes\":10,\"cookTimeMinutes\":0,\"difficulty\":\"Easy\",\"cuisine\":\"Smoothie\",\"tags\":[\"Smoothie\",\"Blueberry\",\"Banana\"],\"image\":\"https://cdn.dummyjson.com/recipe-images/25.webp\",\"rating\":4.8,\"location\":{\"latitude\":48.85841,\"longitude\":2.29447},\"mealType\":[\"Breakfast\",\"Beverage\"]},{\"id\":3,\"name\":\"Russian Borscht\",\"ingredients\":[\"Beets, peeled and shredded\",\"Cabbage, shredded\",\"Potatoes, diced\",\"Onions, finely chopped\",\"Carrots, grated\",\"Tomato paste\",\"Beef or vegetable broth\",\"Garlic, minced\",\"Bay leaves\",\"Sour cream for serving\"],\"instructions\":[\"In a pot, sauté chopped onions and garlic until softened.\",\"Add shredded beets, cabbage, diced potatoes, grated carrots, and tomato paste.\",\"Pour in broth and add bay leaves. Simmer until vegetables are tender.\",\"Serve hot with a dollop of sour cream on top.\",\"Enjoy the hearty and comforting flavors of Russian Borscht!\"],\"prepTimeMinutes\":30,\"cookTimeMinutes\":40,\"difficulty\":\"Medium\",\"cuisine\":\"Russian\",\"tags\":[\"Borscht\",\"Russian\",\"Soup\"],\"image\":\"https://cdn.dummyjson.com/recipe-images/27.webp\",\"rating\":4.3,\"location\":{\"latitude\":27.17516,\"longitude\":78.04213},\"mealType\":[\"Dinner\"]},{\"id\":4,\"name\":\"South Indian Masala Dosa\",\"ingredients\":[\"Dosa batter (fermented rice and urad dal batter)\",\"Potatoes, boiled and mashed\",\"Onions, finely chopped\",\"Mustard seeds\",\"Cumin seeds\",\"Curry leaves\",\"Turmeric powder\",\"Green chilies, chopped\",\"Ghee\",\"Coconut chutney for serving\"],\"instructions\":[\"In a pan, heat ghee and add mustard seeds, cumin seeds, and curry leaves.\",\"Add chopped onions, green chilies, and turmeric powder. Sauté until onions are golden brown.\",\"Mix in boiled and mashed potatoes. Cook until well combined and seasoned.\",\"Spread dosa batter on a hot griddle to make thin pancakes.\",\"Place a spoonful of the potato mixture in the center, fold, and serve hot.\",\"Pair with coconut chutney for a delicious South Indian meal.\"],\"prepTimeMinutes\":40,\"cookTimeMinutes\":20,\"difficulty\":\"Medium\",\"cuisine\":\"Indian\",\"tags\":[\"Dosa\",\"Indian\",\"Asian\"],\"image\":\"https://cdn.dummyjson.com/recipe-images/28.webp\",\"rating\":4.4,\"location\":{\"latitude\":29.97931,\"longitude\":31.13419},\"mealType\":[\"Breakfast\"]},{\"id\":5,\"name\":\"Lebanese Falafel Wrap\",\"ingredients\":[\"Falafel balls\",\"Whole wheat or regular wraps\",\"Tomatoes, diced\",\"Cucumbers, sliced\",\"Red onions, thinly sliced\",\"Lettuce, shredded\",\"Tahini sauce\",\"Fresh parsley, chopped\"],\"instructions\":[\"Warm falafel balls according to package instructions.\",\"Place a generous serving of falafel in the center of each wrap.\",\"Top with diced tomatoes, sliced cucumbers, red onions, shredded lettuce, and fresh parsley.\",\"Drizzle with tahini sauce and wrap tightly.\",\"Enjoy this Lebanese Falafel Wrap filled with fresh and flavorful ingredients!\"],\"prepTimeMinutes\":15,\"cookTimeMinutes\":10,\"difficulty\":\"Easy\",\"cuisine\":\"Lebanese\",\"tags\":[\"Falafel\",\"Lebanese\",\"Wrap\"],\"image\":\"https://cdn.dummyjson.com/recipe-images/29.webp\",\"rating\":4.7,\"location\":{\"latitude\":41.89035,\"longitude\":12.49228},\"mealType\":[\"Lunch\"]}]"

        val responseType = object : TypeToken<List<Recipe>>(){}.type
        val recipes: List<Recipe> = Gson().fromJson(jsonString, responseType)

        return ResultData.Success(recipes)
    }

    override suspend fun getRecipe(): ResultData<Recipe> {
        return ResultData.Success(
            Recipe(
                id = 1,
                name = "Mexican Street Corn (Elote)",
                ingredients = listOf(
                    "Corn on the cob",
                    "Mayonnaise",
                    "Cotija cheese, crumbled",
                    "Chili powder",
                    "Lime wedges"
                ),
                instructions = listOf(
                    "Grill or roast corn on the cob until kernels are charred.",
                    "Brush each cob with mayonnaise, then sprinkle with crumbled Cotija cheese and chili powder.",
                    "Serve with lime wedges for squeezing over the top.",
                    "Enjoy this delicious and flavorful Mexican Street Corn!"
                ),
                prepTimeMinutes = 15,
                cookTimeMinutes = 15,
                difficulty = "Easy",
                cuisine = "Mexican",
                tags = listOf(
                    "Elote",
                    "Mexican",
                    "Street food"
                ),
                image = "https://cdn.dummyjson.com/recipe-images/26.webp",
                rating = 4.6,
                mealType = listOf(
                    "Snack",
                    "Side Dish"
                ),
                location = Location(
                    latitude = 19.42712,
                    longitude = -99.16765
                )
            )
        )
    }
}