package com.example.edittextbugmini

import android.text.InputType
import android.widget.EditText
import android.widget.LinearLayout
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.semantics.insertTextAtCursor
import androidx.compose.ui.semantics.requestFocus
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.setText
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
public fun FormTextFieldComponent() {
    var editText by remember { mutableStateOf<EditText?>(null) }

    Box(
        modifier = Modifier
            .padding(16.dp)
            .border(1.dp, Color.Black)
            .padding(8.dp)
    ) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    setText {
                        editText?.text?.insert(0, it)
                        true
                    }
                    requestFocus {
                        editText?.requestFocus()
                        true
                    }
                    insertTextAtCursor {
                        editText?.text?.insert(editText?.selectionStart ?: 0, it)
                        true
                    }
                },
            factory = { context ->
                val editText = EditText(context).apply {
                    editText = this

                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

                    setPadding(0, 0, 0, 0)

                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    setBackgroundColor(Color.Transparent.toArgb())

//                setOnFocusChangeListener { v, hasFocus ->
//                    onFocusChangeListener.onFocusChange(v, hasFocus)
////                    if (!hasFocus) {
////                        updatedOnUnfocus()
////                        setSelection(0)
////                    }
////                    fieldHasFocus = hasFocus
//                }
                }

                LinearLayout(context).apply {
                    orientation = LinearLayout.VERTICAL
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    addView(editText)
                }
            },
            update = {
                editText?.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
            }
        )
    }
}
