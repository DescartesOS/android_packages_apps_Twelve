/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.twelve.ui.preferences

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import androidx.preference.PreferenceGroup
import com.google.android.material.color.MaterialColors

enum class CardPosition {
    TOP,
    MIDDLE,
    BOTTOM,
    SINGLE
}

object SegmentedCardHelper {
    fun getSegmentedCardBackground(context: Context, position: CardPosition): Drawable {
        val density = context.resources.displayMetrics.density
        val rLarge = 24f * density
        val rSmall = 4f * density

        val radii = when (position) {
            CardPosition.TOP -> floatArrayOf(
                rLarge, rLarge, // Top Left
                rLarge, rLarge, // Top Right
                rSmall, rSmall, // Bottom Right
                rSmall, rSmall  // Bottom Left
            )
            CardPosition.MIDDLE -> floatArrayOf(
                rSmall, rSmall,
                rSmall, rSmall,
                rSmall, rSmall,
                rSmall, rSmall
            )
            CardPosition.BOTTOM -> floatArrayOf(
                rSmall, rSmall,
                rSmall, rSmall,
                rLarge, rLarge,
                rLarge, rLarge
            )
            CardPosition.SINGLE -> floatArrayOf(
                rLarge, rLarge,
                rLarge, rLarge,
                rLarge, rLarge,
                rLarge, rLarge
            )
        }

        val surfaceColor = MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorSurfaceContainer,
            Color.parseColor("#1C1E26")
        )

        val strokeColor = MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorOutlineVariant,
            Color.parseColor("#18FFFFFF")
        )

        val shapeDrawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadii = radii
            setColor(surfaceColor)
            setStroke((1f * density).toInt(), strokeColor)
        }

        val rippleColor = MaterialColors.getColor(
            context,
            android.R.attr.colorControlHighlight,
            Color.parseColor("#26FFFFFF")
        )

        val maskDrawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadii = radii
            setColor(Color.WHITE)
        }

        return RippleDrawable(
            ColorStateList.valueOf(rippleColor),
            shapeDrawable,
            maskDrawable
        )
    }

    fun updateSegmentedPreferences(root: PreferenceGroup) {
        for (i in 0 until root.preferenceCount) {
            val pref = root.getPreference(i)
            if (pref is PreferenceGroup) {
                val count = pref.preferenceCount
                for (j in 0 until count) {
                    val child = pref.getPreference(j)
                    val pos = when {
                        count == 1 -> CardPosition.SINGLE
                        j == 0 -> CardPosition.TOP
                        j == count - 1 -> CardPosition.BOTTOM
                        else -> CardPosition.MIDDLE
                    }
                    when (child) {
                        is ExpressivePreference -> child.cardPosition = pos
                        is ExpressiveSwitchPreference -> child.cardPosition = pos
                    }
                }
            }
        }
    }
}
