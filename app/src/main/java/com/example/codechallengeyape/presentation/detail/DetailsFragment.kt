package com.example.codechallengeyape.presentation.detail

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.codechallengeyape.R
import com.example.codechallengeyape.databinding.FragmentDetailsBinding
import com.example.codechallengeyape.domain.models.Location
import com.example.codechallengeyape.domain.models.Recipe
import com.example.codechallengeyape.framework.viewModels.MainViewModel
import com.example.codechallengeyape.presentation.MainActivity
import com.example.codechallengeyape.presentation.base.BaseFragment
import com.example.codechallengeyape.presentation.theme.ChallengeAppTheme
import com.example.codechallengeyape.presentation.theme.DarkBackground
import com.gowtham.ratingbar.RatingBar

class DetailsFragment: BaseFragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val parentViewModel: MainViewModel by activityViewModels()

    override fun createBinding(container: ViewGroup?): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun setupUiBehavior() {
        binding.apply {
            composeViewDetails.apply {
                // Dispose the Composition when the view's LifecycleOwner
                // is destroyed
                setViewCompositionStrategy(
                    ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
                )
                setContent {
                    ChallengeAppTheme {
                        RecipeDetails(parentViewModel)
                    }
                }
            }
        }
    }

    override fun subscribeToEvents() {
        // do nothing
    }

    override fun getScreenTitle() = R.string.details_title

    override fun configureMenu() {
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}

@Composable
fun RecipeDetails(parentViewModel: MainViewModel) {
    // Observes values coming from the VM's LiveData<Recipe> field
    val recipe by parentViewModel.selectedRecipe.observeAsState()

    // If plant is not null, display the content
    recipe?.let {
        RecipeDetailsContent(
            recipe = it,
            openLocation = {
                parentViewModel.goToScreen(MainActivity.Screens.Location)
            }
        )
    }
}

@Composable
fun RecipeHeader(
    recipeName: String,
    recipeUrlImage: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = recipeName,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(horizontal = 16.dp)
                .background(colorResource(R.color.spacer_background))
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(Color.White, shape = CircleShape)
                .shadow(5.dp, CircleShape)
        ) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color.White, shape = CircleShape)
            ) {

                Image(
                    painter = painterResource(R.drawable.img_placeholder_network),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(284.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape)
                )

                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(recipeUrlImage)
                        .crossfade(true)
                        .build(),
                    loading = {
                        CircularProgressIndicator()
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .size(284.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape)
                )
            }
        }
    }
}

@Composable
fun RecipeRating(
    recipeRating: Double,
    modifier: Modifier = Modifier
) {
    RatingBar(
        value = recipeRating.toFloat(),
        onValueChange = {},
        onRatingChanged = {},
        modifier = modifier
    )
}

@Composable
fun RecipeMainInfoElement(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(DarkBackground, RoundedCornerShape(10.dp))
            .size(width = 100.dp, height = 80.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = value,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()

            )
            Text(
                text = title,
                color = Color.LightGray,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()

            )
        }
    }
}

@Composable
fun RecipeMainInfo(
    difficulty: String,
    time: Int,
    cuisine: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        RecipeMainInfoElement(
            stringResource(R.string.recipe_difficulty_string),
            difficulty,
            modifier = Modifier.weight(1f)
        )

        RecipeMainInfoElement(
            stringResource(R.string.recipe_time_string),
            "${time}min",
            modifier = Modifier.weight(1f)
        )

        RecipeMainInfoElement(
            stringResource(R.string.recipe_cuisine_string),
            cuisine,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun RecipeDetailsContent(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    openLocation: () -> Unit
) {
    Surface(
        color = colorResource(R.color.background),
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            RecipeHeader(
                recipeName = recipe.name,
                recipeUrlImage = recipe.image,
            )

            Spacer(modifier = Modifier.height(24.dp))

            RecipeRating(
                recipeRating = recipe.rating,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            RecipeMainInfo(
                recipe.difficulty,
                recipe.cookTimeMinutes,
                recipe.cuisine
            )

            Spacer(modifier = Modifier.height(24.dp))

            RecipeCard(
                ingredients = recipe.ingredients,
                instructions = recipe.instructions,
                mealTypes = recipe.mealTypes,
                tags = recipe.tags
            )

            Spacer(modifier = Modifier.height(24.dp))

            RecipeLocationButton {
                openLocation()
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun RecipeCard(
    ingredients: List<String>,
    instructions: List<String>,
    mealTypes: List<String>,
    tags: List<String>,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = modifier
            .fillMaxWidth()
    ) {

        RecipeCardIngredients(
            title =  stringResource(R.string.ingredients_section_title),
            ingredients = ingredients
        )

        RecipeCardInstructions(
            title = stringResource(R.string.instructions_section_title),
            instructions = instructions,
        )

        RecipeCardTags(
            title = stringResource(R.string.tags_section_title),
            mealTypes = mealTypes,
            tags = tags
        )
    }
}

@Composable
fun RecipeCardIngredients(
    title: String,
    ingredients: List<String>
) {
    Column {
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkBackground)
                .padding(vertical = 8.dp)
        )

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(colorResource(R.color.spacer_background))
                .padding(vertical = 5.dp)
        )

        ingredients.forEach { ingredient ->
            Text("- $ingredient", modifier = Modifier.padding(start = 8.dp, top = 8.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun RecipeCardInstructions(
    title: String,
    instructions: List<String>,
) {
    Column {
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkBackground)
                .padding(vertical = 8.dp)
        )

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(colorResource(R.color.spacer_background))
                .padding(vertical = 5.dp)
        )

        instructions.forEachIndexed { index, instruction ->
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Step ${index + 1}",
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                )
                Text(instruction, modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp))
            }
        }
    }
}


@Composable
fun RecipeCardTags(
    title: String,
    mealTypes: List<String>,
    tags: List<String>,
) {
    Column {
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkBackground)
                .padding(vertical = 8.dp)
        )

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(colorResource(R.color.spacer_background))
                .padding(vertical = 5.dp)
        )

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(8.dp)
        ) {

            val allTags = mutableListOf<String>()
            allTags.addAll(mealTypes)
            allTags.addAll(tags)

            allTags.forEach { tag ->
                Badge(
                    contentColor = Color.Transparent,
                    containerColor = Color.Transparent,
                    modifier = Modifier
                        .border(1.dp, color = DarkBackground, shape = CircleShape)
                        .padding(3.dp)
                ) {
                    Text(
                        tag,
                        color = DarkBackground,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@Composable
fun RecipeLocationButton(
    modifier: Modifier = Modifier,
    openLocation: () -> Unit
) {
    ElevatedButton(
        onClick = {
            openLocation()
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = DarkBackground
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Go to recipe location",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)

            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreRecipeHeader() {
    RecipeHeader(
        recipeName = "Blueberry Banana Smoothie",
        recipeUrlImage = "https://cdn.dummyjson.com/recipe-images/25.webp",
    )
}

@Preview(showBackground = true)
@Composable
private fun PreRecipeRating() {
    RecipeRating(
        recipeRating = 2.5
    )
}

@Preview(showBackground = true)
@Composable
private fun PrevRecipeMainInfoElement() {
    RecipeMainInfoElement(
        title = "Title",
        value = "Value"
    )
}


@Preview(showBackground = true)
@Composable
private fun PrevRecipeMainInfo() {
    RecipeMainInfo(
        difficulty = "Easy",
        time = 30,
        cuisine = "Mexican"
    )
}

@Preview(showBackground = true)
@Composable
private fun PrevRecipeCardIngredients() {
    RecipeCardIngredients(
        title = "Ingredients",
        ingredients =  listOf(
            "Blueberries, fresh or frozen",
            "Banana, peeled and sliced",
            "Greek yogurt",
            "Almond milk",
            "Honey",
            "Chia seeds (optional)"
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun PrevRecipeCardInstructions() {
    RecipeCardInstructions(
        title = "Instructions",
        instructions =  listOf(
            "In a blender, combine blueberries, banana, Greek yogurt, almond milk, and honey.",
            "Blend until smooth and creamy.",
            "Add chia seeds for extra nutrition and blend briefly.",
            "Pour into a glass and enjoy this nutritious Blueberry Banana Smoothie!"
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun PrevRecipeCardTags() {
    RecipeCardTags(
        title = "Tags",
        mealTypes =listOf(
            "Breakfast",
            "Beverage"
        ),
        tags = listOf(
            "Smoothie",
            "Blueberry",
            "Banana"
        )
    )
}

@Preview
@Composable
private fun PrevRecipeCard() {
    RecipeCard(
        ingredients =  listOf(
            "Blueberries, fresh or frozen",
            "Banana, peeled and sliced",
            "Greek yogurt",
            "Almond milk",
            "Honey",
            "Chia seeds (optional)"
        ),
        instructions =  listOf(
            "In a blender, combine blueberries, banana, Greek yogurt, almond milk, and honey.",
            "Blend until smooth and creamy.",
            "Add chia seeds for extra nutrition and blend briefly.",
            "Pour into a glass and enjoy this nutritious Blueberry Banana Smoothie!"
        ),
        mealTypes =listOf(
            "Breakfast",
            "Beverage"
        ),
        tags = listOf(
            "Smoothie",
            "Blueberry",
            "Banana"
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun PrevRecipeButton() {
    RecipeLocationButton(openLocation = { },)
}

@Preview(heightDp = 1200)
@Composable
private fun PreviewRecipeDetails() {
    RecipeDetailsContent(
        recipe =  Recipe(
            id = -1,
            name = "Blueberry Banana Smoothie",
            ingredients =  listOf(
                "Blueberries, fresh or frozen",
                "Banana, peeled and sliced",
                "Greek yogurt",
                "Almond milk",
                "Honey",
                "Chia seeds (optional)"
            ),
            instructions =  listOf(
                "In a blender, combine blueberries, banana, Greek yogurt, almond milk, and honey.",
                "Blend until smooth and creamy.",
                "Add chia seeds for extra nutrition and blend briefly.",
                "Pour into a glass and enjoy this nutritious Blueberry Banana Smoothie!"
            ),
            prepTimeMinutes =  30,
            cookTimeMinutes = 15,
            difficulty = "Easy",
            cuisine = "Spanish",
            tags = listOf(
                "Smoothie",
                "Blueberry",
                "Banana"
            ),
            image = "https://cdn.dummyjson.com/recipe-images/25.webp",
            rating = 2.5,
            mealTypes = listOf(
                "Breakfast",
                "Beverage"
            ),
            Location(
                latitude = 48.85841,
                longitude = 2.29447
            )
        ),
        openLocation =  {

        }
    )
}