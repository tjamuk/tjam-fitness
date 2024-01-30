package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

/**
 * Used in a create/edit exercise form.
 *
 * There are sections in it.
 *
 * This is basically a subtitle like thing that visually separates the sections.
 */
@Composable
fun SectionDivider(
    imageVector: ImageVector,
    title: String,
)
{
    ConstraintLayout {
        val (divider, icon, text) = createRefs()

        Divider(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .constrainAs(divider) {
                    centerHorizontallyTo(parent)
                }
        )
        Icon(
            imageVector,
            title,
            Modifier
                .padding(start = 10.dp)
                .constrainAs(icon) {
                    top.linkTo(divider.bottom)
                    start.linkTo(divider.start)
                },
            tint = DividerDefaults.color

        )
        Text(
            title,
            Modifier
                .constrainAs(text) {
//                    top.linkTo(divider.bottom)
                    start.linkTo(icon.end)
                    centerVerticallyTo(icon)
                }
                .padding(start = 3.dp),
            color = DividerDefaults.color
        )
    }
}