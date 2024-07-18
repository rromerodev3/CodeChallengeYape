package com.example.codechallengeyape.presentation.detail

import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.codechallengeyape.databinding.FragmentDetailsBinding
import com.example.codechallengeyape.domain.models.Location
import com.example.codechallengeyape.domain.models.Recipe
import com.example.codechallengeyape.framework.viewModels.MainViewModel
import com.example.codechallengeyape.presentation.MainActivity
import com.example.codechallengeyape.presentation.base.BaseFragment
import com.example.codechallengeyape.presentation.theme.ChallengeAppTheme
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
fun RecipeDetailsContent(
    recipe: Recipe,
    openLocation: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.image)
                    .crossfade(true)
                    .build(),
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            RatingBar(
                value = recipe.rating.toFloat(),
                onValueChange = {},
                onRatingChanged = {},
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                enabled = false,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            ) {
                Text(
                    recipe.difficulty,
                    color = Color.Red
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            recipe.ingredients.forEach { ingredient ->
                Text("- $ingredient")
            }
            Spacer(modifier = Modifier.height(16.dp))
            recipe.instructions.forEachIndexed { index, instruction ->
                Text("${index + 1} -> $instruction")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {

                val allTags = mutableListOf<String>()
                allTags.addAll(recipe.mealType)
                allTags.addAll(recipe.tags)

                allTags.forEach { tag ->
                    Badge(
                        contentColor = Color.White,
                        containerColor = Color.White,
                        modifier = Modifier.border(1.dp, color = Color.Blue, shape = CircleShape)
                    ) {
                        Text(
                            tag,
                            color = Color.Blue,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    openLocation()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ver ubicación")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewRecipeDetails() {
    RecipeDetailsContent(
        recipe =  Recipe(
            -1,
            "test recipe",
            listOf("", "", ""),
            listOf("", "", ""),
            2,
            3,
            "HARD",
            "MEXICAN",
            listOf("tag1", "tag2", "tag3"),
            "",
            5.0,
            listOf("", "", ""),
            Location(0.0, 0.0)
        ),
        openLocation =  {

        }
    )
}