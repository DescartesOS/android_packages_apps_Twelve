/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.twelve.ui.preferences

import android.content.Context
import android.util.AttributeSet
import androidx.preference.PreferenceCategory
import org.lineageos.twelve.R

class ExpressivePreferenceCategory @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.preference.R.attr.preferenceCategoryStyle,
    defStyleRes: Int = 0,
) : PreferenceCategory(context, attrs, defStyleAttr, defStyleRes) {

    init {
        layoutResource = R.layout.preference_category_expressive
    }
}
