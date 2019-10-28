package com.lkb.demo

import android.graphics.Typeface
import android.os.Build
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import java.util.regex.Pattern
import android.annotation.TargetApi
import androidx.core.text.HtmlCompat


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

@TargetApi(Build.VERSION_CODES.N)
fun fromHtml(text: String): Spanned {
    val newApiLevel = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    return if (newApiLevel) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(text)
    }
}