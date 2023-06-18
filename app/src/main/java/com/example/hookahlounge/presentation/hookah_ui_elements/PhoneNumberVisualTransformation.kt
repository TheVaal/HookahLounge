package com.example.hookahlounge.presentation.hookah_ui_elements

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneNumberVisualTransformation: VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {


            // change the length
            val trimmed =
                if (text.text.length >= 9) text.text.substring(0..8) else text.text

            val annotatedString = AnnotatedString.Builder().run {
                for (i in trimmed.indices) {
                    append(trimmed[i])
                    if (i == 1 || i == 4 || i == 6) {
                        append(" ")
                    }
                }
                toAnnotatedString()
            }

            val phoneNumberOffsetTranslator = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 1) return offset
                    if (offset <= 4) return offset + 1
                    if (offset <= 6) return offset + 2
                    if (offset <= 9) return offset + 3
                    return 12
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 1) return offset
                    if (offset <= 4) return offset - 1
                    if (offset <= 6) return offset - 2
                    if (offset <= 9) return offset - 3
                    return 9
                }
            }
        return TransformedText(annotatedString, phoneNumberOffsetTranslator)
    }

}