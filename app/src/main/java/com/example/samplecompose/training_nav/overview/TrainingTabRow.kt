package com.example.samplecompose.training_nav

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.*

/**
 * Training tab row
 * トップバーに表示されているコンポーズ。
 * トップバーの高さや表示されているアイコンなどの指定
 * @param allScreen ナビゲーションできる画面の数分の列挙型オブジェクト
 * @param onTabSelected タブをセレクトした時に、実行する処理。画面遷移
 * @param screen  現在表示されている画面
 * @receiver
 */
@Composable
fun TrainingTabRow(
    allScreen: List<TrainingScreen>,
    onTabSelected: (TrainingScreen) -> Unit,
    screen: TrainingScreen,
    openDrawer:()->Unit,
) {
    Surface(
        Modifier
            .height(TabHeight)
            .fillMaxWidth(),
        color = MaterialTheme.colors.background,
        elevation = 8.dp //影をつける設定
    ) {
        Row(
            Modifier.selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(TabHeight)
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu ,
                    contentDescription = "list",
                    modifier = Modifier
                        .size(36.dp)
                        .clickable { openDrawer() }
                )
            }
            Row {
                allScreen.forEach { s ->
                    com.example.samplecompose.training_nav2.overview.TrainingTab(
                        text = s.name,
                        icon = s.icon,
                        onTabSelected = { onTabSelected(s) },
                        selected = s == screen
                    )
                }
            }
        }
    }
}

/**
 * Training tab
 *
 * @param text
 * @param icon
 * @param onTabSelected
 * @param selected
 */
@Composable
fun TrainingTab(
    text: String,
    icon: ImageVector,
    onTabSelected: () -> Unit,
    selected: Boolean
) {
    val color = MaterialTheme.colors.onSurface
    val durationMillis = if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration

    /**
     * Anim spec
     * アニメーションを行う際に、アニメーションされる時間などの設定
     * タブバーに表示されているアイコンをタップすると、色の変更のアニメーション
     * が行われる設定として。なので、テキストが変更した時のアニメーションではないです。
     */
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val tabTintColor by animateColorAsState(
        targetValue = if (selected) color else color.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec
    )
    Row(
        modifier = Modifier
            .padding(16.dp)
            .animateContentSize()
            .height(TabHeight)
            .selectable(
                selected = selected,
                onClick = onTabSelected,
                role = Role.Tab,
                /**
                 * アイコンを覆っているRowの箇所をタップした時に、
                 * タップした時の背景色でのグレーの広がりのアニメーションが
                 * リップルと呼ばれるものタップした時のタップしましたよの見え方
                 */
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            )
            .clearAndSetSemantics { contentDescription = text }

    ) {
        Icon(imageVector = icon, contentDescription = text, tint = tabTintColor)
        if (selected) {
            Spacer(Modifier.width(12.dp))
            Text(text.uppercase(Locale.getDefault()), color = tabTintColor)
        }
    }
}

private val TabHeight = 56.dp
private const val InactiveTabOpacity = 0.60f
private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100