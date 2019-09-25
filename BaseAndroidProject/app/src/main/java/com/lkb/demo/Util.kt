package com.lkb.demo

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannedString
import android.text.style.ForegroundColorSpan
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.italic
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import java.util.regex.Pattern


fun makeBold(fullString: String, boldPart: String): SpannableStringBuilder {
//    var spannableString = buildSpannedString {
//        bold {
//            append("Hello")
//            italic {
//                append(" World")
//            }
//        }
//        append(" Hi")
//    }

    //}
//    spannableString.setSpan(
//        ForegroundColorSpan(Color.RED),
//        0, 1,
//        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//    )
    val text = "I love you so much"
    val wordToFind = "love"
    val word = Pattern.compile(wordToFind)
    val match = word.matcher(text)

//    while (match.find()) {
//        System.out.println("Found love at index " + match.start() + " - " + (match.end() - 1))
//    }
    val fancySentence = SpannableStringBuilder(text)
    fancySentence.setSpan(
        android.text.style.StyleSpan(Typeface.BOLD),
        match.start(),
        3,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return fancySentence
}