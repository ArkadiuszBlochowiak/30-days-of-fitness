package com.example.fitnessdays

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessdays.data.ExercisesRepository
import com.example.fitnessdays.model.Exercise
import com.example.fitnessdays.ui.theme.FitnessDaysTheme


@Composable
fun ExerciseItem(
    item: Exercise,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        onClick = { expanded = !expanded },
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(R.dimen.padding_large),
                    vertical = dimensionResource(R.dimen.padding_medium)
                )
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            ExerciseItemHeader(
                day = item.dayNumber,
                title = item.titleRes,
                expanded = expanded,
                modifier = Modifier
                    .padding(
                        bottom = dimensionResource(R.dimen.padding_small)
                    )
            )
            ExerciseItemImage(
                image = item.imageRes,
                modifier = Modifier
                    .padding(
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
            )
            if(expanded) {
                ExerciseItemDescription(
                    description = item.descriptionRes
                )
            }
        }
    }
}

@Composable
fun ExerciseItemHeader(
    day: Int,
    @StringRes title: Int,
    expanded: Boolean,
    modifier: Modifier = Modifier
) {
    val expandIcon = if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = stringResource(R.string.day, day),
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.displayMedium
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = expandIcon,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .testTag(if(expanded) "expandLess" else "expandMore")
        )
    }
}

@Composable
fun ExerciseItemImage(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .requiredHeight(dimensionResource(R.dimen.image_size))
            .fillMaxWidth()
    )
}

@Composable
fun ExerciseItemDescription(
    @StringRes description: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(description),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ExerciseItemPreview() {
    val exercise = ExercisesRepository.exercises[0]
    FitnessDaysTheme {
        ExerciseItem(
            item = exercise
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseItemDarkThemePreview() {
    val exercise = ExercisesRepository.exercises[0]
    FitnessDaysTheme(darkTheme = true) {
        ExerciseItem(
            item = exercise
        )
    }
}

@Composable
fun ExercisesList(
    list: List<Exercise>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(list) { exercise ->
            ExerciseItem(
                item = exercise,
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(R.dimen.padding_medium),
                        vertical = dimensionResource(R.dimen.padding_small)
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExercisesListPreview() {
    FitnessDaysTheme {
        ExercisesList(
            list = ExercisesRepository.exercises
        )
    }
}