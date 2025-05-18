package com.michee.codelabs.basiclayoutincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.michee.codelabs.basiclayoutincompose.ui.theme.BasicLayoutInComposeTheme

val alignYourBodyData = listOf(
    AlignYourBodyData(
        drawable = R.drawable.ab1_inversions,
        drawableDescription = R.string.description_ab1_inversions,
        text = R.string.text_ab1_inversions
    ),
    AlignYourBodyData(
        drawable = R.drawable.ab2_quick_yoga,
        drawableDescription = R.string.description_ab2_quick_yoga,
        text = R.string.text_ab2_quick_yoga
    ),
    AlignYourBodyData(
        drawable = R.drawable.ab3_stretching,
        drawableDescription = R.string.description_ab3_stretching,
        text = R.string.text_ab3_stretching
    ),
    AlignYourBodyData(
        drawable = R.drawable.ab4_tabata,
        drawableDescription = R.string.description_ab4_tabata,
        text = R.string.text_ab4_tabata
    ),
    AlignYourBodyData(
        drawable = R.drawable.ab5_hiit,
        drawableDescription = R.string.description_ab5_hiit,
        text = R.string.text_ab5_hiit
    ),
    AlignYourBodyData(
        drawable = R.drawable.ab6_pre_natal_yoga,
        drawableDescription = R.string.description_ab6_pre_natal_yoga,
        text = R.string.text_ab6_pre_natal_yoga
    ),
)

val favoriteCollectionData = listOf(
    FavoriteCollectionData(
        drawable = R.drawable.fc1_short_mantras,
        drawableDescription = R.string.description_fc1_short_mantras,
        text = R.string.text_fc1_short_mantras
    ),
    FavoriteCollectionData(
        drawable = R.drawable.fc2_nature_meditations,
        drawableDescription = R.string.description_fc2_nature_meditations,
        text = R.string.text_fc2_nature_meditations
    ),
    FavoriteCollectionData(
        drawable = R.drawable.fc3_stress_and_anxiety,
        drawableDescription = R.string.description_fc3_stress_and_anxiety,
        text = R.string.text_fc3_stress_and_anxiety
    ),
    FavoriteCollectionData(
        drawable = R.drawable.fc4_self_massage,
        drawableDescription = R.string.description_fc4_self_massage,
        text = R.string.text_fc4_self_massage
    ),
    FavoriteCollectionData(
        drawable = R.drawable.fc5_overwhelmed,
        drawableDescription = R.string.description_fc5_overwhelmed,
        text = R.string.text_fc5_overwhelmed
    ),
    FavoriteCollectionData(
        drawable = R.drawable.fc6_nightly_wind_down,
        drawableDescription = R.string.description_fc6_nightly_wind_down,
        text = R.string.text_fc6_nightly_wind_down
    ),
)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSize = calculateWindowSizeClass(this)
            MySootheApp(windowSize)
        }
    }
}

@Composable
fun MySootheApp(windowSize: WindowSizeClass) {
    when(windowSize.widthSizeClass) {
        WindowWidthSizeClass.Expanded -> {
            MySootheAppLandscape()
        }
        WindowWidthSizeClass.Compact -> {
            MySootheAppPortrait()
        }
    }
}

@Composable
fun MySootheAppPortrait() {
    BasicLayoutInComposeTheme {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { _ ->
            HomeScreen(Modifier.padding())
        }
    }
}

@Composable
fun MySootheAppLandscape() {
    Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        BasicLayoutInComposeTheme {
            Row {
                SootheNavigationRail()
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = modifier.height(16.dp))
        SearchBar(Modifier)
        Spacer(modifier = modifier.height(16.dp))
        HomeSection(R.string.title_align_your_body) {
            AlignYourBodyRow(alignYourBodyData = alignYourBodyData)
        }
        HomeSection(R.string.title_favorite_collection) {
            FavoriteCollectionGrid(favoriteCollection = favoriteCollectionData)
        }
        Spacer(modifier = modifier.height(16.dp))
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_home)
                )
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_account)
                )
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Spa,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.bottom_navigation_home)
                    )
                },
                selected = true,
                onClick = {}
            )
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.bottom_navigation_account)
                    )
                },
                selected = false,
                onClick = {}
            )
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.description_search)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .heightIn(min = 56.dp),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun AlignYourBodyElement(
    modifier: Modifier = Modifier,
    @DrawableRes drawable: Int,
    @StringRes drawableDescription: Int,
    @StringRes text: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = stringResource(drawableDescription),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            modifier = modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

data class AlignYourBodyData(val drawable: Int, val drawableDescription: Int, val text: Int)

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier,
    alignYourBodyData: List<AlignYourBodyData>
) {

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(
                drawable = item.drawable,
                drawableDescription = item.drawableDescription,
                text = item.text
            )
        }
    }
}

@Composable
fun FavoriteCollectionCard(
    modifier: Modifier = Modifier,
    @DrawableRes drawable: Int,
    @StringRes drawableDescription: Int,
    @StringRes text: Int
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(drawable),
                contentDescription = stringResource(drawableDescription),
                modifier = modifier.size(80.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

data class FavoriteCollectionData(val drawable: Int, val drawableDescription: Int, val text: Int)

@Composable
fun FavoriteCollectionGrid(
    modifier: Modifier = Modifier,
    favoriteCollection: List<FavoriteCollectionData>
) {

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier.height(168.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(favoriteCollection) { item ->
            FavoriteCollectionCard(
                drawable = item.drawable,
                drawableDescription = item.drawableDescription,
                text = item.text
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MySootheAppPortraitPreview() {
    MySootheAppPortrait()
}

@Preview(showBackground = true, heightDp = 320, widthDp = 720)
@Composable
fun MySootheAppLandscapePreview() {
    MySootheAppLandscape()
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BasicLayoutInComposeTheme {
        HomeScreen(modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun SootheBottomNavigationPreview() {
    BasicLayoutInComposeTheme {
        SootheBottomNavigation(modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun SootheNavigationRailPreview() {
    BasicLayoutInComposeTheme {
        SootheNavigationRail(modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeSectionPreview() {
    BasicLayoutInComposeTheme {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            HomeSection(R.string.title_align_your_body) {
                AlignYourBodyRow(alignYourBodyData = alignYourBodyData)
            }
            HomeSection(R.string.title_favorite_collection) {
                FavoriteCollectionGrid(favoriteCollection = favoriteCollectionData)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xcaf0f8)
@Composable
fun SearchBarPreview() {
    BasicLayoutInComposeTheme {
        Box(
            modifier = Modifier.padding(8.dp)
        ) {
            SearchBar()
        }
    }
}

@Preview(showBackground = true, widthDp = 120, backgroundColor = 0xcaf0f8)
@Composable
fun AlignYourBodyElementPreview()
{
    BasicLayoutInComposeTheme {
        Box(
            modifier = Modifier.padding(8.dp)
        ) {
            AlignYourBodyElement(
                drawable = R.drawable.ab1_inversions,
                drawableDescription = R.string.description_ab1_inversions,
                text = R.string.text_ab1_inversions
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xcaf0f8)
@Composable
fun AlignYourBodyRowPreview()   {
    BasicLayoutInComposeTheme {
        Box(
            modifier = Modifier.padding(8.dp)
        ) {
            AlignYourBodyRow(
                alignYourBodyData = alignYourBodyData
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xcaf0f8)
@Composable
fun FavoriteCollectionCardPreview() {
    BasicLayoutInComposeTheme {
        Box(
            modifier = Modifier.padding(8.dp)
        ) {
            FavoriteCollectionCard(
                drawable = R.drawable.fc2_nature_meditations,
                drawableDescription = R.string.description_fc2_nature_meditations,
                text = R.string.text_fc2_nature_meditations
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xcaf0f8)
@Composable
fun FavoriteCollectionGridPreview() {
    BasicLayoutInComposeTheme {
        Box(
            modifier = Modifier.padding(8.dp)
        ) {
            FavoriteCollectionGrid(favoriteCollection = favoriteCollectionData)
        }
    }
}