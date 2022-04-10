package com.example.samplecompose.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.samplecompose.R
import com.example.samplecompose.ui.componets.NavigationIcon
import com.example.samplecompose.ui.theme.SampleComposeTheme

/**
 * App drawer
 * ドロワービューを表すコンポネートされた関数。
 * @param currentRoute
 * @param navigateToSample1
 * @param navigateToSample2
 * @param closeDrawer
 * @param modifier
 * @receiver
 * @receiver
 * @receiver
 */
@Composable
fun AppDrawer(
    currentRoute: String,
    navigateToSample1: () -> Unit,
    navigateToSample2: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        SampleLogo()
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        DrawerButton(
            icon = Icons.Filled.Home,
            label = stringResource(id = R.string.sample1),
            isSelected = currentRoute == SampleDestinations.SAMPLE_1_ROUTE,
            action = {
                navigateToSample1()
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Icons.Filled.Done,
            label = stringResource(id = R.string.sample2),
            isSelected = currentRoute == SampleDestinations.SAMPLE_2_ROUTE,
            action = {
                navigateToSample2()
                closeDrawer()
            }
        )
    }
}

@Composable
private fun SampleLogo(modifier: Modifier=Modifier){
    Row(modifier = modifier) {
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            imageVector = Icons.Filled.List ,//painterResource(id = R.drawable.ic_android_black_24dp),
            contentDescription = stringResource(id = R.string.app_name),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
        )
    }
}

@Composable
private fun DrawerButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colors

    /**
     * Text icon color
     * ・アイコンが選択されている時は 紫に近い青
     * ・アイコンが選択されていない時は 透明にして
     */
    val textIconColor =
        if (isSelected) colors.primary
        else colors.primary.copy(alpha = 0.62f)

    val surfaceModifier = modifier
        .padding(start = 8.dp, top = 8.dp, end = 8.dp)
        .fillMaxWidth()

    Surface(
        modifier = surfaceModifier,
        color = Color.Cyan,
        shape = MaterialTheme.shapes.small
    ) {
        TextButton(
            onClick = action,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                NavigationIcon(
                    icon = icon,
                    isSelected = isSelected,
                    contentDescription = null, // decorative
                    tintColor = textIconColor
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2,
                    color = textIconColor
                )

            }
        }
    }


}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
    SampleComposeTheme {
        Surface {
            AppDrawer(
                currentRoute = SampleDestinations.SAMPLE_1_ROUTE,
                navigateToSample1 = { },
                navigateToSample2 = { },
                closeDrawer = { }
            )
        }
    }
}